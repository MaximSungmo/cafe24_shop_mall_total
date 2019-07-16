package com.cafe24.shop.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.vo.CategoryVo;
import com.cafe24.shop.vo.CustomerVo;
import com.cafe24.shop.vo.GuestbookVo;

@Controller("categoryAPIController")
@RequestMapping("/api/category")
public class CategoryController {
	
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
	
	
	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public JSONResult add_category(@RequestBody CategoryVo vo) {
//		CategoryVo list = categoryService.get_category_list(vo);
		if(vo.getNo()==1L) {
			return JSONResult.fail("중복값");
		}
		
		return JSONResult.success(vo);
	}
	

}
