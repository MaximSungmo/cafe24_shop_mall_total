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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Ignore;
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
import com.cafe24.shop.vo.ProductImageCategoryVo;
import com.cafe24.shop.vo.ProductImageVo;
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
  List<ProductImageVo> image_list = new ArrayList<ProductImageVo>();

  CategoryVo category_vo1;
  CategoryVo category_vo2;

  @Transactional
  public List<ProductVo> test_data() {
    //테스트용 데이터 생성
    category_vo1 = new CategoryVo(null, "임시카테고리1", null);
    category_vo2 = new CategoryVo(null, "임시카테고리2", null);

    sqlSession.insert("category.insert", category_vo1);
    sqlSession.insert("category.insert", category_vo2);

    ProductVo vo1 = new ProductVo(null, "상품1", "상품1상세내용", "상태1", "Y", 1L, null,
        category_vo1.getNo(), null);
    ProductVo vo2 = new ProductVo(null, "상품2", "상품2상세내용", "상태2", "Y", 1L, null,
        category_vo1.getNo(), null);
    ProductVo vo3 = new ProductVo(null, "상품3", "상품3상세내용", "상태3", "Y", 1L, null,
        category_vo2.getNo(), null);
    ProductVo vo4 = new ProductVo(null, "상품4", "상품4상세내용", "상태4", "N", 1L, null,
        category_vo2.getNo(), null);
    product_list.add(vo1);
    product_list.add(vo2);
    product_list.add(vo3);
    product_list.add(vo4);

    for (int i = 0; i < product_list.size(); i++) {
      sqlSession.insert("product.insert_product", product_list.get(i));
    }
    return product_list;
  }

  public List<ProductDetailVo> product_detail_test_data_() {
    //테스트용 데이터 생성
    Long product_vo1_no = product_list.get(1).getNo();
    Long product_vo2_no = product_list.get(2).getNo();
    Long product_vo3_no = product_list.get(3).getNo();

    ProductDetailVo detail_vo1 = new ProductDetailVo(501L, product_vo1_no, "사이즈270-1", 100L,
        "STOCK", 40L, null);
    ProductDetailVo detail_vo2 = new ProductDetailVo(502L, product_vo2_no, "사이즈270-2", 200L,
        "STOCK", 40L, null);
    ProductDetailVo detail_vo3 = new ProductDetailVo(503L, product_vo3_no, "사이즈270-3", 300L,
        "STOCK", 40L, null);
    detail_list.add(detail_vo1);
    detail_list.add(detail_vo2);
    detail_list.add(detail_vo3);
    sqlSession.insert("product.insert_product_detail", detail_list);
    return detail_list;
  }

  @Transactional
  public List<ProductImageVo> product_image_test_data() {

    // product_image_caregory 등록
    ProductImageCategoryVo productImageCategoryVo1 = new ProductImageCategoryVo(null, "메인화면이미지1",
        null, null, "Y");
    ProductImageCategoryVo productImageCategoryVo2 = new ProductImageCategoryVo(null, "메인화면이미지2",
        null, null, "Y");
    ProductImageCategoryVo productImageCategoryVo3 = new ProductImageCategoryVo(null, "메인화면이미지3",
        null, null, "Y");
    sqlSession.insert("product.insert_product_image_category", productImageCategoryVo1);
    sqlSession.insert("product.insert_product_image_category", productImageCategoryVo2);
    sqlSession.insert("product.insert_product_image_category", productImageCategoryVo3);

    Long product_vo1_no = product_list.get(1).getNo();
    Long product_vo2_no = product_list.get(2).getNo();
    Long product_vo3_no = product_list.get(3).getNo();

    ProductImageVo image_vo1_1 = new ProductImageVo(101L, product_vo1_no, "URL 1-1", null, "Y",
        productImageCategoryVo1.getNo());
    ProductImageVo image_vo1_2 = new ProductImageVo(102L, product_vo1_no, "URL 1-2", null, "Y",
        productImageCategoryVo2.getNo());
    ProductImageVo image_vo1_3 = new ProductImageVo(103L, product_vo1_no, "URL 1-3", null, "Y",
        productImageCategoryVo3.getNo());
    image_list.add(image_vo1_1);
    image_list.add(image_vo1_2);
    image_list.add(image_vo1_3);

    ProductImageVo image_vo2_1 = new ProductImageVo(104L, product_vo2_no, "URL 2-1", null, "Y",
        productImageCategoryVo1.getNo());
    ProductImageVo image_vo2_2 = new ProductImageVo(105L, product_vo2_no, "URL 2-2", null, "Y",
        productImageCategoryVo2.getNo());
    ProductImageVo image_vo2_3 = new ProductImageVo(106L, product_vo2_no, "URL 2-3", null, "Y",
        productImageCategoryVo3.getNo());
    image_list.add(image_vo2_1);
    image_list.add(image_vo2_2);
    image_list.add(image_vo2_3);

    ProductImageVo image_vo3_1 = new ProductImageVo(107L, product_vo3_no, "URL 3-1", null, "Y",
        productImageCategoryVo1.getNo());
    ProductImageVo image_vo3_2 = new ProductImageVo(108L, product_vo3_no, "URL 3-2", null, "Y",
        productImageCategoryVo2.getNo());
    ProductImageVo image_vo3_3 = new ProductImageVo(109L, product_vo3_no, "URL 3-3", null, "Y",
        productImageCategoryVo3.getNo());
    image_list.add(image_vo3_1);
    image_list.add(image_vo3_2);
    image_list.add(image_vo3_3);

    sqlSession.insert("product.insert_product_image_list", image_list);
    return image_list;
  }


  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(print()).build();

    product_list = test_data();
    detail_list = product_detail_test_data_();
    image_list = product_image_test_data();
  }


  /**
   * get_product_list()
   *
   * @throws Exception :상품 목록 가져오기.(param: category_no, kwd)
   */
  @Ignore
  @Test
  public void get_product_list_success_test() throws Exception {
    // ## get_product_list() 성공 테스트
    ResultActions resultActions = mockMvc
        .perform(get("/api/product/" + category_vo1.getNo() + "?kwd=")
            .contentType(MediaType.APPLICATION_JSON));
    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(product_list.get(0).getNo()))
        .andExpect(jsonPath("$.data[0].name").value(product_list.get(0).getName()))
        .andExpect(jsonPath("$.data[0].description").value(product_list.get(0).getDescription()))
        .andExpect(jsonPath("$.data[0].category_no").value(product_list.get(0).getCategory_no()));

    ResultActions resultActions2 = mockMvc
        .perform(get("/api/product/?kwd=").contentType(MediaType.APPLICATION_JSON));
    resultActions2
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(product_list.get(0).getNo()))
        .andExpect(jsonPath("$.data[0].name").value(product_list.get(0).getName()))
        .andExpect(jsonPath("$.data[0].description").value(product_list.get(0).getDescription()))
        .andExpect(jsonPath("$.data[0].category_no").value(product_list.get(0).getCategory_no()));

    ResultActions resultActions3 = mockMvc
        .perform(get("/api/product/" + category_vo1.getNo() + "?kwd=상품2")
            .contentType(MediaType.APPLICATION_JSON));
    resultActions3.andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(product_list.get(1).getNo()))
        .andExpect(jsonPath("$.data[0].name").value(product_list.get(1).getName()))
        .andExpect(jsonPath("$.data[0].description").value(product_list.get(1).getDescription()))
        .andExpect(jsonPath("$.data[0].category_no").value(product_list.get(1).getCategory_no()));

    ResultActions resultActions4 = mockMvc
        .perform(get("/api/product/?kwd=상품3").contentType(MediaType.APPLICATION_JSON));
    resultActions4.andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(product_list.get(2).getNo()))
        .andExpect(jsonPath("$.data[0].name").value(product_list.get(2).getName()))
        .andExpect(jsonPath("$.data[0].description").value(product_list.get(2).getDescription()))
        .andExpect(jsonPath("$.data[0].category_no").value(product_list.get(2).getCategory_no()));
  }

  @Test
  public void get_product_list_fail_test() throws Exception {
    // ## get_product_list() 성공 테스트
    ResultActions resultActions = mockMvc
        .perform(get("/api/product/" + category_vo1.getNo() + "?kwd=xxx")
            .contentType(MediaType.APPLICATION_JSON));
    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("fail"))
        .andExpect(jsonPath("$.message").value("조건에 맞는 정보가 없습니다."));
  }

  @Test
  public void add_product_success_test() throws Exception {
    ProductVo vo = new ProductVo(null, "상품4", "상품4상세내용", "상태4", "N", 1L, null, category_vo2.getNo(),
        null);
    ResultActions resultActions = mockMvc
        .perform(post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(vo)));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"));
