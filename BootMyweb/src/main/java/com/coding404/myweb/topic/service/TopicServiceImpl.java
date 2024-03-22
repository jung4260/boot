package com.coding404.myweb.topic.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.command.TopicVO;

@Service("topicservice")
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicMapper topicmapper;
	
	@Override
	public int regist(TopicVO vo) {
		return topicmapper.regist(vo);
	}

	@Override
	public ArrayList<TopicVO> getlist() {
		return topicmapper.getlist();
	}

	@Override
	public ArrayList<TopicVO> getListMine() {
		return topicmapper.getListMine();
	}

	@Override
	public void delete(int topic_num) {
		topicmapper.delete(topic_num);
	}
	
	

}
