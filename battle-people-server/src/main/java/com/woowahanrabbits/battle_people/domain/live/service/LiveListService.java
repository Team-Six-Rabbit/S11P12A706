package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LiveListService {
    Page<BattleBoard> getActiveBattleBoards(String keyword, Pageable pageable);
    Page<BattleBoard> getWaitBattleBoards(String keyword, Pageable pageable);
}
