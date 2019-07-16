package com.cafe24.shop.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.vo.CategoryVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("categoryAPIController")
@RequestMapping("/api/category")
public class CategoryController {
	
	
	@ApiOperation(value = "카테고리 조회")
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.GET)
	public JSONResult get_category_list() {
//		CategoryVo list = categoryService.get_category_list();

		List<CategoryVo> list = new ArrayList<CategoryVo>();
		list.add(new CategoryVo(1L, "1번카테고리", null));
		list.add(new CategoryVo(2L, "2번카테고리", 1L));
		list.add(new CategoryVo(3L, "3번카테고리", 2L));
		list.add(new CategoryVo(4L, "4번카테고리", 2L));		
		
		return JSONResult.success(list);
	}
	
	@ApiOperation(value = "카테고리 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CategoryVo", value = "CategoryVo", dataType = "CategoryVo"),
    })
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public JSONResult add_category(@RequestBody @Valid CategoryVo vo) {
		if(vo.getNo()==1L) {
			return JSONResult.fail("중복값");
		}
		
		return JSONResult.success(vo);
	}
	

}
