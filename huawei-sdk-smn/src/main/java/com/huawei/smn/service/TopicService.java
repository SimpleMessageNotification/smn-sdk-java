/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http: //www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.request.topic.CreateTopicRequest;
import com.huawei.smn.model.request.topic.DeleteTopicAttributeByNameRequest;
import com.huawei.smn.model.request.topic.DeleteTopicAttributesRequest;
import com.huawei.smn.model.request.topic.DeleteTopicRequest;
import com.huawei.smn.model.request.topic.ListTopicAttributesRequest;
import com.huawei.smn.model.request.topic.ListTopicsRequest;
import com.huawei.smn.model.request.topic.QueryTopicDetailRequest;
import com.huawei.smn.model.request.topic.UpdateTopicAttributeRequest;
import com.huawei.smn.model.request.topic.UpdateTopicRequest;

/**
 * topic service
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public interface TopicService extends CommonService {

    /**
     * create topic
     * <p>
     * success，return<CODE>Map</CODE>including: request_id,message_id,status
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> createTopic(CreateTopicRequest smnRequest) throws RuntimeException;

    /**
     * delete topic
     * <p>
     * success，return<CODE>Map</CODE>including: request_id,message_id,status
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> deleteTopic(DeleteTopicRequest smnRequest) throws RuntimeException;

    /**
     * query topic list
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> listTopics(ListTopicsRequest smnRequest) throws RuntimeException;

    /**
     * query topic detail
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> queryTopicDetail(QueryTopicDetailRequest smnRequest) throws RuntimeException;

    /**
     * update topic display name
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> updateTopic(UpdateTopicRequest smnRequest) throws RuntimeException;

    /**
     * query topic attributes
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> listTopicAttributes(ListTopicAttributesRequest smnRequest) throws RuntimeException;

    /**
     * update topic attributes
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> updateTopicAttribute(UpdateTopicAttributeRequest smnRequest) throws RuntimeException;

    /**
     * delete designated topic attribute by name
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest smnRequest)
            throws RuntimeException;

    /**
     * delete all topic attributes
     * <p>
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> deleteTopicAttributes(DeleteTopicAttributesRequest smnRequest) throws RuntimeException;

}
