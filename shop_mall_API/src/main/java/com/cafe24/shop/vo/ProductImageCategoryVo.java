package com.cafe24.shop.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ProductImageCategoryVo {

	private Long no;
	@NotNull
	private String name;
	private String register_dt;
	private String delete_dt;
	private String use_fl;
	
	private List<ProductImageVo> product_image_list;
	
	public ProductImageCategoryVo() {
	}
	public ProductImageCategoryVo(Long no, String name, String register_dt, String delete_dt, String use_fl) {
		super();
		this.no = no;
		this.name = name;
		this.register_dt = register_dt;
		this.delete_dt = delete_dt;
		this.use_fl = use_fl;
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
	public String getRegister_dt() {
		return register_dt;
	}
	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}
	public String getDelete_dt() {
		return delete_dt;
	}
	public void setDelete_dt(String delete_dt) {
		this.delete_dt = delete_dt;
	}
	public String getUse_fl() {
		return use_fl;
	}
	public void setUse_fl(String use_fl) {
		this.use_fl = use_fl;
	}
	public List<ProductImageVo> getProduct_image_list() {
		return product_image_list;
	}
	public void setProduct_image_list(List<ProductImageVo> product_image_list) {
		this.product_image_list = product_image_list;
	}
	@Override
	public String toString() {
		return "ProductImageCategoryVo [no=" + no + ", name=" + name + ", register_dt=" + register_dt + ", delete_dt="
				+ delete_dt + ", use_fl=" + use_fl + ", product_image_list=" + product_image_list + "]";
	}
	
	
}
