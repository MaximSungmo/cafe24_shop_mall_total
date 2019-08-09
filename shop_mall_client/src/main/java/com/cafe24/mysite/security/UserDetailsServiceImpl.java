package com.cafe24.mysite.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.mysite.provider.CustomerProvider;
import com.cafe24.mysite.vo.CustomerVo;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerProvider customerProvider;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CustomerVo customerVo = customerProvider.get_by_email(email);
		SecurityUser securityUser = new SecurityUser();
		
		if(customerVo != null) {
			securityUser.setNo(customerVo.getNo());
			securityUser.setName(customerVo.getName());
			securityUser.setUsername(customerVo.getEmail());
			securityUser.setPassword(customerVo.getPassword()); 
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(customerVo.getAuth_grade())));
		}
		
		return securityUser;
	}	
}
