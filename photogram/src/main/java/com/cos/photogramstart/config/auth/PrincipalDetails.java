package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	// 생성자
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// 권한 : 한개가 아닐 수 있음 (3개 이상의 권한)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// 왜 ArrayList로 하냐 ? 클릭해서 계속 넘어가다보면 ArrayList 의 부모가 Collection 이다. 즉 어레이리스트도 콜렉션이다 ~ 
		Collection<GrantedAuthority> collector = new ArrayList<>();
		// collector 에는 아무런 권한이 안들어가 있으므로 collector.add를 해줘서 유저가 들고있는 USER_ROLE을 넣어햐나느데 GrantedAhority 라는걸 넣어라고 되어 있음
		// 그래서 new GrantedAuthority 를 넣어보니 이렇게 String getAuthority() 메서드가 나와서 여기에 return user.getRole();을 해준다
		// 좀 더러운 코드가 되서 람다식으로 바꿀 수 있음
//		collector.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// TODO Auto-generated method stub
//				return user.getRole();
//			}
//		});
		// 람다식으로 바꿀 수 있음 -> add 안에 함수를 넣고 싶은게 목적이다. 근데 자바에서는 이 매개변수에 함수를 못넣는다. 목적은 함수를 넘기는거니깐 람다식으로 함수를 넘기면 됨
		collector.add(() -> {return user.getRole();});
		
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
