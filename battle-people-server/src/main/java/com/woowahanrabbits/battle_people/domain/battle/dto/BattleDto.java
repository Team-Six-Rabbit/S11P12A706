package com.woowahanrabbits.battle_people.domain.battle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BattleDto {
	private Long id;
	private VoteInfo voteInfo;
	private int minPeopleCount;
	private int maxPeopleCount;
	private String battleRule;

}
