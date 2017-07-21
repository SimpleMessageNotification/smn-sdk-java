package com.huawei.smn.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.SmnRequest;
import com.huawei.smn.service.MessageTemplateService;

/**
 * Message template service implemented
 * 
 * @author huangqiong
 *
 */
public class MessageTemplateServiceImpl implements MessageTemplateService {
	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory.getLogger(MessageTemplateServiceImpl.class);

	/**
	 * encapsulated request
	 */
	private SmnRequest smnRequest = null;

	/**
	 * init
	 */
	public void init() {
		if (StringUtils.isBlank(smnRequest.getRequestUrl())) {
			logger.error("Request url is null.");
			throw new RuntimeException("Request url is null.");
		}
	}

	/**
	 * create template
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> createMessageTemplate(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
			Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, url);
			logger.debug("End to create message template. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Failed to create message template.", e);
			throw new RuntimeException("Failed to create message template.", e);
		}
	}

	/**
	 * update template
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> updateMessageTemplate(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
			Map<String, Object> responseMap = HttpUtil.put(requestHeader, requestParam, url);
			logger.info("End to update message template. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Failed to update message template.", e);
			throw new RuntimeException("Failed to update message template.", e);
		}
	}

	/**
	 * delete template
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> deleteMessageTemplate(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
			logger.info("End to delete message template. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Failed to delete message template.", e);
			throw new RuntimeException("Failed to delete message template.", e);
		}
	}

	/**
	 * query template list
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> listMessageTemplates(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
			logger.info("End to list message templates. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Failed to list message templates.", e);
			throw new RuntimeException("Failed to list message templates.", e);
		}
	}

	/**
	 * query template detail
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> queryMsgTemplateDetail(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
			logger.info("End to query message templates. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Failed to query message templates.", e);
			throw new RuntimeException("Failed to query message templates.", e);
		}
	}

	@Override
	public void setSmnRequest(SmnRequest smnRequest) {
		this.smnRequest = smnRequest;
	}

	public SmnRequest getSmnRequest() {
		return smnRequest;
	}

}
