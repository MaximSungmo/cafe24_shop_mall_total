package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.Terms_of_use_vo;
import com.cafe24.mysite.vo.CustomerVo;

@Repository
public class CustomerDao {
	@Autowired
	private SqlSession sqlSession;
	
	//Test용 데이터 생성(DB)
	CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
	CustomerVo vo1 = new CustomerVo(101L, "성공테스트", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y");
		
	
	public CustomerVo get_customer_by_email(String email) {
//		sqlSession.selectOne("user.getByEmail", email);
		return vo; 
	}
	
	public Boolean insert(CustomerVo vo) {
		int count = 1;		
//		int count = sqlSession.insert("user.insert", vo);
		return 1 == count;
	}
	
	public CustomerVo get_customer_by_no(Long no){
//		CustomerVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;
	}
	
	public int update(CustomerVo vo) {
		int update_result = 1;
//		int update_result = sqlSession.update("user.update", vo);
		return update_result;
	}	
	
	public int delete(CustomerVo vo) {
		int delete_result = 1;
		vo = vo1;
		vo.setUse_fl("N");
		//int delete_result = sqlSession.delete("customer.delete", vo);
		return delete_result; 
	}
	
	
	
//	public Terms_of_use_vo get_template() {
//		return sqlSession.selectOne("user.get_terms_of_use_template");
//	}
	
	
	
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