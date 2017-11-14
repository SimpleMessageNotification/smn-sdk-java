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
package com.smn.model.request.sms;

import com.smn.common.SmnConstants;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the request to get sms message which is send
 *
 * @author zhangyx
 * @version 0.7
 */
public class GetSmsMessageRequest extends AbstractSmnRequest {
    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(GetSmsMessageRequest.class);

    /**
     * the message id is send
     */
    private String messageId;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Get sms message request projectId is null.");
            throw new RuntimeException("Get sms message request projectId is null.");
        }

        if (StringUtils.isBlank(messageId)) {
            LOGGER.error("Get sms message request messageId is null.");
            throw new RuntimeException("Get sms message request messageId is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.MESSAGE)
                .append(SmnConstants.URL_DELIMITER).append(messageId);

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
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "GetSmsMessageRequest{" +
                "messageId='" + messageId + '\'' +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
