package com.woowahanrabbits.battle_people.domain.live.controller;

import com.woowahanrabbits.battle_people.domain.battle.dto.BattleBoardResponseDto;
import com.woowahanrabbits.battle_people.domain.live.service.LiveListService;
import com.woowahanrabbits.battle_people.domain.user.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/live")
public class LiveListController {

    private final LiveListService liveListService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/active")
    public ResponseEntity<ApiResponseDto<List<BattleBoardResponseDto>>> getActiveBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        try{
            Page<BattleBoardResponseDto> activeBattleBoards = liveListService.getActiveBattleBoards(keyword, pageable);
            System.out.println(activeBattleBoards.getContent());

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>("success", "", activeBattleBoards.getContent()));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto<>("fail", "internal server error", null));
        }

    }


    @GetMapping("/wait")
    public ResponseEntity<ApiResponseDto<List<BattleBoardResponseDto>>> getWaitBattleBoards(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        try{
            Page<BattleBoardResponseDto> waitBattleBoards = liveListService.getWaitBattleBoards(keyword, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<List<BattleBoardResponseDto>>("success", "", waitBattleBoards.getContent()));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto<>("fail", "internal server error", null));
        }

    }


}
