package com.cafe24.mysite.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.ProductVo;

@Controller("productAPIController")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 상품조회, 상품상세조회, 상품등록, 상품수정, 상품삭제

	@ResponseBody
	@RequestMapping(value = { "", "/{no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> index(@PathVariable Optional<Long> no, @RequestParam String kwd, Model model) {
		List<ProductVo> product_list;
		Long category_no = no.isPresent() ? no.get() : 1L;
		product_list = productService.get(category_no, kwd);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_list));
	}

	/**
	 * product insert, POST
	 * 
	 * @param ProductVo
	 * @return ResponseEntity.body(JSONResult.success(vo));
	 */
	@ResponseBody
	@RequestMapping(value = { "" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_product(@RequestBody ProductVo vo) {
//		 데이터가 정상적으로 DB에 입력이 되면 true 값을 반환한다. 
		Boolean insert_result = productService.add_product(vo);
		if (insert_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}
	}

	/**
	 * product 정보 업데이트
	 * 
	 * @param vo
	 * @param bindResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "" }, method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_product(@RequestBody @Valid ProductVo vo,
			BindingResult bindResult) {
		// ### @Valid 통과 불가할 시 error 전달
		if (bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		Long update_result = productService.update_product(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(update_result));
	}

	@ResponseBody
	@RequestMapping(value = { "/{no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_product(@PathVariable Long no) {
//		 데이터가 정상적으로 DB에서 삭제가 되면 true 값을 반환한다. 
		Boolean insert_result = productService.delete_product(no);
		if (insert_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(no));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}
	}
}