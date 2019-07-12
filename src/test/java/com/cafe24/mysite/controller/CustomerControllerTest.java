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
import org.junit.Ignore;
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

import com.cafe24.mysite.vo.CategoryVo;
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
	                  .build();
	}

	   
	/**
	 * check_email_list_success_test
	 * @throws Exception
	 * :이메일 중복 확인(동일 이메일 존재하는 경우) parameter:{email:ksm5318@naver.com}, MockData:{email:ksm5318@naver.com}
	 */
	@Test
	public void check_email_list_success_test() throws Exception {

		// ## check_email, 동일 이메일 존재하는 경우 테스트
		ResultActions resultActions = 
		mockMvc.perform(get("/api/customer/checkemail?email={email}", "ksm5318@naver.com")
				.contentType(MediaType.APPLICATION_JSON));				
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("x"));
	}
	
	/**
	 * check_email_list_fail_test
	 * @throws Exception
	 * :이메일 중복 확인(동일 이메일 존재하지 않는 경우), parameter:{email:ksm5318}, MockData:{email:ksm5318@naver.com}
	 */
	@Test
	public void check_email_list_fail_test() throws Exception {

		ResultActions resultActions =
		mockMvc.perform(get("/api/customer/checkemail?email={email}", "ksm5318")
				.contentType(MediaType.APPLICATION_JSON));	

		// ## check_email 실패 테스트
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(true));
	}
	
	/**
	 *  join_success_test
	 *  @throws Exception
	 *  :회원가입, request body : {uservo:uservo}, 
	 *  정상적으로 가입완료 시 result : success, data : vo.getEmail(); 
	 */
	@Test
	public void join_success_test() throws Exception {		
		// ## join_test() 성공 테스트
		//success 
		CustomerVo vo1 = new CustomerVo(101L, "성공테스트", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo1)));				
		resultActions
		.andExpect(status().isOk()) 
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(vo1.getEmail()));
		
	}
	
	/**
	 * join_validate_fail_test
	 * :@Valid에 의한 회원가입 입력데이터 validation 
	 * @throws Exception
	 */
	@Test
	public void join_validate_fail_test() throws Exception {	
		// ## join_test() 실패테스트
		// 이름 형식 오류 
		CustomerVo vo2 = new CustomerVo(101L, "안ㅏㅈ", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions2 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo2)));				
		resultActions2
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("이름 형식이 맞지 않습니다."));
		
		// ## join_test() 실패테스트
		// 이름 길이 오류 
		CustomerVo vo3 = new CustomerVo(101L, "안", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions3 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo3)));				
		resultActions3
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("이름은 2~8자리 사이로 입력해주세요."));
		
		
		// ## join_test() 실패테스트
		// 이메일 형식 오류 
		CustomerVo vo4 = new CustomerVo(101L, "실패테스트", "EMAILTEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions4 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo4)));				
		resultActions4
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("이메일 형식이 올바르지 않습니다."));
		
		
		// ## join_test() 실패테스트
		// 비밀번호 형식 오류 
		CustomerVo vo5 = new CustomerVo(101L, "실패테스트", "EMAIL@TEST.COM", "PASSWORD11", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions5 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo5)));				
		resultActions5
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("비밀번호는 특수문자/문자/숫자 최소 8~16자리 이내 입력 바랍니다."));

		
		// ## join_test() 실패테스트
		// 비밀번호 길이 오류 
		CustomerVo vo6 = new CustomerVo(101L, "실패테스트", "EMAIL@TEST.COM", "PASSWOR", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions6 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo6)));				
		resultActions6
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("비밀번호는 특수문자/문자/숫자 최소 8~16자리 이내 입력 바랍니다."));
		
		
		// ## join_test() 실패테스트
		// 전화번호 형식 오류 
		CustomerVo vo7 = new CustomerVo(101L, "실패테스트", "EMAIL@TEST.COM", "PASSWORD1!", "0101234-1234", "M", 1L, "Y"); 
		ResultActions resultActions7 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo7)));				
		resultActions7
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("전화번호 형식이 올바르지 않습니다."));
	}
	

	
	
}
