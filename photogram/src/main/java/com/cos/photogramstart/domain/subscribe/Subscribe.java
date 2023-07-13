package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
@Table(
	uniqueConstraints = {
			@UniqueConstraint(
				name="subscribe_uk", // 내가 맘대로 지어도 된다 ~ 
				columnNames = {"fromUserId", "toUserId"} // 어떤 2개를 제약조건으로 걸거냐 ? 
			)
		}
	)
public class Subscribe { // 중간테이블 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다. // MySql 을 쓰면 AutoIncerement , 오라클이면 시퀀스 로 따라갸아하기떄문에 IDENTITY 로 씀
	private int id; 
	
	@JoinColumn(name = "fromUserId") // 이렇게 컬럼명 만들어 ! 니 맘대로 만들지 말고 ! 
	@ManyToOne
	private User fromUser; // 구독하는 유저
	
	@JoinColumn(name = "")
	@ManyToOne
	private User toUser; // 구독받는 유저
	
	private LocalDateTime createDate;
	
	@PrePersist // 디비에 INSERT 되기 직전에 실행 / 위에 값을 넣으면 자동으로 이 값은 들어간다.
	public void createDate() { 
		this.createDate = LocalDateTime.now();
	}
}
