//package com.cafe24.backend.config;
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.server.ServletServerHttpResponse;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import com.cafe24.shop.dto.JSONResult;
//
//
//@Configuration
//@EnableWebSecurity
//public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().antMatchers("/**");
//	}
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//    
//	@Override
//    public void configure(HttpSecurity http) throws Exception {
//		// 인터셉터 URL에 접근 제어(Basic ACL)
//        http
//        	.authorizeRequests()
//        	.anyRequest().permitAll()  
//        	
//        	.and()
//        	.formLogin().loginPage("")
//        // 예외처리
//        .and()
//       	.exceptionHandling()
//    	.accessDeniedHandler(new AccessDeniedHandler() {
//			@Override
//			public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//		    	JSONResult jsonResult = JSONResult.fail( "Access Denied" );
//		    	
//		    	MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//		    	if( jsonConverter.canWrite( jsonResult.getClass(), MediaType.APPLICATION_JSON ) ) {
//		        	jsonConverter.write( jsonResult, MediaType.APPLICATION_JSON, new ServletServerHttpResponse( response ) );
//		    	}
//			}
//    	});
//        
//        // csrf 체크 제외
//        http.csrf().disable();
// 	}
//}