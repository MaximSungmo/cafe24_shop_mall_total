package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.CustomerService;
import com.cafe24.mysite.vo.CustomerVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private CustomerService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		ApplicationContext ac = 
//		WebApplicationContextUtils.
//		getWebApplicationContext(request.getServletContext());
//		CustomerService userService = ac.getBean(CustomerService.class);
		
		CustomerVo userVo = new CustomerVo();
		userVo.setEmail(email);
		userVo.setPassword(password);
		
		CustomerVo authUser = userService.getUser(userVo);
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect( request.getContextPath() + "/" );

		return false;
	}

}
