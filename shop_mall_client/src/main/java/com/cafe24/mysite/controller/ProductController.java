package com.cafe24.mysite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.CartVo;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.OrdersDetailVo;
import com.cafe24.mysite.vo.OrdersVo;
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
			, RedirectAttributes redirectAttr
			, Model model) {
				
		Map<String, JSONResult2<?>> map = productService.insert_cart_list(customer_no, cartvo);
		JSONResult2<CategoryVo> category_list = (JSONResult2<CategoryVo>) map.get("category_list");
		JSONResult2<Integer> insert_cart_list_result_no = (JSONResult2<Integer>) map.get("insert_cart_list_result_no");
		
		// 장바구니에 들어간 갯수 확인하기 
		model.addAttribute("insert_list_result_no", insert_cart_list_result_no);
		
		// 카테고리 목록 가져오기 
		model.addAttribute("category_list", category_list.getData());
		redirectAttr.addFlashAttribute("category_list", category_list.getData());
		return "redirect:/product/cart/"+customer_no;
	}
	
	@RequestMapping(value= {"/cart/{customer_no}"}, method=RequestMethod.GET)
	public String cart_list(@PathVariable Long customer_no
			, HttpServletRequest request
			, Model model) {
		
		// 카트 정보 가져오기
		List<CartVo> cart_vo_list = productService.get_cart_list(customer_no);
		
		model.addAttribute("cart_vo_list", cart_vo_list);
		
		// 카테고리 정보 가져오기 
		JSONResult2<List<CategoryVo>> category_list = productService.get_category_list();
		model.addAttribute("category_list", category_list.getData());
		return "product/cart_list";
	}
	
	@RequestMapping(value= {"/order/{customer_no}"}, method=RequestMethod.POST)
	public String order_check(@PathVariable Long customer_no
			, @RequestParam Map<String, Object> cartvo
			, @RequestParam Long total_sum
			, RedirectAttributes redirectAttr
			, Model model) {
		// 카테고리 정보 가져오기 
		JSONResult2<List<CategoryVo>> category_list = productService.get_category_list();
		model.addAttribute("category_list", category_list.getData());
		redirectAttr.addFlashAttribute("total_sum", total_sum);
		
		return "redirect:/product/order/"+customer_no;
	}
	
	@RequestMapping(value= {"/order/{customer_no}"}, method=RequestMethod.GET)
	public String order_list(@PathVariable Long customer_no
			, HttpServletRequest request 
			, Model model) {
		
		// 카트 정보 가져오기
		List<CartVo> cart_vo_list = productService.get_cart_list(customer_no);
		model.addAttribute("cart_vo_list", cart_vo_list);
				
		// 카테고리 정보 가져오기 
		JSONResult2<List<CategoryVo>> category_list = productService.get_category_list();
		model.addAttribute("category_list", category_list.getData());
		
		return "orders/order_list";
	}

	@RequestMapping(value= {"/order/{customer_no}/add"}, method=RequestMethod.POST)
	public String order_add(@PathVariable Long customer_no
			, @ModelAttribute OrdersVo ordersvo
			, @ModelAttribute("order_detail_vo") OrdersDetailVo order_detail_vo
			, Model model) {

		JSONResult2<Boolean> result = productService.insert_order(ordersvo, order_detail_vo.getOrder_detail_list());
		
		// 카테고리 정보 가져오기 
		JSONResult2<List<CategoryVo>> category_list = productService.get_category_list();
		model.addAttribute("category_list", category_list.getData());
		
		System.out.println(ordersvo);
		System.out.println(order_detail_vo);
		System.out.println(result);
		return "redirect:/";
	}
	

	

}
