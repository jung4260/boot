package com.simple.basic.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidVO {
	
	/*
	 @NotNull - null제외 (String, Integer, Long타입 등에 적용)
	 @NotEmpty - null제외, 공백 제외 (Map, String, Arrays 등등에 적용)
	 @NotBlank - null제외, 공백 제외, 화이트스페이스 제외(String에 적용)
	 
	 @Pattern - 정규표현식에 맞는 문자열을 정할 수 있슴
	 @Email - 이메일형식, blank 허용
	 @Max - 최대값
	 @Min - 최소값 등등..
	 */
	//유효성 검사를 진행할 맴버변수는 wrapper형으로 작성합니다 (형변환의 문제)
	
	@NotBlank(message = "이름은 필수입니다.")
	private String name;
	@NotNull(message="급여는 필수입니다.")
	private Integer salary;
	@Pattern (regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "000-0000-0000형식 입니다.")
	private String phone;
	@Email (message="이메일 형식으로 입력하세요")
	private String email;
	
}
