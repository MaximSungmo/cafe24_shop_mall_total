package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.vo.CustomerVo;

@Controller
public class MainController {

	@RequestMapping( {"/", "/main"} )
	public String main() {
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		
		
		return "<h1>안녕하세요!</h1>";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public CustomerVo hello2() {
		CustomerVo vo= new CustomerVo();
		vo.setNo(10L);
		vo.setName("안대혁");
		vo.setEmail("kickscar@gmail.com");
		
		return vo;
	}
}
