package com.cafe24.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.service.MainService;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;

//@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
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
	
	@RequestMapping({"/product"})
	public String product_main(Model model) {		
		return "admin/product-manage";
	}
	
	@RequestMapping({"/cart"})
	public String cart_main(Model model) {		
		return "admin/cart-manage";
	}
	
	

}
