package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.CustomerDao;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.TermsOfUseVo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * 동일 이메일이 존재하는 경우 true,
	 * (중복이 되지 않아 사용 가능한 경우)존재하지 않는 경우 false
	 * @param email
	 * @return true, false
	 */
	public Boolean exist_email(String email) {
		CustomerVo vo = customerDao.get_customer_by_email(email);
		// DB에서 받아온 데이터와 REQUEST로 받은 데이터가 동일한 지 검증 
		if(!email.equals(vo.getEmail())) {
			return false;
		} 
		return vo != null;
	}
	/**
	 * 회원가입 시 유저 정보를 받아서 insert하여 줌 
	 * @param userVo
	 * @return true, false
	 */
	public Boolean join(CustomerVo vo) {
		return customerDao.insert(vo);
	}
	
	/**
	 * 회원정보 업데이트, 업데이트 완료 시 true
	 * @param userVo
	 * @return true, false
	 */
	public Boolean update_user(CustomerVo vo) {
		return customerDao.update(vo) == 1;
	}
	
	/**
	 * 회원번호(no)로 회원정보(vo) 가져오기 
	 * @param no
	 * @return customerVo
	 */
	public CustomerVo get_by_no(Long no) {
		return customerDao.get_customer_by_no(no) ;
	} 
	
	/**
	 * 회원정보(vo)로 회원삭제-update하기(use_fl = 'N') 
	 * @param vo
	 * @return true, false
	 */
	public Boolean delete(CustomerVo vo) {
		return customerDao.delete(vo) == 1;
	}
	

//	public List<TermsOfUseVo> get_terms_of_use_template() {
//		return customerDao.get_terms_of_use_template();
//	}
	
}
