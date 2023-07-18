package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

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
public class Image { // N, 1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int caption; // 오늘 나 너무 피곤해 ! 
	private int postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user; // 1 , 1 즉 Image 입장에서의 user는 ManyToOne 관계 !
	
	// 이미지 좋아요
	
	private LocalDateTime createDate;
	
	@PrePersist // 항상 데이터베이스가 언제 들어갔는가 시간이 필요하다.
	public void createDate() { 
		this.createDate = LocalDateTime.now();
	}
}
