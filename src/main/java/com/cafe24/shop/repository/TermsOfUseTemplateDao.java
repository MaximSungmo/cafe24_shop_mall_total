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
	
	public List<TermsOfUseVo> testData(){
		//	Test용 데이터 생성(DB)
		TermsOfUseVo vo = new TermsOfUseVo(1L, "약관동의서1","약관동의내용1", "Y", "Y", "2019-07-13 00:00:00", "");
		TermsOfUseVo vo1 = new TermsOfUseVo(2L, "약관동의서2","약관동의내용2", "Y", "Y", "2019-07-13 00:00:00", "");
		List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
		list.add(vo);
		list.add(vo1);
		return list;
	}
	
	
	/*
	 * 회원약관 관리
	 */
	// 회원약관동의서, 회원가입요청 시 전달용
	public List<TermsOfUseVo> get_terms_of_use_template_list() {
		List<TermsOfUseVo> list = testData();
		return list;
//		return sqlSession.selectList("terms.get_terms_of_use_template"); 
	}
	public Long insert_terms_of_use_template(TermsOfUseVo vo) {
//		Long template_no = sqlSession.insert("terms.insert_terms_of_use_template", vo);
		Long template_no = 1L;
		return template_no;
	}
	public Long update_terms_of_use_template(TermsOfUseVo vo) {
//		return sqlSession.update("terms.update_terms_of_use_template", vo); 
		Long updated_template_no = 1L;
		return updated_template_no;
	}
	public Long delete_terms_of_use_template(Long no) {
//		return sqlSession.update("terms.delete_terms_of_use_template", no); 
		Long deleted_template_no = 1L;
		return deleted_template_no;
	}
}
