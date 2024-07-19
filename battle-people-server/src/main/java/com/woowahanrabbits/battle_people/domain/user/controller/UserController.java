package com.woowahanrabbits.battle_people.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowahanrabbits.battle_people.domain.user.dto.JoinDTO;
import com.woowahanrabbits.battle_people.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/join")
	public String joinProcess(@RequestBody JoinDTO joinDTO) {
		if (userService.join(joinDTO)) {
			return "Success";
		}
		return "Fail";
	}
}
