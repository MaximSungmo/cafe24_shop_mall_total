package com.cafe24.shop.termsofuse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.termsofuse.repository.TermsOfUseTemplateDao;
import com.cafe24.shop.termsofuse.model.TermsOfUseVo;

@Service
public class TermsOfUseTemplateService {
	@Autowired
	TermsOfUseTemplateDao termsOfUseVoDao;

	/*
	 *  회원 약관 동의서 관리 
	 */
	public Boolean insert_terms_of_use_template(TermsOfUseVo vo) {
		return termsOfUseVoDao.insert_terms_of_use_template(vo) == 1;
	}
	
	public List<TermsOfUseVo> get_terms_of_use_template_list() {
		return termsOfUseVoDao.get_terms_of_use_template_list();
	}
	
	public Boolean update_terms_of_use_template(TermsOfUseVo vo) {
		return termsOfUseVoDao.update_terms_of_use_template(vo) == 1;
	}
	
	public Boolean delete_terms_of_use_template(Long no) {
		return termsOfUseVoDao.delete_terms_of_use_template(no) == 1;
	}
}
