package com.simple.basic.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemoVO;
import com.simple.basic.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	MemoService memoService;
	
	@GetMapping("/memowrite")
	public String memoWrite() {	
		return "/memo/memowrite";
	}
	
	@PostMapping("/memoForm")
	public String memoForm(MemoVO vo) {
		memoService.insertMemo(vo);
		return"redirect:/memo/memolist";
	}
	
	@GetMapping("/memolist")
	public String memoList(Model model) {
		
		model.addAttribute("list", memoService.getList() );
		
		return "/memo/memolist";
	}
	
}
