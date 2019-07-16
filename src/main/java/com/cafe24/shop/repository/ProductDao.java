package com.cafe24.shop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shop.vo.ProductDetailVo;
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
			}		
			return filtered_list;
		}		
	} 
	
	public List<ProductDetailVo> get_product_detail_test_data(Long no) {
		ProductDetailVo vo = new ProductDetailVo(1L, 1L, "사이즈270-2", "STOCK", 40L, 1L);
		ProductDetailVo vo2 = new ProductDetailVo(2L, 2L, "사이즈270-2", "STOCK", 40L, 1L);
		ProductDetailVo vo3 = new ProductDetailVo(3L, 3L, "사이즈270-3", "STOCK", 40L, 1L);
		List<ProductDetailVo> list = new ArrayList<ProductDetailVo>();
		if(no==1L) {list.add(vo);}
		else if(no==2L){list.add(vo2);}
		else if(no==3L){list.add(vo3);}
		return list;		
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

	public Long add_product_detail(ProductDetailVo vo) {
		//인서트 후 pk돌려받기 
//		return sqlSession.insert("product.add_product_detail", vo);
		
		Long insert_product_detail_no = vo.getNo();
		return insert_product_detail_no;
	}

	public List<ProductDetailVo> get_product_detail_list(Long no) {
		List<ProductDetailVo> product_detail_list = get_product_detail_test_data(no);
//		List<ProductDetailVo> product_list = sqlSession.selectList("product.get_product_detail_list", no);
		return product_detail_list;
	}

	public Long update_product_detail(Long product_detail_no) {
		Long updated_product_detail_no = product_detail_no;
//		Long updated_product_detail_no = sqlSession.update("product.update_product_detail", product_detail_no);
		return updated_product_detail_no;
	}

	public Long delete_product_detail(Map<String, Long> map) {
//		return sqlSession.delete("product.delete_product", vo);
		Long deleted_product_detail_no = map.get("product_detail_no");
		return deleted_product_detail_no;
	}

}


