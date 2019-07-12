package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.CustomerService;
import com.cafe24.mysite.vo.CustomerVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	/**
	 * customer/update 화면으로 이동, no로 회원정보 가져옴
	 * @param no
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@RequestParam("no") Long no, Model model) {
		CustomerVo vo = customerService.get_by_no(no);
		model.addAttribute(vo);
		return "customer/update";
	}
}
