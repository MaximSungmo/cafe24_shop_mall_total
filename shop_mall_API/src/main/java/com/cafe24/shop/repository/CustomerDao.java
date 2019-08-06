package com.cafe24.shop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.CustomerVo;

@Repository
public class CustomerDao {
	@Autowired
	private SqlSession sqlSession;
	
	public CustomerVo get_customer_by_email(String email) {
		return 	sqlSession.selectOne("customer.getByEmail", email);
	} 
	
	public Boolean insert(CustomerVo vo) {
		int count = sqlSession.insert("customer.insert", vo);
		return count==1;
	}
	
	public CustomerVo get_customer_by_no(Long no){
		CustomerVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;
	}
	
	public int update_customer(CustomerVo vo) {
		int update_result = sqlSession.update("customer.update", vo);
		return update_result;
	}	
	
	public int delete_customer(Long no) {
		int delete_result = sqlSession.update("customer.delete_customer", no);
		return delete_result; 
	}
	

	public CustomerVo login(CustomerVo vo) {
		return sqlSession.selectOne("customer.getByEmailAndPassword", vo); 
	}

	public Boolean insert_checked_terms_of_use(CustomerVo vo) {
		System.out.println(vo.getTerms_of_use_no()+"aaaaaaterms");
		System.out.println(vo.getNo()+"aaaaaa custos");
		return sqlSession.insert("customer.insert_checked_terms_of_use", vo)==1;
	}

	public List<CustomerVo> get_list() {
		
		return sqlSession.selectList("customer.get_list");
	}

}