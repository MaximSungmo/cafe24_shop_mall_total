package com.cafe24.shop.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.OrdersDetailVo;
import com.cafe24.shop.vo.OrdersVo;

@Repository
public class OrdersDao {

	@Autowired
	SqlSession sqlSession;
	
	public Integer add_orders(OrdersVo ordersvo) {
		return sqlSession.insert("orders.insert_orders", ordersvo);
	}

	public Integer add_orders_detail(List<OrdersDetailVo> orders_detail_list) {
		return sqlSession.insert("orders.insert_orders_detail", orders_detail_list);
	}

	public List<OrdersVo> get_orders(Long customer_no) {
		return sqlSession.selectList("orders.get_orders_by_customer_no", customer_no);
	}

	public List<OrdersVo> get_orders_by_admin(Map<String, Long> map) {
		return sqlSession.selectList("orders.get_orders_by_admin", map);
	}
	
	

}
