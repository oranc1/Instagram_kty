package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

import jdk.internal.icu.impl.ICUBinary.Authenticate;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 1. 추천
		System.out.println("세션 정보 : " + principalDetails.getUser());
		
		// 2. 비추
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		System.out.println("세션 정보2 : " + mPrincipalDetails.getUser());
		
		
		return "user/update";
		
	}
}
