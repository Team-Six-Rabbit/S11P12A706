package com.woowahanrabbits.battle_people.domain.vote.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahanrabbits.battle_people.domain.vote.domain.UserVoteOpinion;
import com.woowahanrabbits.battle_people.domain.vote.domain.UserVoteOpinionId;

public interface UserVoteOpinionRepository extends JpaRepository<UserVoteOpinion, UserVoteOpinionId> {
	UserVoteOpinion findByUser_IdAndVoteInfo_Id(Long userId, Long voteInfoId);
}
