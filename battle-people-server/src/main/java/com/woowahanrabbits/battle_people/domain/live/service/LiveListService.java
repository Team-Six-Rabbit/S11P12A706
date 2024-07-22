package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleBoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LiveListService {
    Page<BattleBoardResponseDto> getActiveBattleBoards(String keyword, Pageable pageable);
    Page<BattleBoardResponseDto> getWaitBattleBoards(String keyword, Pageable pageable);
}
