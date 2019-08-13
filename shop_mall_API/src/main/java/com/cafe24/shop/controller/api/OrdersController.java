package com.cafe24.shop.controller.api;

import java.util.List;
import java.util.Optional;

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
import com.cafe24.shop.service.CustomerService;
import com.cafe24.shop.service.OrdersService;
import com.cafe24.shop.vo.OrdersDetailVo;
import com.cafe24.shop.vo.OrdersVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller()
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	@Autowired
	CustomerService customerService;
	

	@ApiOperation(value = "주문 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "OrdersVo", value = "OrdersVo", dataType = "OrdersVo", paramType = "body"),
        @ApiImplicitParam(name = "OrdersDetailVo", value = "OrdersDetailVo", dataType = "OrdersDetailVo", paramType = "body"),

    })
	@ResponseBody
	@RequestMapping(value = { "" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_cart(
			@RequestBody OrdersVo ordersvo) {
		
		List<OrdersDetailVo> orders_detail_list = ordersvo.getOrders_detail_list();
//		OrdersVo ordersvo =(OrdersVo) map.get("ordersvo");
//		List<OrdersDetailVo> orders_detail_list = (List<OrdersDetailVo>) map.get("orders_detail_list");
		
		Boolean inserted_orders_result=ordersService.add_orders(ordersvo, orders_detail_list);		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(inserted_orders_result));
	}
	
	@ApiOperation(value = "주문 조회")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_orders(
			@PathVariable(value="customer_no") Long customer_no) {
		List<OrdersVo> orders_list = ordersService.get_orders(customer_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orders_list));
	}
	
	
	@ApiOperation(value = "관리자 주문 조회")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name = "customer_no", value = "customer_no", dataType = "long", paramType = "path"),
    	 @ApiImplicitParam(name = "product_detail_no", value = "product_detail_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{customer_no}/{product_detail_no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_orders_by_admin(
			@PathVariable(value="customer_no") Optional<Long> customer_no
			, @PathVariable(value="product_detail_no") Optional<Long> product_detail_no) {
		Long checked_customer_no = customer_no.isPresent()? customer_no.get(): null;
		Long checked_product_detail_no = product_detail_no.isPresent()? product_detail_no.get(): null;

		List<OrdersVo> orders_list = ordersService.get_orders_by_admin(checked_customer_no, checked_product_detail_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orders_list));
	}
	
	
	// 일정 기간이 지나면 주문이 취소되는 것  QUARDZ로 구현 예정  
	// 또는 주문 취소 버튼 클릭 
	@ApiOperation(value = "주문 삭제")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name = "orders_no", value = "orders_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{orders_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_orders(
			@PathVariable(value="orders_no") Long orders_no) {
		Boolean deleted_orders_no = ordersService.delete_orders(orders_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleted_orders_no));
	}
}
