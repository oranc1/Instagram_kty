package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}") // application.yml 에 path 를 지정해놨다 
	private String uploadFolder; // C:/workspace/springbootwork/upload/ 이게 들어온다.
	
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) { // 실제 로직은 서비스에서 !
		UUID uuid = UUID.randomUUID(); // 고유성이 보장되는 id를 만들기 위한 표준 규약
		String imageFileName = uuid + "-" + imageUploadDto.getFile().getOriginalFilename(); // 1.jpg 를 Upload폴더에 넣고 또 우연히 1.jpg 를 넣게 될 수 도 있다.
		System.out.println("이미지 파일이름" + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		// 통신(외부에서 파일을 받아올때),I/O(하드디스크에 기록,읽을때) -> 예외가 발생할 수 있다. 그래서 예외 처리를 해줘야한다,
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes()); //
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
