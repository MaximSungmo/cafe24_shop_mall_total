package com.cafe24.shop.repository;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductImageVo;
import com.cafe24.shop.vo.ProductVo;

@Repository
public class ProductDao {

	@Autowired
	SqlSession sqlSession;
	
	public Boolean add_product(ProductVo vo) {
		return sqlSession.insert("product.insert_product", vo)==1;		
	}
	
	public List<ProductVo> get_product_list(Map<String, Object> map){
		List<ProductVo> product_list = sqlSession.selectList("product.get_product_list", map);
		return product_list;
	}
		
	public Integer delete_product(Long no) {
		return sqlSession.delete("product.delete_product", no);
	}
	
	public Integer update_product(ProductVo vo) {	
		return sqlSession.update("product.update_product", vo);
	}

	public Integer add_product_detail(List<ProductDetailVo> list) {
		return sqlSession.insert("product.insert_product_detail", list);
	}

	public List<ProductDetailVo> get_product_detail_list(Long no) {
		List<ProductDetailVo> product_list = sqlSession.selectList("product.get_product_detail_list", no);
		return product_list;
	}

	public Integer update_product_detail(List<ProductDetailVo> product_detail_list) {
		return sqlSession.update("product.update_product_detail_list", product_detail_list);
	}

	public Integer delete_product_detail(Map<String, Long> map) {
		return sqlSession.delete("product.delete_product_detail", map);
	}
	
	public Integer delete_product_detail_list(List<ProductDetailVo> product_detail_list) {
		return sqlSession.delete("product.delete_product_detail_list",product_detail_list);
	}
	

	public List<ProductVo> get_product_list_by_result_map(Long category_no) {
		List<ProductVo> list_by_result_map = sqlSession.selectList("product.get_product_list_by_result_map", category_no);
		return list_by_result_map;
	}

	public Integer add_product_image_list(List<ProductImageVo> product_image_list) {
		return sqlSession.insert("product.insert_product_image_list", product_image_list);
			
	}

	public List<ProductImageVo> get_product_image_list(ProductImageVo product_image_vo) {
		return sqlSession.selectList("product.get_product_image_list", product_image_vo);
	}

	public Integer update_product_image_list(List<ProductImageVo> checked_product_image_list) {
		return sqlSession.update("product.update_product_image_list", checked_product_image_list);
	}


	public Integer delete_product_image(Long product_image_no) {
		return sqlSession.delete("product.delete_product_image", product_image_no);
	}

	public List<ProductVo> get_product_by_product_no_result_map(Long product_no) {
		return sqlSession.selectList("get_product_by_product_no_result_map", product_no);
	}

	
}
