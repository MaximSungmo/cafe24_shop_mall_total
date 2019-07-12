package com.cafe24.mysite.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Content;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mysite.vo.CustomerVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerControllerTest {
	/**
	 * 기본 mockMvc 설정 및 applicationContext 주입 
	 */
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	                  .alwaysDo(print())
	                  .alwaysExpect(status().isOk())
	                  .build();
	}

	   
	/**
	 * check_email_list_success_test
	 * @throws Exception
	 * :이메일 중복 확인(성공) parameter:{email:ksm5318@naver.com}, MockData:{email:ksm5318@naver.com}
	 */
	@Test
	public void check_email_list_success_test() throws Exception {

		// ## check_email 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(get("/api/customer/checkemail?email={email}", "ksm5318@naver.com")
				.contentType(MediaType.APPLICATION_JSON));				
		
		resultActions
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value("ksm5318@naver.com"));		
	}
	
	/**
	 * check_email_list_fail_test
	 * @throws Exception
	 * :이메일 중복 확인(실패), parameter:{email:ksm5318}, MockData:{email:ksm5318@naver.com}
	 */
	@Test
	public void check_email_list_fail_test() throws Exception {

		ResultActions resultActions =
		mockMvc.perform(get("/api/customer/checkemail?email={email}", "ksm5318")
				.contentType(MediaType.APPLICATION_JSON));	

		// ## check_email 실패 테스트
		resultActions
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("x"));
	}
	

	
}
