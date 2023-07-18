package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler { // 여기서 어노테이션 @ControllerAdvice을 쓰면 모든 Exception을 다 낚아챔 @RestController 로 데이터를 리턴할거다
	
	@ExceptionHandler(CustomValidationException.class) // RuntimeException 발동하는 모든 Exception을 이 함수가 다 가로챔
	public String validationException(CustomValidationException e) {
		// CMRespDto, Script 비교
		// 1.클라이언트에게 응답할 때는 Script 좋음 : 응답을 브라우저(클라이언트)가 받는다.
		// 2.Ajax통신 - CMRespDto : 개발자가 자바스크립트 코드로 서버쪽으로 던져서 응답받음
		// 3.Android통신- CMRespDto : 응답을 개발자가 받는 것
		// 즉 클라이언트는 1) 이 좋고 개발자는 2), 3) 이 좋다.
		return Script.back(e.getErrorMap().toString());
	}
	
	@ExceptionHandler(CustomValidationApiException.class) // RuntimeException 발동하는 모든 Exception을 이 함수가 다 가로챔
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) { // ResponseEntity<?> ?로 하면 응답할때 자동으로 제네릭타입이 결정된다
		System.out.println("=================나실행되니?==================");
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class) // RuntimeException 발동하는 모든 Exception을 이 함수가 다 가로챔
	public ResponseEntity<?> apiException(CustomApiException e) { // ResponseEntity<?> ?로 하면 응답할때 자동으로 제네릭타입이 결정된다
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
}


