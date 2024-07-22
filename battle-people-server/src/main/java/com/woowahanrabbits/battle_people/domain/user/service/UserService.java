package com.woowahanrabbits.battle_people.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.woowahanrabbits.battle_people.domain.user.domain.UserEntity;
import com.woowahanrabbits.battle_people.domain.user.dto.JoinDTO;
import com.woowahanrabbits.battle_people.domain.user.infrastructure.UserRepository;

@Service
public class UserService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(AuthenticationManager authenticationManager, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public Boolean join(JoinDTO joinDTO) {
		String email = joinDTO.getEmail();
		String password = joinDTO.getPassword();

		Boolean isExists = userRepository.existsByEmail(email);

		if (isExists) {
			return false;
		}
		UserEntity userEntity = new UserEntity(email, bCryptPasswordEncoder.encode(password), "ROLE_USER");
		userRepository.save(userEntity);
		return true;
	}

	public Authentication authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
		return authenticationManager.authenticate(authToken);
	}


}
