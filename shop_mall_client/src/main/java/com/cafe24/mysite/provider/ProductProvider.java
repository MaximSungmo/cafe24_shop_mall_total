package com.cafe24.mysite.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.vo.ProductVo;

@Repository
public class ProductProvider {
	String BASE_URL = "http://localhost:8081";

	@Autowired
	RestTemplate restTemplate;
	
	public JSONResultProduct get_product_list(Long no) {
		// 전체 상품 가져오기 
		JSONResultProduct product_list = restTemplate.getForObject(BASE_URL+"/api/product/all/"+no, JSONResultProduct.class);		
		return product_list;
	}
	//DTOa
	public static class JSONResultProduct extends JSONResult2<List<ProductVo>>{
	}
}
