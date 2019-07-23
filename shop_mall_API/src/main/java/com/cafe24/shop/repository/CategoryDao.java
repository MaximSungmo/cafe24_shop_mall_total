package com.cafe24.shop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
		
	/*
	 * 회원약관 관리
	 */
	// 회원약관동의서, 회원가입요청 시 전달용
	
	public Integer insert_category(CategoryVo vo) {
		return sqlSession.insert("category.insert", vo);
	}
	
	public List<CategoryVo> get_category_list() {
		return sqlSession.selectList("category.get_category_list"); 
	}
	
	public Integer update_category(CategoryVo vo) {
		return sqlSession.update("category.update_category", vo); 
	}
	
	public Integer delete_category(CategoryVo vo) {
		return sqlSession.delete("category.delete_category", vo); 
	}

	public CategoryVo get_category_by_no(Long no) {
		return sqlSession.selectOne("category.get_category_by_no", no); 
	}

	// 카테고리 삭제를 위하여 삭제 대상이 되는 카테고리의 부모의 번호로 삭제 대상이 되는 카테고리 번호를 부모의 번호로 가지고 있는 모든 카테고리 부모의 번호 변경
	public Integer update_by_parent_no(Long update_by_parent_no) {
		return sqlSession.update("category.update_by_parent_no", update_by_parent_no);
	}
}
