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
 * the request to list topic attribute
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.8
 */
public class ListTopicAttributesRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(ListTopicAttributesRequest.class);

    /**
     * list topic attribute suffix
     */
    final static String LIST_TOPIC_ATTRIBUTE_SUFFIX = "/attributes?";

    /**
     * final string "name="
     */
    final static String NAME_SUFFIX = "name=";

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * attribute name
     */
    private String attributesName;

    /**
     * build and get request uri
     */
    public String getRequestUri() throws RuntimeException {

        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List topic attribute request,projectId is null.");
            throw new NullPointerException("List topic attribute request, projectId is null.");
        }

        if (StringUtils.isBlank(topicUrn)) {
            LOGGER.error("TopicUrn is null.");
            throw new NullPointerException("TopicUrn is null.");
        }

        if (!StringUtils.isEmpty(getAttributesName()) && !isValidAttributeName(attributesName)) {
            LOGGER.error("Attribute name is not valid.");
            throw new RuntimeException("Attribute name is not valid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(topicUrn).append(LIST_TOPIC_ATTRIBUTE_SUFFIX);

        // 设置参数
        if (!StringUtils.isEmpty(getAttributesName())) {
            sb.append(NAME_SUFFIX).append(getAttributesName());
        }

        LOGGER.info("Request uri is {}.", sb.toString());
        return sb.toString();
    }

    private boolean isValidAttributeName(String attributesName) {

        if (AccessPolicyType.ACCESS_POLICY.equals(attributesName)
                || AccessPolicyType.INTRODUCTION.equals(attributesName)
                || AccessPolicyType.SMS_SIGN_ID.equals(attributesName)) {
            return true;
        }

        return false;
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() throws RuntimeException {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ListTopicAttributesRequest [topicUrn=").append(topicUrn).append(", attributesName=")
                .append(attributesName).append(", smnEndpoint=").append(smnEndpoint).append(", projectId=")
                .append(projectId).append("]");
        return builder.toString();
    }

}
