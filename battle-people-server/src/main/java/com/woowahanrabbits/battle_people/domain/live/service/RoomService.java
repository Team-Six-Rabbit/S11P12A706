package com.woowahanrabbits.battle_people.domain.live.service;

import com.woowahanrabbits.battle_people.domain.live.domain.LiveApplyUser;
import com.woowahanrabbits.battle_people.domain.live.domain.Room;
import com.woowahanrabbits.battle_people.domain.user.domain.User;

import java.util.List;

public interface RoomService {
    Room createRoom(String roomId);
    LiveApplyUser addParticipant(String roomId, long id);
    List<LiveApplyUser> getParticipants(String roomId);
}
