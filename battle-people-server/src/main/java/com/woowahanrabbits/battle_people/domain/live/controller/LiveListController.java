package com.woowahanrabbits.battle_people.domain.live.controller;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.live.service.LiveListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/live")
public class LiveListController {

    private final LiveListService liveListService;

    @GetMapping("/active")
    public ResponseEntity<Page<BattleBoard>> getActiveBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<BattleBoard> activeBattleBoards = liveListService.getActiveBattleBoards(keyword, pageable);
        return ResponseEntity.ok(activeBattleBoards);
    }

    @GetMapping("/wait")
    public ResponseEntity<Page<BattleBoard>> getWaitBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<BattleBoard> waitBattleBoards = liveListService.getWaitBattleBoards(keyword, pageable);
        return ResponseEntity.ok(waitBattleBoards);
    }
}
