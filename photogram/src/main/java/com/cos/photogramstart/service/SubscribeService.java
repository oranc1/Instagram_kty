package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	
	@Transactional  // 둘다 인서트하거나 딜리트하는 데이터베이스에 영향을 주니깐 Transctioanl 쓰자.
	public void 구독하기(int fromUserId, int toUserId) {
		subscribeRepository.mSubscribe(fromUserId, toUserId); 
		
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
		
	}
}
