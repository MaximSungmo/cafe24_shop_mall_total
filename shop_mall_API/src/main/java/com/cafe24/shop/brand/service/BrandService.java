package com.cafe24.shop.brand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.brand.repository.BrandDao;
import com.cafe24.shop.brand.model.BrandVo;

@Service
public class BrandService {

	@Autowired
	BrandDao brandDao;
	
	public Boolean insert_brand(BrandVo brandvo) {
		return brandDao.insert_brand(brandvo)==1;
	}

	public List<BrandVo> get_brand_list() {
		return brandDao.get_brand_list();
	}

	public Boolean update_brand(BrandVo brandvo) {
		return brandDao.update_brand(brandvo)==1;
	}

	public Boolean delete_brand(BrandVo brandvo) {
		return brandDao.delete_brand(brandvo)==1;
	}

}
