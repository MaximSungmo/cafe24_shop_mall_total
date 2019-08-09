package com.cafe24.mysite.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cafe24.mysite.security.CustomUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/assets/**")
			.antMatchers("/favicon.ico");
	}

	@Override
    public void configure(HttpSecurity http) throws Exception {

		// AuthorizedURL(Basic ACL)
        http
        	.authorizeRequests()
        		// 인증이 되었을 경우
        		.antMatchers("/user/update", "/user/logout").authenticated()
        		.antMatchers("/board/write", "/board/modify", "/board/modify/**").authenticated()
        		.antMatchers("/admin").authenticated()
        		.antMatchers("/customer").authenticated()

        		// ADMIN 권한
        		// .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        		// .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN");
//        		 .antMatchers("/admin", "/admin/**").hasRole("ADMIN")


        		// 모두 허용
        		.anyRequest().permitAll()
        
        // FormLoginConfigurer
        .and()
        	.formLogin()
        		.loginPage("/customer/login")
        		.loginProcessingUrl("/customer/auth")
        		.usernameParameter("email")
        		.passwordParameter("password")
        		.failureUrl("/customer/login?result=fail")
        		.successHandler(authenticationSuccessHandler())
        		
        
        // LogoutConfigurer
        .and()
        	.logout()
        			.logoutRequestMatcher(new AntPathRequestMatcher("/customer/logout"))
        			.logoutSuccessUrl("/")
        			.deleteCookies("JSESSIONID")
        			.invalidateHttpSession(true)
        
        // ExceptionHandlingConfigurer
        .and()
        	.exceptionHandling()
        		.accessDeniedPage("/views/error/404.jsp")
        
        // RememberMeConfigurer
        .and()
        	.rememberMe()
        		.key("mysite")
        		.rememberMeParameter("remember-me")

        // CSRFConfigurer(Temporary for Test)
        .and()
        	.csrf()
        		.disable();
	}

	// 사용자 세부 서비스를 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth
			.userDetailsService(userDetailsService)
			.and()
			.authenticationProvider(authProvider());
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
	    return new CustomUrlAuthenticationSuccessHandler();
	}
	
	// Encode the Password on Authentication
	// BCrypt Password Encoder(with Random Salt)
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}	
}