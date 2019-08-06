package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.CustomerService;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.TermsOfUseVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String joinform(Model model){
		//약관 동의서 보여주기
		JSONResult2<List<TermsOfUseVo>> terms_list = customerService.get_terms_of_use_template();
		model.addAttribute("terms_of_use_template_list",terms_list.getData());		
		return "customer/join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(
			@ModelAttribute CustomerVo customervo,
			Model model){
		//회원 가입 정보 받아오기 
		//가입 성공 시 true, 실패 시 false
		Boolean result = customerService.join(customervo);
		model.addAttribute("result", result);
		return "customer/joinsuccess";
	}
//	
//	@RequestMapping(value="/update", method = RequestMethod.GET)
//	public String customer_update(Model model){
//		
//		//약관 동의서 보여주기
//		return "customer/update";
//	}
	
	
	
	
	
	
}
