package com.cafe24.mysite.vo;

public class ProductVo {

	// 상품(product)테이블 정보
	private Long no;
	private String name;
	private String description;
	private String status;
	private String use_fl;
	private Long like_cnt;
	private String register_dt;
	private Long category_no;
	private Long brand_no;

	
	/**
	 * 생성자 목록
	 */
	public ProductVo() {

	}
	public ProductVo(Long no, String name, String description,
			String status, String use_fl, Long like_cnt, Long category_no) {
		this.no=no;
		this.name=name;
		this.description=description;
		this.status=status;
		this.use_fl=use_fl;
		this.like_cnt=like_cnt;
		this.category_no=category_no;
	}
	public ProductVo(Long no, String name, String description,
			String status, String use_fl, Long like_cnt, Long category_no, Long brand_no) {
		this.no=no;
		this.name=name;
		this.description=description;
		this.status=status;
		this.use_fl=use_fl;
		this.like_cnt=like_cnt;
		this.category_no=category_no;
		this.brand_no=brand_no;
	}
	public ProductVo(Long no, String name, String description,
			String status, String use_fl, Long like_cnt, String register_dt, 
			Long category_no, Long brand_no) {
		this.no=no;
		this.name=name;
		this.description=description;
		this.status=status;
		this.use_fl=use_fl;
		this.like_cnt=like_cnt;
		this.register_dt=register_dt;
		this.category_no=category_no;
		this.brand_no=brand_no;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUse_fl() {
		return use_fl;
	}
	public void setUse_fl(String use_fl) {
		this.use_fl = use_fl;
	}
	public Long getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(Long like_cnt) {
		this.like_cnt = like_cnt;
	}
	public String getRegister_dt() {
		return register_dt;
	}
	public void setRegister_dt(String register_dt) {
		this.register_dt = register_dt;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	public Long getBrand_no() {
		return brand_no;
	}
	public void setBrand_no(Long brand_no) {
		this.brand_no = brand_no;
	}
	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", use_fl=" + use_fl + ", like_cnt=" + like_cnt + ", register_dt=" + register_dt + ", category_no="
				+ category_no + ", brand_no=" + brand_no + "]";
	}
}
