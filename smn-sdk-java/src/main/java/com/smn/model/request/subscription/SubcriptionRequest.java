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

import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * the request to subscription
 *
 * @author huangqiong
 * @author yangyanping
 * @version 0.2
 */
public class SubcriptionRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(SubcriptionRequest.class);

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * protocol
     */
    private String protocol;

    /**
     * message getSignHeader point
     */
    private String endpoint;

    /**
     * remark
     */
    private String remark;

    /**
     * (non-Javadoc)
     *
     * @see AbstractSmnRequest#getRequestUri()
     */
    @Override
    public String getRequestUri() {
        validate();

        if (StringUtils.isBlank(topicUrn)) {
            LOGGER.error("Subcription request topicUrn is null.");
            throw new NullPointerException("Subcription request topicUrn is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(topicUrn).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUBSCRIPTIONS);

        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();

    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {

        if (!ValidationUtil.validateProtocol(protocol)) {
            LOGGER.error("Protocol is not valid.");
            throw new RuntimeException("Protocol is not valid.");
        }

        if (StringUtils.isBlank(endpoint)) {
            LOGGER.error("Endpoint is null.");
            throw new RuntimeException("Endpoint is null.");
        }

        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put(SmnConstants.SMN_PROTOCOL, protocol);
        requestParameterMap.put(SmnConstants.ENDPOINT, endpoint);
        requestParameterMap.put(SmnConstants.SMN_SUBCRIBE_REMARK, remark);
        return requestParameterMap;
    }

    /**
     * 校验参数
     */
    private void validate() {
        if (!ValidationUtil.validateTopicUrn(topicUrn)) {
            throw new RuntimeException(" subscribe request topic_urn is null.");
        }

        if (!ValidationUtil.validateEndPoint(endpoint, protocol)) {
            throw new RuntimeException("subscribe request smnEndpoint is illegal");
        }
        if (!checkRemark(remark)) {
            throw new RuntimeException("subscribe request remark is illegal");
        }

    }

    /**
     * check remark
     *
     * @param remark
     * @return boolean
     */
    private boolean checkRemark(String remark) {
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        if (remark == null) {
            return true;
        }
        try {
            byte[] b = remark.getBytes("utf-8");
            if (b.length > smnConfiguration.getMaxRemarkLength()) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
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
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SubcriptionRequest [topicUrn=").append(topicUrn)
                .append(", protocol=").append(protocol)
                .append(", remark=").append(remark)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}
