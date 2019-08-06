package com.cafe24.mysite.vo;

public class OrdersVo {

	private Long no;
	private Long price;
	private String address;
	private String receiver_nm;
	private String phone_no;
	private String delevery_status;
	private String shipping_method;
	private String customer_request;
	private Long customer_no;
	private Long payment_no;
	private String payment_method;
	private String card_no;
	
	
	public OrdersVo() {
		super();
	}

	public OrdersVo(Long no, Long price, String address, String receiver_nm, String phone_no, String delevery_status,
			String shipping_method, String customer_request, Long customer_no, Long payment_no, String payment_method,
			String card_no) {
		super();
		this.no = no;
		this.price = price;
		this.address = address;
		this.receiver_nm = receiver_nm;
		this.phone_no = phone_no;
		this.delevery_status = delevery_status;
		this.shipping_method = shipping_method;
		this.customer_request = customer_request;
		this.customer_no = customer_no;
		this.payment_no = payment_no;
		this.payment_method = payment_method;
		this.card_no = card_no;
	}
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver_nm() {
		return receiver_nm;
	}

	public void setReceiver_nm(String receiver_nm) {
		this.receiver_nm = receiver_nm;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getDelevery_status() {
		return delevery_status;
	}

	public void setDelevery_status(String delevery_status) {
		this.delevery_status = delevery_status;
	}

	public String getShipping_method() {
		return shipping_method;
	}

	public void setShipping_method(String shipping_method) {
		this.shipping_method = shipping_method;
	}

	public String getCustomer_request() {
		return customer_request;
	}

	public void setCustomer_request(String customer_request) {
		this.customer_request = customer_request;
	}

	public Long getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(Long customer_no) {
		this.customer_no = customer_no;
	}

	public Long getPayment_no() {
		return payment_no;
	}

	public void setPayment_no(Long payment_no) {
		this.payment_no = payment_no;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", price=" + price + ", address=" + address + ", receiver_nm=" + receiver_nm
				+ ", phone_no=" + phone_no + ", delevery_status=" + delevery_status + ", shipping_method="
				+ shipping_method + ", customer_request=" + customer_request + ", customer_no=" + customer_no
				+ ", payment_no=" + payment_no + ", payment_method=" + payment_method + ", card_no=" + card_no + "]";
	}
	
	
	
}
