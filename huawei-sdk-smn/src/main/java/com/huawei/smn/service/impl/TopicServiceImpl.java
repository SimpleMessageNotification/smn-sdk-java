package com.huawei.smn.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.SmnRequest;
import com.huawei.smn.service.TopicService;

/**
 * Topic service implemented
 * 
 * @author huangqiong
 *
 */
public class TopicServiceImpl implements TopicService {
	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

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
	 * create topic
	 *
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> createTopic(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, Object> responseMap = HttpUtil.post(smnRequest.getRequestHeaderMap(),
					smnRequest.getRequestParameterMap(), url);
			logger.info("End to create topic. RequestId is {}. TopicUrn is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap.get("topic_urn"),
					System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to create topic.", e);
			throw new RuntimeException("Fail to create topic.", e);
		}
	}

	/**
	 * delete topic
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> deleteTopic(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
			logger.info("End to delete topic. RequestId is {}. TopicUrn is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap.get("topic_urn"),
					System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to delete topic.", e);
			throw new RuntimeException("Fail to delete topic.", e);
		}
	}

	/**
	 * query topic list
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> listTopics(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
			logger.info("End to list topic. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to list topic.", e);
			throw new RuntimeException("Fail to list topic.", e);
		}
	}

	/**
	 * query topic detail
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> queryTopicDetail(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
			logger.info("End to query topic. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to query topic.", e);
			throw new RuntimeException("Fail to query topic.", e);
		}
	}

	/**
	 * update topic
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> updateTopic(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
			Map<String, Object> responseMap = HttpUtil.put(requestHeader, requestParam, smnRequest.getRequestUrl());
			logger.info("End to update topic. RequestId is {}. TopicUrn is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap.get("topic_urn"),
					System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to update topic.", e);
			throw new RuntimeException("Fail to update topic.", e);
		}
	}

	/**
	 * query topic attribute
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> listTopicAttributes(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
			logger.info("End to list topic attributes. RequestId is {}. responseMap is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to list topic attributes.", e);
			throw new RuntimeException("Fail to list topic attributes.", e);
		}
	}

	/**
	 * update topic attributes
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> updateTopicAttribute(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
			Map<String, Object> responseMap = HttpUtil.put(requestHeader, requestParam, url);
			logger.info("End to update topic attributes. responseMap is {}.Cost is {}ms", responseMap,
					System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to update topic attributes.", e);
			throw new RuntimeException("Fail to update topic attributes.", e);
		}
	}

	/**
	 * delete attribute for designated topic
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> deleteTopicAttributeByName(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.delete(requestHeader, smnRequest.getRequestUrl());
			logger.info("End to send delete topic attributes by name. RequestId is {}. MessageId is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to delete topic attributes by name.", e);
			throw new RuntimeException("Fail to delete topic attributes by name.", e);
		}
	}

	/**
	 * delete all attributes
	 * 
	 * @param smnRequest
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	public Map<String, Object> deleteTopicAttributes(SmnRequest smnRequest) throws RuntimeException {
		long startTime = System.currentTimeMillis();
		try {
			init();
			String url = smnRequest.getRequestUrl();
			Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
			Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
			logger.info("End to delete topic attributes. RequestId is {}. MessageId is {}. Cost is {}ms",
					responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
			return responseMap;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to delete topic attributes.", e);
			throw new RuntimeException("Fail to delete topic attributes.", e);
		}
	}

	public SmnRequest getSmnRequest() {
		return smnRequest;
	}

	@Override
	public void setSmnRequest(SmnRequest smnRequest) {
		this.smnRequest = smnRequest;

	}
}
