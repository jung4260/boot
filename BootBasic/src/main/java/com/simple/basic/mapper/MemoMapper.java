package com.simple.basic.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.MemoVO;

@Mapper
public interface MemoMapper {
	
	public int insertMemo(MemoVO vo);
	public ArrayList<MemoVO> getList();
	
}
