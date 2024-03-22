package com.coding404.myweb.topic.service;

import java.util.ArrayList;

import com.coding404.myweb.command.TopicVO;

public interface TopicService {

	
	public int regist(TopicVO vo);
	public ArrayList<TopicVO> getlist();
	public ArrayList<TopicVO> getListMine();
	public void delete(int topic_num);
	
}
