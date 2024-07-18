package com.woowahanrabbits.battle_people.domain.battle.infrastructure;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BattleRepository extends JpaRepository<BattleBoard, Long> {

    @Query("SELECT bb FROM BattleBoard bb JOIN bb.voteInfo vi WHERE :currentTime BETWEEN vi.startDate AND vi.endDate "
    + "AND vi.title LIKE %:keyword%")
    Page<BattleBoard> findAllActiveBattleBoards(
            @Param("currentTime") Date currentTime,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    @Query("SELECT bb FROM BattleBoard bb JOIN bb.voteInfo vi WHERE vi.startDate BETWEEN :currentTime AND :endTime "
    + "AND vi.title LIKE %:keyword%")
    Page<BattleBoard> findAllWaitBattleBoards(
            @Param("currentTime") Date currentTime,
            @Param("endTime") Date endTime,
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
