package com.woowahanrabbits.battle_people.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JwtToken {
	private String grantType; // JWT 인증 타입
	private String accessToken;
	private String refreshToken;
}

