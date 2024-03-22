package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoVO {

	private int mno;
	private String writer;
	private String memo;
	
	
	//N:1인 경우는 N쪽에 컬럼을 추가
	private String id;
	private String email;
	private String name;
	
}
