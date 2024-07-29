package com.woowahanrabbits.battle_people.domain.live.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahanrabbits.battle_people.domain.live.domain.LiveApplyUser;

public interface LiveApplyUserRepository extends JpaRepository<LiveApplyUser, Long> {
	List<LiveApplyUser> findAllByRoom_Id(Long id);

	List<LiveApplyUser> findByRoomId(String roomId);

}
