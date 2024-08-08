package com.woowahanrabbits.battle_people.domain.notify.service;

import java.util.List;

import com.woowahanrabbits.battle_people.domain.battle.dto.BattleResponse;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleVoteDto;
import com.woowahanrabbits.battle_people.domain.notify.dto.NotifyResponseDto;

public interface NotifyService {

	NotifyResponseDto sendNotify(BattleVoteDto battleBoardId);

	BattleResponse mappingDtos(BattleVoteDto battleVoteDto);

	List<NotifyResponseDto> getNotificationList(Long userId, int page, int size);
}
