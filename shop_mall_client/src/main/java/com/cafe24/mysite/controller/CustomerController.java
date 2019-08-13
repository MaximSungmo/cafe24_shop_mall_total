package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.CustomerService;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.TermsOfUseVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	// 회원가입 화면으로 이동 
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String joinform(@ModelAttribute("customervo") CustomerVo customervo, 
			BindingResult bindingResult,
			Model model){
		//약관 동의서 보여주기
		JSONResult2<List<TermsOfUseVo>> terms_list = customerService.get_terms_of_use_template();
		model.addAttribute("terms_of_use_template_list",terms_list.getData());		
		return "customer/join";
	}
	
	// 회원가입 정보 보내기
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(
			@ModelAttribute("customervo") @Valid CustomerVo customervo,
			BindingResult bindResult,
			Model model){
		//회원 가입 정보 받아서 validation
		if (bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			// Validation에 걸린 항목에 대하여 view에서 안내하기
			model.addAllAttributes(bindResult.getModel());
			System.out.println(customervo);
			// 실패 시 입력했던 모든 정보는 보존될 수 있도록 함(재작성하지 않도록)
			// 실패 시 약관동의서 재전송, 기본 입력 정보 유지 
			model.addAttribute("terms_of_use_template_list", customervo.getTermsofusevolist());
			return "/customer/join";
		}
		
		// 회원정보로 join 요청 api 보내기 
		customervo.setPassword(customervo.getPassword());
		JSONResult2<Boolean> result = customerService.join(customervo);
		//가입 성공 시 success, 실패 시 fail
		if("fail".equals(result.getResult())) {
			// 실패 시 입력했던 모든 정보는 보존될 수 있도록 함(재작성하지 않도록)
			// 실패 시 약관동의서 재전송, 기본 입력 정보 유지 
			model.addAttribute("terms_of_use_template_list", customervo.getTermsofusevolist());
			return "/customer/join";
		}
		// 회원 가입 성공 시 페이지 이동
		model.addAttribute("result", result);
		return "redirect:/customer/join_success";
	}
	
	// 회원가입 완료 후 success화면으로 이동 
	@RequestMapping(value="/join_success", method = RequestMethod.GET)
	public String join_success(Model model){
		return "customer/join_success";
	}
	
	// 로그인 화면으로 이동 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="result", defaultValue = "", required = false) String result,Model model){
		model.addAttribute("result", result);
		return "customer/login";
	}
	
	
	@RequestMapping(value="loginPostNaver", method=RequestMethod.GET)
	public String loginPOSTNaver(HttpServletRequest request) {
		
		String storedState = (String) request.getSession().getAttribute("state");	
		
		System.out.println(storedState+"::::");
		return "customer/loginPostNaver";
	}
	

	
	
	
//	
//	@RequestMapping(value="/update", method = RequestMethod.GET)
//	public String customer_update(Model model){
//		
//		//약관 동의서 보여주기
//		return "customer/update";
//	}
	
	
	
	
	
	
}
