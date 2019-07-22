package com.cafe24.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shop.repository.CategoryDao;
import com.cafe24.shop.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	private int result;

	/*
	 *  회원 약관 동의서 관리 
	 */
	public Boolean insert_category(CategoryVo vo) {
		return categoryDao.insert_category(vo)==1;
	}
	
	public List<CategoryVo> get_category_list() {
		return categoryDao.get_category_list();
	}
	
	public Boolean update_category(CategoryVo vo) {
		return categoryDao.update_category(vo)==1;
	}
	
	@Transactional
	public Boolean delete_category(CategoryVo vo) {
		Long update_by_parent_no = vo.getParent_no();
		categoryDao.update_by_parent_no(update_by_parent_no);
		return categoryDao.delete_category(vo)==1;
	}

	public Boolean get_category_by_no(Long no) {
		CategoryVo vo =categoryDao.get_category_by_no(no);
		return vo!= null; 
	}
}

