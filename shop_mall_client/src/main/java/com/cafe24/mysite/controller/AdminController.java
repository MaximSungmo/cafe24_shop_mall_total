package com.cafe24.mysite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.service.AdminService;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.OrdersVo;
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
	
	/*
	 * 카테고리 관련
	 */
	//카테고리 메인화면 
	@RequestMapping(value={"/category"}, method = RequestMethod.GET)
	public String category_main(@ModelAttribute("categoryvo") CategoryVo categoryvo, Model model) {		
		JSONResult2<List<CategoryVo>> category_list = adminService.get_category();
		model.addAttribute("category_list",category_list.getData());
		return "admin/category-manage";
	}
	
	@RequestMapping(value={"/category/add"}, method=RequestMethod.POST)
	public String category_add(@ModelAttribute("categoryvo") CategoryVo categoryvo, Model model) {		
		adminService.insert_category(categoryvo);
		return "redirect:/admin/category";
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
	// 상품 관리 메인 (조회)
	@RequestMapping(value={"/product"}, method=RequestMethod.GET)
	public String product_main(
			@RequestParam(value="category_no", required = false, defaultValue = "0") Long category_no
			, Model model) {
		JSONResult2<List<ProductVo>> product_list = productService.get_product_list_by_categoryno(category_no);
		model.addAttribute("product_list", product_list.getData());
		return "admin/product-manage";
	}
	
	// 상품 등록 화면으로 이동
	@RequestMapping(value={"/product/add"}, method = RequestMethod.GET)
	public String product_add_page(
			@ModelAttribute("productvo") ProductVo productvo
			, Model model) {
		// 카테고리 가져와서 리스트 띄우기 
		JSONResult2<List<CategoryVo>> category_list = adminService.get_category();
		model.addAttribute("category_list",category_list.getData());
		List<MultipartFile> multipartPhoto = new ArrayList<MultipartFile>();
		model.addAttribute("multipartPhoto", multipartPhoto);
		// 상품 옵션 가져오기
		
		return "admin/product-add";
	}
	
	// 상품 등록 요청
	@RequestMapping(value={"/product/add"}, method = RequestMethod.POST)
	public String product_add(@ModelAttribute("productvo") ProductVo productvo
			, @ModelAttribute("multipartPhoto") List<MultipartFile> multipartPhoto
			, Model model) {
		adminService.add_product(productvo, multipartPhoto);
		return "redirect:/admin/product/add";
	}
	
	
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test() {
		
		return "test/multipart_test";
	}
	@RequestMapping(value="/test", method = RequestMethod.POST)
	public String test(@ModelAttribute("files") List<MultipartFile> files) {
		
		return "test/multipart_test";
	}
	
	
	
	/*
	 * 장바구니 관련
	 */
	@RequestMapping({"/cart"})
	public String cart_main(Model model) {		
		return "admin/cart-manage";
	}
	
	
	/*
	 * 주문 정보 가져오기(관리자)
	 */
	
	@RequestMapping(value= {"/{customer_no}"}, method = RequestMethod.GET)
	public String order_list(
			@PathVariable Long customer_no
			, Model model) {		
		Long product_detail_no = 1L;
		JSONResult2<List<OrdersVo>> orders_list = adminService.get_order_list(customer_no, product_detail_no);
		model.addAttribute("order_list", orders_list.getData());
		
		return "admin/order-manage";
	}
	
	
}
