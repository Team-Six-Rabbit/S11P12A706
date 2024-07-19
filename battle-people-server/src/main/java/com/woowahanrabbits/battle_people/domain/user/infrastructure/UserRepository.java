package com.woowahanrabbits.battle_people.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woowahanrabbits.battle_people.domain.user.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Boolean existsByEmail(String email);
	UserEntity findByEmail(String email);
}
