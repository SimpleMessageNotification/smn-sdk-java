package com.huawei.smn.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.SmnRequest;
import com.huawei.smn.service.PublishService;

/**
 * Publish service implemented
 * 
 * @author huangqiong
 *
 */
public class PublishServiceImpl implements PublishService {
	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory.getLogger(PublishServiceImpl.class);
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
		if (smnRequest.getIamService() == null) {
			logger.error("IamService is null.");
			throw new RuntimeException("IamService is null.");
		}
	}

	/**
	 * message publish
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> publish(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
			Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, smnRequest.getRequestUrl());
			logger.info("End to publish message. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Failed to publish message.", e);
			throw new RuntimeException("Failed to publish message.", e);
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
