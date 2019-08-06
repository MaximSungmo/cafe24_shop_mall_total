package com.cafe24.mysite.vo;

public class CategoryVo {

	private Long no;
	private String name;
	private Long parent_no;
	
	public CategoryVo(Long no, String name, Long parent_no) {
		this.no = no;
		this.name = name;
		this.parent_no = parent_no;
	}
	
	public CategoryVo() {
		super();
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
	public Long getParent_no() {
		return parent_no;
	}
	public void setParent_no(Long parent_no) {
		this.parent_no = parent_no;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", parent_no=" + parent_no + "]";
	}
	
	
}
