package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler { // 여기서 어노테이션 @ControllerAdvice을 쓰면 모든 Exception을 다 낚아챔 @RestController 로 데이터를 리턴할거다
	
	@ExceptionHandler(CustomValidationException.class) // RuntimeException 발동하는 모든 Exception을 이 함수가 다 가로챔
	public Map<String, String> validationException(CustomValidationException e) {
		return e.getErrorMap();
	}
}
