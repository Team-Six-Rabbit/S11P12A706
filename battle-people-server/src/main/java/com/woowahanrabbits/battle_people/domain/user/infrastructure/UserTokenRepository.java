package com.woowahanrabbits.battle_people.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woowahanrabbits.battle_people.domain.user.domain.UserToken;

import jakarta.transaction.Transactional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
	Boolean existsByRefreshToken(String refreshToken);

	@Transactional
	void deleteByRefreshToken(String refreshToken);
}
