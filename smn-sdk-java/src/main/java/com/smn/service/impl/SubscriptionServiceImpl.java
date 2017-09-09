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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.HttpResponse;
import com.smn.common.utils.HttpUtil;
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;
import com.smn.service.AbstractCommonService;
import com.smn.service.SubscriptionService;

/**
 * Subscribe service implemented
 * 
 * @author huangqiong
 * @version 0.6
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
     * 
     * @see
     * com.huawei.smn.service.SubscriptionService#listSubscriptions(com.huawei.
     * smn.model.request.subscription.ListSubscriptionsRequest)
     */
    public HttpResponse listSubscriptions(ListSubscriptionsRequest listSubscriptionsRequest) throws RuntimeException {

        LOGGER.info("Start list subscribtion.");

        try {
            Map<String, String> requestHeader = listSubscriptionsRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            listSubscriptionsRequest.setSmnEndpoint(smnEndpoint);
            listSubscriptionsRequest.setProjectId(projectId);
            String url = buildRequestUrl(listSubscriptionsRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            HttpResponse httpResponse = HttpUtil.get(requestHeader, url);
            return httpResponse;
        } catch (Exception e) {
            LOGGER.error("Fail to list subscriptions.", e);
            throw new RuntimeException("Fail to list subscriptions.", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huawei.smn.service.SubscriptionService#listSubscriptions(com.huawei.
     * smn.model.request.subscription.ListSubscriptionsRequest)
     */
    public HttpResponse subscribe(SubcriptionRequest subcriptionRequest) throws RuntimeException {

        LOGGER.info("Start a new  subscribtion.");

        try {
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            subcriptionRequest.setSmnEndpoint(smnEndpoint);
            projectId = getIAMService().getAuthentication().getProjectId();
            subcriptionRequest.setProjectId(projectId);
            Map<String, String> requestHeader = subcriptionRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = subcriptionRequest.getRequestParameterMap();
            String url = buildRequestUrl(subcriptionRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            HttpResponse httpResponse = HttpUtil.post(requestHeader, requestParam, url);
            return httpResponse;
        } catch (Exception e) {
            LOGGER.error("Fail to subscribe.", e);
            throw new RuntimeException("Fail to subscribe.", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huawei.smn.service.SubscriptionService#unsubscribe(com.huawei.smn.
     * model.request.subscription.UnSubcriptionRequest)
     */
    public HttpResponse unsubscribe(UnSubcriptionRequest unSubcriptionRequest) throws RuntimeException {

        LOGGER.info("Start delete a subscribtion.");

        try {
            Map<String, String> requestHeader = unSubcriptionRequest.getRequestHeaderMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            unSubcriptionRequest.setSmnEndpoint(smnEndpoint);
            unSubcriptionRequest.setProjectId(projectId);
            String url = buildRequestUrl(unSubcriptionRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            HttpResponse httpResponse = HttpUtil.delete(requestHeader, url);
            return httpResponse;
        } catch (Exception e) {
            LOGGER.error("Fail to unsubscribe.", e);
            throw new RuntimeException("Fail to unsubscribe.", e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.huawei.smn.service.SubscriptionService#listSubscriptionsByTopic(com.
     * huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest)
     */
    public HttpResponse listSubscriptionsByTopic(ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest)
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
            HttpResponse httpResponse = HttpUtil.get(requestHeader, url);
            return httpResponse;
        } catch (Exception e) {
            LOGGER.error("Fail to list subscribtion by topic.", e);
            throw new RuntimeException("Fail to list subscribtion by topic.", e);
        }
    }

}
