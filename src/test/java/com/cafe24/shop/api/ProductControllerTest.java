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
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(print()).build();
	}

	List<ProductVo> list = new ArrayList<ProductVo>();
	ProductVo vo1 = new ProductVo(1L, "상품1", "상품1상세내용", "상태1", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);
	ProductVo vo2 = new ProductVo(2L, "상품2", "상품2상세내용", "상태2", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);
	ProductVo vo3 = new ProductVo(3L, "상품3", "상품3상세내용", "상태3", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);
	ProductVo vo4 = new ProductVo(4L, "상품4", "상품4상세내용", "상태4", "N", 1L, "2019-07-14 00:00:00", 1L, 1L);

	ProductDetailVo detail_vo = new ProductDetailVo(1L, 1L, "사이즈270-1", "STOCK", 40L, 1L);
	ProductDetailVo detail_vo2 = new ProductDetailVo(2L, 2L, "사이즈270-2", "STOCK", 40L, 1L);
	ProductDetailVo detail_vo3 = new ProductDetailVo(3L, 3L, "사이즈270-3", "STOCK", 40L, 1L);
	List<ProductDetailVo> detail_list = new ArrayList<ProductDetailVo>();

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
		.perform(delete("/api/product/"+vo3.getNo())
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
		.content(new Gson().toJson(detail_vo)));
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
		.content(new Gson().toJson(vo3)));
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
