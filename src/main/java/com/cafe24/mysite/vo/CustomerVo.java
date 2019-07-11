package com.cafe24.mysite.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CustomerVo {
	private Long no;

	@NotEmpty
	@Length(min=2, max=8)
	private String name;

	@Email
	@NotEmpty
	private String email;
	
	private String password;
	private String gender;
	private String joinDate;
	

	private String AGREEMENT_FL;
	private Long TERMS_OF_USE_NO;

	
	public Long getTERMS_OF_USE_NO() {
		return TERMS_OF_USE_NO;
	}

	public void setTERMS_OF_USE_NO(Long tERMS_OF_USE_NO) {
		TERMS_OF_USE_NO = tERMS_OF_USE_NO;
	}

	public String getAGREEMENT_FL() {
		return AGREEMENT_FL;
	}

	public void setAGREEMENT_FL(String aGREEMENT_FL) {
		AGREEMENT_FL = aGREEMENT_FL;
	}

	public CustomerVo() {
	}
	
	public  CustomerVo(Long no,String email, String password) {
		this.no = no;
		this.email = email;
		this.password = password;
	}
	
	public  CustomerVo(String email, String password) {
		this.email = email;
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "CustomerVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", joinDate=" + joinDate + "]";
	}
}
