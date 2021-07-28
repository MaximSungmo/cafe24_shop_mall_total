package com.cafe24.shop.brand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.common.dto.JSONResult;
import com.cafe24.shop.brand.service.BrandService;
import com.cafe24.shop.brand.model.BrandVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("BrandAPIController")
@RequestMapping("/api/brand")
public class BrandController {

	
	@Autowired
	BrandService brandService;
	
	
	@ApiOperation(value = "브랜드 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BrandVo", value = "BrandVo", dataType = "BrandVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_brand(@RequestBody @Valid BrandVo brandvo, BindingResult bindResult) {	
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		Boolean insert_result = brandService.insert_brand(brandvo);
		if(insert_result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(insert_result));			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("브랜드 정보가 정상적으로 등록되지 않았습니다."));
		}
	}
	
	@ApiOperation(value = "브랜드 조회")
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_brand_list() {
		List<BrandVo> list = brandService.get_brand_list();
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}	
	
	@ApiOperation(value = "브랜드 업데이트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BrandVo", value = "BrandVo", dataType = "BrandVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_brand(@RequestBody @Valid BrandVo brandvo, BindingResult bindResult) {	
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		Boolean update_result = brandService.update_brand(brandvo);
		if(update_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(brandvo));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("브랜드 정보가 정상적으로 수정되지 않았습니다."));
		}
	}
	
	
	@ApiOperation(value = "브랜드 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "BrandVo", value = "BrandVo", dataType = "BrandVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{brand_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_category(@RequestBody BrandVo vo) {
		Boolean delete_result = brandService.delete_brand(vo);
		if (delete_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(delete_result));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}
	}
	
}
