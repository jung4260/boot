package com.coding404.myweb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	@Qualifier("topicservice")
	private TopicService topicservice;
	
	@GetMapping("/topicDetail")
	public String detail() {
		return "/topic/topicDetail";
	}
	
	@GetMapping("/topicListMe")
	public String listMe(Model model) {
		
		ArrayList<TopicVO> list = topicservice.getListMine();
		model.addAttribute("list", list);
		
		return "/topic/topicListMe";
	}
	
	@GetMapping("/topicListAll")
	public String listAll(Model model) {
		
		ArrayList<TopicVO> list = topicservice.getlist();
		model.addAttribute("list", list);
		
		return "/topic/topicListAll";
	}
	
	@GetMapping("/topicModify")
	public String modify() {
		return "/topic/topicModify";
	}
	
	@GetMapping("/topicReg")
	public String topicReg() {
		return "/topic/topicReg";
	}
	
	@PostMapping("/topicRegForm")
	public String regForm(TopicVO vo, RedirectAttributes ra) {
		int result = topicservice.regist(vo);
		
		if (result == 1) {
			ra.addAttribute("msg", "성공");
		}else {
			ra.addAttribute("msg", "실패");
		}
		
		return "redirect:/topic/topicListAll";
	}
	
	@GetMapping("/topicDelete")
	public String topicDelete() {
		topicservice.delete(0);
		return "redirect:/topicListMe";
	}
	
	
}
