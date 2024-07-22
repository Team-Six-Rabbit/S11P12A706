package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.battle.dto.BattleBoardResponseDto;
import com.woowahanrabbits.battle_people.domain.battle.infrastructure.BattleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class LiveListServiceImpl implements LiveListService {

    private final BattleRepository battleRepository;

    @Override
    public Page<BattleBoardResponseDto> getActiveBattleBoards(String keyword, Pageable pageable) {
        Date currentTime = new Date();

        return battleRepository.findAllActiveBattleBoards(currentTime, keyword, pageable).map(this::convertToDto);
    }

    @Override
    public Page<BattleBoardResponseDto> getWaitBattleBoards(String keyword, Pageable pageable) {
        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.MINUTE, 20);
        Date endTime = calendar.getTime();
        return battleRepository.findAllWaitBattleBoards(currentTime, endTime, keyword, pageable).map(this::convertToDto);
    }

    private BattleBoardResponseDto convertToDto(BattleBoard battleBoard) {
        return new BattleBoardResponseDto(
                battleBoard.getId(),
                battleBoard.getRegistUser().getId(),
                battleBoard.getOppositeUser() != null ? battleBoard.getOppositeUser().getId() : null,
                battleBoard.getVoteInfo().getStartDate(),
                battleBoard.getVoteInfo().getEndDate(),
                battleBoard.getMaxPeopleCount(),
                battleBoard.getCurrentState(), // Assuming category is stored in currentState
                battleBoard.getImageUrl(),
                battleBoard.getLiveUri()
        );
    }
}
