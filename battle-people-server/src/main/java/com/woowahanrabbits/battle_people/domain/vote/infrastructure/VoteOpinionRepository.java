package com.woowahanrabbits.battle_people.domain.vote.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinion;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinionId;

public interface VoteOpinionRepository extends JpaRepository<VoteOpinion, VoteOpinionId> {
	List<VoteOpinion> findAllByVoteInfoId(Long voteInfoId);

}
