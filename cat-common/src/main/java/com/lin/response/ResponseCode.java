package com.lin.response;

/**
 * 统一返回编码枚举
 * @author hzh 2019-07-25 15:23
 */
public enum ResponseCode {
	/**
	 * 成功
	 */
	SUCCESS(10000, ""),
	/**
	 * 数据检验错误
	 */
	DATA_VALID_FAIL(100, "DATA_VALID_FAIL"),
	BUSINESS_ERROR(30000, "BUSINESS_ERROR"),
	SERVER_NO_AVAILABLE(50000, "server no available"),
	BOOK_SERVICE_NO_AVAILABLE(50001, "book service no available"),
	USER_NO_AVAILABLE(50002, "user service no available"),
	COMMENT_CONTROL_NO_AVAILABLE(50003, "comment control service no available"),
	ORDER_SERVICE_NO_AVAILABLE(50004, "order service no available"),
	METHOD_NOT_ALLOWED(50405, "METHOD_NOT_ALLOWED"),
	IOT_UN_KNOW_ERROR(50900, "IOT_UN_KNOW_ERROR"),
	IOT_GROUP_NOT_EXITS(50901, "IOT_GROUP_NOT_EXITS"),
	IOT_MACHINE_ALREADY_EXITS(50901, "IOT_MACHINE_ALREADY_EXITS"),
	IOT_MACHINE_NOT_EXITS(50902, "IOT_MACHINE_NOT_EXITS"),
	IOT_CHANNEL_AREA_NOT_EXITS(50903, "IOT_CHANNEL_AREA_NOT_EXITS"),
	DEVICE_IOT_CHANNEL_NOT_EXITS(50904, "DEVICE_IOT_CHANNEL_NOT_EXITS"),
	IOT_CHANNEL_NOT_EXITS(50905, "IOT_CHANNEL_NOT_EXITS"),
	DEVICE_NOT_EXITS(50100, "device not exits"),
	DEVICE_ALREADY_EXITS(50101, "device already exits"),
	DEVICE_SHIP_ALREADY_EXITS(50102, "device ship already exits"),
	DEVICE_ADAPTER_ALREADY_EXITS(50103, "device adapter already exits"),
	POWER_BANK_ALREADY_EXITS(50200, "power bank already exits"),
	POWER_BANK_NOT_EXITS(50201, "power bank not exits");

	private int code;
	private String message;

	ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
