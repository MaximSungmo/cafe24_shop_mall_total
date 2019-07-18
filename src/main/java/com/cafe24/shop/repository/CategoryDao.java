package com.cafe24.shop.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> testData(){
		//	Test용 데이터 생성(DB)
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		list.add(new CategoryVo(1L, "1번카테고리", null));
		list.add(new CategoryVo(2L, "2번카테고리", 1L));
		list.add(new CategoryVo(3L, "3번카테고리", 2L));
		list.add(new CategoryVo(4L, "4번카테고리", 2L));	
		return list;
	}
	
	
	/*
	 * 회원약관 관리
	 */
	// 회원약관동의서, 회원가입요청 시 전달용
	
	public CategoryVo insert_category(CategoryVo vo) {
//		Long inserted_category_no = sqlSession.insert("terms.insert_terms_of_use_template", vo);
		CategoryVo inserted_category_vo = testData().get(0);
		return inserted_category_vo;
	}
	
	public List<CategoryVo> get_category_list() {
		List<CategoryVo> list = testData();
		return list;
//		return sqlSession.selectList("terms.get_terms_of_use_template"); 
	}
	
	public Long update_category(CategoryVo vo) {
//		return sqlSession.update("terms.update_terms_of_use_template", vo); 
		Long updated_template_no = 1L;
		return updated_template_no;
	}
	
	public Long delete_category(Long no) {
//		return sqlSession.update("terms.delete_terms_of_use_template", no); 
		Long deleted_template_no = 1L;
		return deleted_template_no;
	}
}
