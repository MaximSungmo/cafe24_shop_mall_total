package com.cafe24.mysite.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.vo.CustomerVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;

@Controller("customerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {
	
//	@Autowired
//	private CustomerService customerService;
	
	/**
	 * Email 중복을 검증
	 * @param email
	 * @return String email
	 */
	@ResponseBody
	@RequestMapping(value="/checkemail", method = RequestMethod.GET)
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
//		String checked_email = customerService.existEmail(email);
	
		CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
		String checked_email = vo.getEmail();
		if(!(email.equals(checked_email))) {
			return JSONResult.fail("x");
		};
		return JSONResult.success(checked_email);
	}
	
	/**
	 * 회원가입 처리(@Valid로 입력 값 유효성 server-side 검증)
	 * @param vo
	 * @return 
	 */
//	@ApiOperation(value="회원가입")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name="vo", value=
//				"	* 이메일: 한글, 영문 2~자까지\r\n" + 
//				"	* 패스워드: 숫자 8자\r\n" + 
//				"	* 전화번호: 숫자 10~11자리\r\n" + 
//				"	* 성별: M,F\r\n" +
//				"	* 사용여부: Y, N Default 'Y'\r\n"+
//				"	* 회원권한: MEMBER, ADMIN Default 'MEMBER'\r\n" + 
//				"	* 추천회원: 숫자 한자리 또는 두자리\r\n"+
//				"	* 약관동의서번호: 숫자\r\n"+
//				"	* 동의여부:Y,N \r\n "+
//				"	* 동의일자: DATATIME\r\n",	
//				required=true, dataType="CustomerVo", defaultValue="")
//	})
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(@RequestBody @Valid CustomerVo vo,
			BindingResult bindResult
			) {
		// ### @Valid 통과 불가할 시 error 전달
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		// 회원번호 되돌려받기 .
//		Long join_result = customerService.join(vo);
		return ResponseEntity.status(HttpStatus.OK)
				.body(JSONResult.success(vo));
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public JSONResult update(@RequestParam(value="email", required=true, defaultValue="") String email) {
//		String checked_email = customerService.existEmail(email);
	
		CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
		String checked_email = vo.getEmail();
		if(!(email.equals(checked_email))) {
			return JSONResult.fail("x");
		};
		return JSONResult.success(checked_email);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public JSONResult withdraw(@RequestParam(value="email", required=true, defaultValue="") String email) {
//		String checked_email = customerService.existEmail(email);
	
		CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
		String checked_email = vo.getEmail();
		if(!(email.equals(checked_email))) {
			return JSONResult.fail("x");
		};
		return JSONResult.success(checked_email);
	}
	
	
	
	
}
