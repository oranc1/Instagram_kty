package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA query method - 복잡한 쿼리도 짤 수 있다.
	User findByUsername(String username); // 그럼 찾아서 User 오브젝트를 리턴해준다.
	
}
