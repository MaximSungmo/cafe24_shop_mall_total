package com.cafe24.shop.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	private static int COUNT_PER_PAGE = 5; 
	
	@Autowired
	private SqlSession sqlSession;

	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return count;
	}
	
	public int insert( GuestbookVo vo ) {
		return sqlSession.insert( "guestbook.insert", vo );
	}
	
	
	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> result = sqlSession.selectList( "guestbook.getList");
		return result;
	}	
	
	public List<GuestbookVo> getList(Long lastNo){
		List<GuestbookVo> result = sqlSession.selectList( "guestbook.getList2", lastNo);
		return result;
	}
	
}

