package com.woowahanrabbits.battle_people.domain.BalanceGame.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.woowahanrabbits.battle_people.domain.BalanceGame.dto.BalanceGameCommentDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BalanceGameReturnDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleReturnDto;
import com.woowahanrabbits.battle_people.domain.user.domain.User;

public interface BalanceGameService {
	void addBalanceGame(BattleReturnDto battleReturnDto);

	List<BalanceGameReturnDto> getBalanceGameByConditions(int category, int status, int page, User user);

	void deleteBalanceGame(Long id);

	Page<?> getCommentsByBattleId(Long id, Pageable pageable);

	void addComment(BalanceGameCommentDto balanceGameCommentDto);
}
