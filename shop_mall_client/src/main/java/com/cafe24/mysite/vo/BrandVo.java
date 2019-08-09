package com.cafe24.mysite.vo;

import java.util.List;

/**
 * @author bit
 *
 */
public class BrandVo {

	private Long no;
	private String name;
	private String address;
	private String phone_no;
	private String register_dt;
	private String delete_dt;
	private String use_fl; //"Y" or "N"
	
	private List<ProductVo> product_list;



	public BrandVo() {
	}
		
	public BrandVo(Long no, String name, String address, String phone_no, String register_dt, String delete_dt,
			String use_fl) {
		super();
		this.no = no;
		this.name = name;
		this.address = address;
		this.phone_no = phone_no;
		this.register_dt = register_dt;
		this.delete_dt = delete_dt;
		this.use_fl = use_fl;
	}

	public BrandVo(Long no, String name, String address, String phone_no, String register_dt, String delete_dt,
			String use_fl, List<ProductVo> product_list) {
		super();
		this.no = no;
		this.name = name;
		this.address = address;
		this.phone_no = phone_no;
		this.register_dt = register_dt;
		this.delete_dt = delete_dt;
		this.use_fl = use_fl;
		this.product_list = product_list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
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

	public List<ProductVo> getProduct_list() {
		return product_list;
	}

	public void setProduct_list(List<ProductVo> product_list) {
		this.product_list = product_list;
	}

	@Override
	public String toString() {
		return "BrandVo [no=" + no + ", address=" + address + ", phone_no=" + phone_no + ", register_dt=" + register_dt
				+ ", delete_dt=" + delete_dt + ", use_fl=" + use_fl + ", product_list=" + product_list + "]";
	}
	
	
	
	
}
