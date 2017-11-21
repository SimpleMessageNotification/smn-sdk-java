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
 * list smsn sign request message
 *
 * @author zhangyx
 * @version 0.9
 */
public class ListSmsSignsRequest extends AbstractSmnRequest {

    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ListSmsSignsRequest.class);

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {

        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List sms sign request projectId is null.");
            throw new RuntimeException("List sms sign request projectId is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMS_SIGNATURE);

        LOGGER.info("Request url is {}.", sb.toString());
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
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ListSmsCallbackEventRequest [projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}

