package com.simple.basic.test01;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.MemoVO;
import com.simple.basic.command.UsersVO;

@Mapper
public interface TestMapper {
	
	public String getTime();
	public ArrayList<MemoVO> joinOwn(); //N:1
	public UsersVO joinTwo(); //1:N
	
}
