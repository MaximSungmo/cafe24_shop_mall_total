package com.cafe24.shop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.ProductVo;

@Repository
public class ProductDao {

	@Autowired
	SqlSession sqlSession;
	
		
//	public Boolean input_test_data(ProductVo vo) {
//		//테스트용 데이터 생성
//		return vo != null;
//	}
	public List<ProductVo> get_test_data(Map<String, Object> map) {
		//테스트용 데이터 생성
		List<ProductVo> list = new ArrayList<ProductVo>();
		ProductVo vo1 = new ProductVo(1L, "상품1", "상품1상세내용", "상태1", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);
		ProductVo vo2 = new ProductVo(2L, "상품2", "상품2상세내용", "상태2", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);
		ProductVo vo3 = new ProductVo(3L, "상품3", "상품3상세내용", "상태3", "Y", 1L, "2019-07-14 00:00:00", 1L, 1L);
		ProductVo vo4 = new ProductVo(4L, "상품4", "상품4상세내용", "상태4", "N", 1L, "2019-07-14 00:00:00", 1L, 1L);
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		list.add(vo4);
		System.out.println((Long) map.get("category_no")+":numbernumbernumbernumber");
		
		Long no=(Long) map.get("category_no");
		String kwd = (String) map.get("kwd");
		
		if("".equals(kwd)) {
			return list;
		}else {
			List<ProductVo> filtered_list = new ArrayList<ProductVo>();
			// 조건에 따라 필터링 된 테스트 데이터 만들기
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getName().matches(".*"+(String)map.get("kwd")+".*")) {
					filtered_list.add(list.get(i));
				}
//				if(list.get(i).equals(kwd)) {
//					if(list.get(i).equals(kwd)){
//						
//					};
//				}
			}		
			return filtered_list;
		}		
	} 
	
	public List<ProductVo> get_product_list(Map<String, Object> map){
		List<ProductVo> product_list = get_test_data(map);
//		List<ProductVo> product_list = sqlSession.selectList("product.get_product_list", map);
		return product_list;
	}
	
	public Boolean add_product(ProductVo vo) {
//		return input_test_data(vo);
		int input_result = 1;
//		return sqlSession.insert("product.add_product", vo)==1;
		return input_result == 1;
		
	}
	
	
	
	public Long delete_product(Long no) {
//		return sqlSession.delete("product.delete_product", vo);
		
		Long delete_result = no;
		return delete_result;

	}
	
	public Long update_product(ProductVo vo) {
		
		Long update_result = vo.getNo();;
//		return sqlSession.update("product.update_product", vo);
		return update_result;

	}
	
	// 회원약관동의서, 회원가입요청 시 전달용
//		public List<TermsOfUseVo> get_terms_of_use_template() {
//			List<TermsOfUseVo> list = new ArrayList<TermsOfUseVo>();
//			list.add(termsOfUseVo);
//			return list;
////			return sqlSession.selectList("user.get_terms_of_use_template"); 
//		}

}


