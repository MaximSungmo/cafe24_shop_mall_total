package com.cafe24.shop.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.TermsOfUseTemplateService;
import com.cafe24.shop.vo.TermsOfUseVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("TermsOfUseAPIController")
@RequestMapping("/api/terms")
public class TermsOfUseTemplateController {
	@Autowired
	TermsOfUseTemplateService termsOfUseVoService;

	
	/*
	 * 회원약관동의서 관리(CRUD)
	 */
	@ApiOperation(value = "회원약관동의서 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "TermsOfUseVo", value = "TermsOfUseVo", dataType = "TermsOfUseVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_terms_of_use_template(
			@RequestBody @Valid TermsOfUseVo vo,
			BindingResult bindResult) {
		// ### @Valid 통과 불가할 시 error 전달
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		Boolean insert_result = termsOfUseVoService.insert_terms_of_use_template(vo);
		if(insert_result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}	
	}	
	
	
	@ApiOperation(value = "회원약관동의서 조회")
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_terms_of_use_template_list() {
		List<TermsOfUseVo> terms_of_use_template_list = termsOfUseVoService.get_terms_of_use_template_list();
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(terms_of_use_template_list));
	}	

	@ApiOperation(value = "회원약관동의서 업데이트")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "TermsOfUseVo", value = "TermsOfUseVo", dataType = "TermsOfUseVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_terms_of_use(@RequestBody @Valid TermsOfUseVo vo,
			BindingResult bindResult) {
		
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		
		Boolean update_result = termsOfUseVoService.update_terms_of_use_template(vo);
		
		if(update_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}	
	}
	
	@ApiOperation(value = "회원약관동의서 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "no", value = "terms_of_use_template_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value="/{no}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_terms_of_use(@PathVariable Long no) {
		termsOfUseVoService.delete_terms_of_use_template(no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(no));
	}
}
