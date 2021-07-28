package com.cafe24.shop.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shop.cart.repository.CartDao;
import com.cafe24.shop.order.repository.OrdersDao;
import com.cafe24.shop.cart.model.CartVo;
import com.cafe24.shop.order.model.OrdersDetailVo;
import com.cafe24.shop.order.model.OrdersVo;

@Service
public class OrdersService {

	@Autowired
	OrdersDao ordersDao;
	
	@Autowired
	CartDao cartDao;
	
	@Transactional
	public Boolean add_orders(OrdersVo ordersvo, List<OrdersDetailVo> orders_detail_list) {
		Boolean flag = false;
		Long customer_no = ordersvo.getCustomer_no();
		Boolean add_orders_result = (ordersDao.add_orders(ordersvo)==1);
		
		for(OrdersDetailVo orders_detail_vo : orders_detail_list) {
			orders_detail_vo.setOrder_no(ordersvo.getNo());
		}
		
		if(add_orders_result) {
			ordersDao.add_orders_detail(orders_detail_list);
			// 카트 업데이트 시키기 
			List<CartVo> cartvo_list = new ArrayList<CartVo>();
			for(int i =0; i<cartvo_list.size(); i++) {
				CartVo tempvo = new CartVo();
				tempvo.setProduct_detail_no(orders_detail_list.get(i).getProduct_detail_no());
				tempvo.setCustomer_no(customer_no);
				tempvo.setOrdered_cart("Y");
				cartvo_list.add(tempvo);
			}
//			cartDao.update_cart_list_by_order(cartvo_list);
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}

	public List<OrdersVo> get_orders(Long customer_no) {
		return ordersDao.get_orders(customer_no);
	}

	public List<OrdersVo> get_orders_by_admin(Long checked_customer_no, Long checked_product_detail_no) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("customer_no", checked_customer_no);
		map.put("product_detail_no", checked_product_detail_no);	
		return ordersDao.get_orders_by_admin(map);
	}

	public Boolean delete_orders(Long orders_no) {
		return null;
	}

}
