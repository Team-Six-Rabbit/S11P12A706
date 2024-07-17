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
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/live")
public class LiveListController {

    private final LiveListService liveListService;

    @GetMapping("/active")
    public ResponseEntity<List<BattleBoard>> getActiveBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        List<BattleBoard> activeBattleBoards = liveListService.getActiveBattleBoards(keyword);
        return ResponseEntity.ok(activeBattleBoards);
    }

    @GetMapping("/wait")
    public ResponseEntity<List<BattleBoard>> getWaitBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        List<BattleBoard> waitBattleBoards = liveListService.getWaitBattleBoards(keyword);
        return ResponseEntity.ok(waitBattleBoards);
    }
}
