package com.cafe24.mysite.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.vo.CartVo;
import com.cafe24.mysite.vo.ProductDetailVo;
import com.cafe24.mysite.vo.ProductImageVo;
import com.cafe24.mysite.vo.ProductVo;

@Repository
public class ProductProvider {
	String BASE_URL = "http://localhost:8081";

	@Autowired
	private RestTemplate restTemplate;
//	@Autowired
//	OAuth2RestTemplate restTemplate;
	public JSONResultProduct get_product_list(Long no) {
		// 전체 상품 가져오기 
		RestTemplate restTemplate = new RestTemplate();
		JSONResultProduct product_list = restTemplate.getForObject(BASE_URL+"/api/product/all/"+no, JSONResultProduct.class);		
		return product_list;
	}
	
	public JSONResult2<List<ProductVo>> get_product_detail_list(Long product_no) {
		// 해당하는 상품 가져오기 
//		JSONResultProduct product_list = restTemplate.getForObject(BASE_URL+"/api/product/"+product_no+"/detail", JSONResultProduct.class);
		JSONResult2<List<ProductVo>> product_vo= restTemplate.getForObject(BASE_URL+"/api/product/detail/all/"+product_no, JSONResultProduct.class);
		return product_vo;
	}
	
	
	

	public JSONResult2<List<ProductVo>> get_product_list_by_categoryno(Long category_no) {
		JSONResult2<List<ProductVo>> product_list= restTemplate.getForObject(BASE_URL+"/api/product/admin/"+category_no, JSONResultProduct.class);
		return product_list;
	}
	

	public JSONResult2<Long> add_product(ProductVo productvo) {
		JSONResult2<Long> no = restTemplate.postForObject(BASE_URL+"/api/product", productvo, JSONResultLong.class);
		return no;
	}

	public JSONResult2<List<ProductDetailVo>> add_product_detail(List<ProductDetailVo> product_detail_list) {
		JSONResult2<List<ProductDetailVo>> _product_detail_list = restTemplate.postForObject(BASE_URL+"/api/product/"+product_detail_list.get(0).getProduct_no()+"/detail", product_detail_list, JSONResultProductDetail.class);
		return _product_detail_list;
	}
	
	public JSONResult2<List<ProductImageVo>> add_product_image(List<ProductImageVo> product_image_list) {
		JSONResult2<List<ProductImageVo>> _product_image_list = restTemplate.postForObject(BASE_URL+"/api/product/image", product_image_list, JSONResultProductImage.class);
		return _product_image_list;
	}

	public JSONResult2<Integer> insert_cart_list(Long customer_no, CartVo cartvo) {
		JSONResult2<Integer> insert_cart_result_no = restTemplate.postForObject(BASE_URL+"/api/cart/"+customer_no, cartvo.getCartvo_list(), JSONResultInteger.class);
		return insert_cart_result_no;
	}

	public JSONResult2<List<CartVo>> get_cart_list(Long customer_no) {
		JSONResult2<List<CartVo>> cart_vo_list = restTemplate.getForObject(BASE_URL+"/api/cart/"+customer_no, JSONResultCart.class);
		return cart_vo_list;
	}
	
	
	//DTOa
	public static class JSONResultProduct extends JSONResult2<List<ProductVo>>{}
	public static class JSONResultProductDetail extends JSONResult2<List<ProductDetailVo>>{}
	public static class JSONResultProductImage extends JSONResult2<List<ProductImageVo>>{}
	public static class JSONResultCart extends JSONResult2<List<CartVo>>{}
	public static class JSONResultLong extends JSONResult2<Long>{}
	public static class JSONResultInteger extends JSONResult2<Integer>{}
	
}
