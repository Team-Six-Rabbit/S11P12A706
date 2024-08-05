package com.woowahanrabbits.battle_people.domain.battle.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleApplyUser;
import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.battle.dto.AwaitingBattleResponseDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleApplyDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleInviteRequest;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleRespondRequest;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleResponse;
import com.woowahanrabbits.battle_people.domain.battle.infrastructure.BattleApplyUserRepository;
import com.woowahanrabbits.battle_people.domain.battle.infrastructure.BattleRepository;
import com.woowahanrabbits.battle_people.domain.user.domain.User;
import com.woowahanrabbits.battle_people.domain.user.infrastructure.UserRepository;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteInfo;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinion;
import com.woowahanrabbits.battle_people.domain.vote.dto.BattleOpinionDto;
import com.woowahanrabbits.battle_people.domain.vote.dto.GetVoteInfoWithUserCountDto;
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.VoteInfoRepository;
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.VoteOpinionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService {

	private final BattleRepository battleRepository;
	private final VoteOpinionRepository voteOpinionRepository;
	private final BattleApplyUserRepository battleApplyUserRepository;
	private final VoteInfoRepository voteInfoRepository;
	private final UserRepository userRepository;

	@Value("${min.people.count.value}")
	private Integer minPeopleCount;

	@Override
	public void registBattle(BattleInviteRequest battleInviteRequest, User user) {

		Date startDate = battleInviteRequest.getStartDate();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MINUTE, battleInviteRequest.getTime());
		Date endDate = calendar.getTime();

		if (battleInviteRequest.getOppositeUserId() == user.getId()) {
			throw new IllegalArgumentException("Not valid user");
		}
		if (battleInviteRequest.getTime() <= 0) {
			throw new RuntimeException("Wrong time setting");
		}

		// 현재 시간
		Date now = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(now);

		calendar.add(Calendar.MINUTE, 5);
		Date fiveMinutesLater = calendar.getTime();

		if (startDate.before(fiveMinutesLater)) {
			throw new IllegalArgumentException("Invalid start time: must be after at least 5 minutes");
		}

		//VoteInfo 만들기
		VoteInfo voteInfo = VoteInfo.builder()
			.title(battleInviteRequest.getTitle())
			.startDate(battleInviteRequest.getStartDate())
			.endDate(endDate)
			.category(battleInviteRequest.getCategory())
			.currentState(0)
			.detail(battleInviteRequest.getDetail())
			.build();

		//투표 정보 저장
		voteInfoRepository.save(voteInfo);

		//VoteOpinion 만들기
		VoteOpinion voteOpinion = VoteOpinion.builder()
			.voteOpinionIndex(0)
			.voteInfoId(voteInfo.getId())
			.user(user)
			.opinion(battleInviteRequest.getOpinion())
			.build();
		voteOpinionRepository.save(voteOpinion);

		//battle board
		BattleBoard battleBoard = BattleBoard.builder()
			.registUser(user)
			.oppositeUser(userRepository.findById(battleInviteRequest.getOppositeUserId()).orElseThrow())
			.voteInfo(voteInfo)
			.maxPeopleCount(battleInviteRequest.getMaxPeopleCount())
			.build();
		battleRepository.save(battleBoard);
	}

	@Override
	public List<BattleResponse> getReceivedBattleList(User user, int page) {
		Pageable pageable = PageRequest.of(page, 12);
		List<BattleBoard> list = battleRepository.findByOppositeUserIdAndVoteInfoCurrentState(user.getId(), 0, pageable)
			.getContent();

		List<BattleResponse> returnList = new ArrayList<>();

		for (BattleBoard battleBoard : list) {
			List<VoteOpinion> voteOpinions = voteOpinionRepository.findByVoteInfoId(
				battleBoard.getVoteInfo().getId());

			returnList.add(new BattleResponse(battleBoard, voteOpinions));
		}

		return returnList;
	}

	@Override
	public void acceptOrDeclineBattle(BattleRespondRequest battleRespondRequest, User user) {
		BattleBoard battleBoard = battleRepository.findById(battleRespondRequest.getBattleId()).orElseThrow();
		VoteInfo requestVote = battleBoard.getVoteInfo();

		if (battleBoard.getOppositeUser().getId() != user.getId() || battleBoard.getVoteInfo().getCurrentState() != 0) {
			throw new RuntimeException("No such elements");
		}

		if (battleRespondRequest.getRespond().equals("decline")) {
			requestVote.setCurrentState(1);
			voteInfoRepository.save(requestVote);
			battleBoard.setRejectionReason(battleRespondRequest.getContent());
			battleRepository.save(battleBoard);
		} else if (battleRespondRequest.getRespond().equals("accept")) {

			//일정이 겹치는 라이브가 있는지 확인
			List<VoteInfo> list = battleRepository.findVoteInfosByUserIds(user.getId(), user.getId());
			for (VoteInfo voteInfo : list) {
				if (!isTimeValid(voteInfo, requestVote)) {
					throw new RuntimeException("Scheduled battle exists");
				}
			}

			requestVote.setCurrentState(2);
			voteInfoRepository.save(requestVote);
			VoteOpinion voteOpinion = VoteOpinion.builder()
				.voteOpinionIndex(1)
				.voteInfoId(battleBoard.getVoteInfo().getId())
				.user(user)
				.opinion(battleRespondRequest.getContent())
				.build();
			voteOpinionRepository.save(voteOpinion);
		}
	}

	boolean isTimeValid(VoteInfo voteInfo, VoteInfo requestVote) {
		return voteInfo.getEndDate().after(requestVote.getStartDate()) && voteInfo.getStartDate()
			.before(requestVote.getEndDate());
	}

	@Override
	public List<AwaitingBattleResponseDto> getAwaitingBattleList(Integer category, int page, User user) {
		Pageable pageable = PageRequest.of(page, 12);
		List<GetVoteInfoWithUserCountDto> list = voteInfoRepository.getVoteInfoWithUserCount(user.getId(), pageable)
			.getContent();

		List<AwaitingBattleResponseDto> returnList = new ArrayList<>();

		for (GetVoteInfoWithUserCountDto getVoteInfoWithUserCountDto : list) {
			List<VoteOpinion> votes = voteOpinionRepository.findByVoteInfoId(
				getVoteInfoWithUserCountDto.getId());

			List<BattleOpinionDto> battleOpinionDtos = new ArrayList<>();
			for (VoteOpinion voteOpinion : votes) {
				battleOpinionDtos.add(new BattleOpinionDto(voteOpinion));
			}

			returnList.add(new AwaitingBattleResponseDto(getVoteInfoWithUserCountDto, battleOpinionDtos));
		}
		return returnList;
	}

	@Override
	public void applyBattle(BattleApplyDto battleApplyDto, User user) {
		BattleBoard battleBoard = battleRepository.findById(battleApplyDto.getBattleId()).orElseThrow();
		if (battleBoard.getOppositeUser().getId() == user.getId()
			|| battleBoard.getRegistUser().getId() == user.getId()) {
			//주최자는 참여 신청 X
			throw new RuntimeException("Can't apply my battle");
		}

		int currentPeopleCount = battleApplyUserRepository.countByBattleBoardId(battleBoard.getId());

		//최대 인원
		if (currentPeopleCount >= battleBoard.getMaxPeopleCount()) {
			throw new RuntimeException("Apply fully charged");
		}

		BattleApplyUser battleApplyUser = BattleApplyUser.builder()
			.battleBoard(battleBoard)
			.user(user)
			.selectedOpinion(battleApplyDto.getSelectedOpinion())
			.build();
		battleApplyUserRepository.save(battleApplyUser);

		//최소 인원 충족 체크
		if (currentPeopleCount > minPeopleCount) {
			VoteInfo voteInfo = battleBoard.getVoteInfo();
			voteInfo.setCurrentState(3);
			voteInfoRepository.save(voteInfo);
		}
	}
}
