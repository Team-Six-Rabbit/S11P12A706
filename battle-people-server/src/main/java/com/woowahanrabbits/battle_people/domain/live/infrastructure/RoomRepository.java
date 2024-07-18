package com.woowahanrabbits.battle_people.domain.live.infrastructure;

import com.woowahanrabbits.battle_people.domain.live.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomId(String roomId);
}
