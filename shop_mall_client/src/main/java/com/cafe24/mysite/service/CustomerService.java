package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	String BASE_URL = "http://localhost:8081";

	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	
	/**
	 * Join(insert) Customer information into DB
	 * @param customervo
	 * @return true, false 
	 */
	public Boolean join(CustomerVo customervo) {	
		return customerProvider.insert_customer(customervo)==1;
	}
	
	
	/*
	 * 약관동의서(terms_of_use)
	 */	
	public JSONResult2<List<TermsOfUseVo>> get_terms_of_use_template() {
		JSONResultTermsOfUse terms_of_use_template_list= restTemplate.getForObject(BASE_URL + "/api/terms", JSONResultTermsOfUse.class);
//		JSONResult2<List<TermsOfUseVo>> terms_of_use_template_list = customerProvider.get_terms_of_use_template();
		return terms_of_use_template_list;
	}


	public CustomerVo authenticate(String email, String password) {
		
		return null;
	}
	
}
