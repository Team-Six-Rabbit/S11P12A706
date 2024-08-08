package com.woowahanrabbits.battle_people.domain.battle.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.woowahanrabbits.battle_people.domain.vote.domain.VoteInfo;
import com.woowahanrabbits.battle_people.domain.vote.dto.BattleOpinionDto;

import lombok.Getter;

@Getter
public class AwaitingBattleResponseDto {
	private Long id;
	private String title;
	private List<BattleOpinionDto> opinions;
	private Date startDate;
	private Date endDate;
	private int category;
	private int maxPeopleCount;
	private int userCount;
	@JsonProperty("isVoted")
	private boolean userVote;

	public AwaitingBattleResponseDto(VoteInfo voteInfo,
		List<BattleOpinionDto> battleOpinionDtos, int userCount, int maxPeopleCount, boolean userVoted) {
		this.id = voteInfo.getId();
		this.title = voteInfo.getTitle();
		this.opinions = battleOpinionDtos;
		this.userCount = userCount;
		this.maxPeopleCount = maxPeopleCount;
		this.userVote = userVoted;
		this.startDate = voteInfo.getStartDate();
		this.endDate = voteInfo.getEndDate();
		this.category = voteInfo.getCategory();
	}
}
