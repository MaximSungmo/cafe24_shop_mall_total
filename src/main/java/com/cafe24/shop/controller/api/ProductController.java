package com.cafe24.shop.controller.api;

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

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.service.ProductService;
import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("productAPIController")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	

	@ApiOperation(value = "상품 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "kwd", value = "kwd", dataType = "string", paramType = "query"),
    })
	@ResponseBody
	@RequestMapping(value = {"","/{no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_list(@PathVariable Optional<Long> no, 
			@RequestParam(value = "kwd", required = false, defaultValue = "") String kwd, 
			Model model) {
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
	@ApiOperation(value = "상품 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ProductVo", value = "ProductVo", dataType = "ProductVo", paramType = "body"),
    })
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
	@ApiOperation(value = "상품 업데이트")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "ProductVo", value = "ProductVo", dataType = "ProductVo", paramType = "body"),
    })
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

	@ApiOperation(value = "상품 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_product(@PathVariable Long no) {
//		 데이터가 정상적으로 DB에서 삭제가 되면 true 값을 반환한다. 
		Long insert_result = productService.delete_product(no);
		if (insert_result==no) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(insert_result));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("Server Error"));
		}
	}
	
	
	@ApiOperation(value = "상품 상세정보 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "ProductDetailVo", value = "ProductDetailVo", dataType = "ProductDetailVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_product_detail(
			@PathVariable Long product_no,
			@RequestBody ProductDetailVo vo) {
		vo.setNo(product_no);
		Long insert_product_detail_no=productService.add_product_detail(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(insert_product_detail_no));
	}
	
	@ApiOperation(value = "상품 상세정보 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_detail_list(@PathVariable Long product_no) {
		List<ProductDetailVo> get_product_detail_list = productService.get_product_detail_list(product_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(get_product_detail_list));
	}
	
	@ApiOperation(value = "상품 상세정보 업데이트")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "product_detail_no", value = "product_detail_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail/{product_detail_no}" }, method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_product_detail(
			@PathVariable(value="product_no") Long product_no,
			@PathVariable(value="product_no") Long product_detail_no) {
		Long updated_product_detail_no = productService.update_product_detail(product_detail_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(updated_product_detail_no));
	}
	
	@ApiOperation(value = "상품 상세정보 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ProductDetailVo", value = "ProductDetailVo", dataType = "ProductDetailVo", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail/{product_detail_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> updat1e_product_detail(
			@PathVariable(value="product_no") Long product_no,
			@PathVariable(value="product_no") Long product_detail_no) {
		Long deleted_product_detail_no = productService.delete_product_detail(product_no, product_detail_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(deleted_product_detail_no));
	}
	
	
}