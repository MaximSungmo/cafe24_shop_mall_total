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

	public List<CartVo> get_cart_test_data(Map<String, Long> map) {
		CartVo vo = new CartVo(1L, 1L, 1L, 1L, "2019-07-16 00:00:00", "", "N");
		CartVo vo2 = new CartVo(2L, 1L, 2L, 1L, "2019-07-16 00:00:00", "", "N");
		CartVo vo3 = new CartVo(3L, 1L, 3L, 1L, "2019-07-16 00:00:00", "", "N");
		List<CartVo> list = new ArrayList<CartVo>();
		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		return list;		
	}
	
	public Long add_cart(Map<String, Long> map) {
		Long inserted_cart_no = 1L;
//		Long inserted_cart_no =sqlSession.insert("cart.add_cart", map);
		return inserted_cart_no;
	}
	
}
