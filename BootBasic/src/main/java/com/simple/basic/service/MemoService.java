package com.simple.basic.service;

import java.util.ArrayList;

import com.simple.basic.command.MemoVO;

public interface MemoService {

	public void insertMemo(MemoVO vo);
	public ArrayList<MemoVO> getList();
}
