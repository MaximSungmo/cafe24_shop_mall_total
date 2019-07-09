package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert( BoardVo boardVo ) {
		return sqlSession.insert( "board.insert", boardVo );
	}
	
	public List<BoardVo> getList( String keyword, Integer page, Integer size ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "keyword", keyword );
		map.put( "startIndex", (page-1)*size );
		map.put( "size", size );
		
		return sqlSession.selectList( "board.getList", map );
	}

	public int update( BoardVo boardVo ) {
		return sqlSession.update( "board.update", boardVo );
	}
	
	public int delete( Long no, Long userNo ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.delete( "board.delete", map );
	}

	public BoardVo get( Long no ) {
		return sqlSession.selectOne( "board.getByNo", no );
	}
	
	public BoardVo get( Long no, Long userNo ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.selectOne( "board.getByNoAndUserNo", map );
	}

	public int updateHit( Long no ) {
		return sqlSession.update( "board.updateHit", no );
	}
	
	public int updateOrderNo( Integer groupNo, Integer orderNo ) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put( "groupNo", groupNo );
		map.put( "orderNo", orderNo );
		
		return sqlSession.update( "board.updateOrederNo", map );
	}
	
	public int getTotalCount( String keyword ) {
		return sqlSession.selectOne( "board.getTotalCount", keyword );
	}	
}