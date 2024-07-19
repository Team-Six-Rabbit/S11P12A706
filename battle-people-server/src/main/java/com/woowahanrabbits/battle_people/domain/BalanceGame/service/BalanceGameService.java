package com.woowahanrabbits.battle_people.domain.BalanceGame.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.woowahanrabbits.battle_people.domain.BalanceGame.dto.BalanceGameCommentDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleReturnDto;

public interface BalanceGameService {
	void addBalanceGame(BattleReturnDto battleReturnDto);

	Page<?> getBalanceGameByConditions(int category, int status, Pageable pageable);

	void deleteBalanceGame(Long id);

	Page<?> getCommentsByBattleId(Long id, Pageable pageable);

	void addComment(BalanceGameCommentDto balanceGameCommentDto);
}
