package com.cafe24.mysite.vo;

import java.util.List;

public class OrdersDetailVo {

	private Long no;
	private Long order_no;
	private Long product_detail_no;
	private String product_option;
	private Long product_image_no;
	private String product_image_url;
	private Long category_no;
	private Long brand_no;
	private Long order_product_price;
	private Long order_product_cnt;
	private List<OrdersDetailVo> order_detail_list;

	public OrdersDetailVo(){
		
	}
	public OrdersDetailVo(Long no, Long order_no, Long product_detail_no, String product_option, Long product_image_no,
			String product_image_url, Long category_no, Long brand_no, Long order_product_price, Long order_product_cnt,
			List<OrdersDetailVo> order_detail_list) {
		super();
		this.no = no;
		this.order_no = order_no;
		this.product_detail_no = product_detail_no;
		this.product_option = product_option;
		this.product_image_no = product_image_no;
		this.product_image_url = product_image_url;
		this.category_no = category_no;
		this.brand_no = brand_no;
		this.order_product_price = order_product_price;
		this.order_product_cnt = order_product_cnt;
		this.order_detail_list = order_detail_list;
	}

	@Override
	public String toString() {
		return "OrdersDetailVo [no=" + no + ", order_no=" + order_no + ", product_detail_no=" + product_detail_no
				+ ", product_option=" + product_option + ", product_image_no=" + product_image_no
				+ ", product_image_url=" + product_image_url + ", category_no=" + category_no + ", brand_no=" + brand_no
				+ ", order_product_price=" + order_product_price + ", order_product_cnt=" + order_product_cnt
				+ ", order_detail_list=" + order_detail_list + "]";
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}

	public Long getProduct_detail_no() {
		return product_detail_no;
	}

	public void setProduct_detail_no(Long product_detail_no) {
		this.product_detail_no = product_detail_no;
	}

	public String getProduct_option() {
		return product_option;
	}

	public void setProduct_option(String product_option) {
		this.product_option = product_option;
	}

	public Long getProduct_image_no() {
		return product_image_no;
	}

	public void setProduct_image_no(Long product_image_no) {
		this.product_image_no = product_image_no;
	}

	public String getProduct_image_url() {
		return product_image_url;
	}

	public void setProduct_image_url(String product_image_url) {
		this.product_image_url = product_image_url;
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

	public Long getOrder_product_price() {
		return order_product_price;
	}

	public void setOrder_product_price(Long order_product_price) {
		this.order_product_price = order_product_price;
	}

	public Long getOrder_product_cnt() {
		return order_product_cnt;
	}

	public void setOrder_product_cnt(Long order_product_cnt) {
		this.order_product_cnt = order_product_cnt;
	}

	public List<OrdersDetailVo> getOrder_detail_list() {
		return order_detail_list;
	}

	public void setOrder_detail_list(List<OrdersDetailVo> order_detail_list) {
		this.order_detail_list = order_detail_list;
	}

}
