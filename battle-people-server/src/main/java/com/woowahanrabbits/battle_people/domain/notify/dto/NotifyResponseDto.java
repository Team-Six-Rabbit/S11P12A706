package com.woowahanrabbits.battle_people.domain.notify.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleResponse;
import com.woowahanrabbits.battle_people.domain.user.dto.BasicUserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotifyResponseDto {
	private String title;
	private BattleResponse battleResponse;
	private BasicUserDto targetUser;

}
