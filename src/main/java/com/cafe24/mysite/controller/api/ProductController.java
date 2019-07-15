package com.cafe24.mysite.controller.api;

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

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.ProductService;
import com.cafe24.mysite.vo.ProductVo;

@Controller("productAPIController")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//상품조회, 상품상세조회, 상품등록, 상품수정, 상품삭제
	
	/** 
	 * product 조회 
	 * @param 
	 * @return String email
	 */
	@ResponseBody
	@RequestMapping(value= {""}, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_product(@RequestBody ProductVo vo) {
//		 데이터가 정상적으로 DB에 입력이 되면 true 값을 반환한다. 
		Boolean insert_result = productService.add_product(vo);
		if(insert_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}
	} 
	
	/**
	 * product 정보 업데이트 
	 * @param vo
	 * @param bindResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= {"/{no}"}, method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_product(
			@PathVariable Long no,
			@RequestBody @Valid ProductVo vo,
			BindingResult bindResult) {
		
		// ### @Valid 통과 불가할 시 error 전달
//		if(bindResult.hasErrors()) {
//			List<ObjectError> list = bindResult.getAllErrors();
//			for(ObjectError error : list) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
//			}
//		}
		return null;
		
//		입력 데이터가 동일한 지 확인
//		if(no.equals(vo.getNo()){
////			 데이터가 정상적으로 DB에 입력이 되면 true 값을 반환한다. 
//			Boolean insert_result = productService.update_product(vo);
//			if(insert_result) {
//				return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
//			}else {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
//			}	
		}

	
	
	@ResponseBody
	@RequestMapping(value= {"/{no}"}, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_product(@RequestBody ProductVo vo) {
//		 데이터가 정상적으로 DB에서 삭제가 되면 true 값을 반환한다. 
		Boolean insert_result = productService.delete_product(vo);
		if(insert_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}	
	}
	
	
//	/**
//	 * 회원가입 처리(@Valid로 입력 값 유효성 server-side 검증)
//	 * @param vo
//	 * @return 
//	 */
//	@ResponseBody
//	@RequestMapping(value="", method = RequestMethod.POST)
//	public ResponseEntity<JSONResult> join(@RequestBody @Valid CustomerVo vo,
//			BindingResult bindResult
//			) {
//		// ### @Valid 통과 불가할 시 error 전달
//		if(bindResult.hasErrors()) {
//			List<ObjectError> list = bindResult.getAllErrors();
//			for(ObjectError error : list) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
//			}
//		}
//		// 데이터가 정상적으로 DB에 입력이 되면 true 값을 반환한다. 
//		Boolean insert_result = customerService.join(vo);
//		if(insert_result) {
//			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo.getEmail()));
//		}else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
//		}		
//	}
//	
//	/**
//	 * 수정 요청한 정보(param:vo)로 고객의 정보를 업데이트
//	 * @Auth를 통해서 고객 정보 비교가 필요함 
//	 * @param vo
//	 * @param bindResult
//	 * @return
//	 */
////	@Auth
//	@ResponseBody
//	@RequestMapping(value="/{no}", method = RequestMethod.PUT)
//	public ResponseEntity<JSONResult> update(@RequestBody @Valid CustomerVo vo,
//			BindingResult bindResult) {
//		// ### @Valid 통과 불가할 시 error 전달
//		if(bindResult.hasErrors()) {
//			List<ObjectError> list = bindResult.getAllErrors();
//			for(ObjectError error : list) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
//			}
//		}
//		Boolean update_result = customerService.update_user(vo);
//		//ResponseBody로 진행 전 비밀번호 표시하지 않기 위하여 공백처리 
//		vo.setPassword("");
//		if(update_result) {
//			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
//		}else {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("update-fail"));
//		}
//	}
//	 
//	/**
//	 * 회원정보를 받아 회원삭제-update하기(use_fl = 'N')
//	 * @param vo
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
//	public JSONResult withdraw(@RequestBody CustomerVo vo) {
//		Boolean delete_result = customerService.delete(vo);
//		return JSONResult.success(delete_result);
//	}
}