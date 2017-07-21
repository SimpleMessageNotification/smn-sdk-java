package com.huawei.smn.model;

import java.util.Map;

import com.huawei.smn.service.impl.IAMServiceImpl;

/**
 * huangqiong
 * 
 * @author huangqiong
 *
 */
public interface SmnRequest {
	
	void init();

	String getRequestUrl();

	Map<String, Object> getRequestParameterMap();

	Map<String, String> getRequestHeaderMap();

	AuthenticationBean getAuthenticationBean() throws RuntimeException;

	IAMServiceImpl getIamService();

	IAMServiceImpl refreshIamService();

}
