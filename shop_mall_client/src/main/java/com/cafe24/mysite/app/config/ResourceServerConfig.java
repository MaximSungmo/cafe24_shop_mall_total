package com.cafe24.mysite.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();

		// 자원 서버 접근 권한 설정
		http
			.authorizeRequests()
			.antMatchers("/hello").access("#oauth2.hasScope('read')")
			.antMatchers("/hello2").access("#oauth2.hasScope('read')")
			.anyRequest().permitAll();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("pjmall_api");
		// DB에서 Resource_id에 해당하는 부분이 맞아야 통과시킴 
	}
	
	
}
