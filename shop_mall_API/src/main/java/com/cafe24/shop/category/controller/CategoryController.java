package com.cafe24.shop.category.controller;

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
import com.cafe24.shop.category.service.CategoryService;
import com.cafe24.shop.category.model.CategoryVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("categoryAPIController")
@RequestMapping("/api/category")
public class CategoryController {
	
	
	@Autowired
	CategoryService categoryService;
	
	
	@ApiOperation(value = "카테고리 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CategoryVo", value = "CategoryVo", dataType = "CategoryVo"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_category(@RequestBody @Valid CategoryVo vo, BindingResult bindResult) {	
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		// parent_no에 입력된 카테고리 번호는 기존에 존재하는 카테고리 번호가 맞는 지 확인 (번호가 있는 경우(true)에만 insert 작업 진행 
		Boolean check_available_parent_no = categoryService.get_category_by_no(vo.getParent_no());
		if(vo.getParent_no()==null || check_available_parent_no) {
			Boolean insert_result = categoryService.insert_category(vo);
			return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(insert_result));			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상위 카테고리 정보 오류가 발생하였습니다."));
		}
	}
	
	@ApiOperation(value = "카테고리 조회")
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_category_list() {
		List<CategoryVo> list = categoryService.get_category_list();
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}	
	
	@ApiOperation(value = "카테고리 업데이트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CategoryVo", value = "CategoryVo", dataType = "CategoryVo"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_category(@RequestBody @Valid CategoryVo vo, BindingResult bindResult) {
		
		if(bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for(ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		Boolean check_available_parent_no = categoryService.get_category_by_no(vo.getParent_no());
		if(vo.getParent_no()==null || check_available_parent_no) {
			categoryService.update_category(vo);
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상위 카테고리 정보 오류가 발생하였습니다."));
		}
	}
	
	
	//카테고리는 기본적으로 default 카테고리가 하나 생성된 후 삭제될 수 없도록 삭제 버튼을 막아 놓는다.
	//따라서 모든 카테고리가 삭제되는 경우에는 default 카테고리로 이동이 된다.
	
	@ApiOperation(value = "카테고리 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "category_no", value = "category_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{category_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_category(@RequestBody CategoryVo vo) {
//		 데이터가 정상적으로 DB에서 삭제가 되면 true 값을 반환한다. 
		Boolean delete_result = categoryService.delete_category(vo);
		if (delete_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(delete_result));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}
	}
	

}
