package com.cafe24.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.TermsOfUseTemplateDao;
import com.cafe24.shop.vo.TermsOfUseVo;

@Service
public class TermsOfUseTemplateService {
	@Autowired
	TermsOfUseTemplateDao termsOfUseVoDao;

	/*
	 *  회원 약관 동의서 관리 
	 */
	public Long insert_terms_of_use_template(TermsOfUseVo vo) {
		Long template_no = termsOfUseVoDao.insert_terms_of_use_template(vo);
		return template_no;
	}
	
	public List<TermsOfUseVo> get_terms_of_use_template_list() {
		return termsOfUseVoDao.get_terms_of_use_template_list();
	}
	
	public Long update_terms_of_use_template(TermsOfUseVo vo) {
		Long updated_template_no = termsOfUseVoDao.update_terms_of_use_template(vo);
		return updated_template_no;
	}
	public Long delete_terms_of_use_template(Long no) {
		Long delete_template_no = termsOfUseVoDao.delete_terms_of_use_template(no);
		return delete_template_no;
	}
}
