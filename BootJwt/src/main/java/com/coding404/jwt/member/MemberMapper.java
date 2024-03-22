package com.coding404.jwt.member;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.jwt.command.MemberVO;

@Mapper
public interface MemberMapper {
	void join(MemberVO vo); 
	MemberVO login(String username);
}
