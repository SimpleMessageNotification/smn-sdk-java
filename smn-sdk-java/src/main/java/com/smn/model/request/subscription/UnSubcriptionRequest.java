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
package com.smn.model.request.subscription;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.SmnConstants;
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;

/**
 * the request to unsubscription
 *
 * @author huangqiong
 * @version 0.1
 * author yangyanping
 * @version 0.2
 */
public class UnSubcriptionRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(UnSubcriptionRequest.class);

    /**
     * subscripter's unique resource identifier
     */
    private String subscriptionUrn;

    /**
     *参数校验
     */
    private void validate(){
        
        if(!ValidationUtil.validateTopicUrn(subscriptionUrn)){
            throw new RuntimeException("subscription urn is illegal");
        }
    }
    
    /**
 * build and get request url
     */
    @Override
    public String getRequestUri() throws RuntimeException {
    
        validate();
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("UnSubcription request projectId is null.");
            throw new NullPointerException("UnSubcription request projectId is null.");
        }

        if (StringUtils.isBlank(subscriptionUrn)) {
            LOGGER.error("SubscriptionUrn is null.");
            throw new NullPointerException("SubscriptionUrn is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUBSCRIPTIONS)
                .append(SmnConstants.URL_DELIMITER).append(subscriptionUrn);

        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    /**
     * @return the subscriptionUrn
     */
    public String getSubscriptionUrn() {
        
        return subscriptionUrn;
    }

    /**
     * @param subscriptionUrn
     *            the subscriptionUrn to set
     */
    public void setSubscriptionUrn(String subscriptionUrn) {
        this.subscriptionUrn = subscriptionUrn;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UnSubcriptionRequest [subscriptionUrn=").append(subscriptionUrn).append(", smnEndpoint=")
                .append(smnEndpoint).append(", projectId=").append(projectId).append(", xAuthToken=").append(xAuthToken)
                .append("]");
        return builder.toString();
    }

}
