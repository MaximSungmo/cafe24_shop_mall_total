package com.cafe24.shop.brand.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.brand.model.BrandVo;

@Repository
public class BrandDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Integer insert_brand(BrandVo brandvo) {
		return sqlSession.insert("brand.insert_brand", brandvo);
	}

	public List<BrandVo> get_brand_list() {
		return sqlSession.selectList("brand.get_brand_list");
	}

	public Integer update_brand(BrandVo brandvo) {
		return sqlSession.update("brand.update_brand", brandvo);
	}

	public Integer delete_brand(BrandVo brandvo) {
		return sqlSession.update("brand.delete_brand", brandvo);
	}
	
}
