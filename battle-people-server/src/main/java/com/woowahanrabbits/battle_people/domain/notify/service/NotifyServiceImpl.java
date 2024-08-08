package com.woowahanrabbits.battle_people.domain.notify.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleResponse;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleVoteDto;
import com.woowahanrabbits.battle_people.domain.notify.dto.NotifyResponseDto;
import com.woowahanrabbits.battle_people.domain.user.dto.BasicUserDto;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteInfo;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinion;
import com.woowahanrabbits.battle_people.domain.vote.dto.BattleOpinionDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotifyServiceImpl implements NotifyService {

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public NotifyResponseDto sendNotify(BattleVoteDto battleVoteDto) {
		BasicUserDto targetUser = new BasicUserDto(battleVoteDto.getBattleBoard().getOppositeUser());
		BattleResponse notifyDto = mappingDtos(battleVoteDto);

		String title = targetUser.getNickname()
			+ "님이 배틀 요청을 보냈어요! 지금 바로 확인해보세요.";

		NotifyResponseDto notifyResponseDto = NotifyResponseDto.builder()
			.title(title)
			.battleResponse(notifyDto)
			.targetUser(targetUser)
			.build();

		//BattleResponse 타입으로 알림보내기
		redisTemplate.convertAndSend("notify", notifyResponseDto);
		redisTemplate.opsForList().leftPush("notify:" + targetUser.getId(), notifyResponseDto);
		return notifyResponseDto;
	}

	@Override
	public BattleResponse mappingDtos(BattleVoteDto battleVoteDto) {
		BattleBoard battleBoard = battleVoteDto.getBattleBoard();
		VoteInfo voteInfo = battleVoteDto.getVoteInfo();
		List<VoteOpinion> opinions = battleVoteDto.getVoteOpinions();

		BattleDto battleDto = BattleDto.builder()
			.id(battleBoard.getId())
			.voteInfo(voteInfo)
			.maxPeopleCount(battleBoard.getMaxPeopleCount())
			.battleRule(battleBoard.getBattleRule())
			.build();

		List<BattleOpinionDto> battleOpinionDtos = new ArrayList<>();
		for (VoteOpinion voteOpinion : opinions) {
			battleOpinionDtos.add(new BattleOpinionDto(voteOpinion));
		}

		BattleResponse notifyDto = BattleResponse.builder()
			.battle(battleDto)
			.opinions(battleOpinionDtos)
			.build();
		return notifyDto;
	}

	@Override
	public List<NotifyResponseDto> getNotificationList(Long userId, int page, int size) {
		int start = page * size;
		int end = (page + 1) * size - 1;
		List<Object> list = redisTemplate.opsForList().range("notify:" + userId, start, end);
		List<NotifyResponseDto> returnList = new ArrayList<>();
		for (Object object : list) {
			NotifyResponseDto notifyDto = (NotifyResponseDto)object;
			returnList.add(notifyDto);
		}
		return returnList;
	}

}
