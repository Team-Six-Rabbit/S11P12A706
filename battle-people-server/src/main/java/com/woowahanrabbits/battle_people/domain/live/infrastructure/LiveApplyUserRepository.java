package com.woowahanrabbits.battle_people.domain.live.infrastructure;

import com.woowahanrabbits.battle_people.domain.live.domain.LiveApplyUser;
import com.woowahanrabbits.battle_people.domain.live.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveApplyUserRepository extends JpaRepository<LiveApplyUser, Long> {
    List<LiveApplyUser> findByRoom(Room room);
}
