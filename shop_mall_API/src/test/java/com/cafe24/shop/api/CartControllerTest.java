package com.cafe24.shop.api;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.vo.CartVo;
import com.cafe24.shop.vo.CategoryVo;
import com.cafe24.shop.vo.CustomerVo;
import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductVo;
import com.cafe24.shop.vo.TermsOfUseVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class CartControllerTest {

	@Autowired
	private SqlSession sqlSession;
	
	//Test용 데이터 생성(DB)
	List<ProductVo> product_list = new ArrayList<ProductVo>();
	
	@Transactional
	public List<ProductVo> test_data_product() {
		//테스트용 데이터 생성
		CategoryVo category_vo1 = new CategoryVo(null, "임시카테고리1", null);
		CategoryVo category_vo2 = new CategoryVo(null, "임시카테고리2", null);

		sqlSession.insert("category.insert", category_vo1);
		sqlSession.insert("category.insert", category_vo2);

		ProductVo vo1 = new ProductVo(null, "상품1", "상품1상세내용", "상태1", "Y", 1L, null, category_vo1.getNo(), null);
		ProductVo vo2 = new ProductVo(null, "상품2", "상품2상세내용", "상태2", "Y", 1L, null, category_vo1.getNo(), null);
		ProductVo vo3 = new ProductVo(null, "상품3", "상품3상세내용", "상태3", "Y", 1L, null, category_vo2.getNo(), null);
		ProductVo vo4 = new ProductVo(null, "상품4", "상품4상세내용", "상태4", "N", 1L, null, category_vo2.getNo(), null);
		product_list.add(vo1);
		product_list.add(vo2);
		product_list.add(vo3);
		product_list.add(vo4);
		
		for(int i=0; i<product_list.size(); i++) {
			sqlSession.insert("product.insert_product", product_list.get(i));
		}
		return product_list;
	}
	
	List<ProductDetailVo> product_detail_list = new ArrayList<ProductDetailVo>();
	public List<ProductDetailVo> test_data_product_detail() {
		//테스트용 데이터 생성
		Long product_vo1_no = product_list.get(1).getNo();
		Long product_vo2_no = product_list.get(2).getNo();
		Long product_vo3_no = product_list.get(3).getNo();

		ProductDetailVo detail_vo1 = new ProductDetailVo(6L, product_vo1_no, "사이즈270-1", 100L, "STOCK", 40L, null);
		ProductDetailVo detail_vo2 = new ProductDetailVo(7L, product_vo2_no, "사이즈270-2", 200L, "STOCK", 40L, null);
		ProductDetailVo detail_vo3 = new ProductDetailVo(8L, product_vo3_no, "사이즈270-3", 300L, "STOCK", 40L, null);
		product_detail_list.add(detail_vo1);
		product_detail_list.add(detail_vo2);
		product_detail_list.add(detail_vo3);
		sqlSession.insert("product.insert_product_detail", product_detail_list);
		return product_detail_list;
	}
	
	List<TermsOfUseVo> termsofuse_list = new ArrayList<TermsOfUseVo>();
	// 조회 및 업데이트를 위한 사전 데이터 삽입
		public List<TermsOfUseVo> test_data_terms(){
			TermsOfUseVo term_vo1 = new TermsOfUseVo(null, "약관동의서1","약관동의내용1", "Y", "Y", null, null);
			TermsOfUseVo term_vo2 = new TermsOfUseVo(null, "약관동의서2","약관동의내용2", "Y", "Y", null, null);
			termsofuse_list.add(term_vo1);
			termsofuse_list.add(term_vo2);
			for(int i=0; i<termsofuse_list.size(); i++) {
				sqlSession.insert("termsofuse.insert", termsofuse_list.get(i));
			}		
			return termsofuse_list;
		}
		
	List<CustomerVo> customer_list = new ArrayList<CustomerVo>();
	public List<CustomerVo> test_data_customer(){
		termsofuse_list = test_data_terms();		
		Long termsofuse_vo1_no = termsofuse_list.get(0).getNo();
		Long termsofuse_vo2_no = termsofuse_list.get(0).getNo();
		CustomerVo vo1 = new CustomerVo(null, "성공테스트", "ksm5318@naver.com", "PASSWORD1!", "010-1234-1234", "M", termsofuse_vo1_no, "Y");
		CustomerVo vo2 = new CustomerVo(null, "성공테스트2", "EMAIL2@TEST.COM", "PASSWORD12!", "010-2222-2222", "M", termsofuse_vo2_no, "Y");
		customer_list.add(vo1);
		customer_list.add(vo2);
		for(int i=0; i<customer_list.size(); i++) {
			sqlSession.insert("customer.insert", customer_list.get(i));
		}
		return customer_list;
	}
	
	List<CartVo> cart_list = new ArrayList<CartVo>();
	public List<CartVo> test_data_cart(){
		CartVo cartvo1= new CartVo(null, customer_list.get(0).getNo(), product_detail_list.get(0).getNo(), 1L, null, null, null);
		CartVo cartvo2= new CartVo(null, customer_list.get(0).getNo(), product_detail_list.get(1).getNo(), 1L, null, null, null);
		CartVo cartvo3= new CartVo(null, customer_list.get(0).getNo(), product_detail_list.get(2).getNo(), 1L, null, null, null);
		cart_list.add(cartvo1);
		cart_list.add(cartvo2);
		cart_list.add(cartvo3);
		for(int i=0; i<cart_list.size(); i++) {
			sqlSession.insert("cart.insert_cart", cart_list.get(i));
		}
		return cart_list;
	}

	
	/**
	 * 기본 mockMvc 설정 및 applicationContext 주입 
	 */
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
	                  .alwaysDo(print())
	                  .build();
		termsofuse_list = test_data_terms();
		customer_list = test_data_customer();
		product_list = test_data_product();
		product_detail_list = test_data_product_detail();
		cart_list = test_data_cart();
	}
	
	
	@Test
	public void add_cart_success_test() throws Exception {
		// 임시 
		product_detail_list.get(0).setNo(6L);
		
		ResultActions resultActions = 
		mockMvc.perform(get("/api/cart/"
				+customer_list.get(0).getNo()+"/"
				+product_detail_list.get(0).getNo()
				+"?count=1")
				.contentType(MediaType.APPLICATION_JSON));				
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(1));
		
		// Count Request parameter없이 default 로 보내기 
		ResultActions resultActions2 = 
		mockMvc.perform(get("/api/cart/"
				+customer_list.get(0).getNo()+"/"
				+product_detail_list.get(0).getNo())
				.contentType(MediaType.APPLICATION_JSON));				
		
		resultActions2
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(1));
	}
	
	@Test
	public void add_cart_list_success_test() throws Exception {
		// 임시 
		product_detail_list.get(0).setNo(6L);
		product_detail_list.get(1).setNo(7L);
		product_detail_list.get(1).setNo(8L);
		
		ResultActions resultActions = 
		mockMvc.perform(post("/api/cart/"
				+cart_list.get(0).getCustomer_no())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cart_list)));				
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(3));		
	}
	
	@Test
	public void get_cart_list_success_test() throws Exception {
		
		ResultActions resultActions = 
		mockMvc.perform(get("/api/cart/"
				+customer_list.get(0).getNo())
				.contentType(MediaType.APPLICATION_JSON));				
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
//		.andExpect(jsonPath("$.data[0].no").value(cart_list.get(0).getNo()))
		.andExpect(jsonPath("$.data[0].customer_no").value(cart_list.get(0).getCustomer_no()))
		.andExpect(jsonPath("$.data[0].product_detail_no").value(cart_list.get(0).getProduct_detail_no()))
		.andExpect(jsonPath("$.data[0].productDetailVo.no").value(product_detail_list.get(0).getNo()))
//		.andExpect(jsonPath("$.data[0].productDetailVo.product_no").value(product_detail_list.get(0).getProduct_no()))
//		.andExpect(jsonPath("$.data[0].productDetailVo.product_option").value(product_detail_list.get(0).getProduct_option()))
		
//		.andExpect(jsonPath("$.data[1].no").value(cart_list.get(1).getNo()))
		.andExpect(jsonPath("$.data[1].customer_no").value(cart_list.get(1).getCustomer_no()))
		.andExpect(jsonPath("$.data[1].product_detail_no").value(cart_list.get(1).getProduct_detail_no()))
		.andExpect(jsonPath("$.data[1].productDetailVo.no").value(product_detail_list.get(1).getNo()))
//		.andExpect(jsonPath("$.data[1].productDetailVo.product_no").value(product_detail_list.get(1).getProduct_no()))
//		.andExpect(jsonPath("$.data[1].productDetailVo.product_option").value(product_detail_list.get(1).getProduct_option()))
		
//		.andExpect(jsonPath("$.data[2].no").value(cart_list.get(2).getNo()))
		.andExpect(jsonPath("$.data[2].customer_no").value(cart_list.get(2).getCustomer_no()))
		.andExpect(jsonPath("$.data[2].product_detail_no").value(cart_list.get(2).getProduct_detail_no()))
		.andExpect(jsonPath("$.data[2].productDetailVo.no").value(product_detail_list.get(2).getNo()));
//		.andExpect(jsonPath("$.data[2].productDetailVo.product_no").value(product_detail_list.get(2).getProduct_no()))
//		.andExpect(jsonPath("$.data[2].productDetailVo.product_option").value(product_detail_list.get(2).getProduct_option()));		
	}
	
	
	@Test
	public void update_cart_list_success_test() throws Exception {
		
		ResultActions resultActions = 
		mockMvc.perform(get("/api/cart/"
				+customer_list.get(0).getNo())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(cart_list)));				
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
//		.andExpect(jsonPath("$.data[0].no").value(cart_list.get(0).getNo()))
		.andExpect(jsonPath("$.data[0].customer_no").value(cart_list.get(0).getCustomer_no()))
		.andExpect(jsonPath("$.data[0].product_detail_no").value(cart_list.get(0).getProduct_detail_no()))
		.andExpect(jsonPath("$.data[0].productDetailVo.no").value(product_detail_list.get(0).getNo()))
