package com.woowahanrabbits.battle_people.domain.vote.infrastructure;

import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinion;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteOpinionRepository extends JpaRepository<VoteOpinion, VoteOpinionId> {
    List<VoteOpinion> findByVoteInfoId(Long voteInfoId);

}
