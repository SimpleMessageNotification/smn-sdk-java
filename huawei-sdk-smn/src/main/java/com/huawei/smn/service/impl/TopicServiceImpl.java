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
package com.huawei.smn.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.request.topic.CreateTopicRequest;
import com.huawei.smn.model.request.topic.DeleteTopicAttributeByNameRequest;
import com.huawei.smn.model.request.topic.DeleteTopicAttributesRequest;
import com.huawei.smn.model.request.topic.DeleteTopicRequest;
import com.huawei.smn.model.request.topic.ListTopicAttributesRequest;
import com.huawei.smn.model.request.topic.ListTopicsRequest;
import com.huawei.smn.model.request.topic.QueryTopicDetailRequest;
import com.huawei.smn.model.request.topic.UpdateTopicAttributeRequest;
import com.huawei.smn.model.request.topic.UpdateTopicRequest;
import com.huawei.smn.service.AbstractCommonService;
import com.huawei.smn.service.TopicService;

/**
 * Topic service implemented
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public class TopicServiceImpl extends AbstractCommonService implements TopicService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    /**
     * smn host url
     */
    private String smnEndpoint;

    /**
     * project id
     */
    private String projectId;

    /**
     * create topic
     *
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    public Map<String, Object> createTopic(CreateTopicRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to create topic.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to create topic.", e);
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
    public Map<String, Object> deleteTopic(DeleteTopicRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to delete topic.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to delete topic.", e);
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
    public Map<String, Object> listTopics(ListTopicsRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to list topic.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to list topic.", e);
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
    public Map<String, Object> queryTopicDetail(QueryTopicDetailRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to query topic detail.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            return responseMap;
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
    public Map<String, Object> updateTopic(UpdateTopicRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to update topic.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.put(requestHeader, requestParam, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to update topic.", e);
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
    public Map<String, Object> listTopicAttributes(ListTopicAttributesRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to list topic attributes.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to list topic attributes.", e);
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
    public Map<String, Object> updateTopicAttribute(UpdateTopicAttributeRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to update topic attributes.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.put(requestHeader, requestParam, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to update topic attributes.", e);
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
    public Map<String, Object> deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest smnRequest)
            throws RuntimeException {
        LOGGER.info("Start to delete topic attributes by name.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to delete topic attributes by name.", e);
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
    public Map<String, Object> deleteTopicAttributes(DeleteTopicAttributesRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to delete topic attributes.");

        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to delete topic attributes.", e);
            throw new RuntimeException("Fail to delete topic attributes.", e);
        }
    }

}
