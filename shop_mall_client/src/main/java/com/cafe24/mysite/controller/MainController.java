package com.cafe24.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.MainService;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.ProductVo;

@Controller
public class MainController {
	//API BASE URL
	String BASE_URL = "http://localhost:8081";
	
	
	@Autowired
	MainService mainService;
	
	
	@RequestMapping( {"/", "/main"} )
	public String main(Model model) {

		
		Map<String, JSONResult2<?>> map = mainService.main_page();
		JSONResult2<CategoryVo> category_list = (JSONResult2<CategoryVo>) map.get("category_list");
		JSONResult2<ProductVo> product_list = (JSONResult2<ProductVo>) map.get("product_list");
		
		System.out.println(product_list);
		// 카테고리 목록 가져오기 
		model.addAttribute("category_list", category_list.getData());
				
		// 전체 상품 가져오기 
		model.addAttribute("product_list", product_list.getData());
		return "main/index";
	}
	
	@RequestMapping( {"/test"} )
	public String test(Model model) {

		return "main/index";
	}
	
	
	
	
	
}
