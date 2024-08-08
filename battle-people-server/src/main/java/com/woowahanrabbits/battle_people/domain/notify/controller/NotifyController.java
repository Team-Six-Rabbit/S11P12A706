package com.woowahanrabbits.battle_people.domain.notify.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowahanrabbits.battle_people.domain.api.dto.ApiResponseDto;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleVoteDto;
import com.woowahanrabbits.battle_people.domain.notify.dto.NotifyResponseDto;
import com.woowahanrabbits.battle_people.domain.notify.service.NotifyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notify")
@Tag(name = "NotifyController", description = "알림 컨트롤러")
public class NotifyController {

	private final NotifyService notifyService;

	@PostMapping("/send")
	public NotifyResponseDto sendNoti(@RequestBody BattleVoteDto battleVoteDto) {

		return notifyService.sendNotify(battleVoteDto);
	}

	@GetMapping("/{userId}")
	@Operation(summary = "알림 리스트 불러오기.")
	public ResponseEntity<?> getNotifications(@PathVariable("userId") Long userId, int page, int size) {

		List<NotifyResponseDto> notifies = notifyService.getNotificationList(userId, page, size);

		return ResponseEntity.status(HttpStatus.OK)
			.body(new ApiResponseDto<>("success", "", notifies));
	}

}
