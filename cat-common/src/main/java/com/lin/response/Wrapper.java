package com.lin.response;

import java.util.List;

/**
 * 统一返回数据模型
 * @author hzh 2019-07-25 14:51
 */
public class Wrapper<T> {

	private int code;
	private String message;
	private T data;

	public static <T> Wrapper<T> success() {
		ResponseCode responseCode = ResponseCode.SUCCESS;
		return new Wrapper<>(responseCode.getCode(), responseCode.getMessage(), null);
	}

	public static <T> Wrapper<T> success(T data) {
		ResponseCode responseCode = ResponseCode.SUCCESS;
		return new Wrapper<>(responseCode.getCode(), responseCode.getMessage(), data);
	}

	public static <K> Wrapper<PageData<K>> success(List<K> data, int totalCount) {
		ResponseCode responseCode = ResponseCode.SUCCESS;
		PageData<K> pageData = new PageData<>(totalCount, data);
		return new Wrapper<>(responseCode.getCode(), responseCode.getMessage(), pageData);
	}

	public static <T> Wrapper<T> fail(ResponseCode responseCode) {
		return new Wrapper<>(responseCode.getCode(), responseCode.getMessage(), null);
	}

	public static <T> Wrapper<T> fail(String errorMessage) {
		return new Wrapper<>(ResponseCode.BUSINESS_ERROR.getCode(), errorMessage, null);
	}

	public static <T> Wrapper<T> fail(int responseCode, String errorMessage) {
		return new Wrapper<>(responseCode, errorMessage, null);
	}

	public static <T> Wrapper<T> fail(ResponseCode responseCode, String errorMessage) {
		return new Wrapper<>(responseCode.getCode(), errorMessage, null);
	}

	private Wrapper(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

}
