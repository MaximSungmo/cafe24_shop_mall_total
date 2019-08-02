package com.cafe24.mysite.vo;

import java.util.List;

public class TestVo {
	private TestDetailVo scripttags;

	@Override
	public String toString() {
		return "TestVo [scripttags=" + scripttags + "]";
	}

	public TestVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestVo(TestDetailVo scripttags) {
		super();
		this.scripttags = scripttags;
	}

	public TestDetailVo getScripttags() {
		return scripttags;
	}

	public void setScripttags(TestDetailVo scripttags) {
		this.scripttags = scripttags;
	}


	
	
	
}
