package com.cafe24.shop.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.TermsOfUseVo;

@Repository
public class TermsOfUseTemplateDao {
	@Autowired
	private SqlSession sqlSession;

	
	/*
	 * 회원약관 관리
	 */


	public Integer insert_terms_of_use_template(TermsOfUseVo vo) {
		return sqlSession.insert("termsofuse.insert", vo);
	}

	// 회원약관동의서, 회원가입요청 시 전달용
	public List<TermsOfUseVo> get_terms_of_use_template_list() {
		return sqlSession.selectList("termsofuse.get_term_of_use_template_list");
	}
	
	public Integer update_terms_of_use_template(TermsOfUseVo vo) {
		return sqlSession.update("termsofuse.update_terms_of_use_template", vo);
	}

	public Integer delete_terms_of_use_template(Long no) {
		return sqlSession.update("termsofuse.delete_terms_of_use_template", no);
	}
}