//		.andExpect(jsonPath("$.data.name").value(vo.getName()))
//		.andExpect(jsonPath("$.data.description").value(vo.getDescription()))
//		.andExpect(jsonPath("$.data.category_no").value(vo.getCategory_no()));
  }

  @Test
  public void add_product_vaild_fail_test() throws Exception {
    //name is null
    ProductVo vo = new ProductVo(null, null, "상품4상세내용", "상태4", "N", 1L, null, category_vo2.getNo(),
        null);
    ResultActions resultActions = mockMvc
        .perform(post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(vo)));

    resultActions
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.result").value("fail"));
  }

  @Test
  public void update_product_success_test() throws Exception {
    product_list.get(3).setName("변경된카테고리 이름");
    product_list.get(3).setDescription("변경된카테고리 설명");

    ResultActions resultActions = mockMvc
        .perform(put("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(product_list.get(3))));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data.no").value(product_list.get(3).getNo()))
        .andExpect(jsonPath("$.data.name").value(product_list.get(3).getName()))
        .andExpect(jsonPath("$.data.description").value(product_list.get(3).getDescription()))
        .andExpect(jsonPath("$.data.category_no").value(product_list.get(3).getCategory_no()));
  }

  @Test
  public void update_product_vaild_fail_test() throws Exception {
    product_list.get(3).setName(null);
    product_list.get(3).setDescription("변경된카테고리 설명");

    ResultActions resultActions = mockMvc
        .perform(put("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(product_list.get(3))));

    resultActions
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.result").value("fail"));
  }

  @Test
  public void delete_product_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(delete("/api/product/" + product_list.get(0).getNo())
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data").value(product_list.get(0).getNo()));
  }


  /*
   * 상품 상세정보, product_detail
   */
  @Test
  public void add_product_detail_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(post("/api/product/" + detail_list.get(0).getProduct_no() + "/detail")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(detail_list)));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].product_no").value(detail_list.get(0).getProduct_no()))
        .andExpect(
            jsonPath("$.data[0].product_option").value(detail_list.get(0).getProduct_option()));
  }

  @Test
  public void get_product_detail_list_success_test() throws Exception {
    ResultActions resultActions = mockMvc
        .perform(get("/api/product/" + detail_list.get(0).getProduct_no() + "/detail")
            .contentType(MediaType.APPLICATION_JSON));
    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].product_no").value(detail_list.get(0).getProduct_no()))
        .andExpect(
            jsonPath("$.data[0].product_option").value(detail_list.get(0).getProduct_option()));
  }


  @Test
  public void update_product_detail_success_test() throws Exception {
    detail_list.get(0).setPrice(500L);
    detail_list.get(1).setPrice(500L);
    detail_list.get(2).setPrice(500L);

    detail_list.get(0).setProduct_option("오래오래오");
    detail_list.get(0).setProduct_option("육육육");
    detail_list.get(0).setProduct_option("아아아");

    ResultActions resultActions = mockMvc
        .perform(put("/api/product/" + detail_list.get(0).getProduct_no() + "/detail")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(detail_list)));

    resultActions.andExpect(status()
        .isOk()).andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.result").value("success"));
  }

  @Test
  public void delete_product_detail_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(delete("/api/product/"
            + detail_list.get(0).getProduct_no()
            + "/detail/" + detail_list.get(0).getNo())
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"));
  }

  @Test
  public void delete_product_detail_list_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(delete("/api/product/" + detail_list.get(0).getProduct_no() + "/detail")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(detail_list)));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].product_no").value(detail_list.get(0).getProduct_no()))
        .andExpect(
            jsonPath("$.data[0].product_option").value(detail_list.get(0).getProduct_option()))
        .andExpect(jsonPath("$.data[1].product_no").value(detail_list.get(1).getProduct_no()))
        .andExpect(
            jsonPath("$.data[1].product_option").value(detail_list.get(1).getProduct_option()));
  }

  @Ignore
  @Test
  public void get_product_list_by_result_map_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(get("/api/product/all/" + category_vo1.getNo())
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(product_list.get(0).getNo()))
        .andExpect(jsonPath("$.data[0].name").value(product_list.get(0).getName()))
        .andExpect(jsonPath("$.data[0].category_no").value(product_list.get(0).getCategory_no()))
        .andExpect(jsonPath("$.data[0].use_fl").value(product_list.get(0).getUse_fl()))
        .andExpect(jsonPath("$.data[1].no").value(product_list.get(1).getNo()))
        .andExpect(jsonPath("$.data[1].name").value(product_list.get(1).getName()))
        .andExpect(jsonPath("$.data[1].category_no").value(product_list.get(1).getCategory_no()))
        .andExpect(jsonPath("$.data[1].use_fl").value(product_list.get(1).getUse_fl()));
  }

  @Test
  public void add_product_image_list_success_test() throws Exception {
    for (int i = 0; i < image_list.size(); i++) {
      image_list.get(i).setNo((long) (200 + i));
      System.out.println(image_list.get(i) + "::::");
    }

    ResultActions resultActions = mockMvc
        .perform(post("/api/product/image")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(image_list)));
    resultActions
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(image_list.get(0).getNo()))
        .andExpect(jsonPath("$.data[0].url").value(image_list.get(0).getUrl()))
        .andExpect(jsonPath("$.data[0].product_no").value(image_list.get(0).getProduct_no()))
        .andExpect(jsonPath("$.data[1].no").value(image_list.get(1).getNo()))
        .andExpect(jsonPath("$.data[1].url").value(image_list.get(1).getUrl()))
        .andExpect(jsonPath("$.data[1].product_no").value(image_list.get(1).getProduct_no()))
        .andExpect(jsonPath("$.data[2].no").value(image_list.get(2).getNo()))
        .andExpect(jsonPath("$.data[2].url").value(image_list.get(2).getUrl()))
        .andExpect(jsonPath("$.data[2].product_no").value(image_list.get(2).getProduct_no()));
  }


  @Test
  public void get_product_image_list_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(
            get("/api/product/" + product_list.get(1).getNo() + "/image?product_image_category_no=")
                .contentType(MediaType.APPLICATION_JSON));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data[0].no").value(image_list.get(0).getNo()))
        .andExpect(jsonPath("$.data[0].url").value(image_list.get(0).getUrl()))
        .andExpect(jsonPath("$.data[0].product_no").value(image_list.get(0).getProduct_no()))
        .andExpect(jsonPath("$.data[1].no").value(image_list.get(1).getNo()))
        .andExpect(jsonPath("$.data[1].url").value(image_list.get(1).getUrl()))
        .andExpect(jsonPath("$.data[1].product_no").value(image_list.get(1).getProduct_no()))
        .andExpect(jsonPath("$.data[2].no").value(image_list.get(2).getNo()))
        .andExpect(jsonPath("$.data[2].url").value(image_list.get(2).getUrl()))
        .andExpect(jsonPath("$.data[2].product_no").value(image_list.get(2).getProduct_no()));
  }

  @Test
  public void get_product_image_list_none_success_test() throws Exception {

    ResultActions resultActions = mockMvc
        .perform(get("/api/product/" + 999797979 + "/image?product_image_category_no=2232")
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data").value("조건에 맞는 데이터가 없습니다."));
  }


  @Test
  public void update_product_image_list_success_test() throws Exception {

    image_list.get(0).setUrl("바뀐 URL");
    image_list.get(1).setUrl("바뀐 URL");

    ResultActions resultActions = mockMvc
        .perform(put("/api/product/" + product_list.get(1).getNo() + "/image")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(image_list)));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data").value(true));
  }


  @Test
  public void delete_product_image_success_test() throws Exception {
    ResultActions resultActions = mockMvc
        .perform(delete(
            "/api/product/" + product_list.get(1).getNo() + "/image/" + image_list.get(0).getNo())
            .contentType(MediaType.APPLICATION_JSON));

    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value("success"))
        .andExpect(jsonPath("$.data").value(image_list.get(0).getNo()));
  }

}
