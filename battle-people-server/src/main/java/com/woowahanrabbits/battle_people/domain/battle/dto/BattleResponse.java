package com.woowahanrabbits.battle_people.domain.battle.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.woowahanrabbits.battle_people.domain.vote.dto.BattleOpinionDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BattleResponse {
	private BattleDto battle;
	private List<BattleOpinionDto> opinions;

	public BattleResponse(BattleDto battleDto, List<BattleOpinionDto> opinions) {
		this.battle = battleDto;
		this.opinions = opinions;
	}

}
