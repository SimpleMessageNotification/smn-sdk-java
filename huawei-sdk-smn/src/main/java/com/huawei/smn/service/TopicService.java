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

    // set request
    void setSmnRequest(SmnRequest smnRequest);

    // init
    void init();

    // create topic
    Map<String, Object> createTopic() throws RuntimeException;

    // delete topic
    Map<String, Object> deleteTopic() throws RuntimeException;

    // query topic list
    Map<String, Object> listTopics() throws RuntimeException;

    // query topic detail
    Map<String, Object> queryTopicDetail() throws RuntimeException;

    // update topic display name
    Map<String, Object> updateTopic() throws RuntimeException;

    // query topic attributes
    Map<String, Object> listTopicAttributes() throws RuntimeException;

    // update topic attributes
    Map<String, Object> updateTopicAttribute() throws RuntimeException;

    // delete designated topic attribute by name
    Map<String, Object> deleteTopicAttributeByName() throws RuntimeException;

    // delete all topic attributes
    Map<String, Object> deleteTopicAttributes() throws RuntimeException;

}
