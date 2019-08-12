package com.cafe24.shop.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.cafe24.shop.service.CategoryService;
import com.cafe24.shop.service.ProductService;
import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductImageVo;
import com.cafe24.shop.vo.ProductVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller("productAPIController")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "상품 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "category_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "kwd", value = "kwd", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "get_count", value = "get_count", dataType = "long", paramType = "query"),
        @ApiImplicitParam(name = "last_product_no", value = "last_product_no", dataType = "long", paramType = "query"),
    })
	@ResponseBody
	@RequestMapping(value = {"","/{no}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_list(
			@PathVariable Optional<Long> no, 
			@RequestParam(value = "kwd", required = false, defaultValue = "") String kwd,
			@RequestParam(value="get_count", required=false, defaultValue= "30") Long get_count,
			@RequestParam(value="last_product_no", required=false, defaultValue= "0") Long last_product_no, 
			Model model) {
		
		Long category_no = no.isPresent() ? no.get() : 0;
		List<ProductVo> product_list = productService.get(category_no, kwd, get_count, last_product_no);
		if(!product_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("조건에 맞는 정보가 없습니다."));
		}
	}
	
	
	// 어드민에서 사용할 API 임시 작성
	@ResponseBody
	@RequestMapping(value = {"/admin","/admin/{no}"}, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_list(
			@PathVariable Optional<Long> no, 
			Model model) {
		// category_no 넘어오지 않을 시 0으로 정보 보내어 조건 없이 모든 정보 가져옴
		Long category_no = no.isPresent() ? no.get() : 0;
		List<ProductVo> product_list = productService.get_product_list_by_result_map(category_no);
		if(!product_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("조건에 맞는 정보가 없습니다."));
		}
	}
	
	@ApiOperation(value = "상품 상세 조회/화면에 모든 정보 가져오기")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = {"/all/{no}"}, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_list_by_result_map(
			@PathVariable(value="no") Optional<Long> no) {
		Long category_no = no.isPresent() ? no.get() : 0;
		List<ProductVo> product_list = productService.get_product_list_by_result_map(category_no);
		if(!product_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("조건에 맞는 정보가 없습니다."));
		}
	}
	
	
	@ApiOperation(value = "상품 1개, 상품 상세 조회/화면에 모든 정보 가져오기")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = {"/detail/all/{product_no}"}, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_list_by_result_map(
			@PathVariable(value="product_no") Long product_no) {
		List<ProductVo> product_vo = productService.get_product_by_product_no_result_map(product_no);
		if(product_vo!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_vo));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("조건에 맞는 정보가 없습니다."));
		}
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
	public ResponseEntity<JSONResult> add_product(@RequestBody @Valid ProductVo vo,
			BindingResult bindResult) {
		
		if (bindResult.hasErrors()) {
			List<ObjectError> list = bindResult.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}

		Boolean insert_result = productService.add_product(vo);
		if (insert_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo.getNo()));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("정상적으로 정보가 등록되지 않았습니다."));
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
		Boolean update_result = productService.update_product(vo);
		if(update_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("정상적으로 정보가 업데이트되지 않았습니다."));
		}
	}

	@ApiOperation(value = "상품 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_product(@PathVariable Long no) {
//		 데이터가 정상적으로 DB에서 삭제가 되면 true 값을 반환한다. 
		Boolean insert_result = productService.delete_product(no);
		if (insert_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(no));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("정상적으로 정보가 삭제되지 않았습니다."));
		}
	}
	
	
	
	
	@ApiOperation(value = "상품 상세정보 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "ProductDetailVo", value = "ProductDetailVo", dataType = "ProductDetailVo", paramType = "body"),
    })
	@Transactional
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_product_detail(
			@PathVariable Long product_no,
			@RequestBody List<ProductDetailVo> vo_list) {
		for(int i=0; i<vo_list.size(); i++) {
			vo_list.get(i).setProduct_no(product_no);
		}
		productService.add_product_detail(vo_list);
		if(!vo_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("정상적으로 정보가 등록되지 않았습니다."));
		}
	}
	
	@ApiOperation(value = "상품 상세정보 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_detail_list(@PathVariable Long product_no) {
		List<ProductDetailVo> get_product_detail_list = productService.get_product_detail_list(product_no);
		if(!get_product_detail_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(get_product_detail_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("조건에 맞는 정보가 없습니다."));
		}
	}
	
	@ApiOperation(value = "상품 상세정보 업데이트")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "product_detail_list", value = "product_detail_list", dataType = "list", paramType = "body"),
    })
	@Transactional
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_product_detail(
			@PathVariable(value="product_no") Long product_no,
			@RequestBody List<ProductDetailVo> product_detail_list) {
		productService.update_product_detail(product_detail_list);
		
		if(!product_detail_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_detail_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("정상적으로 정보가 업데이트되지 않았습니다."));
		}
	}
	
	@ApiOperation(value = "상품 상세정보 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_detail_no", value = "product_detail_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail/{product_detail_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_product_detail(
			@PathVariable(value="product_no") Long product_no,
			@PathVariable(value="product_detail_no") Long product_detail_no) {
		
		productService.delete_product_detail(product_no, product_detail_no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_detail_no));
	}
	
	
	@ApiOperation(value = "상품 상세정보 리스트 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_detail_list", value = "product_detail_list", dataType = "List", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/detail" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete_product_detail_list(
			@PathVariable(value="product_no") Long product_no,
			@RequestBody List<ProductDetailVo> product_detail_list) {
		productService.delete_product_detail_list(product_detail_list);	
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_detail_list));
	}
	
	
	@ApiOperation(value = "상품 이미지 리스트 추가")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_image_list", value = "product_image_list", dataType = "List", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/image" }, method = RequestMethod.POST)
	public ResponseEntity<JSONResult> add_product_image_list(
			@RequestBody List<ProductImageVo> product_image_list
			//, BindingResult bindResult
			) {		
//		if (bindResult.hasErrors()) {
//			List<ObjectError> list = bindResult.getAllErrors();
//			for (ObjectError error : list) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
//			}
//		}
		//입력 요청한 데이터와 실제 입력된 데이터의 결과 count가 같은 지 확인 
		Integer insert_product_image_result = productService.add_product_image_list(product_image_list);	
		if(insert_product_image_result>0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(product_image_list));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("Server Error"));
		}		
	}	
	
	
	@ApiOperation(value = "상품 이미지 리스트 가져오기")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "product_image_category_no", value = "product_image_category_no", dataType = "long", paramType = "query"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/image" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> get_product_image_list(
			@PathVariable Long  product_no,
			@RequestParam Long product_image_category_no
			) {
		ProductImageVo product_image_vo = new ProductImageVo();
		product_image_vo.setProduct_no(product_no);
		product_image_vo.setProduct_image_category_no(product_image_category_no);
		
		List<ProductImageVo> product_image_list = productService.get_product_image_list(product_image_vo);	
		//리스트가 있다면 정상 처리 , 없다면 정상처리(데이터가 없음)
		if(!product_image_list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_image_list));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("조건에 맞는 데이터가 없습니다."));
		}		
	}
	
	
	@ApiOperation(value = "상품 이미지 리스트 업데이트")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "product_image_list", value = "product_image_list", dataType = "list", paramType = "body"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/image" }, method = RequestMethod.PUT)
	public ResponseEntity<JSONResult> update_product_image_list(
			@PathVariable Long product_no,
			@RequestBody List<ProductImageVo> product_image_list
			) {
		Boolean update_result = productService.update_product_image_list(product_image_list, product_no);	
		//update가 정상적으로 되었으면 true, 아니라면 false
		if(update_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(update_result));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("Server Error"));
		}		
	}
	
	@ApiOperation(value = "상품 이미지 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product_no", value = "product_no", dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "product_image_no", value = "product_image_no", dataType = "long", paramType = "path"),
    })
	@ResponseBody
	@RequestMapping(value = { "/{product_no}/image/{product_image_no}" }, method = RequestMethod.DELETE)
	public ResponseEntity<JSONResult> update_product_image_list(
			@PathVariable Long product_no,
			@PathVariable Long product_image_no
			) {
		Boolean delete_result = productService.delete_product_image(product_image_no);	
		//update가 정상적으로 되었으면 true, 아니라면 false
		if(delete_result) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(product_image_no));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("Server Error"));
		}		
	}
	
	
	
}