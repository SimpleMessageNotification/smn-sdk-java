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

import com.smn.common.AccessPolicyType;
import com.smn.common.SmnConstants;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the request to delete topic attribute
 *
 * @author huangqiong
 * @version 0.1
 */
public class DeleteTopicAttributeByNameRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteTopicAttributeByNameRequest.class);

    /**
     * final string attributes
     */
    private static final String ATTRIBUTES = "attributes";

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * attribute's name,may support specified string only
     */
    private String attributesName;

    /**
     * build and get request uri
     */
    public String getRequestUri() throws RuntimeException {

        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Delete topic attribute by name request, projectId is null.");
            throw new NullPointerException("Delete topic attribute by name request, projectId is null.");
        }

        if (StringUtils.isBlank(topicUrn)) {
            LOGGER.error("TopicUrn is null.");
            throw new NullPointerException("TopicUrn is null.");
        }

        if (StringUtils.isBlank(attributesName) || !isValidAttributeName(attributesName)) {
            LOGGER.error("Attribute name is null or not valid");
            throw new RuntimeException("Attribute name is null or not valid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(topicUrn).append(SmnConstants.URL_DELIMITER).append(ATTRIBUTES)
                .append(SmnConstants.URL_DELIMITER).append(attributesName);

        LOGGER.info("Request uri is {}.", sb.toString());
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

    private boolean isValidAttributeName(String attribute) {

        if (AccessPolicyType.ACCESS_POLICY.equals(attribute) || AccessPolicyType.INTRODUCTION.equals(attribute)
                || AccessPolicyType.SMS_SIGN_ID.equals(attribute)) {
            return true;
        }

        return false;
    }

    /**
     * @return the topicUrn
     */
    public String getTopicUrn() {
        return topicUrn;
    }

    /**
     * @param topicUrn the topicUrn to set
     */
    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    /**
     * @return the attributesName
     */
    public String getAttributesName() {
        return attributesName;
    }

    /**
     * @param attributesName the attributesName to set
     */
    public void setAttributesName(String attributesName) {
        this.attributesName = attributesName;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeleteTopicAttributeByNameRequest [topicUrn=").append(topicUrn)
                .append(", attributesName=").append(attributesName)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}
