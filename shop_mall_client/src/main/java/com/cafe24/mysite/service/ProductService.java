package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.ProductProvider;
import com.cafe24.mysite.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	ProductProvider productProvider;

	public JSONResult2<List<ProductVo>> get_product_detail(Long product_no) {
		return 	productProvider.get_product_detail_list(product_no);
	}
	
	
}
