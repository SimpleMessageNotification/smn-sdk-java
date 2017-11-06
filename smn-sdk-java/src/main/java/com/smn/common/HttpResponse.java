/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
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
