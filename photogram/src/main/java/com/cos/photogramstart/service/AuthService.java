package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 1.IoC 2.트랜잭션 관리
public class AuthService {
	
	private final UserRepository userRepository;
	
	public User 회원가입(User user) {
		//회원가입 진행
		User userEntity = userRepository.save(user); // 왜 Entity 라고 거냐고 save (DB에 들어간 뒤에) 뒤에 응답 받을려고
		return userEntity;
	}
}
