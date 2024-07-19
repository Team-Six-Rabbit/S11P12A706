package com.woowahanrabbits.battle_people.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.woowahanrabbits.battle_people.domain.user.domain.RefreshEntity;

public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {
	Boolean existsByRefresh(String refresh);
	@Transactional
	void deleteByRefresh(String refresh);
}
