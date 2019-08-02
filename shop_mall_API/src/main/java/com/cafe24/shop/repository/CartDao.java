package com.cafe24.shop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CartVo;
import com.cafe24.shop.vo.ProductDetailVo;

@Repository
public class CartDao {
	@Autowired
	SqlSession sqlSession;
	
	public Integer add_cart(Map<String, Long> map) {
		return sqlSession.insert("cart.insert_cart", map);
	}

	public Integer add_cart_list(List<CartVo> cartvo_list) {
		return sqlSession.insert("cart.insert_cart_list", cartvo_list);
	}

	public List<CartVo> get_cart_list(Long customer_no) {
		return sqlSession.selectList("cart.get_cart_list", customer_no);
	}

	public Integer update_cart_list(List<CartVo> cartvo_list) {
		return sqlSession.update("cart.update_cart_list", cartvo_list);
	}

	public Integer delete_cart_list(List<CartVo> cartvo_list) {
		return sqlSession.delete("cart.delete_cart_list", cartvo_list);
	}

	public Integer update_cart_list_by_order(List<CartVo> cartvo_list) {
		return sqlSession.update("cart.update_cart_list_by_order", cartvo_list);
	}
	
}
