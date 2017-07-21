package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.SmnRequest;

/**
 * Message template service
 * 
 * @author huangqiong
 *
 */
public interface MessageTemplateService {
	/**
	 * set request
	 * 
	 * @param smnRequest
	 */
	void setSmnRequest(SmnRequest smnRequest);

	// init
	public void init();

	// create template
	Map<String, Object> createMessageTemplate(SmnRequest smnRequest) throws RuntimeException;

	// update template
	Map<String, Object> updateMessageTemplate(SmnRequest smnRequest) throws RuntimeException;

	// delete template
	Map<String, Object> deleteMessageTemplate(SmnRequest smnRequest) throws RuntimeException;

	// query template list
	Map<String, Object> listMessageTemplates(SmnRequest smnRequest) throws RuntimeException;

	// query template detail
	Map<String, Object> queryMsgTemplateDetail(SmnRequest smnRequest) throws RuntimeException;

}
