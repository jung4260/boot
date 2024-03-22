package com.simple.basic.command;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersVO {
	
	private String id;
	private String pw;
	private String name;
	private String email;
	
	//1:N방식에서는 N테이블을 변수로 선언
	private List<MemoVO> memo;
	
}
