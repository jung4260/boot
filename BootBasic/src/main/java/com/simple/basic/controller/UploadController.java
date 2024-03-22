package com.simple.basic.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.basic.command.UploadVO;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/fileupload")
public class UploadController {

	//업로드경로
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//날짜 폴더 만드는 함수 - 윈도우 시스템상 하나의 폴더에 파일이 66532개 가능함 그 이상 불가
	public String makeFolder () {
		
		String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		File file = new File(uploadPath + "/" + filepath);
		if(file.exists() == false) { //해당 파일이 있으면 true
			file.mkdirs();
		}
		
		return filepath;
	}
	
	//업로드화면
	@GetMapping("/upload")
	public String upload() {
		return "fileupload/upload";
	}
	
	//업로드요청
	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {
		
		//1. 파일명
		//브라우저별로 파일의 경로가 포함되서 올라오는 경우가 있기 때문에
		String originName = file.getOriginalFilename();
		originName = originName.substring(originName.lastIndexOf("\\") + 1);
		
		//2. 파일사이즈
		//long size = file.getSize();
		
		//4.동일한 파일로 업로드가 되면, 기존파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
		String uuid = UUID.randomUUID().toString();
		
		//날짜별로 폴더생성
		String filepath = makeFolder();
		
		//3. 업로드할 경로 지정
		String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + originName;
		
		System.out.println("파일명:" + originName); //원본파일명 DB저장
		System.out.println("폴더명:" + filepath ); //폴더명 DB저장
		System.out.println("랜덤이름:" + uuid); //랜덤한 이름
		System.out.println("업로드 할 경로:" + savePath);		
		//System.out.println("파일사이즈:" + size);
		
		try {
			File saveFile = new File(savePath); //업로드경로
			file.transferTo(saveFile); //업로드
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fileupload/upload_ok";
	}
	
	
	@PostMapping("/upload_ok2")
	public String upload_ok2(MultipartHttpServletRequest part) {
		
		
		List <MultipartFile> list = part.getFiles("file");
		
		for(MultipartFile file : list) {
			
			//1. 파일명
			//브라우저별로 파일의 경로가 포함되서 올라오는 경우가 있기 때문에
			String originName = file.getOriginalFilename();
			originName = originName.substring(originName.lastIndexOf("\\") + 1);
			
			//2. 파일사이즈
			//long size = file.getSize();
			
			//4.동일한 파일로 업로드가 되면, 기존파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
			String uuid = UUID.randomUUID().toString();
			
			//날짜별로 폴더생성
			String filepath = makeFolder();
			
			//3. 업로드할 경로 지정
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + originName;
			
			System.out.println("파일명:" + originName); //원본파일명 DB저장
			System.out.println("폴더명:" + filepath ); //폴더명 DB저장
			System.out.println("랜덤이름:" + uuid); //랜덤한 이름
			System.out.println("업로드 할 경로:" + savePath);		
			//System.out.println("파일사이즈:" + size);
			
			try {
				File saveFile = new File(savePath); //업로드경로
				file.transferTo(saveFile); //업로드
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		return "fileupload/upload_ok";
	}
	
	@PostMapping("/upload_ok3")
	public String upload_ok3(@RequestParam("file") List<MultipartFile> list) {
		
		//MultipartFile 비어있으면, 필터링
		list = list.stream().filter( m -> m.isEmpty() == false ).collect(Collectors.toList());
		
		for(MultipartFile file : list) {
			
			//1. 파일명
			//브라우저별로 파일의 경로가 포함되서 올라오는 경우가 있기 때문에
			String originName = file.getOriginalFilename();
			originName = originName.substring(originName.lastIndexOf("\\") + 1);
			
			//2. 파일사이즈
			//long size = file.getSize();
			
			//4.동일한 파일로 업로드가 되면, 기존파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
			String uuid = UUID.randomUUID().toString();
			
			//날짜별로 폴더생성
			String filepath = makeFolder();
			
			//3. 업로드할 경로 지정
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + originName;
			
			System.out.println("파일명:" + originName); //원본파일명 DB저장
			System.out.println("폴더명:" + filepath ); //폴더명 DB저장
			System.out.println("랜덤이름:" + uuid); //랜덤한 이름
			System.out.println("업로드 할 경로:" + savePath);		
			//System.out.println("파일사이즈:" + size);
			
			try {
				File saveFile = new File(savePath); //업로드경로
				file.transferTo(saveFile); //업로드
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return "fileupload/upload_ok";
	}
	
	
	//REST방식으로 컨트롤러 
	@PostMapping("/upload_ok4")
	@ResponseBody //REST방식 - 넘어오는 데이터가 multipart form형식이여서 @RequestBody를 쓰지 않음
	public String upload_ok4(/*
								 * @RequestParam("writer") String writer,
								 * 
								 * @RequestParam("file") MultipartFile file
								 */
							UploadVO	vo) {
		
		MultipartFile file = vo.getFile(); //vo에 저장된 file 객체
		
		//이하여백
		//1. 파일명
		//브라우저별로 파일의 경로가 포함되서 올라오는 경우가 있기 때문에
		String originName = file.getOriginalFilename();
		originName = originName.substring(originName.lastIndexOf("\\") + 1);
		
		//2. 파일사이즈
		//long size = file.getSize();
		
		//4.동일한 파일로 업로드가 되면, 기존파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
		String uuid = UUID.randomUUID().toString();
		
		//날짜별로 폴더생성
		String filepath = makeFolder();
		
		//3. 업로드할 경로 지정
		String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + originName;
		
		System.out.println("파일명:" + originName); //원본파일명 DB저장
		System.out.println("폴더명:" + filepath ); //폴더명 DB저장
		System.out.println("랜덤이름:" + uuid); //랜덤한 이름
		System.out.println("업로드 할 경로:" + savePath);		
		//System.out.println("파일사이즈:" + size);
		
		try {
			File saveFile = new File(savePath); //업로드경로
			file.transferTo(saveFile); //업로드
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "success";
	}
	
	
	
	
	
}
