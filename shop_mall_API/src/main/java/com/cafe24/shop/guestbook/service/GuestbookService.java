package com.cafe24.shop.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.guestbook.repository.GuestbookDao;
import com.cafe24.shop.guestbook.model.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getContentList() {
		return guestbookDao.getList();
	}
	public List<GuestbookVo> getContentList(Long lastNo) {
		return guestbookDao.getList(lastNo);
	}
	
	
	public boolean deleteContent( GuestbookVo vo ){
		return 1 == guestbookDao.delete( vo );
	}
	
	public boolean writeContent( GuestbookVo vo ) {
		int count = guestbookDao.insert(vo);
		return count == 1;
	}

	public boolean addContents(GuestbookVo vo) {
		int count = guestbookDao.insert(vo);
		return count == 1;
	}
	
	
}
