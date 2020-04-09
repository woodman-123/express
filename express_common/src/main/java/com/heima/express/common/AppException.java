package com.heima.express.common;

public class AppException extends RuntimeException{
	
	private Integer keyCode;
	private String message;
	
	

	public AppException() {
		super();
	}

	

	public AppException(Integer keyCode, String message) {
		super();
		this.keyCode = keyCode;
		this.message = message;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public Integer getKeyCode() {
		return keyCode;
	}



	public void setKeyCode(Integer keyCode) {
		this.keyCode = keyCode;
	}



	@Override
	public String toString() {
		return "AppException [keyCode=" + keyCode + ", message=" + message + "]";
	}

	
	

}
