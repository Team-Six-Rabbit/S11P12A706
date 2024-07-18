package com.woowahanrabbits.battle_people.domain.live.controller;

import com.woowahanrabbits.battle_people.domain.live.domain.LiveApplyUser;
import com.woowahanrabbits.battle_people.domain.live.domain.Room;
import com.woowahanrabbits.battle_people.domain.live.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LiveRoomController {
    private RoomService roomService;

    @MessageMapping("/createRoom")
    @SendTo("/topic/rooms")
    public Room createRoom(String roomId) {
        return roomService.createRoom(roomId);
    }

    @MessageMapping("/addParticipant")
    @SendTo("/topic/participants")
    public LiveApplyUser addParticipant(String roomId, long userId) {
        return roomService.addParticipant(roomId, userId);
    }
}
