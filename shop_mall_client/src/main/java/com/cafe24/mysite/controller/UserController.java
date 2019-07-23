package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.repository.CustomerDao;
import com.cafe24.mysite.service.CustomerService;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.TermsOfUseVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private CustomerService userService;
	
	@Autowired
	private CustomerDao userdao;
	
	/**
	 * 
	 * @param userVo
	 * @return
	 * 
	 * For user join function. 
	 */
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(
			@ModelAttribute CustomerVo userVo, 
			@ModelAttribute TermsOfUseVo terms_of_use_vo,
			Model model) {
		
//		model.addAttribute("terms_of_use_template", userdao.get_template());		
		return "user/terms_of_use_form";
	}
	
//	
//	@RequestMapping(value="/join", method=RequestMethod.POST)
//	public String join(
//		@ModelAttribute @Valid CustomerVo userVo,
//		BindingResult result,
//		Model model) {
//		
//		if( result.hasErrors() ) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) {
//				System.out.println(error);
//			}
//			model.addAllAttributes(result.getModel());	
//			return "user/join";
//		}
//		
//		userService.join(userVo);
//		return "redirect:/user/joinsuccess";
//	}

//	@RequestMapping("/joinsuccess")
//	public String joinSuccess() {
//		return "user/joinsuccess";
//	}
//
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login() {
//		return "user/login";
//	}
//	
//	@Auth
//	@RequestMapping( value="/update", method=RequestMethod.GET )
//	public String update(
//		@AuthUser CustomerVo authUser,
//		Model model ){
//		CustomerVo userVo = userService.getUser( authUser.getNo() );
//		model.addAttribute( "userVo", userVo );
//		return "user/update";
//	}
//	
//	@RequestMapping( value="/update", method=RequestMethod.POST )
//	public String update( HttpSession session, @ModelAttribute CustomerVo userVo ){
//		CustomerVo authUser = (CustomerVo)session.getAttribute("authUser");
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		
//		userVo.setNo( authUser.getNo() );
//		userService.updateUser( userVo );
//		
//		// session의 authUser 변경
//		authUser.setName(userVo.getName());
//		
//		return "redirect:/user/update?result=success";
//	}

	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void auth(){}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(){}
	
//	@ExceptionHandler( Exception.class )
//	public String handleUserDaoException() {
//		System.out.println("!!!!!!!!!!!");
//		return "error/exception";
//	}
	
	
}
