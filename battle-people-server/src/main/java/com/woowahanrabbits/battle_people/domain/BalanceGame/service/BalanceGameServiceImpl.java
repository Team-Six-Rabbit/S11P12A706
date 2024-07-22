package com.woowahanrabbits.battle_people.domain.BalanceGame.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.woowahanrabbits.battle_people.domain.BalanceGame.domain.BalanceGameBoardComment;
import com.woowahanrabbits.battle_people.domain.BalanceGame.dto.BalanceGameCommentDto;
import com.woowahanrabbits.battle_people.domain.BalanceGame.infrastructure.BalanceGameRepository;
import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleReturnDto;
import com.woowahanrabbits.battle_people.domain.battle.infrastructure.BattleRepository;
import com.woowahanrabbits.battle_people.domain.user.domain.User;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinion;
import com.woowahanrabbits.battle_people.domain.vote.dto.BalanceGameVoteReturnDto;
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.UserVoteOpinionRepository;
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.VoteInfoRepository;
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.VoteOpinionRepository;

@Service
public class BalanceGameServiceImpl implements BalanceGameService {

	private final VoteInfoRepository voteInfoRepository;
	private final VoteOpinionRepository voteOpinionRepository;
	private final BattleRepository battleRepository;
	private final BalanceGameRepository balanceGameRepository;
	private final UserVoteOpinionRepository userVoteOpinionRepository;

	public BalanceGameServiceImpl(VoteInfoRepository voteInfoRepository, VoteOpinionRepository voteOpinionRepository,
		BattleRepository battleRepository, BalanceGameRepository balanceGameRepository,
		UserVoteOpinionRepository userVoteOpinionRepository) {
		this.voteInfoRepository = voteInfoRepository;
		this.voteOpinionRepository = voteOpinionRepository;
		this.battleRepository = battleRepository;
		this.balanceGameRepository = balanceGameRepository;
		this.userVoteOpinionRepository = userVoteOpinionRepository;
	}

	@Override
	public void addBalanceGame(BattleReturnDto battleReturnDto) {
		voteInfoRepository.save(battleReturnDto.getBattleBoard().getVoteInfo());
		for(int i=0;i < battleReturnDto.getOpinionList().size();i++) {
			VoteOpinion voteOpinion = battleReturnDto.getOpinionList().get(i);
			voteOpinion.setVoteInfoId(battleReturnDto.getBattleBoard().getVoteInfo().getId());
			voteOpinion.setVoteOpinionIndex(i);
			if(i==0) voteOpinion.setUser(battleReturnDto.getBattleBoard().getRegistUser());
			voteOpinionRepository.save(voteOpinion);
		}
		battleReturnDto.getBattleBoard().setCurrentState(4);
		battleRepository.save(battleReturnDto.getBattleBoard());
	}

	@Override
	public Object getBalanceGameByConditions(int category, int status, int page, User user) {
		Pageable pageable = PageRequest.of(page, 12);
		List<Object[]> list = userVoteOpinionRepository.findAllVotesWithCountsAndUserVoteStatus(user.getId());
		List<BalanceGameVoteReturnDto> returnList = list.stream()
			.map(result -> new BalanceGameVoteReturnDto(
				((Number) result[0]).longValue(),  // battleId
				(String) result[1],                // title
				(String) result[2],                // opinion1
				(String) result[3],                // opinion2
				((Number) result[4]).intValue(),   // opinion1Count
				((Number) result[5]).intValue(),   // opinion2Count
				result[6] != null ? ((Number) result[6]).intValue() : null    // userVote
			))
			.collect(Collectors.toList());
		System.out.println(returnList.toString());
		return list;
	}

	@Override
	public void deleteBalanceGame(Long id) {
		battleRepository.deleteById(id);
	}

	@Override
	public Page<?> getCommentsByBattleId(Long id, Pageable pageable) {
		Page<BalanceGameCommentDto> pages = balanceGameRepository.findCommentsByBattleBoardId(id, pageable);
		System.out.println(pages.toList().toString());
		return pages;
	}

	@Override
	public void addComment(BalanceGameCommentDto balanceGameCommentDto) {

		BalanceGameBoardComment bgbcomment = BalanceGameBoardComment.builder()
			.user(balanceGameCommentDto.getUser())
			.content(balanceGameCommentDto.getContent())
			.battleBoard(BattleBoard.builder().id(balanceGameCommentDto.getBattleBoardId()).build())
			.build();

		balanceGameRepository.save(bgbcomment);
	}
}
