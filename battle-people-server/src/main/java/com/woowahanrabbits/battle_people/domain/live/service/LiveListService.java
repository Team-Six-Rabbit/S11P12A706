package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LiveListService {
    List<BattleBoard> getActiveBattleBoards(String keyword);
    List<BattleBoard> getWaitBattleBoards(String keyword);
}
