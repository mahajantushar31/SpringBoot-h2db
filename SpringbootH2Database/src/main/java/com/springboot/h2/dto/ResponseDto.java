package com.springboot.h2.dto;

public class ResponseDto {
	String code;
	String message;
	Object responseObject;
	
	
	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResponseDto(String code, String message, Object responseObject) {
		super();
		this.code = code;
		this.message = message;
		this.responseObject = responseObject;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
	@Override
	public String toString() {
		return "ResponseDto [code=" + code + ", message=" + message + ", responseObject=" + responseObject + "]";
	}
	
}
