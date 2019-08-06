package com.cafe24.mysite.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
	RestTemplate restTemplate;
	
	public Long insert_customer(CustomerVo customervo) {
		// Post로 회원가입 정보 입력하기		
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//		map.add("name", customervo.getName());
//		map.add("email", customervo.getEmail());
//		map.add("password", customervo.getPassword());
//		
//		Gson gson = new Gson();
//		gson.toJson(customervo);
//		
//		System.out.println("gson변경 후 : "+gson.toJson(customervo));
//		System.out.println(gson.toJson(customervo).getClass());
		//JSON 형태로 보낼 것으로 알림
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(customervo, headers);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("email", "first.last@example.com");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//		ResponseEntity<String> response = restTemplate.postForEntity( BASE_URL + "/api/customer", request , String.class );
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		System.out.println(customervo);
		JSONResult2<Long> result = restTemplate.postForObject(BASE_URL + "/api/customer", customervo, JSONResultJoin.class);
//		System.out.println(result);
		return 1L;
	}

	//약관동의서 
	public JSONResult2<List<TermsOfUseVo>> get_terms_of_use_template() {
		JSONResultTermsOfUse terms_of_use_template_list= restTemplate.getForObject(BASE_URL + "/api/terms", JSONResultTermsOfUse.class);
		return terms_of_use_template_list;
	}
	
	
	

	public JSONResultCustomer get_customer_list() {
		JSONResultCustomer customer_list = restTemplate.getForObject(BASE_URL+"/api/customer/get", JSONResultCustomer.class);
		return customer_list;
	}
	
	
	public static class JSONResultTermsOfUse extends JSONResult2<List<TermsOfUseVo>> {}
	public static class JSONResultCustomer extends JSONResult2<List<CustomerVo>> {}
	public static class JSONResultJoin extends JSONResult2<Long> {}
}
