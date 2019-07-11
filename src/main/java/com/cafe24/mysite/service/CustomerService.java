package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.CustomerDao;
import com.cafe24.mysite.vo.CustomerVo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao userDao;
	
	public Boolean existEmail(String email) {
		CustomerVo userVo = new CustomerVo("ksm5318", "1234");
		
		return userVo != null;
	}
	
	public Boolean join(CustomerVo userVo) {
		return userDao.insert(userVo);
	}

	public CustomerVo getUser(Long no) {
		return userDao.get(no) ;
	}
	
	public CustomerVo getUser(CustomerVo userVo) {
		return userDao.get(userVo.getEmail(), userVo.getPassword());
	}
	
	public boolean updateUser( CustomerVo userVo ) {
		return userDao.update( userVo ) == 1;
	}
}
