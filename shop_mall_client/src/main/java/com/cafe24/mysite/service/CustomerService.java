package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.CustomerProvider;
import com.cafe24.mysite.provider.CustomerProvider.JSONResultTermsOfUse;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.TermsOfUseVo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerProvider customerProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
//	String BASE_URL = "http://localhost:8081";

//	@Autowired
//	private OAuth2RestTemplate restTemplate;
	
	//네이버 
	//LJUphYN28JQqObTwLTuY
	//fDgi8Ycloj  sect
	
	/**
	 * Join(insert) Customer information into DB
	 * @param customervo
	 * @return true, false 
	 */
	public JSONResult2<Boolean> join(CustomerVo customervo) {	
		//BCrypt로 암호화하여 데이터 넣기
		customervo.setPassword(passwordEncoder.encode(customervo.getPassword()));
		return customerProvider.insert_customer(customervo);
	}
	
	
	/*
	 * 약관동의서(terms_of_use)
	 */	
	public JSONResult2<List<TermsOfUseVo>> get_terms_of_use_template() {
		JSONResult2<List<TermsOfUseVo>> terms_of_use_template_list = customerProvider.get_terms_of_use_template();
		return terms_of_use_template_list;
	}


	public CustomerVo authenticate(String email, String password) {
		
		return null;
	}
	
	
	
}
