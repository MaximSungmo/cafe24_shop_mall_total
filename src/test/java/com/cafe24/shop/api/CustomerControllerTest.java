package com.cafe24.shop.api;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.controller.api.CustomerController;
import com.cafe24.shop.vo.CustomerVo;
import com.cafe24.shop.vo.TermsOfUseVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class CustomerControllerTest {
	
	@Autowired 
	private CustomerController customerController;
	@Autowired
	private SqlSession sqlSession;
	 
	
//	Test용 데이터 생성(DB)
	List<TermsOfUseVo> termsofuse_list = new ArrayList<TermsOfUseVo>();
	// 조회 및 업데이트를 위한 사전 데이터 삽입
		public List<TermsOfUseVo> test_data_terms(){
			TermsOfUseVo term_vo1 = new TermsOfUseVo(null, "약관동의서1","약관동의내용1", "Y", "Y", null, null);
			TermsOfUseVo term_vo2 = new TermsOfUseVo(null, "약관동의서2","약관동의내용2", "Y", "Y", null, null);
			termsofuse_list.add(term_vo1);
			termsofuse_list.add(term_vo2);
			for(int i=0; i<termsofuse_list.size(); i++) {
				sqlSession.insert("termsofuse.insert", termsofuse_list.get(i));
			}		
			return termsofuse_list;
		}
		
	List<CustomerVo> list = new ArrayList<CustomerVo>();
	public List<CustomerVo> test_data(){
		termsofuse_list = test_data_terms();		
		Long termsofuse_vo1_no = termsofuse_list.get(0).getNo();
		Long termsofuse_vo2_no = termsofuse_list.get(0).getNo();
		CustomerVo vo1 = new CustomerVo(null, "성공테스트", "ksm5318@naver.com", "PASSWORD1!", "010-1234-1234", "M", termsofuse_vo1_no, "Y");
		CustomerVo vo2 = new CustomerVo(null, "성공테스트2", "EMAIL2@TEST.COM", "PASSWORD12!", "010-2222-2222", "M", termsofuse_vo2_no, "Y");
		list.add(vo1);
		list.add(vo2);

		for(int i=0; i<list.size(); i++) {
			sqlSession.insert("customer.insert", list.get(i));
		}
		
		
		return list;
	}
	
	
	
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
		list = test_data();
		termsofuse_list = test_data_terms();
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
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(true));
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
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("Available_email"));
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
		CustomerVo join_vo = list.get(0);
		ResultActions resultActions = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(join_vo)));				
		resultActions
		.andExpect(status().isOk()) 
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(true));
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
		
		// ## join_test() 실패테스트
		// 성별 형식 오류 
		CustomerVo vo8 = new CustomerVo(101L, "실패테스트", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "s", 1L, "Y"); 
		ResultActions resultActions8 = 
		mockMvc.perform(post("/api/customer/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo8)));				
		resultActions8
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("Invalid Gender"));
	}
	

	
	/**
	 * update_success_test
	 * : 회원정보 업데이트- 성공테스트
	 * @throws Exception
	 */
	@Test
	public void update_success_test() throws Exception {	
		// ## update_success_test() 업데이트 성공테스트
		
		list.get(0).setName("변경이름");
		list.get(0).setPhone("010-5000-4949");

		ResultActions resultActions = 
		mockMvc.perform(put("/api/customer/"+list.get(0).getNo())
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));	
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data.no").value(list.get(0).getNo()))
		.andExpect(jsonPath("$.data.name").value("변경이름"))
		.andExpect(jsonPath("$.data.password").value(""))
		.andExpect(jsonPath("$.data.phone").value("010-5000-4949"))
		.andExpect(jsonPath("$.data.email").value(list.get(0).getEmail()));		
	}
	
	/**
	 * update_fail_test
	 * :업데이트 실패, 이름 형식 오류 
	 * @throws Exception
	 */
	@Test
	public void update_fail_test() throws Exception {	
		// ## update_faill_test() 이름 형식 오류로 인한  업데이트 실패테스트
		CustomerVo vo2 = new CustomerVo(101L, "안ㅏㅈ", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y"); 
		ResultActions resultActions2 = 
		mockMvc.perform(put("/api/customer/"+vo2.getNo())
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo2)));				
		resultActions2
//		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("이름 형식이 맞지 않습니다."));	
	}
	
	/**
	 * delete_success_test
	 * :업데이트 실패, 이름 형식 오류 , USE_FL Y->N
	 * @throws Exception
	 */
	@Test
	public void delete_success_test() throws Exception {	
		// ## delete_success_test() 업데이트 성공테스트
		ResultActions resultActions = 
		mockMvc.perform(delete("/api/customer/"+list.get(0).getNo())
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0).getNo())));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(true));	
	}
	
	/**
	 * login_success_test()
	 * :로그인 성공
	 * @throws Exception
	 */
	@Test
	public void login_success_test() throws Exception {	
		// ## login_success_test() 업데이트 성공테스트
		ResultActions resultActions = 
		mockMvc.perform(post("/api/customer/login")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"));
	}
	
	/**
	 * login_fail_test()
	 * :로그인 실패 
	 * @throws Exception
	 */
	@Test
	public void login_fail_test() throws Exception {	
		// ## login_fail_test() 업데이트 성공테스트
		// 패스워드 형식 오류 
		CustomerVo vo1 = new CustomerVo("EMAIL@TEST.COM", "PASSWORD"); 
		ResultActions resultActions = 
		mockMvc.perform(post("/api/customer/login")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo1)));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(vo1.getNo()));	
		
		// ## login_fail_test() 업데이트 성공테스트
		// 이메일 형식 오류 
		CustomerVo vo2 = new CustomerVo("EMAILTEST.COM", "PASSWORD"); 
		ResultActions resultActions2 = 
		mockMvc.perform(post("/api/customer/login")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo2)));				
		resultActions2
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("이메일 형식이 올바르지 않습니다."));	
	}
	
	
	
	
	
	
}
