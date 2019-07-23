package com.cafe24.shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	public ResponseEntity<JSONResult> add_terms_of_use_template(@RequestBody TermsOfUseVo vo) {
		termsOfUseVoService.insert_terms_of_use_template(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		
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
	public ResponseEntity<JSONResult> update_terms_of_use(@RequestBody TermsOfUseVo vo) {
		termsOfUseVoService.update_terms_of_use_template(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
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
