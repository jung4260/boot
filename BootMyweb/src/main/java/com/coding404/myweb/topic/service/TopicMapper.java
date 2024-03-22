package com.coding404.myweb.topic.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.myweb.command.TopicVO;

@Mapper
public interface TopicMapper {

	public int regist(TopicVO vo);
	public ArrayList<TopicVO> getlist();
	public ArrayList<TopicVO> getListMine();
	public void delete(int topic_num);
}
