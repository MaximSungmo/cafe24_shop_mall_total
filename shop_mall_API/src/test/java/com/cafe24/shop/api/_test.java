package com.cafe24.shop.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shop.repository.ProductDao;
import com.cafe24.shop.vo.CategoryVo;
import com.cafe24.shop.vo.ProductDetailVo;
import com.cafe24.shop.vo.ProductVo;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class _test {
	
	/*
	 * 테스트 데이터 생성
	 */

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;


	@Autowired
	private ProductDao productDao;
	@Autowired
	private SqlSession sqlSession;
	List<ProductVo> product_list = new ArrayList<ProductVo>();
	List<ProductDetailVo> detail_list = new ArrayList<ProductDetailVo>();
	List<ProductVo> product_list2 = new ArrayList<ProductVo>();

	List<CategoryVo> ca_list = new ArrayList<CategoryVo>();
	
	CategoryVo category_vo1;
	CategoryVo category_vo2;
	@Transactional
	public List<ProductVo> test_data() {
		//테스트용 데이터 생성
		category_vo1 = new CategoryVo(null, "임시카테고리1", null);
		category_vo2 = new CategoryVo(null, "임시카테고리2", null);

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
	
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(print()).build();
		product_list = test_data();
	}
	@Test
	public void test() {
//		product_list2=productDao.test();
		for(int i=0; i<product_list2.size(); i++) {
			System.out.println(product_list2.get(i));
		}
	}
	
	@Test
	public void tes2t() {
		ca_list=sqlSession.selectList("category.selectTest");
		for(int i=0; i<ca_list.size(); i++) {
			System.out.println(ca_list.get(i)+"--------------------");
		}
	}
	
	
	
}
