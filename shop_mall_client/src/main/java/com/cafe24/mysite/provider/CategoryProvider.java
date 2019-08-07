package com.cafe24.mysite.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.vo.CategoryVo;

@Repository
public class CategoryProvider {
	String BASE_URL = "http://localhost:8081";

//	@Autowired
//	RestTemplate restTemplate;


	public JSONResultCategory get_category_list() {
		// 카테고리 목록 가져오기
		RestTemplate restTemplate = new RestTemplate();
		JSONResultCategory category_list = restTemplate.getForObject(BASE_URL + "/api/category", JSONResultCategory.class);
		return category_list;
	}
	
	
	//DTO
	public static class JSONResultCategory extends JSONResult2<List<CategoryVo>>{
	}
	
}
