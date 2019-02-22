package com.revature.util;

public class HttpException extends RuntimeException {
	
	public int code;
	public String message;
	
	public HttpException(int code, String message) {
		System.out.println("Http Exception thrown with " + code + " " + message);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
