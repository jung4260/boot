package com.simple.basic.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	
	@Pattern (regexp = "[a-zA-Z0-9]{8,}", message="아이디는 영문자,숫자 8자 이상입니다.")
	private String id;
	
	@Pattern (regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message="비밀번호 영문자,숫자,특수문자 조합 8글자 이상 입니다.")
	private String pw;
	
}