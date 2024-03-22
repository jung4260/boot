package com.coding404.demo.member;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.demo.command.MemberVO;

@Mapper
public interface MemberMapper {

	void join (MemberVO vo);
	MemberVO login (String username);
}
