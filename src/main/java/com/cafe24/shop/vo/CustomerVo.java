package com.cafe24.shop.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.shop.validator.ValidGender;

import io.swagger.annotations.ApiModel;
@ApiModel(value = "CustomerVo")
public class CustomerVo {
	
	private Long no;
	@Length(min=2, max=8, message="이름은 2~8자리 사이로 입력해주세요.")
	@Pattern(regexp="^[가-힣]*$", message="이름 형식이 맞지 않습니다.")
	private String name;

	@Email(message="이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@Length(min=8, max=16, message="비밀번호는 특수문자/문자/숫자 최소 8~16자리 이내 입력 바랍니다.")
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message="비밀번호는 특수문자/문자/숫자 최소 8~16자리 이내 입력 바랍니다.")
	private String password;
	
	@Length(min=4, max=16, message="전화번호 형식이 올바르지 않습니다.")
	@Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$", message="전화번호 형식이 올바르지 않습니다.")
	private String phone;
	
	@ValidGender
	private String gender;
	private String use_fl = "Y";
	private String auth_grade = "ROLE_USER";
	private String recommender_id;
	private Long terms_of_use_no;
	private String agreement_fl;
	private String agreement_dt;
	
	
	/**
	 * 생성자 목록 
	 */
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
	
	public CustomerVo(Long no, String name, String email, String password, String phone, String gender, String recommender_id, Long terms_of_use_no, String agreement_fl) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.recommender_id = recommender_id;
		this.terms_of_use_no = terms_of_use_no;
		this.agreement_fl = agreement_fl;
	}
	
	public CustomerVo(Long no, String name, String email, String password, String phone, String gender, Long terms_of_use_no, String agreement_fl) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.terms_of_use_no = terms_of_use_no;
		this.agreement_fl = agreement_fl;
	}
	
	public CustomerVo(Long no, String name, String email, String password, String phone, String gender, String use_fl, String auth_user, String recommender_id, Long terms_of_use_no, String agreement_fl, String agreement_dt) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.terms_of_use_no = terms_of_use_no;
		this.agreement_fl = agreement_fl;
		
	}

	
	/**
	 * getter, setter 생성
	 */
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUse_fl() {
		return use_fl;
	}

	public void setUse_fl(String use_fl) {
		this.use_fl = use_fl;
	}

	public String getAuth_grade() {
		return auth_grade;
	}

	public void setAuth_grade(String auth_grade) {
		this.auth_grade = auth_grade;
	}

	public String getRecommender_id() {
		return recommender_id;
	}

	public void setRecommender_id(String recommender_id) {
		this.recommender_id = recommender_id;
	}

	public Long getTerms_of_use_no() {
		return terms_of_use_no;
	}

	public void setTerms_of_use_no(Long terms_of_use_no) {
		this.terms_of_use_no = terms_of_use_no;
	}

	public String getAgreement_fl() {
		return agreement_fl;
	}

	public void setAgreement_fl(String agreement_fl) {
		this.agreement_fl = agreement_fl;
	}

	public String getAgreement_dt() {
		return agreement_dt;
	}

	public void setAgreement_dt(String agreement_dt) {
		this.agreement_dt = agreement_dt;
	}

	@Override
	public String toString() {
		return "CustomerVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", gender=" + gender + ", use_fl=" + use_fl + ", auth_grade=" + auth_grade
				+ ", recommender_id=" + recommender_id + ", terms_of_use_no=" + terms_of_use_no + ", agreement_fl="
				+ agreement_fl + ", agreement_dt=" + agreement_dt + "]";
	}

	
	
}
