package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final DI 할떄 사용
@Controller // 1.IoC 2.파일을 리턴하는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;
	
// Service 를 쓰기 위해선 2가지가 더 있는데 ! (Autowired는 내가 국비때 배움) 	
//	1) @Autowired
	
//	2) public AuthController(AuthService authService) {
//		this.authService = authService;
//	} 
//  3)@RequiredArgsConstructor // final DI 할떄 사용 - 이걸 사용할거임
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		
		return "auth/signup";
	}
	
	// 회원가입버튼 -> /auth/signup -> /auth/signin
	// 회원가입버튼X
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // key=value(x-www-form-urlencoded) : 스프링이 기본적으로 데이터를 받는 형식	
		log.info(signupDto.toString());
		// User <- SignupDTO
		User user = signupDto.toEntity();
		log.info(user.toString());
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin";
	}
	
	
	
	
	
	
	
	
	
	
	
}
