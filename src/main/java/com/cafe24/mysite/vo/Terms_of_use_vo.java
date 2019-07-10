package com.cafe24.mysite.vo;

public class Terms_of_use_vo {

	private Long no;
	private String title;
	private String content;
	private String register_dt;
	private String delete_dt;
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
		return "Terms_of_use [no=" + no + ", title=" + title + ", content=" + content + ", register_dt=" + register_dt
				+ ", delete_dt=" + delete_dt + "]";
	}
	
}
