package com.cafe24.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.ProductVo;

@Controller
@RequestMapping( "/product" )
public class ProductController {
	//API BASE URL
	String BASE_URL = "http://localhost:8081";

	@Autowired
	ProductService productService;
	
	@RequestMapping( {"/{product_no}/detail"} )
	public String product_detail(
			@PathVariable Long product_no
			, @ModelAttribute CategoryVo category_list
			, Model model) {
		JSONResult2<List<ProductVo>> product_list =  productService.get_product_detail(product_no);
		model.addAttribute("product_list", product_list.getData());
System.out.println(category_list);
		return "product/product_detail";
	}
	
	
		
		
		
		
	
		
}
	