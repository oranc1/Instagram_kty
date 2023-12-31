package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService; // 서비스의 회원수정을 호출해야하니깐 DI 해주고
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, 
		@Valid UserUpdateDto userUpdateDto,
		BindingResult bindingResult, // 꼭 @Vaild 가 적혀있는 다음 파라미터에 적어야됨 ! ! 
		@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage()); // put 해서 key 와 값을 설정
				System.out.println("================");
				System.out.println(error.getDefaultMessage());
				System.out.println("================");
			}
			throw new CustomValidationApiException("유효성검사 실패함", errorMap);
		} else {
			//@PathVariable 은 매핑에 {id} 가 있으니 쓰는것 @PathVariable int id 그대로 주자.
//		System.out.println(userUpdateDto);
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity()); // userUpdateDto.toEntitiy 를 날린다. 
			principalDetails.setUser(userEntity); // 세션정보변경
			return new CMRespDto<>(1, "회원수정완료", userEntity); // 1(성공), 	
		}
				
	}
	
	
}
