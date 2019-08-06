package com.cafe24.mysite.dto;

public class JSONResult2<T> {
	private String result;  //success, fail
	private String message; //if fail, set
	private Object data;    //if success, set

	public JSONResult2<T> success(Object data) {
		return new JSONResult2<T>("success", null, data);
	}

	public JSONResult2<T> fail(String message) {
		return new JSONResult2<T>("fail", message, null);
	}
	
	
	public JSONResult2(String result, String message, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
}



