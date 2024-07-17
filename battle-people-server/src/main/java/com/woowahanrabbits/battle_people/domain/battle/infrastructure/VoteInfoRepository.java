package com.woowahanrabbits.battle_people.domain.battle.infrastructure;

import com.woowahanrabbits.battle_people.domain.vote.domain.VoteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteInfoRepository extends JpaRepository<VoteInfo, Long> {
}
