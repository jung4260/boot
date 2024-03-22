package com.coding404.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@GetMapping("/noticeDetail")
	public String detail() {
		return "/notice/noticeDetail";
	}
	
	@GetMapping("/noticeList")
	public String list() {
		return "/notice/noticeList";
	}
	
	@GetMapping("/noticeReg")
	public String reg() {
		return "/notice/noticeReg";
	}
	
}
