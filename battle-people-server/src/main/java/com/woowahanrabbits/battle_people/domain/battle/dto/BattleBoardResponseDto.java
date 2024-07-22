package com.woowahanrabbits.battle_people.domain.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class BattleBoardResponseDto {


    private Long id;
    private long registUserId;
    private long oppositeUserId;
    private Date startDate;
    private Date endDate;
    private int maxPeopleCount;
    private int category;
    private String imageUri;
    private String liveUri;
}
