package com.cafe24.shop.category.model;

import com.cafe24.shop.product.model.ProductVo;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CategoryVo")
public class CategoryVo {

	private Long no;
	
	@NotNull
	private String name;
	private Long parent_no;
	private List<ProductVo> product_list;
	
	
	public List<ProductVo> getProduct_list() {
		return product_list;
	}
	public void setProduct_list(List<ProductVo> product_list) {
		this.product_list = product_list;
	}
	/*
	 * 생성자 목록
	 */
	public CategoryVo() {
		
	}
	public CategoryVo(Long no, String name, Long parent_no) {
		this.no = no;
		this.name = name;
		this.parent_no = parent_no;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParent_no() {
		return parent_no;
	}
	public void setParent_no(Long parent_no) {
		this.parent_no = parent_no;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", parent_no=" + parent_no + ", product_list=" + product_list
				+ "]";
	}
	
	
}
