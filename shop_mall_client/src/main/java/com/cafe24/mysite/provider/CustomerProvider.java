package com.cafe24.mysite.provider;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.TermsOfUseVo;

@Repository
public class CustomerProvider {
	String BASE_URL = "http://localhost:8081";
	@Autowired
	private RestTemplate restTemplate;
	
	
	public JSONResultJoin insert_customer(CustomerVo customervo) {
		// Post로 회원가입 정보 입력하기		
		
		System.out.println(customervo);
		JSONResultJoin result = restTemplate.postForObject(BASE_URL + "/api/customer", customervo, JSONResultJoin.class);
		return result;
	}

	//약관동의서 
	public JSONResult2<List<TermsOfUseVo>> get_terms_of_use_template() {
		RestTemplate restTemplate = new RestTemplate();
		JSONResultTermsOfUse terms_of_use_template_list= restTemplate.getForObject(BASE_URL + "/api/terms", JSONResultTermsOfUse.class);
		return terms_of_use_template_list;
	}
	

	public JSONResultCustomer get_customer_list() {
		JSONResultCustomer customer_list = restTemplate.getForObject(BASE_URL+"/api/customer/get", JSONResultCustomer.class);
		return customer_list;
	}
	
	public CustomerVo get_by_email(String email) {
		CustomerVo customer_list = restTemplate.postForObject(BASE_URL+"/api/customer/get_user?email="+email, email, CustomerVo.class);
		return customer_list;
	}
	
	
	public static class JSONResultTermsOfUse extends JSONResult2<List<TermsOfUseVo>> {}
	public static class JSONResultCustomer extends JSONResult2<List<CustomerVo>> {}
	public static class JSONResultJoin extends JSONResult2<Boolean> {}
	
}
