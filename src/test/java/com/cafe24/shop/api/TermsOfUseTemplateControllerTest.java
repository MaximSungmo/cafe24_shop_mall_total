package com.cafe24.shop.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.vo.TermsOfUseVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TermsOfUseTemplateControllerTest {
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
	
	/*
	 * 임시 테스트 데이터
	 */
	public List<TermsOfUseVo> testData(){
		//	Test용 데이터 생성(DB)
		TermsOfUseVo vo = new TermsOfUseVo(1L, "약관동의서1","약관동의내용1", "Y", "Y", "2019-07-13 00:00:00", "");
		TermsOfUseVo vo1 = new TermsOfUseVo(2L, "약관동의서2","약관동의내용2", "Y", "Y", "2019-07-13 00:00:00", "");
		List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
		list.add(vo);
		list.add(vo1);
		return list;
	}
	
	/*
	 * 회원약관동의서 관리 
	 */
	
	/**
	 * 회원약관동의서 생성
	 * @throws Exception
	 */
	@Test
	public void add_terms_of_use_template_success_test() throws Exception {
		//test data 임시 주입 및 비교 
		List<TermsOfUseVo> list = testData();
		//list.get(0)
		//TermsOfUseVo vo = new TermsOfUseVo(1L, "약관동의서1","약관동의내용1", "Y", "Y", "2019-07-13 00:00:00", "");

		ResultActions resultActions = 
		mockMvc.perform(post("/api/terms")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(list.get(0).getNo()));	
	}
	
	/**
	 * 회원약관동의서 조회
	 * @throws Exception
	 */
	@Test
	public void get_terms_of_use_template_list_success_test() throws Exception {
		//test data 임시 주입 및 비교 
		List<TermsOfUseVo> list = testData();

		
		ResultActions resultActions = 
		mockMvc.perform(get("/api/terms")
				.contentType(MediaType.APPLICATION_JSON));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"));
		
		for(int i=0; i<list.size(); i++) {
			resultActions
			.andExpect(jsonPath("$.data["+i+"].no").value(list.get(i).getNo()))
			.andExpect(jsonPath("$.data["+i+"].title").value(list.get(i).getTitle()))
			.andExpect(jsonPath("$.data["+i+"].content").value(list.get(i).getContent()));
		}
	}
	
	/**
	 * 회원약관동의서 업데이트
	 * @throws Exception
	 */
	@Test
	public void update_terms_of_use_template_success_test() throws Exception {
		//test data 임시 주입 및 비교 
		List<TermsOfUseVo> list = testData();
		//list.get(0)
		//TermsOfUseVo vo = new TermsOfUseVo(1L, "약관동의서1","약관동의내용1", "Y", "Y", "2019-07-13 00:00:00", "");

		ResultActions resultActions = 
		mockMvc.perform(put("/api/terms")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(list.get(0).getNo()));	
	}
	
	/**
	 * 회원약관동의서 삭제(use_fl = 'N')
	 * @throws Exception
	 */
	@Test
	public void delete_terms_of_use_template_success_test() throws Exception {
		//test data 임시 주입 및 비교 
		List<TermsOfUseVo> list = testData();
		//list.get(0)
		//TermsOfUseVo vo = new TermsOfUseVo(1L, "약관동의서1","약관동의내용1", "Y", "Y", "2019-07-13 00:00:00", "");

		ResultActions resultActions = 
		mockMvc.perform(delete("/api/terms/"+list.get(0).getNo())
				.contentType(MediaType.APPLICATION_JSON));				
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(list.get(0).getNo()));	
	}
	
	
}

