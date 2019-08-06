package com.cafe24.mysite.vo;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "TermsOfUseVo")
public class TermsOfUseVo {

	private Long no;
	private String title;
	private String content;
	private String use_fl;
	private String necessary_fl;
	private String register_dt;
	private String delete_dt;
	
	/*
	 * 생성자 목록
	 */
	public TermsOfUseVo(){	
	}
	public TermsOfUseVo(String title, String content, String register_dt){
		this.title=title;
		this.content=content;
		this.register_dt=register_dt;
	}
	public TermsOfUseVo(Long no, String title, String content, String register_dt, String delete_dt){
		this.no=no;
		this.title=title;
		this.content=content;
		this.register_dt=register_dt;
		this.delete_dt=delete_dt;
	}
	public TermsOfUseVo(Long no, String title, String content, String use_fl, String necessary_fl, String register_dt, String delete_dt){
		this.no=no;
		this.title=title;
		this.content=content;
		this.use_fl=use_fl;
		this.necessary_fl=necessary_fl;
		this.register_dt=register_dt;
		this.delete_dt=delete_dt;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUse_fl() {
		return use_fl;
	}
	public void setUse_fl(String use_fl) {
		this.use_fl = use_fl;
	}
	public String getNecessary_fl() {
		return necessary_fl;
	}
	public void setNecessary_fl(String necessary_fl) {
		this.necessary_fl = necessary_fl;
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
	@Override
	public String toString() {
		return "TermsOfUseVo [no=" + no + ", title=" + title + ", content=" + content + ", use_fl=" + use_fl
				+ ", necessary_fl=" + necessary_fl + ", register_dt=" + register_dt + ", delete_dt=" + delete_dt + "]";
	}
	
	
	
}
