package com.woowahanrabbits.battle_people.domain.user.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowahanrabbits.battle_people.domain.user.domain.RefreshEntity;
import com.woowahanrabbits.battle_people.domain.user.dto.CustomUserDetails;
import com.woowahanrabbits.battle_people.domain.user.dto.JoinDTO;
import com.woowahanrabbits.battle_people.domain.user.infrastructure.RefreshRepository;
import com.woowahanrabbits.battle_people.domain.user.jwt.JwtUtil;
import com.woowahanrabbits.battle_people.domain.user.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;
	private final JwtUtil jwtUtil;
	private final RefreshRepository refreshRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JoinDTO joinDTO, HttpServletRequest request,
		HttpServletResponse response) {
		// CustomAuthenticationFilter에 의해 인증이 처리됩니다.
		Authentication authentication = userService.authenticate(joinDTO.getEmail(), joinDTO.getPassword());

		System.out.println("joinDTO = " + joinDTO);
		if (authentication != null && authentication.isAuthenticated()) {
			CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

			String username = customUserDetails.getUsername();

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
			GrantedAuthority auth = iterator.next();
			String role = auth.getAuthority();

			// 토큰 생성
			String access = jwtUtil.createToken("access", username, role, 600000L);
			String refresh = jwtUtil.createToken("refresh", username, role, 86400000L);

			// Refresh 토큰 저장
			addRefreshEntity(username, refresh, 86400000L);

			// 응답 설정
			response.addCookie(createCookie("access", access, "/"));
			response.addCookie(createCookie("refresh", refresh, "/auth/refresh"));
			response.setStatus(HttpStatus.OK.value());
			// System.out.println("login success");
			return new ResponseEntity<>("LOGIN SUCCESS", HttpStatus.OK);
		}
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		System.out.println("FAILED TO LOGIN");
		return new ResponseEntity<>("LOGIN FAILED", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
		// get refresh token
		String refresh = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("refresh")) {
				refresh = cookie.getValue();
			}
		}

		// test
		System.out.println(refresh.toString());

		if (refresh == null) {
			System.out.println("refresh is null");
			// response status code
			return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
		}

		// expired check
		try {
			jwtUtil.isExpired(refresh);
		} catch (ExpiredJwtException e) {
			System.out.println("token is expired");

			// response status code
			return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
		}

		// 토큰이 refresh인지 확인 (발급 시 페이로드에 명시)
		String category = jwtUtil.getCategory(refresh);

		if (!category.equals("refresh")) {
			System.out.println("refresh token not valid");
			// response status code
			return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
		}

		// DB에 저장되어 있는지 확인
		Boolean isExist = refreshRepository.existsByRefresh(refresh);
		if (!isExist) {
			// response body
			return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
		}

		String username = jwtUtil.getUsername(refresh);
		String role = jwtUtil.getRole(refresh);

		// make new JWT
		String newAccess = jwtUtil.createToken("access", username, role, 600000L);
		String newRefresh = jwtUtil.createToken("refresh", username, role, 86400000L); // 24시간

		// Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
		refreshRepository.deleteByRefresh(refresh);
		addRefreshEntity(username, newRefresh, 86400000L);

		// response
		response.addCookie(createCookie("accessToken", newAccess, "/"));
		response.addCookie(createCookie("refreshToken", newRefresh, "/auth/refresh"));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void addRefreshEntity(String username, String refresh, Long expiredMs) {
		Date date = new Date(System.currentTimeMillis() + expiredMs);

		RefreshEntity refreshEntity = new RefreshEntity();
		refreshEntity.setUsername(username);
		refreshEntity.setRefresh(refresh);
		refreshEntity.setExpiration(date.toString());

		refreshRepository.save(refreshEntity);
	}

	private Cookie createCookie(String key, String value, String path) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(24 * 60 * 60);
		cookie.setPath(path);
		cookie.setHttpOnly(true);

		return cookie;
	}
}
