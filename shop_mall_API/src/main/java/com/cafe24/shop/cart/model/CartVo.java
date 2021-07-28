package com.cafe24.shop.cart.model;

import com.cafe24.shop.customer.model.CustomerVo;
import com.cafe24.shop.product.model.ProductDetailVo;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CartVo")
public class CartVo {

	private Long no;
	@NotNull
	private Long customer_no;
	private CustomerVo customerVo;
	@NotNull
	private Long product_detail_no;
	private ProductDetailVo productDetailVo;
	private Long count;
	private String register_dt;
	private String delete_dt;
	private String ordered_cart;
	
	
	/*
	 * 생성자목록 
	 */
	public CartVo() {
	}
	
	public CartVo(Long customer_no, Long product_detail_no, Long count, String register_Dt) {
		this.customer_no=customer_no;
		this.product_detail_no=product_detail_no;
		this.count=count;
		this.register_dt=register_Dt;
	}

	public CartVo(Long no, Long customer_no, Long product_detail_no, Long count, String register_Dt, String delete_dt, String ordered_cart ) {
		this.no=no;
		this.customer_no=customer_no;
		this.product_detail_no=product_detail_no;
		this.count=count;
		this.register_dt=register_Dt;
		this.delete_dt=delete_dt;
		this.ordered_cart=ordered_cart;
	}
	

	public CartVo(Long no, Long customer_no, CustomerVo customerVo, Long product_detail_no,
			ProductDetailVo productDetailVo, Long count, String register_dt, String delete_dt, String ordered_cart) {
		super();
		this.no = no;
		this.customer_no = customer_no;
		this.customerVo = customerVo;
		this.product_detail_no = product_detail_no;
		this.productDetailVo = productDetailVo;
		this.count = count;
		this.register_dt = register_dt;
		this.delete_dt = delete_dt;
		this.ordered_cart = ordered_cart;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(Long customer_no) {
		this.customer_no = customer_no;
	}
	public Long getProduct_detail_no() {
		return product_detail_no;
	}
	public void setProduct_detail_no(Long product_detail_no) {
		this.product_detail_no = product_detail_no;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public String getOrdered_cart() {
		return ordered_cart;
	}
	public void setOrdered_cart(String ordered_cart) {
		this.ordered_cart = ordered_cart;
	}

	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}

	public ProductDetailVo getProductDetailVo() {
		return productDetailVo;
	}

	public void setProductDetailVo(ProductDetailVo productDetailVo) {
		this.productDetailVo = productDetailVo;
	}

	@Override
	public String toString() {
		return "CartVo [no=" + no + ", customer_no=" + customer_no + ", customerVo=" + customerVo
				+ ", product_detail_no=" + product_detail_no + ", productDetailVo=" + productDetailVo + ", count="
				+ count + ", register_dt=" + register_dt + ", delete_dt=" + delete_dt + ", ordered_cart=" + ordered_cart
				+ "]";
	}
	
	
		
}
