package com.woowahanrabbits.battle_people.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.woowahanrabbits.battle_people.domain.user.domain.UserEntity;
import com.woowahanrabbits.battle_people.domain.user.dto.CustomUserDetails;
import com.woowahanrabbits.battle_people.domain.user.infrastructure.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
		return new CustomUserDetails(user); // CustomUserDetails를 반환
	}
}
