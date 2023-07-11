package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // @RequiredArgsConstructor어노테이션은 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션. 
@Service					
public class UserService {
	
	private final UserRepository userRepository; 
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional // 회원수정이 일어나는거니깐
	public User 회원수정(int id, User user) {
		// 1.영속화
		User userEntity = userRepository.findById(id).orElseThrow(() ->{ return new CustomValidationApiException("찾을 수 없는 id입니다.");}); // findById 에서 1번 user을 찾을건데 만약 1번 user 가 없으면 null 이 리턴 ! 그래서 자바에선 Optional을 만들어줌
		// Optional이 하는 일은 1. 무조건 찾았다 걱정마 get() 2. 못찾았어 익섹션 발동시킬게 orElseThrow() 일단은 .get() 으로 무조건 찾았다고 하자 나중에 orElseThorw()로 바꿀거임
		
		// 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity; // 수정된 오브젝트를 리턴하면 더티체킹일어나서 업데이트가 완료됨.
	}
}
