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
import com.huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.huawei.smn.model.request.subscription.ListSubscriptionsRequest;
import com.huawei.smn.model.request.subscription.SubcriptionRequest;
import com.huawei.smn.model.request.subscription.UnSubcriptionRequest;
import com.huawei.smn.service.AbstractCommonService;
import com.huawei.smn.service.SubscriptionService;

/**
 * Subscribe service implemented
 * 
 * @author huangqiong
 * @version 0.1
 *
 */
public class SubscriptionServiceImpl extends AbstractCommonService implements SubscriptionService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    /**
     * smn host url
     */
    private String smnEndpoint;

    /**
     * project id
     */
    private String projectId;

    /*
     * (non-Javadoc)
     * @see
     * com.huawei.smn.service.SubscriptionService#subscribe(com.huawei.smn.model
     * .request.subscription.SubcriptionRequest)
     */
    public Map<String, Object> subscribe(SubcriptionRequest subcriptionRequest) throws RuntimeException {

        LOGGER.info("Start a new  subscribtion.");

        try {
            Map<String, String> requestHeader = subcriptionRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = subcriptionRequest.getRequestParameterMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            subcriptionRequest.setSmnEndpoint(smnEndpoint);
            subcriptionRequest.setProjectId(projectId);
            String url = buildRequestUrl(subcriptionRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to subscribe.", e);
            throw new RuntimeException("Fail to subscribe.", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.huawei.smn.service.SubscriptionService#unsubscribe(com.huawei.smn.
     * model.request.subscription.UnSubcriptionRequest)
     */
    public Map<String, Object> unsubscribe(UnSubcriptionRequest unSubcriptionRequest) throws RuntimeException {

        LOGGER.info("Start delete a subscribtion.");

        try {
            Map<String, String> requestHeader = unSubcriptionRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            unSubcriptionRequest.setSmnEndpoint(smnEndpoint);
            unSubcriptionRequest.setProjectId(projectId);
            String url = buildRequestUrl(unSubcriptionRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to unsubscribe.", e);
            throw new RuntimeException("Fail to unsubscribe.", e);
        }

    }

    /*
     * (non-Javadoc)
     * @see
     * com.huawei.smn.service.SubscriptionService#listSubscriptions(com.huawei.
     * smn.model.request.subscription.ListSubscriptionsRequest)
     */
    public Map<String, Object> listSubscriptions(ListSubscriptionsRequest listSubscriptionsRequest)
            throws RuntimeException {

        LOGGER.info("Start list subscribtion.");

        try {
            Map<String, String> requestHeader = listSubscriptionsRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            listSubscriptionsRequest.setSmnEndpoint(smnEndpoint);
            listSubscriptionsRequest.setProjectId(projectId);
            String url = buildRequestUrl(listSubscriptionsRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to list subscriptions.", e);
            throw new RuntimeException("Fail to list subscriptions.", e);
        }

    }

    /*
     * (non-Javadoc)
     * @see
     * com.huawei.smn.service.SubscriptionService#listSubscriptionsByTopic(com.
     * huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest)
     */
    public Map<String, Object> listSubscriptionsByTopic(ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest)
            throws RuntimeException {

        LOGGER.info("Start list subscribtion by topic.");

        try {
            Map<String, String> requestHeader = listSubscriptionsByTopicRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            listSubscriptionsByTopicRequest.setSmnEndpoint(smnEndpoint);
            listSubscriptionsByTopicRequest.setProjectId(projectId);
            String url = buildRequestUrl(listSubscriptionsByTopicRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Fail to list subscribtion by topic.", e);
            throw new RuntimeException("Fail to list subscribtion by topic.", e);
        }
    }
}
