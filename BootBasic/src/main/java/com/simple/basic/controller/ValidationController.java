package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemberVO;
import com.simple.basic.command.ValidVO;

@Controller
@RequestMapping("/valid")
public class ValidationController {
	
	@GetMapping("/ex01")
	public String ex01 () {
		return "valid/ex01";
	}
	
	//REST API에서 클라이언트에서 데이터를 전달 받을 떄도 쓸 수 있음
	//폼요청
	@PostMapping("/validForm")
	public String validForm(@Valid ValidVO vo, Errors errors, Model model) {

		//유효성 검사에 실패한 목록을 Errors안에 바인딩
	if(errors.hasErrors() ) { //error가 발생한 목록이 있으면 true
		List <FieldError> list = errors.getFieldErrors(); //유효성 검사에 실패한 목록
		
		for(FieldError err : list) {
			String field = err.getField(); //유효성 검사에 실패한 변수명
			String msg = err.getDefaultMessage(); //유효성 검사에 실패한 변수 메세지
//			System.out.println(field);
//			System.out.println(msg);
//			System.out.println("-----------");
			
			if(err.isBindingFailure()) { //유효성검사에 실패가 아니라, 자바코드에 에러인경우 true
				model.addAttribute("valid_" + field , "숫자로 입력하세요");
			}else {
				model.addAttribute("valid_" + field, msg); //valid_이름, 메세지 				
			}
			
		}
		
		model.addAttribute("vo", vo);
		
		return "valid/ex01"; // 다시 원래 화면
	}
		System.out.println(vo.toString());
		//데이터베이서 처리~
		
		return "valid/ex01_result";
	}

	@GetMapping("/quiz01")
	public String quiz01() {
		return "/valid/quiz01";
	}
	
	@PostMapping("/quizForm")
	public String quizForm(@Valid MemberVO vo, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			List <FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
			
				model.addAttribute(err.getField() , err.getDefaultMessage());				
				
			}
			
			model.addAttribute("vo", vo);
			return "valid/quiz01";
		}
		
		
		return "valid/quiz01_result";
	}
	
	
}
