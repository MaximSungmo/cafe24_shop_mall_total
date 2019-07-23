package com.cafe24.shop.api;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.cafe24.shop.vo.CategoryVo;
import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {
	
	/*
	 * 테스트 데이터 생성
	 */
	@Autowired
	private SqlSession sqlSession;
	List<ProductVo> product_list = new ArrayList<ProductVo>();
	List<ProductDetailVo> detail_list = new ArrayList<ProductDetailVo>();

	public List<ProductVo> test_data() {
		//테스트용 데이터 생성
		CategoryVo category_vo = new CategoryVo(null, "임시카테고리1", null);
		sqlSession.insert("category.insert", category_vo);
		
		ProductVo vo1 = new ProductVo(null, "상품1", "상품1상세내용", "상태1", "Y", 1L, "2019-07-14 00:00:00", category_vo.getNo(), null);
		ProductVo vo2 = new ProductVo(null, "상품2", "상품2상세내용", "상태2", "Y", 1L, "2019-07-14 00:00:00", category_vo.getNo(), null);
		ProductVo vo3 = new ProductVo(null, "상품3", "상품3상세내용", "상태3", "Y", 1L, "2019-07-14 00:00:00", category_vo.getNo(), null);
		ProductVo vo4 = new ProductVo(null, "상품4", "상품4상세내용", "상태4", "N", 1L, "2019-07-14 00:00:00", category_vo.getNo(), null);
		product_list.add(vo1);
		product_list.add(vo2);
		product_list.add(vo3);
		product_list.add(vo4);
		
		for(int i=0; i<product_list.size(); i++) {
			sqlSession.insert("product.insert_product", product_list.get(0));
		}
		return product_list;
	}
	
	public List<ProductDetailVo> productdetail_test_data_() {
		//테스트용 데이터 생성
		Long product_vo1_no = product_list.get(1).getNo();
		Long product_vo2_no = product_list.get(2).getNo();
		Long product_vo3_no = product_list.get(3).getNo();

		ProductDetailVo detail_vo1 = new ProductDetailVo(null, product_vo1_no, "사이즈270-1", "STOCK", 40L, null);
		ProductDetailVo detail_vo2 = new ProductDetailVo(null, product_vo2_no, "사이즈270-2", "STOCK", 40L, null);
		ProductDetailVo detail_vo3 = new ProductDetailVo(null, product_vo3_no, "사이즈270-3", "STOCK", 40L, null);
		detail_list.add(detail_vo1);
		detail_list.add(detail_vo2);
		detail_list.add(detail_vo3);
		for(int i=0; i<detail_list.size(); i++) {
			sqlSession.insert("product.insert_productDetail", detail_list.get(0));
		}
		return detail_list;
	}
	
	
		
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(print()).build();
		
		product_list = test_data();
		detail_list = productdetail_test_data_();
	}

	

	/**
	 * get_product_list()
	 * 
	 * @throws Exception :상품 목록 가져오기.(param: category_no, kwd)
	 */
	@Test
	public void get_product_list() throws Exception {
		// ## get_product_list() 성공 테스트
		ResultActions resultActions = mockMvc
		.perform(get("/api/product/1?kwd=").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data[0].no").value("1")).andExpect(jsonPath("$.data[0].name").value("상품1"))
		.andExpect(jsonPath("$.data[0].description").value("상품1상세내용"))
		.andExpect(jsonPath("$.data[0].category_no").value("1"));

		ResultActions resultActions2 = mockMvc
		.perform(get("/api/product/?kwd=").contentType(MediaType.APPLICATION_JSON));
		resultActions2.andExpect(status().isOk()).andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data", hasSize(4))).andExpect(jsonPath("$.data[0].no").value("1"))
		.andExpect(jsonPath("$.data[0].name").value("상품1"))
		.andExpect(jsonPath("$.data[0].description").value("상품1상세내용"))
		.andExpect(jsonPath("$.data[0].category_no").value("1"));
 
		ResultActions resultActions3 = mockMvc
		.perform(get("/api/product/1?kwd=상품2").contentType(MediaType.APPLICATION_JSON));
		resultActions3.andExpect(status().isOk()).andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data[0].no").value("2")).andExpect(jsonPath("$.data[0].name").value("상품2"))
		.andExpect(jsonPath("$.data[0].description").value("상품2상세내용"))
		.andExpect(jsonPath("$.data[0].category_no").value("1"));

		ResultActions resultActions4 = mockMvc
		.perform(get("/api/product/?kwd=상품3").contentType(MediaType.APPLICATION_JSON));
		resultActions4.andExpect(status().isOk()).andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data[0].no").value("3")).andExpect(jsonPath("$.data[0].name").value("상품3"))
		.andExpect(jsonPath("$.data[0].description").value("상품3상세내용"))
		.andExpect(jsonPath("$.data[0].category_no").value("1"));
	}

	@Test
	public void add_product() throws Exception {
		ProductVo vo3 = new ProductVo(3L, "상품3", "상품3상세내용", "상태3", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);

		ResultActions resultActions = mockMvc
		.perform(post("/api/product")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(vo3)));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data.no").value(3))
		.andExpect(jsonPath("$.data.name").value("상품3"))
		.andExpect(jsonPath("$.data.description").value("상품3상세내용"));
	}

	@Test
	public void update_product() throws Exception {
		ProductVo vo3 = new ProductVo(3L, "상품3", "상품3상세내용", "상태3", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);

		ResultActions resultActions = mockMvc
		.perform(put("/api/product")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(vo3)));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(3));
	}

	@Test
	public void delete_product() throws Exception {

		ResultActions resultActions = mockMvc
		.perform(delete("/api/product/"+product_list.get(0))
		.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(3));
	}


	/*
	 * 상품 상세정보, product_detail
	 */
	@Test
	public void add_product_detail_success_test() throws Exception {
//		ProductDetailVo detail_vo = new ProductDetailVo(1L, 1L, "사이즈270-1", "STOCK", 40L, 1L);

		ResultActions resultActions = mockMvc
		.perform(post("/api/product/1/detail")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(detail_list.get(0))));
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(1));
	}
	
	@Test
	public void get_product_detail_list_success_test() throws Exception {
		// ## get_product_list() 성공 테스트
		ResultActions resultActions = mockMvc
		.perform(get("/api/product/2/detail").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status()
		.isOk()).andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data[0].no").value(2))
		.andExpect(jsonPath("$.data[0].product_no").value(2))
		.andExpect(jsonPath("$.data[0].product_option").value("사이즈270-2"));
	}

	@Test
	public void update_product_detail_success_test() throws Exception {
		ResultActions resultActions = mockMvc
		.perform(put("/api/product/3/detail/3")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new Gson().toJson(product_list.get(3))));
		resultActions.andExpect(status()
		.isOk()).andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(3));
		
	}
	
	@Test
	public void delete_product_detail_success_test() throws Exception {

		ResultActions resultActions = mockMvc
		.perform(delete("/api/product/3/detail/3")
		.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value("success"))
		.andExpect(jsonPath("$.data").value(3));
	}
}
