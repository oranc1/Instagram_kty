package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException {

	// 객체를 구분할 떄 ! (노랑색 마우스 갔다대고 첫번째 클릭)
	private static final long serialVersionUID = 1L;
	
	
	public CustomApiException(String message) {
		super(message);
	}
}
