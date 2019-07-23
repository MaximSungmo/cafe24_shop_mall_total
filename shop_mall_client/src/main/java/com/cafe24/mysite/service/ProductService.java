package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.ProductDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.ProductVo;
@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public List<ProductVo> get(Long no, String kwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category_no", no);
		map.put("kwd", kwd);
		return productDao.get_product_list(map);
	}

	public Boolean add_product(ProductVo vo) {
		return productDao.add_product(vo);

	}

	public Long update_product(ProductVo vo) {
		return productDao.update_product(vo);
	}

	public Long delete_product(Long no) {
		return productDao.delete_product(no);
	}

}