//		.andExpect(jsonPath("$.data[0].productDetailVo.product_no").value(product_detail_list.get(0).getProduct_no()))
//		.andExpect(jsonPath("$.data[0].productDetailVo.product_option").value(product_detail_list.get(0).getProduct_option()))
		
//		.andExpect(jsonPath("$.data[1].no").value(cart_list.get(1).getNo()))
		.andExpect(jsonPath("$.data[1].customer_no").value(cart_list.get(1).getCustomer_no()))
		.andExpect(jsonPath("$.data[1].product_detail_no").value(cart_list.get(1).getProduct_detail_no()))
		.andExpect(jsonPath("$.data[1].productDetailVo.no").value(product_detail_list.get(1).getNo()))
//		.andExpect(jsonPath("$.data[1].productDetailVo.product_no").value(product_detail_list.get(1).getProduct_no()))
//		.andExpect(jsonPath("$.data[1].productDetailVo.product_option").value(product_detail_list.get(1).getProduct_option()))
		
//		.andExpect(jsonPath("$.data[2].no").value(cart_list.get(2).getNo()))
		.andExpect(jsonPath("$.data[2].customer_no").value(cart_list.get(2).getCustomer_no()))
		.andExpect(jsonPath("$.data[2].product_detail_no").value(cart_list.get(2).getProduct_detail_no()))
		.andExpect(jsonPath("$.data[2].productDetailVo.no").value(product_detail_list.get(2).getNo()));
//		.andExpect(jsonPath("$.data[2].productDetailVo.product_no").value(product_detail_list.get(2).getProduct_no()))
//		.andExpect(jsonPath("$.data[2].productDetailVo.product_option").value(product_detail_list.get(2).getProduct_option()));		
	}
	
	
	
	
	

}
