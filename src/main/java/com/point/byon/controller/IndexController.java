package com.point.byon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.point.byon.dto.OrdersDTO;
import com.point.byon.dto.UsersDTO;
import com.point.byon.service.IndexService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@Autowired
	IndexService indexService;
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		
		List<String> errorMessage = null;
		errorMessage = indexService.prepare();
		model.addAttribute("errorMessage", errorMessage);
		
		UsersDTO user = indexService.getUser();
		session.setAttribute("userId", user.getId());
		
		OrdersDTO order = indexService.getOrder();
		model.addAttribute("order", order);
		
		return "/index";
	}

}
