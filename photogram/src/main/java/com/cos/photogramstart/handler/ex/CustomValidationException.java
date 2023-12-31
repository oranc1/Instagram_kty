package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException {

	// 객체를 구분할 떄 ! (노랑색 마우스 갔다대고 첫번째 클릭)
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;
	
	public CustomValidationException(String message, Map<String, String>errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
	// erroMap 으로 리턴
	public Map<String, String> getErrorMap() {
		return errorMap;
	}
}
