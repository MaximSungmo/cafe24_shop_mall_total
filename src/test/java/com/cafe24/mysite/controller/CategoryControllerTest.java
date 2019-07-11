package com.cafe24.mysite.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CategoryControllerTest {
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
	
	
	@Test
	public void get_category_list_test() throws Exception {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		list.add(new CategoryVo(1L, "1번카테고리", null));
		list.add(new CategoryVo(2L, "2번카테고리", 1L));
		list.add(new CategoryVo(3L, "3번카테고리", 2L));
		list.add(new CategoryVo(4L, "4번카테고리", 2L));	
		
		// ## get_category_list() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(get("/api/category")
				.contentType(MediaType.APPLICATION_JSON));				
	
		resultActions
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data[0].no").value("1"))	
		.andExpect(jsonPath("$.data[0].name").value("1번카테고리"))
//		.andExpect(jsonPath("$.data[0].name").value(null))	
		.andExpect(jsonPath("$.data[1].no").value("2"))	
		.andExpect(jsonPath("$.data[1].name").value("2번카테고리"))
		.andExpect(jsonPath("$.data[1].parent_no").value("1"))
		.andExpect(jsonPath("$.data[2].no").value("3"))	
		.andExpect(jsonPath("$.data[2].name").value("3번카테고리"))
		.andExpect(jsonPath("$.data[2].parent_no").value("2"))
		.andExpect(jsonPath("$.data[3].no").value("4"))	
		.andExpect(jsonPath("$.data[3].name").value("4번카테고리"))
		.andExpect(jsonPath("$.data[3].parent_no").value("2"));
	}
	
	/**
	 * add_category_test();
	 */		
	@Test
	public void add_category_test() throws Exception {
		CategoryVo vo = new CategoryVo(1L, "1번카테고리", null);
		CategoryVo vo1 = new CategoryVo(5L, "5번카테고리", null);
		CategoryVo vo2 = new CategoryVo(6L, "6번카테고리", 5L);
		
		// ## add_category() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(post("/api/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo1)));				
	
		resultActions
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data.no").value("5"))	
		.andExpect(jsonPath("$.data.name").value("5번카테고리"));
//		.andExpect(jsonPath("$.data.parent_no").value(null))
		
		ResultActions resultActions2 = 
		mockMvc.perform(post("/api/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo2)));	
		resultActions2
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data.no").value("6"))	
		.andExpect(jsonPath("$.data.name").value("6번카테고리"))
		.andExpect(jsonPath("$.data.parent_no").value(5L));
		
		// ## add_category() 실패 테스트
		ResultActions resultActions3 =
		mockMvc.perform(post("/api/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));	
		resultActions3
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("중복값"));	
//		.andExpect(jsonPath("$.data").value(null));
//		.andExpect(jsonPath("$.data.parent_no").value(5L));

		
		
	}
	
}
