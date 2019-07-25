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
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "ProductDetailVo", value = "ProductDetailVo", dataType = "ProductDetailVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}/{product_detail_no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> add_cart(
			@PathVariable(value="customer_no") Long customer_no,
			@PathVariable(value="product_detail_no") Long product_detail_no,
			@RequestParam(value="count") Long count) {
		Integer inserted_cart_no=cartService.add_cart(customer_no, product_detail_no, count);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(inserted_cart_no));
	}
	
	@ApiOperation(value = "카트 추가(리스트)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "ProductDetailVo", value = "ProductDetailVo", dataType = "ProductDetailVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_cart_list(
			@PathVariable Optional<Long> customer_no
			, @RequestBody List<CartVo> cartvo_list
			, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String session_id = session.getId();	
		CustomerVo customervo = new CustomerVo(null, "비회원", session_id+"@.com", "PASSWORD1!", "010-1234-1234", "M", null, "Y");
		Long no ;
		if(customer_no.isPresent()) {
			no = customer_no.get();
		}
		else {
			//비회원 정보 입력하기
			customerService.join(customervo);
			no = customervo.getNo();
		}
		Integer inserted_cart_no=cartService.add_cart_list(no, cartvo_list);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(inserted_cart_no));
	}
//	@ApiOperation(value = "상품 상세정보 조회")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
//    })
//	@ResponseBody
//	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.GET)
//	public ResponseEntity<JSONResult> get_product_detail_list(@PathVariable Long product_no) {
//		List<ProductDetailVo> get_product_detail_list = productService.get_product_detail_list(product_no);
//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(get_product_detail_list));
//	}
//	
//	@ApiOperation(value = "상품 상세정보 업데이트")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
//        @ApiImplicitParam(name = "product_detail_no", value = "product_detail_no", dataType = "long", paramType = "path"),
//    })
//	@ResponseBody
//	@RequestMapping(value = { "/{product_no}/detail/{product_detail_no}" }, method = RequestMethod.PUT)
//	public ResponseEntity<JSONResult> update_product_detail(
//			@PathVariable(value="product_no") Long product_no,
//			@PathVariable(value="product_no") Long product_detail_no) {
//		Long updated_product_detail_no = productService.update_product_detail(product_detail_no);
//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(updated_product_detail_no));
//	}
//	
//	@ApiOperation(value = "상품 상세정보 삭제")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "ProductDetailVo", value = "ProductDetailVo", dataType = "ProductDetailVo", paramType = "body"),
//    })
//	@ResponseBody
//	@RequestMapping(value = { "/{product_no}/detail/{product_detail_no}" }, method = RequestMethod.DELETE)
//	public ResponseEntity<JSONResult> updat1e_product_detail(
//			@PathVariable(value="product_no") Long product_no,
//			@PathVariable(value="product_no") Long product_detail_no) {
//		Long deleted_product_detail_no = productService.delete_product_detail(product_no, product_detail_no);
//		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleted_product_detail_no));
//	}
	
	
	
}
