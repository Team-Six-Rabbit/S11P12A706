package com.woowahanrabbits.battle_people.domain.battle.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woowahanrabbits.battle_people.domain.api.dto.ApiResponseDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.AwaitingBattleResponseDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleApplyDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleInviteRequest;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleRespondRequest;
import com.woowahanrabbits.battle_people.domain.battle.service.BattleService;
import com.woowahanrabbits.battle_people.domain.notify.service.NotifyService;
import com.woowahanrabbits.battle_people.domain.user.domain.User;
import com.woowahanrabbits.battle_people.domain.user.infrastructure.UserRepository;
import com.woowahanrabbits.battle_people.domain.user.resolver.LoginUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/battle")
@Tag(name = "BattleController", description = "배틀 컨트롤러")
public class BattleController {

	private final BattleService battleService;
	private final NotifyService notifyService;
	private final UserRepository userRepository;

	//배틀 등록
	@PostMapping("/invite")
	@Operation(summary = "[점화] 배틀을 요청한다.")
	public ResponseEntity<?> registBattle(@RequestBody BattleInviteRequest battleInviteRequest) {
		User user = userRepository.findById(7L).orElseThrow();
		notifyService.sendNotify(battleService.registBattle(battleInviteRequest, user));
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>("success", "", null));
	}

	@PatchMapping("/accept-or-decline")
	@Operation(summary = "[불씨] 배틀을 수락 또는 거절한다.")
	public ResponseEntity<?> acceptOrDeclineBattle(@RequestBody BattleRespondRequest battleRespondRequest,
		@LoginUser User user) {

		battleService.acceptOrDeclineBattle(battleRespondRequest, user);
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ApiResponseDto<>("success", "", null));
	}

	@GetMapping("/apply-list")
	@Operation(summary = "[불씨] 모집중인 배틀을 조회한다.")
	public ResponseEntity<?> getAwaitingBattleList(@RequestParam(defaultValue = "") Integer category, int page) {
		User user = userRepository.findById(7L).orElseThrow();
		List<AwaitingBattleResponseDto> list = battleService.getAwaitingBattleList(category, page, user);
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ApiResponseDto<>("success", "", list));
	}

	@PostMapping("/apply")
	@Operation(summary = "모집중인 특정 배틀에 참여 신청한다.")
	public ResponseEntity<?> applyBattle(@RequestBody @Valid BattleApplyDto battleApplyDto,
		@LoginUser User user) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ApiResponseDto<>("success", "", battleService.applyBattle(battleApplyDto, user)));
	}
}
