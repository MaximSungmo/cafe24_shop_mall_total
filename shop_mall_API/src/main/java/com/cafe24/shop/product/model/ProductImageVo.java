package com.cafe24.shop.product.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ProductImageVo")
public class ProductImageVo {

	private Long no;
	@NotNull
	private Long product_no;
	@NotNull
	private String url;
	private String register_dt;
	private String use_fl;
	private Long product_image_category_no;

	private ProductImageCategoryVo product_image_category_vo;
	
	/**
	 * 생성자 목록
	 */
	public ProductImageVo() {

	}
	public ProductImageVo(Long product_no, String url, 
			String use_fl, Long product_image_category_no) {
		this.product_no=product_no;
		this.url=url;
		this.use_fl=use_fl;
		this.product_image_category_no=product_image_category_no;
	}
	public ProductImageVo(Long no, Long product_no, String url, 
			String use_fl, Long product_image_category_no) {
		this.no=no;
		this.product_no=product_no;
		this.url=url;
		this.use_fl=use_fl;
		this.product_image_category_no=product_image_category_no;
	}
	public ProductImageVo(Long no, Long product_no, String url, 
			String register_dt, String use_fl, Long product_image_category_no) {
		this.no=no;
		this.product_no=product_no;
		this.url=url;
		this.register_dt=register_dt;
		this.use_fl=use_fl;
		this.product_image_category_no=product_image_category_no;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegister_dt() {
		return register_dt;
	}
	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}
	public String getUse_fl() {
		return use_fl;
	}
	public void setUse_fl(String use_fl) {
		this.use_fl = use_fl;
	}
	public Long getProduct_image_category_no() {
		return product_image_category_no;
	}
	public void setProduct_image_category_no(Long product_image_category_no) {
		this.product_image_category_no = product_image_category_no;
	}
	
	public ProductImageCategoryVo getProduct_image_category_vo() {
		return product_image_category_vo;
	}
	public void setProduct_image_category_vo(ProductImageCategoryVo product_image_category_vo) {
		this.product_image_category_vo = product_image_category_vo;
	}

	@Override
	public String toString() {
		return "ProductImageVo [no=" + no + ", product_no=" + product_no + ", url=" + url + ", register_dt="
				+ register_dt + ", use_fl=" + use_fl + ", product_image_category_no=" + product_image_category_no
				+ ", product_image_category_vo=" + product_image_category_vo + "]";
	}
	
}
