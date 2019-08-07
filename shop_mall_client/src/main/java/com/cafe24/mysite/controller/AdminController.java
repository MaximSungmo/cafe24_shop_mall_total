package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.ProductVo;

//@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	
//	@RequestMapping({"", "/main"})
//	public String main() {
//		return "admin/main";
//	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	/*
	 * 시작하기
	 */
	@RequestMapping({"", "/main"})
	public String main() {
		return "admin/index";
	}
	
	@RequestMapping({"/category"})
	public String category_main(Model model) {		
		JSONResult2<List<CategoryVo>> category_list = adminService.get_category();
		model.addAttribute("category_list",category_list.getData());
		return "admin/category-manage";
	}
	
	/*
	 * 회원 관련
	 */
	@RequestMapping({"/customer"})
	public String customer_main(Model model) {	
		JSONResult2<List<CustomerVo>> customer_list = adminService.get_customer();
		model.addAttribute("customer_list",customer_list.getData());
		return "admin/customer-manage";
	}
	
	
	/*
	 * 상품 관련
	 */
	@RequestMapping({"/product"})
	public String product_main(
			@RequestParam(value="category_no", required = false, defaultValue = "0") Long category_no
			, Model model) {
		JSONResult2<List<ProductVo>> product_list = productService.get_product_list_by_categoryno(category_no);
		model.addAttribute("product_list", product_list.getData());
		return "admin/product-manage";
	}
	
	
	
	
	
	/*
	 * 장바구니 관련
	 */
	@RequestMapping({"/cart"})
	public String cart_main(Model model) {		
		return "admin/cart-manage";
	}
	
	

}
