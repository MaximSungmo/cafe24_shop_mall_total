package com.cafe24.shop.controller.api;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.CustomerService;
import com.cafe24.shop.vo.CustomerVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("customerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CustomerService customerService;
	
	/** 
	 * Email 중복을 검증
	 * @param email
	 * @return String email
	 */ 
	@ApiOperation(value = "중복 이메일 확인")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "email", value = "email", dataType = "string", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value="/checkemail", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		boolean checked_email = customerService.exist_email(email);
		if(checked_email) { 
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(checked_email));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("Available_email"));
		}
	}
	
	/**
	 * 회원가입 처리(@Valid로 입력 값 유효성 server-side 검증)
	 * @param vo
	 * @return 
	 */
	@ApiOperation(value = "회원가입")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "CustomerVo", value = "CustomerVo", dataType = "CustomerVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> join(
			@RequestBody @Valid CustomerVo vo, BindingResult bindResult) {
		
		// ### @Valid 통과 불가할 시 error 전달 
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail(error.getDefaultMessage()));
			} 
		}
		System.out.println(vo);
		// 데이터가 정상적으로 DB에 입력이 되면 true 값을 반환한다. 
		Boolean insert_result = customerService.join(vo);
		if(insert_result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(insert_result));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}		
	}
	
	/**
	 * 수정 요청한 정보(param:vo)로 고객의 정보를 업데이트
	 * @Auth를 통해서 고객 정보 비교가 필요함 
	 * @param vo
	 * @param bindResult
	 * @return
	 */
	@ApiOperation(value = "회원정보 업데이트")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "CustomerVo", value = "CustomerVo", dataType = "CustomerVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="/{no}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update(@RequestBody @Valid CustomerVo vo,
			BindingResult bindResult) {
		// ### @Valid 통과 불가할 시 error 전달
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		Boolean update_result = customerService.update_customer(vo);
		//ResponseBody로 진행 전 비밀번호 표시하지 않기 위하여 공백처리 
		vo.setPassword("");
		if(update_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("update-fail"));
		}
	}
	 
	/**
	 * 회원정보를 받아 회원삭제-update하기(use_fl = 'N')
	 * @param vo
	 * @return
	 */
	@ApiOperation(value = "회원정보 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value="/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> withdraw(@PathVariable Long no) {
		Boolean delete_result = customerService.delete_customer(no);
		if(delete_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(delete_result));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("delete-fail"));
		}
	}
	
	
	@ApiOperation(value = "로그인 요청")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "CustomerVo", value = "CustomerVo", dataType = "CustomerVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> login(@RequestBody CustomerVo vo) {
		CustomerVo customer_vo = customerService.login(vo);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<CustomerVo>> validatorResults =
				validator.validateProperty(vo, "email");
		if( validatorResults.isEmpty()== false) {
			for(ConstraintViolation<CustomerVo> validatorResult : validatorResults ) {
//				String message = validatorResult.getMessage();
				String message = messageSource.getMessage("Email.customerVo.email", null, LocaleContextHolder.getLocale());
				JSONResult result = JSONResult.fail(message);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(customer_vo));
	}
	 
	@ApiOperation(value = "로그인 정보 가져오기")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "CustomerVo", value = "CustomerVo", dataType = "CustomerVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="/get_user", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> get_by_email(@RequestParam String email) {
		CustomerVo customer_vo = customerService.get_by_email(email);
				
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(customer_vo));
	}
	
	
	@ApiOperation(value = "회원 목록 가져오기")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "CustomerVo", value = "CustomerVo", dataType = "CustomerVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_list() {
		List<CustomerVo> customer_list = customerService.get_list();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(customer_list));
	}
	
	
	
	

	
	
	
}
