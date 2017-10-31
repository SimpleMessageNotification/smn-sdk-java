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

import com.smn.common.HttpMethod;
import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.http.HttpConfiguration;
import com.smn.model.request.topic.*;
import com.smn.service.AbstractCommonService;
import com.smn.service.IAMService;
import com.smn.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Topic service implemented
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class TopicServiceImpl extends AbstractCommonService implements TopicService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    /**
     * 无参构造函数
     */
    public TopicServiceImpl() {
        super();
    }

    /**
     * 给定iamService和smnConfiguration构造实例
     *
     * @param iamService        the iamService to set
     * @param smnConfiguration  the smnConfiguration to set
     * @param httpConfiguration the http configuration
     */
    public TopicServiceImpl(IAMService iamService, SmnConfiguration smnConfiguration, HttpConfiguration httpConfiguration) {
        super(iamService, smnConfiguration, httpConfiguration);
    }

    /**
     * (non-Javadoc)
     *
     * @see TopicService#createTopic(CreateTopicRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#deleteTopic(DeleteTopicRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#listTopics(ListTopicsRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#queryTopicDetail(QueryTopicDetailRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#updateTopic(UpdateTopicRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#listTopicAttributes(ListTopicAttributesRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#updateTopicAttribute(UpdateTopicAttributeRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest)
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
     * (non-Javadoc)
     *
     * @see TopicService#deleteTopicAttributes(DeleteTopicAttributesRequest)
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
