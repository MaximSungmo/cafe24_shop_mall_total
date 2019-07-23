package com.cafe24.shop.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.GuestbookService;
import com.cafe24.shop.vo.GuestbookVo;

@Controller("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService; 
	
//	@ResponseBody
//	@RequestMapping(value="/list/{no}", method=RequestMethod.GET)
//	public JSONResult list(@PathVariable(value="no") int no) {
//		List<GuestbookVo> list = guestbookService.getContentList();
//		return JSONResult.success(list);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public JSONResult add(@RequestBody GuestbookVo guestbookVo) {
		guestbookService.addContents(guestbookVo);
		return JSONResult.success(guestbookVo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method=RequestMethod.DELETE)
	public JSONResult delete(@RequestBody GuestbookVo vo) {
		boolean result = guestbookService.deleteContent(vo);
		return JSONResult.success(result ? vo.getNo() : -1);
	}
	
	@ResponseBody
	@RequestMapping(value ="/list/{lastNo}", method=RequestMethod.GET )
	public JSONResult timeline(@PathVariable("lastNo") Long lastNo){
		List<GuestbookVo> list = guestbookService.getContentList(lastNo);
		return JSONResult.success(list);
	}
}
