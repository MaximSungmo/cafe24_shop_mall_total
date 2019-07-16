package com.cafe24.shop.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CustomerVo;
import com.cafe24.shop.vo.TermsOfUseVo;

@Repository
public class CustomerDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CustomerVo> testData(){
	//	Test용 데이터 생성(DB)
		CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
		CustomerVo vo1 = new CustomerVo(101L, "성공테스트", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y");
		List<CustomerVo> list = new ArrayList<CustomerVo>();
		list.add(vo);
		list.add(vo1);
		return list;
	}
	
	TermsOfUseVo termsOfUseVo = new TermsOfUseVo(1L, "약관동의서1","약관동의내용1","2019-07-13 00:00:00", "");

	
	public CustomerVo get_customer_by_email(String email) {
		List<CustomerVo> list = testData(); 
		CustomerVo vo = list.get(0);
//		sqlSession.selectOne("user.getByEmail", email);
		return vo; 
	} 
	
	public Boolean insert(CustomerVo vo) {
		int count = 1;		
//		int count = sqlSession.insert("user.insert", vo);
		return 1 == count;
	}
	
	public CustomerVo get_customer_by_no(Long no){
		List<CustomerVo> list = testData(); 
		CustomerVo vo = list.get(0);
//		CustomerVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;
	}
	
	public int update(CustomerVo vo) {
		int update_result = 1;
//		int update_result = sqlSession.update("user.update", vo);
		return update_result;
	}	
	
	public int delete_customer(Long no) {
		List<CustomerVo> list = testData(); 
		CustomerVo vo = list.get(1);
		vo.setUse_fl("N");
		int delete_result = 1;
		//int delete_result = sqlSession.update("customer.delete", no);
		return delete_result; 
	}
	
	
	
	
	
	// 회원약관동의서, 회원가입요청 시 전달용
	public List<TermsOfUseVo> get_terms_of_use_template() {
		List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
		list.add(termsOfUseVo);
		return list;
//		return sqlSession.selectList("user.get_terms_of_use_template"); 
	}
	public Boolean insert_terms_of_use_template() {
		List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
		list.add(termsOfUseVo);
		return null;
//		return sqlSession.selectList("user.get_terms_of_use_template"); 
	}
	public Boolean update_terms_of_use_template() {
		List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
		list.add(termsOfUseVo);
		return null;
//		return sqlSession.selectList("user.get_terms_of_use_template"); 
	}
	public Boolean delete_terms_of_use_template() {
		List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
		list.add(termsOfUseVo);
		return null;
//		return sqlSession.selectList("user.get_terms_of_use_template"); 
	}


	public Long get_login_id(CustomerVo vo) {
		Long get_login_id = vo.getNo();
//		return sqlSession.selectOne("customer.get_by_email", email); 
		return get_login_id;
	}


	
	
	
//	
//	
//	public CustomerVo get(String email, String password) throws UserDaoException {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("email", email);
//		map.put("password", password);
//		CustomerVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
//		
//		return userVo;
//	}	
//	
//	
//	

}