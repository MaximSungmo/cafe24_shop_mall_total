package com.cafe24.shop.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ProductDetailVo")
public class ProductDetailVo {

	private Long no;
	@NotNull
	private Long product_no;
	@NotNull
	private String product_option;
	private Long price;
	//'STOCK', 'NO_STOCK'
	private String stock_cd;
	private Long stock_cnt;
	private Long warehouse_no;
	
	
	/*
	 * 생성자 목록 
	 */
	
	public ProductDetailVo() {
	}
	public ProductDetailVo(Long no, Long product_no, String product_option, 
			String stock_cd, Long stock_cnt, Long warehose_no) {
		this.no=no;
		this.product_no=product_no;
		this.product_option=product_option;
		this.stock_cd=stock_cd;
		this.stock_cnt=stock_cnt;
		this.warehouse_no=warehose_no;
	}
	public ProductDetailVo(Long no, Long product_no, String product_option, 
			Long price, String stock_cd, Long stock_cnt, Long warehose_no) {
		this.no=no;
		this.product_no=product_no;
		this.product_option=product_option;
		this.price=price;
		this.stock_cd=stock_cd;
		this.stock_cnt=stock_cnt;
		this.warehouse_no=warehose_no;
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
	public String getProduct_option() {
		return product_option;
	}
	public void setProduct_option(String product_option) {
		this.product_option = product_option;
	}
	public String getStock_cd() {
		return stock_cd;
	}
	public void setStock_cd(String stock_cd) {
		this.stock_cd = stock_cd;
	}
	public Long getStock_cnt() {
		return stock_cnt;
	}
	public void setStock_cnt(Long stock_cnt) {
		this.stock_cnt = stock_cnt;
	}
	public Long getWarehouse_no() {
		return warehouse_no;
	}
	public void setWarehouse_no(Long warehouse_no) {
		this.warehouse_no = warehouse_no;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductDetailVo [no=" + no + ", product_no=" + product_no + ", product_option=" + product_option
				+ ", price=" + price + ", stock_cd=" + stock_cd + ", stock_cnt=" + stock_cnt + ", warehouse_no="
				+ warehouse_no + "]";
	}
	
	
	
	
}
