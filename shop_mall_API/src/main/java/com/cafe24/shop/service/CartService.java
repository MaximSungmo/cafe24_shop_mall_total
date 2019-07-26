package com.cafe24.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.CartDao;
import com.cafe24.shop.vo.CartVo;
import com.cafe24.shop.vo.ProductDetailVo;
@Service
public class CartService {
	@Autowired
	CartDao cartDao;
	
	/*
	 * 상품 상세정보, product_detail 
	 */
//	public Long add_product_detail(ProductDetailVo vo) {
//		return productDao.add_product_detail(vo);
//
//	}
//
//	public List<ProductDetailVo> get_product_detail_list(Long product_no) {
//		return productDao.get_product_detail_list(product_no);
//	}
//
//	public Long update_product_detail(Long product_detail_no) {
//		return productDao.update_product_detail(product_detail_no);
//	}
//
//	public Long delete_product_detail(Long product_no, Long product_detail_no) {
//		Map<String, Long> map = new HashMap<String, Long>();
//		map.put("product_no", product_no);
//		map.put("product_detail_no", product_detail_no);
//		return productDao.delete_product_detail(map);
//	}

	public Integer add_cart(Long customer_no, Long product_detail_no, Long count) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("customer_no", customer_no);
		map.put("product_detail_no", product_detail_no);
		map.put("count", count);
		return cartDao.add_cart(map);
	}

	public Integer add_cart_list(List<CartVo> cartvo_list) {
		return cartDao.add_cart_list(cartvo_list);
		
	}

	public List<CartVo> get_cart_list(Long customer_no) {
		return cartDao.get_cart_list(customer_no);
	}

	public Integer update_cart_list(List<CartVo> cartvo_list) {
		
		return cartDao.update_cart_list(cartvo_list);
	}

	
}
