package com.cafe24.mysite.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping( "/product" )
public class ProductController {
	@Autowired
	private ProductService productService;
	
	/**
	 *  CategoryNo를 전달받음, 검색어 있을 시 kwd
	 * @param no
	 * @param kwd
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"","/{no}"}, method = RequestMethod.GET)
	public String index(@PathVariable Optional<Long> no, 
			@RequestParam String kwd,
			Model model ){
		List<GuestbookVo> product_list = productService.get(no, kwd);
		model.addAttribute( "product_list", product_list );
		return "product/list"; 
	}
	
	
	
}
	