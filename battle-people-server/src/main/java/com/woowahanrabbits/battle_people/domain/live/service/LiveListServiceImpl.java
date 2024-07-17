package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.battle.infrastructure.BattleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveListServiceImpl implements LiveListService{

    private final BattleRepository battleRepository;

    @Override
    public Page<BattleBoard> getActiveBattleBoards(String keyword, Pageable pageable) {
        Date currentTime = new Date();

        return battleRepository.findAllActiveBattleBoards(currentTime, keyword, pageable);

    }

    @Override
    public Page<BattleBoard> getWaitBattleBoards(String keyword, Pageable pageable) {
        Date currentTime = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);

        calendar.add(Calendar.MINUTE, 20);

        Date endTime = calendar.getTime();


        return battleRepository.findAllWaitBattleBoards(currentTime, endTime, keyword, pageable);
    }
}
