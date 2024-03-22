package com.simple.basic.test01;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.simple.basic.command.MemoVO;
import com.simple.basic.command.UsersVO;

@SpringBootTest
public class TestCode02 {

	@Autowired
	TestMapper testMapper;
	
	@Test
	public void test01() {
		System.out.println("현재시간:" + testMapper.getTime());
	}
	
	//조인의 처리
	//N:1
	@Test
	public void test02() {
		ArrayList<MemoVO> list = testMapper.joinOwn();
		System.out.println(list.toString());
	}
	
	
	//조인의 처리
	//1:N
	
	@Test
	public void test03() {
		UsersVO vo = testMapper.joinTwo();
		System.out.println(vo.toString());
	}
	
	
	
	
	
	
}
