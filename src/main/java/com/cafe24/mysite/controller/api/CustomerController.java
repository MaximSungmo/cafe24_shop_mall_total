package com.cafe24.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.vo.CustomerVo;

@Controller("customerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {
	
//	@Autowired
//	private CustomerService customerService;
	
	@ResponseBody
	@RequestMapping(value="/checkemail", method = RequestMethod.GET)
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
//		String checked_email = customerService.existEmail(email);
	
		CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
		String checked_email = vo.getEmail();
		if(!(email.equals(checked_email))) {
			return JSONResult.fail("x");
		};
		return JSONResult.success(checked_email);
	}
	
}
