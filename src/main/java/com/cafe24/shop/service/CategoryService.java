package com.cafe24.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.CategoryDao;
import com.cafe24.shop.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	/*
	 *  회원 약관 동의서 관리 
	 */
	public CategoryVo insert_category(CategoryVo vo) {
		CategoryVo inserted_category_vo = categoryDao.insert_category(vo);
		return inserted_category_vo;
	}
	
	public List<CategoryVo> get_category_list() {
		return categoryDao.get_category_list();
	}
	
	public Long update_category(CategoryVo vo) {
		Long updated_category_no = categoryDao.update_category(vo);
		return updated_category_no;
	}
	public Long delete_category(Long no) {
		Long delete_category_no = categoryDao.delete_category(no);
		return delete_category_no;
	}
}

