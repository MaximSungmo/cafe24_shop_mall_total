package com.cafe24.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.AdminProvider;
import com.cafe24.mysite.provider.CategoryProvider;
import com.cafe24.mysite.provider.CustomerProvider;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminProvider adminProvider;

	@Autowired
	private CategoryProvider categoryProvider;
	
	@Autowired
	private CustomerProvider customerProvider;
	
	
	public JSONResult2<List<CategoryVo>> get_category() {
		JSONResult2<List<CategoryVo>> category_list = categoryProvider.get_category_list();
		return category_list;
	}
	
	
	public JSONResult2<List<CustomerVo>> get_customer() {
		JSONResult2<List<CustomerVo>> customer_list = customerProvider.get_customer_list();
		return customer_list;
	}
	
	
}
