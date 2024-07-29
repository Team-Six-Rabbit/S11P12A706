package com.woowahanrabbits.battle_people.domain.battle.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleApplyUser;
import com.woowahanrabbits.battle_people.domain.battle.domain.BattleApplyUserId;

public interface BattleApplyUserRepository extends JpaRepository<BattleApplyUser, BattleApplyUserId> {
	List<BattleApplyUser> findByBattleBoard_IdAndSelectedOpinion(Long battleBoardId, int selectedOpinion);

}
