package com.cafe24.mysite.vo;

import java.util.List;

public class TestDetailVo {
	String shop_no;
	String script_no;
	String src;
	List<String> display_locations;
	String created_date;
	String updated_date;
	@Override
	public String toString() {
		return "scripttag [shop_no=" + shop_no + ", script_no=" + script_no + ", src=" + src + ", display_locations="
				+ display_locations + ", created_date=" + created_date + ", updated_date=" + updated_date + "]";
	}
	public TestDetailVo(String shop_no, String script_no, String src, List<String> display_locations) {
		super();
		this.shop_no = shop_no;
		this.script_no = script_no;
		this.src = src;
		this.display_locations = display_locations;
	}
	public TestDetailVo(String shop_no, String script_no, String src, List<String> display_locations, String created_date,
			String updated_date) {
		super();
		this.shop_no = shop_no;
		this.script_no = script_no;
		this.src = src;
		this.display_locations = display_locations;
		this.created_date = created_date;
		this.updated_date = updated_date;
	}
	public TestDetailVo() {
		super();
	}
	public String getShop_no() {
		return shop_no;
	}
	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}
	public String getScript_no() {
		return script_no;
	}
	public void setScript_no(String script_no) {
		this.script_no = script_no;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public List<String> getDisplay_locations() {
		return display_locations;
	}
	public void setDisplay_locations(List<String> display_locations) {
		this.display_locations = display_locations;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	
}

