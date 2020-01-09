package com.lin.response;

/**
 * 统一返回成功数据模型
 * @author hzh 2019-09-04 16:38
 */
public class WrapperSuccess<T> {

	private int code;
	private T data;

	public static <T> WrapperSuccess<T> success() {
		ResponseCode responseCode = ResponseCode.SUCCESS;
		return new WrapperSuccess<>(responseCode.getCode(), null);
	}

	public static <T> WrapperSuccess<T> success(T data) {
		ResponseCode responseCode = ResponseCode.SUCCESS;
		return new WrapperSuccess<>(responseCode.getCode(), data);
	}

	private WrapperSuccess(int code, T data) {
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
