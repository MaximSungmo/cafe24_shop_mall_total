package com.cafe24.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.CartVo;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.ProductVo;

@Controller
@RequestMapping("/product")
public class ProductController {
	// API BASE URL
	String BASE_URL = "http://localhost:8081";

	@Autowired
	ProductService productService;

	@RequestMapping({ "/{product_no}/detail" })
	public String product_detail(@PathVariable Long product_no, Model model) {

		Map<String, JSONResult2<?>> map = productService.get_product_detail(product_no);
		JSONResult2<CategoryVo> category_list = (JSONResult2<CategoryVo>) map.get("category_list");
		JSONResult2<ProductVo> product_list = (JSONResult2<ProductVo>) map.get("product_list");
		
		// 카테고리 목록 가져오기 
		model.addAttribute("category_list", category_list.getData());
				
		// 전체 상품 가져오기 
		model.addAttribute("product_list", product_list.getData());
		return "product/product_detail";
	}
	
	
	@RequestMapping(value= {"/cart/{customer_no}"}, method=RequestMethod.POST)
	public String cart_add(@PathVariable Long customer_no
			, @ModelAttribute(value="cartvo") CartVo cartvo
			, Model model) {
		
		System.out.println(cartvo);
		
			
		
		return "redirect:/";
		
	}

}
