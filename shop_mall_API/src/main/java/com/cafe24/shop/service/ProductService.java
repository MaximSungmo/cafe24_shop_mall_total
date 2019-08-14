package com.cafe24.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.ProductDao;
import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductImageVo;
import com.cafe24.shop.vo.ProductVo;
@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	/*
	 * 상품 정보, product
	 */
	public List<ProductVo> get(Long no, String kwd, Long get_count, Long last_product_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category_no", no);
		map.put("kwd", kwd);
		map.put("get_count", get_count); 
		map.put("last_product_no", last_product_no);
		return productDao.get_product_list(map);
	}

	public Boolean add_product(ProductVo vo) {
		return productDao.add_product(vo);

	}
	
	public Boolean update_product(ProductVo vo) {
		return productDao.update_product(vo)==1;
	}

	public Boolean delete_product(Long no) {
		return productDao.delete_product(no)==1;
	}

	public List<ProductVo> get_product_list_by_result_map(Long category_no) {
		return productDao.get_product_list_by_result_map(category_no);
	}
	
	public List<ProductVo> get_product_by_product_no_result_map(Long product_no) {
		return productDao.get_product_by_product_no_result_map(product_no);
	}
	/*
	 * 상품 상세정보, product_detail 
	 */
	public Integer add_product_detail(List<ProductDetailVo> vo_list) {
		return productDao.add_product_detail(vo_list);
	}

	public List<ProductDetailVo> get_product_detail_list(Long product_no) {
		return productDao.get_product_detail_list(product_no);
	}

	public Integer update_product_detail(List<ProductDetailVo> product_detail_list) {
		return productDao.update_product_detail(product_detail_list);
	}

	public Boolean delete_product_detail(Long product_no, Long product_detail_no) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("product_no", product_no);
		map.put("product_detail_no", product_detail_no);
		return productDao.delete_product_detail(map)==1;
	}
	

	public Integer delete_product_detail_list(List<ProductDetailVo> product_detail_list) {
		return productDao.delete_product_detail_list(product_detail_list);
	}

	public Integer add_product_image_list(List<ProductImageVo> product_image_list) {
		return productDao.add_product_image_list(product_image_list);
	}

	public List<ProductImageVo> get_product_image_list(ProductImageVo product_image_vo) {
		return productDao.get_product_image_list(product_image_vo);
	}

	public Boolean update_product_image_list(List<ProductImageVo> product_image_list, Long product_no) {
		List<ProductImageVo> checked_product_image_list = new ArrayList<ProductImageVo>();
		// 이미지 번호가 잘못 넘어오는 경우를 대비한 작업 
//		for(int i=0; i<product_image_list.size(); i++) {
//			if(product_image_list.get(i).getProduct_no()==product_no) {
//				checked_product_image_list.add(product_image_list.get(i));
//			}
//		}
		return productDao.update_product_image_list(product_image_list) == 1;
		
	}

	public Boolean delete_product_image(Long product_image_no) {
		return productDao.delete_product_image(product_image_no)==1;
	}

	
}
