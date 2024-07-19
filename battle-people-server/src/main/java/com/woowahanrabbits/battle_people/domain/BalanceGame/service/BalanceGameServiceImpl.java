package com.woowahanrabbits.battle_people.domain.BalanceGame.service;

import org.springframework.data.domain.Page;
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
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.VoteInfoRepository;
import com.woowahanrabbits.battle_people.domain.vote.infrastructure.VoteOpinionRepository;

@Service
public class BalanceGameServiceImpl implements BalanceGameService {

	private final VoteInfoRepository voteInfoRepository;
	private final VoteOpinionRepository voteOpinionRepository;
	private final BattleRepository battleRepository;
	private final BalanceGameRepository balanceGameRepository;

	public BalanceGameServiceImpl(VoteInfoRepository voteInfoRepository, VoteOpinionRepository voteOpinionRepository,
		BattleRepository battleRepository, BalanceGameRepository balanceGameRepository) {
		this.voteInfoRepository = voteInfoRepository;
		this.voteOpinionRepository = voteOpinionRepository;
		this.battleRepository = battleRepository;
		this.balanceGameRepository = balanceGameRepository;
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
	public Page<?> getBalanceGameByConditions(int category, int status, Pageable pageable) {
		return null;
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
