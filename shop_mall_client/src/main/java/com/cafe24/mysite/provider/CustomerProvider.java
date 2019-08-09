package com.cafe24.mysite.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.vo.CategoryVo;
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
		JSONResultCustomer_vo customer_vo = restTemplate.postForObject(BASE_URL+"/api/customer/get_user?email="+email, email, JSONResultCustomer_vo.class);
		return customer_vo.getData();
	}
	
	
	// 카테고리 넣기
	public JSONResult2<Boolean> insert_category(CategoryVo categoryvo) {
		return restTemplate.postForObject(BASE_URL+"/api/category", categoryvo, JSONResultBoolean.class);
	}
	
	public static class JSONResultTermsOfUse extends JSONResult2<List<TermsOfUseVo>> {}
	public static class JSONResultCustomer extends JSONResult2<List<CustomerVo>> {}
	public static class JSONResultJoin extends JSONResult2<Boolean> {}
	public static class JSONResultCustomer_vo extends JSONResult2<CustomerVo> {}
	public static class JSONResultBoolean extends JSONResult2<Boolean> {}

	
	
}
