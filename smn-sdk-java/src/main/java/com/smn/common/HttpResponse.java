/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

/**
 * @author huangqiong
 * @version 0.6
 */
public class HttpResponse {

	private int httpCode;

	private Map<String, Object> body = new HashMap<String, Object>();

	public boolean isSuccessed() {
		// if httpCode in range [200,300)
		if (httpCode >= HttpStatus.SC_OK && httpCode < HttpStatus.SC_MULTIPLE_CHOICES) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the httpCode
	 */
	public int getHttpCode() {
		return httpCode;
	}

	/**
	 * @param httpCode
	 *            the httpCode to set
	 */
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	/**
	 * @return the body
	 */
	public Map<String, Object> getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HttpResponse [httpCode=" + httpCode + ", body=" + body + "]";
	}

}
