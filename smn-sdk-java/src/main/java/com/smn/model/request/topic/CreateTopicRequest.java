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
package com.smn.model.request.topic;

import com.smn.common.SmnConstants;
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the request to create topic
 *
 * @author huangqiong*
 * @version 0.1
 */
public class CreateTopicRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(CreateTopicRequest.class);

    /**
     * final string "name"
     */
    final static String NAME = "name";

    /**
     * final string "displayName"
     */
    final static String DISPLAY_NAME = "display_name";

    /**
     * topic name
     */
    private String name;

    /**
     * topic's descriptions
     */
    private String displayName;

    /**
     * build and get request uri
     */
    public String getRequestUri() throws RuntimeException {

        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Create topic request, projectId is null.");
            throw new NullPointerException("Create topic request, projectId is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI);

        LOGGER.info("Request uri is {}.", sb.toString());
        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put(DISPLAY_NAME, displayName);
        requestParameterMap.put(NAME, name);
        return requestParameterMap;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {

        if (!ValidationUtil.validateTopicName(name)) {
            throw new RuntimeException("Topic name is illegal.");
        }

        this.name = name;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * set displayName
     * <p>
     * validate display length before set the value
     *
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {

        if (StringUtils.isNoneEmpty(displayName)) {
            if (ValidationUtil.validateDisplayName(displayName)) {
                this.displayName = displayName;
            } else {
                throw new RuntimeException("Display name is oversized.");
            }
        } else {
            this.displayName = displayName;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreateTopicRequest [name=").append(name).append(", displayName=").append(displayName)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}
