package com.heima.express.common;

public class AppResult {

	
	//响应码
	private Integer keyCode;
	//响应信息
	private String message;
	
	private Object data;
	
	

	public AppResult() {
		super();
	}

	public AppResult(Integer keyCode, String message, Object data) {
		super();
		this.keyCode = keyCode;
		this.message = message;
		this.data = data;
	}

	public Integer getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(Integer keyCode) {
		this.keyCode = keyCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultMap [keyCode=" + keyCode + ", message=" + message + ", data=" + data + "]";
	}

}
