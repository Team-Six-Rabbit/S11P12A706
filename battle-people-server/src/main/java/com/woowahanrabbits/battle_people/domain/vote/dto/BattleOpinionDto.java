package com.woowahanrabbits.battle_people.domain.vote.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.woowahanrabbits.battle_people.domain.user.dto.BasicUserDto;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteOpinion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BattleOpinionDto {
	private Integer index;
	private String opinion;
	private BasicUserDto user;

	public BattleOpinionDto(VoteOpinion voteOpinion) {
		this.index = voteOpinion.getVoteOpinionIndex();
		this.opinion = voteOpinion.getOpinion();
		this.user = new BasicUserDto(voteOpinion.getUser());
	}

	@JsonCreator
	public BattleOpinionDto(
		@JsonProperty("index") int index,
		@JsonProperty("opinion") String opinion,
		@JsonProperty("user") BasicUserDto user) {
		this.index = index;
		this.opinion = opinion;
		this.user = user;
	}

}
