package com.woowahanrabbits.battle_people.domain.user.infrastructure;

import com.woowahanrabbits.battle_people.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long userId);
}