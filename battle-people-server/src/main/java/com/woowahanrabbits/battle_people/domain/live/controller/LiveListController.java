package com.woowahanrabbits.battle_people.domain.live.controller;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.live.service.LiveListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/live")
public class LiveListController {
    private final LiveListService liveListService;

    /**
     *
     * @param keyword
     * @param pageable
     * @return
     */
    @GetMapping("/active")
    public Page<BattleBoard> getActiveBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
       try {
           return liveListService.getActiveBattleBoards(keyword, pageable);
       }


    }

    @GetMapping("/wait")
    public Page<BattleBoard> getWaitBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return liveListService.getWaitBattleBoards(keyword, pageable);
    }

}