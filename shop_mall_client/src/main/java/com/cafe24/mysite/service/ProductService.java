package com.cafe24.mysite.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.CategoryProvider;
import com.cafe24.mysite.provider.ProductProvider;
import com.cafe24.mysite.vo.CartVo;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.OrdersDetailVo;
import com.cafe24.mysite.vo.OrdersVo;
import com.cafe24.mysite.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	ProductProvider productProvider;
	@Autowired
	CategoryProvider categoryProvider;
	
	
	 public Map<String, JSONResult2<?>> get_product_detail(Long product_no) {
		// 카테고리 목록 가져오기
		JSONResult2<List<CategoryVo>> category_list = categoryProvider.get_category_list();
		JSONResult2<List<ProductVo>> product_list = productProvider.get_product_detail_list(product_no);
		Map<String, JSONResult2<?>> map = new HashMap<String, JSONResult2<?>>();
		map.put("category_list", category_list);
		map.put("product_list", product_list);
		return map;
	}
	public JSONResult2<List<ProductVo>> get_product_list_by_categoryno(Long category_no) {
		JSONResult2<List<ProductVo>> product_list = productProvider.get_product_list_by_categoryno(category_no);
		return product_list;
	}
	
	// 카트 넣기
	public Map<String, JSONResult2<?>> insert_cart_list(Long customer_no, CartVo cartvo) {
		// 고객번호 주입 
		for(CartVo cart_vo : cartvo.getCartvo_list()) {
			cart_vo.setCustomer_no(customer_no);
		}
		
		JSONResult2<List<CategoryVo>> category_list = categoryProvider.get_category_list();
		JSONResult2<Integer> insert_cart_list_result_no = productProvider.insert_cart_list(customer_no, cartvo);

		Map<String, JSONResult2<?>> map = new HashMap<String, JSONResult2<?>>();
		map.put("category_list", category_list);
		map.put("insert_cart_list_result_no", insert_cart_list_result_no);
		return map;
		
	}
	public List<CartVo> get_cart_list(Long customer_no) {
		
		JSONResult2<List<CartVo>> cart_vo_list = productProvider.get_cart_list(customer_no);
		
		List<CartVo> cart_list = new ArrayList<CartVo>();
		for(CartVo cart_vo :cart_vo_list.getData()) {
			if("N".equals(cart_vo.getOrdered_cart())) {
				cart_list.add(cart_vo);
			}
		}
		return cart_list;
	}
	
	public JSONResult2<List<CategoryVo>> get_category_list(){
		JSONResult2<List<CategoryVo>> category_list = categoryProvider.get_category_list();
		return category_list;
	}
	
	public JSONResult2<Boolean> insert_order(OrdersVo ordersvo, List<OrdersDetailVo> order_detail_list) {
		JSONResult2<Boolean> insert_order = productProvider.insert_order(ordersvo, order_detail_list);
		for(OrdersDetailVo orders_detail_vo : order_detail_list) {
			orders_detail_vo.setOrder_no(ordersvo.getNo());
		}
		return insert_order;
		
	}
	
	
	
}
