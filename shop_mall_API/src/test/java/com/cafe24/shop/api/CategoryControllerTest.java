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
import org.junit.Ignore;
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

import com.cafe24.shop.vo.CategoryVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class CategoryControllerTest {
	

	@Autowired
	SqlSession sqlSession;
	/*
	 * 임시 테스트 데이터
	 */
	List<CategoryVo> list = new ArrayList<CategoryVo>();
	CategoryVo vo1 = new CategoryVo(null, "1번카테고리", null);
	CategoryVo vo2 = new CategoryVo(null, "2번카테고리", null);
	CategoryVo vo3 = new CategoryVo(null, "3번카테고리", null);
	CategoryVo vo4 = new CategoryVo(null, "4번카테고리", null);
	public List<CategoryVo> testData(){
//		sqlSession.delete("category.deleteAll");
		
		//	Test용 데이터 생성(DB)
		list.add(vo1);		
		list.add(vo2);
		list.add(vo3);
		list.add(vo4);
		
		sqlSession.insert("category.insert", list.get(0));
		vo2.setParent_no(list.get(0).getNo());
		sqlSession.insert("category.insert", list.get(1));
		vo3.setParent_no(list.get(1).getNo());
		sqlSession.insert("category.insert", list.get(2));
		vo4.setParent_no(list.get(1).getNo());
		sqlSession.insert("category.insert", list.get(3));	
		return list;
	}
	
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	                  .alwaysDo(print())
	                  .build();
		list=testData();
		System.out.println(list);
	}
	
	/**
	 * add_category_test();
	 * @throws Exception
	 * :카테고리 추가하기 (성공테스트, 중복값으로 인한 실패테스트)
	 */
	@Test
	public void add_category_success_test() throws Exception {
		CategoryVo vo = new CategoryVo(null, "1번카테고리", null);
	
		// ## add_category() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(post("/api/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));				
	
		resultActions
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(true));	
	}
	
	@Test
	public void add_category_fail_test() throws Exception {
		CategoryVo vo = new CategoryVo(null, "오류 카테고리", 999999L);
		
		// ## add_category() 실패 테스트 - 상위카테고리 키 FK 참조 오류
		ResultActions resultActions =
			mockMvc.perform(post("/api/category")
					.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));	
			resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result").value("fail"))
			.andExpect(jsonPath("$.message").value("상위 카테고리 정보 오류가 발생하였습니다."));			
	}
	
	/**
	 * get_category_list_test() 
	 * @throws Exception
	 * :카테고리 목록 가져오기.
	 */
	@Test
	public void get_category_list_test() throws Exception {
		// ## get_category_list() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(get("/api/category")
				.contentType(MediaType.APPLICATION_JSON));				
	
		resultActions
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data[0].no").value(list.get(0).getNo()))	
		.andExpect(jsonPath("$.data[0].name").value(list.get(0).getName()))
		.andExpect(jsonPath("$.data[1].no").value(list.get(1).getNo()))	
		.andExpect(jsonPath("$.data[1].name").value(list.get(1).getName()))
		.andExpect(jsonPath("$.data[1].parent_no").value(list.get(1).getParent_no()))
		.andExpect(jsonPath("$.data[2].no").value(list.get(2).getNo()))	
		.andExpect(jsonPath("$.data[2].name").value(list.get(2).getName()))
		.andExpect(jsonPath("$.data[2].parent_no").value(list.get(2).getParent_no()))
		.andExpect(jsonPath("$.data[3].no").value(list.get(3).getNo()))	
		.andExpect(jsonPath("$.data[3].name").value(list.get(3).getName()))
		.andExpect(jsonPath("$.data[3].parent_no").value(list.get(3).getParent_no()));
	}
	
	/**
	 * update_category_success_test() 
	 * @throws Exception
	 * :카테고리 업데이트.
	 */
	@Test
	public void update_category_success_test() throws Exception {		
		// ## get_category_list() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(put("/api/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));				
	
		resultActions
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data.no").value(list.get(0).getNo()))
		.andExpect(jsonPath("$.data.name").value(list.get(0).getName()))
		.andExpect(jsonPath("$.data.parent_no").value(list.get(0).getParent_no()));
	}
	
	/**
	 * update_category_success_test() 
	 * @throws Exception
	 * :카테고리 업데이트.
	 */
	@Test
	public void update_category_fail_test() throws Exception {		
		list.get(0).setParent_no(999999L);
		// ## get_category_list() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(put("/api/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));				
	
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result").value("fail"))
		.andExpect(jsonPath("$.message").value("상위 카테고리 정보 오류가 발생하였습니다."));	
	}
	
	/**
	 * delete_category_success_test() 
	 * @throws Exception
	 * :카테고리 삭제.
	 */
	@Test
	public void delete_category_success_test() throws Exception {
		Long delete_category_no = list.get(0).getNo();
		// ## get_category_list() 성공 테스트
		ResultActions resultActions = 
		mockMvc.perform(delete("/api/category/"+delete_category_no)
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(list.get(0))));				
	
		resultActions
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(true));
	}
	
	
	
}
