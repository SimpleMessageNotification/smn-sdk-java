/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.service.impl;

import com.smn.common.HttpMethod;
import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.common.ClientConfiguration;
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;
import com.smn.service.AbstractCommonService;
import com.smn.service.IAMService;
import com.smn.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subscribe service implemented
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class SubscriptionServiceImpl extends AbstractCommonService implements SubscriptionService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    /**
     * no args constructor
     */
    public SubscriptionServiceImpl() {
        super();
    }

    /**
     * give iamService and smnConfiguration constructor
     *
     * @param iamService        the iamService to set
     * @param smnConfiguration  the smnConfiguration to set
     * @param clientConfiguration the client configuration
     */
    public SubscriptionServiceImpl(IAMService iamService, SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        super(iamService, smnConfiguration, clientConfiguration);
    }

    /**
     * (non-Javadoc)
     *
     * @see SubscriptionService#listSubscriptions(ListSubscriptionsRequest)
     */
    public HttpResponse listSubscriptions(ListSubscriptionsRequest listSubscriptionsRequest) throws RuntimeException {

        LOGGER.info("Start list subscribtion.");

        try {
            return sendRequest(listSubscriptionsRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Fail to list subscriptions.", e);
            throw new RuntimeException("Fail to list subscriptions.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SubscriptionService#subscribe(SubcriptionRequest)
     */
    public HttpResponse subscribe(SubcriptionRequest subcriptionRequest) throws RuntimeException {

        LOGGER.info("Start a new  subscribtion.");

        try {
            return sendRequest(subcriptionRequest, HttpMethod.POST);
        } catch (Exception e) {
            LOGGER.error("Fail to subscribe.", e);
            throw new RuntimeException("Fail to subscribe.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SubscriptionService#unsubscribe(UnSubcriptionRequest)
     */
    public HttpResponse unsubscribe(UnSubcriptionRequest unSubcriptionRequest) throws RuntimeException {

        LOGGER.info("Start delete a subscribtion.");

        try {
            return sendRequest(unSubcriptionRequest, HttpMethod.DELETE);
        } catch (Exception e) {
            LOGGER.error("Fail to unsubscribe.", e);
            throw new RuntimeException("Fail to unsubscribe.", e);
        }

    }

    /**
     * (non-Javadoc)
     *
     * @see SubscriptionService#listSubscriptionsByTopic(ListSubscriptionsByTopicRequest)
     */
    public HttpResponse listSubscriptionsByTopic(ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest)
            throws RuntimeException {

        LOGGER.info("Start list subscribtion by topic.");

        try {
            return sendRequest(listSubscriptionsByTopicRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Fail to list subscribtion by topic.", e);
            throw new RuntimeException("Fail to list subscribtion by topic.", e);
        }
    }

}
