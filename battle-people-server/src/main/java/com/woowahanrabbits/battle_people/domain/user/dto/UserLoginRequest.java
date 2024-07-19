package com.woowahanrabbits.battle_people.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
	private String email;
	private String password;
}
