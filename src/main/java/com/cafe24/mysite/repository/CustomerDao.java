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
	
	public Terms_of_use_vo get_template() {
		return sqlSession.selectOne("user.get_terms_of_use_template");
	}
	
	public CustomerVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	public CustomerVo get(Long no){
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public CustomerVo get(String email, String password) throws UserDaoException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		CustomerVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		
		return userVo;
	}	
	
	public Boolean insert(CustomerVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		return 1 == count;
	}
	
	public int update( CustomerVo userVo ) {
		return sqlSession.update( "user.update", userVo );
	}	
}