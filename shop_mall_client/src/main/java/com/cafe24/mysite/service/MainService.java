package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.CategoryProvider;
import com.cafe24.mysite.provider.ProductProvider;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.ProductVo;

@Service
public class MainService {
	// API BASE URL
	String BASE_URL = "http://localhost:8081";

	
	@Autowired
	ProductProvider productProvider;
	
	@Autowired
	CategoryProvider categoryProvider;
	

	public Map<String, JSONResult2<?>> main_page(Long category_no) {
		
		// 카테고리 목록 가져오기
		JSONResult2<List<CategoryVo>> category_list = categoryProvider.get_category_list();
		// 전체 상품 가져오기
		Long no = category_no;
		JSONResult2<List<ProductVo>> product_list = productProvider.get_product_list(no);
		
		Map<String, JSONResult2<?>> map = new HashMap<String, JSONResult2<?>>();
		map.put("category_list", category_list);
		map.put("product_list", product_list);
		return map;
	}



	
}
