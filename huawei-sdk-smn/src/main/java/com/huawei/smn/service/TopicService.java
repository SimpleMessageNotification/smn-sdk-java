package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.SmnRequest;

/**
 * topic service
 * 
 * @author huangqiong
 *
 */
public interface TopicService {

	/**
	 * set request
	 * 
	 * @param smnRequest
	 */
	void setSmnRequest(SmnRequest smnRequest);

	// init
	void init();

	// create topic
	Map<String, Object> createTopic(SmnRequest smnRequest) throws RuntimeException;

	// delete topic
	Map<String, Object> deleteTopic(SmnRequest smnRequest) throws RuntimeException;

	// query topic list
	Map<String, Object> listTopics(SmnRequest smnRequest) throws RuntimeException;

	// query topic detail
	Map<String, Object> queryTopicDetail(SmnRequest smnRequest) throws RuntimeException;

	// update topic display name
	Map<String, Object> updateTopic(SmnRequest smnRequest) throws RuntimeException;

	// query topic attributes
	Map<String, Object> listTopicAttributes(SmnRequest smnRequest) throws RuntimeException;

	// update topic attributes
	Map<String, Object> updateTopicAttribute(SmnRequest smnRequest) throws RuntimeException;

	// delete designated topic attribute by name
	Map<String, Object> deleteTopicAttributeByName(SmnRequest smnRequest) throws RuntimeException;

	// delete all topic attributes
	Map<String, Object> deleteTopicAttributes(SmnRequest smnRequest) throws RuntimeException;

}
