package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티 활성화
@Configuration // loC 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
		http.csrf().disable(); // 이제 csrf 토큰 검사 안함
		http.authorizeRequests()
		 .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**").authenticated() // 인증이 필요한 페이지는 아무나 못들어감
		 .anyRequest().permitAll()
		 .and()
		 .formLogin()
		 .loginPage("/auth/signin")
		 .defaultSuccessUrl("/");
	}
}
