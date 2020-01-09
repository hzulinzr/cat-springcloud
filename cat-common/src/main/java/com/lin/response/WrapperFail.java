package com.lin.response;

/**
 * 统一返回失败数据模型
 * @author hzh 2019-09-04 16:38
 */
public class WrapperFail {

	private int code;
	private String message;

	public static WrapperFail fail(String errorMessage) {
		return new WrapperFail(ResponseCode.BUSINESS_ERROR.getCode(), errorMessage);
	}

	public static WrapperFail fail(int responseCode, String errorMessage) {
		return new WrapperFail(responseCode, errorMessage);
	}


	private WrapperFail(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
