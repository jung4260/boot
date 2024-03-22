package com.simple.basic.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.MemoVO;
import com.simple.basic.mapper.MemoMapper;

@Service
public class MemoServiceImpl implements MemoService{

	@Autowired
	MemoMapper memoMapper;
	
	@Override
	public void insertMemo(MemoVO vo) {
		memoMapper.insertMemo(vo);
	}

	@Override
	public ArrayList<MemoVO> getList() {
		ArrayList<MemoVO> list = memoMapper.getList();
		return list;
	}

	
	
}
