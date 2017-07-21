package com.huawei.smn.common;

public enum HttpMethodEnum {
	/**
	 * http GET 方法
	 */
	GET("GET"),
	/**
	 * http POST方法
	 */
	POST("POST"),
	/**
	 * http PUT方法
	 */
	PUT("PUT"),
	/**
	 * http DELETE方法
	 */
	DELETE("DELETE");
	private String code;

	/**
	 * 默认构造函数
	 */
	HttpMethodEnum() {
	}

	/**
	 * 构造函数
	 * 
	 * @param code
	 */
	HttpMethodEnum(String code) {
		this.code = code;
	}

	/**
	 * 获取HTTP方法 的值
	 * 
	 * @return HTTP方法的值
	 */
	public String getCode() {
		return code;
	}

}
