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
package com.smn.service.impl;

import com.smn.common.utils.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.HttpResponse;
import com.smn.model.request.topic.CreateTopicRequest;
import com.smn.model.request.topic.DeleteTopicAttributeByNameRequest;
import com.smn.model.request.topic.DeleteTopicAttributesRequest;
import com.smn.model.request.topic.DeleteTopicRequest;
import com.smn.model.request.topic.ListTopicAttributesRequest;
import com.smn.model.request.topic.ListTopicsRequest;
import com.smn.model.request.topic.QueryTopicDetailRequest;
import com.smn.model.request.topic.UpdateTopicAttributeRequest;
import com.smn.model.request.topic.UpdateTopicRequest;
import com.smn.service.AbstractCommonService;
import com.smn.service.TopicService;

/**
 * Topic service implemented
 *
 * @author huangqiong
 * @version 0.1
 * @date 2017年8月2日
 */
public class TopicServiceImpl extends AbstractCommonService implements TopicService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    /**
     * create topic
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse createTopic(CreateTopicRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to create topic.");
        try {
            return sendRequest(smnRequest, HttpMethod.POST);
        } catch (Exception e) {
            LOGGER.error("Fail to create topic.", e);
            throw new RuntimeException("Fail to create topic.", e);
        }
    }

    /**
     * delete topic
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse deleteTopic(DeleteTopicRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to delete topic.");
        try {
            return sendRequest(smnRequest, HttpMethod.DELETE);
        } catch (Exception e) {
            LOGGER.error("Fail to delete topic.", e);
            throw new RuntimeException("Fail to delete topic.", e);
        }
    }

    /**
     * query topic list
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse listTopics(ListTopicsRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to list topic.");
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Fail to list topic.", e);
            throw new RuntimeException("Fail to list topic.", e);
        }
    }

    /**
     * query topic detail
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse queryTopicDetail(QueryTopicDetailRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to query topic detail.");
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Fail to query topic.", e);
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
    public HttpResponse updateTopic(UpdateTopicRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to update topic.");
        try {
            return sendRequest(smnRequest, HttpMethod.PUT);
        } catch (Exception e) {
            LOGGER.error("Fail to update topic.", e);
            throw new RuntimeException("Fail to update topic.", e);
        }
    }

    /**
     * query topic attribute
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse listTopicAttributes(ListTopicAttributesRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to list topic attributes.");
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Fail to list topic attributes.", e);
            throw new RuntimeException("Fail to list topic attributes.", e);
        }
    }

    /**
     * update topic attributes
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse updateTopicAttribute(UpdateTopicAttributeRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to update topic attributes.");
        try {
            return sendRequest(smnRequest, HttpMethod.PUT);
        } catch (Exception e) {
            LOGGER.error("Fail to update topic attributes.", e);
            throw new RuntimeException("Fail to update topic attributes.", e);
        }
    }

    /**
     * delete attribute for designated topic
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest smnRequest)
            throws RuntimeException {
        LOGGER.info("Start to delete topic attributes by name.");
        try {
            return sendRequest(smnRequest, HttpMethod.DELETE);
        } catch (Exception e) {
            LOGGER.error("Fail to delete topic attributes by name.", e);
            throw new RuntimeException("Fail to delete topic attributes by name.", e);
        }
    }

    /**
     * delete all attributes
     *
     * @param smnRequest
     * @return HttpResponse
     * @throws RuntimeException
     */
    public HttpResponse deleteTopicAttributes(DeleteTopicAttributesRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to delete topic attributes.");
        try {
            return sendRequest(smnRequest, HttpMethod.DELETE);
        } catch (Exception e) {
            LOGGER.error("Fail to delete topic attributes.", e);
            throw new RuntimeException("Fail to delete topic attributes.", e);
        }
    }
}
