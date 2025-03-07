package com.point.byon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/prepare")
	public String prepare(Model model) {
		model.addAttribute("message", "데이타 입력 성공");
		return "/index";
	}
	
}
