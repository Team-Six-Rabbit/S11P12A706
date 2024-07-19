package com.woowahanrabbits.battle_people.domain.BalanceGame.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woowahanrabbits.battle_people.domain.BalanceGame.domain.BalanceGameBoardComment;
import com.woowahanrabbits.battle_people.domain.BalanceGame.dto.BalanceGameCommentDto;
import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;

public interface BalanceGameRepository extends JpaRepository<BalanceGameBoardComment, Long> {

	@Query("SELECT new com.woowahanrabbits.battle_people.domain.BalanceGame.dto.BalanceGameCommentDto(b.id, b.battleBoard.id, b.user, b.content, b.registDate) " +
		"FROM BalanceGameBoardComment b " +
		"WHERE b.battleBoard.id = :battleBoardId")
	Page<BalanceGameCommentDto> findCommentsByBattleBoardId(@Param("battleBoardId") Long battleBoardId, Pageable pageable);
}
