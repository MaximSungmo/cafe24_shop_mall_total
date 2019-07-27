package com.cafe24.shop.controller.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.CartService;
import com.cafe24.shop.service.CustomerService;
import com.cafe24.shop.vo.CartVo;
import com.cafe24.shop.vo.CustomerVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("CartAPIController")
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	@Autowired
	CustomerService customerService;
	

	@ApiOperation(value = "카트 추가(단일)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_detail_no", value = "product_detail_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "count", value = "count", dataType = "long", paramType = "query"),
        @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}/{product_detail_no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> add_cart(
			@PathVariable(value="customer_no") Long customer_no,
			@PathVariable(value="product_detail_no") Long product_detail_no,
			@RequestParam(value="count", defaultValue="1") Long count) {
		Integer inserted_cart_no=cartService.add_cart(customer_no, product_detail_no, count);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(inserted_cart_no));
	}
	
	@ApiOperation(value = "카트 추가(리스트)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "CartVo", value = "CartVo", dataType = "CartVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_cart_list(
			@PathVariable Optional<Long> customer_no
			, @RequestBody List<CartVo> cartvo_list) {
		
		// 회원 아이디 체크 
		System.out.println(cartvo_list);
		Integer inserted_cart_no=cartService.add_cart_list(cartvo_list);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(inserted_cart_no));
	}
	
	
	@ApiOperation(value = "카트 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_detail_list(@PathVariable Long customer_no) {
		List<CartVo> get_product_detail_list = cartService.get_cart_list(customer_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(get_product_detail_list));
	}
	
	@ApiOperation(value = "카트 정보 업데이트")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "CartVo", value = "CartVo", dataType = "CartVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}" }, method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_cart_list(
			@PathVariable(value="customer_no") Long customer_no,
			@RequestBody List<CartVo> cartvo_list) {
		
		Integer updated_cart_no = cartService.update_cart_list(cartvo_list);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(updated_cart_no));
	}
	
	@ApiOperation(value = "카트 정보 삭제")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
         @ApiImplicitParam(name = "CartVo", value = "CartVo", dataType = "CartVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_cart_list(
			@PathVariable(value="customer_no") Long customer_no,
			@RequestBody List<CartVo> cartvo_list) {
		Integer deleted_cart_no = cartService.delete_cart_list(cartvo_list);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleted_cart_no));
	}
	
	
	
}
