package com.woowahanrabbits.battle_people.domain.user.dto;

import com.woowahanrabbits.battle_people.domain.battle.dto.BattleBoardResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String code;

    private String msg;

    private T data;

}
