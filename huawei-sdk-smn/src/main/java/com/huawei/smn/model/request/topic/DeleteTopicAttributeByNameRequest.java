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
package com.huawei.smn.model.request.topic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.AccessPolicyType;
import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public class DeleteTopicAttributeByNameRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteTopicAttributeByNameRequest.class);

    /**
     * topic attribute access policy
     */
    public static final String ACCESS_POLICY = "access_policy";

    /**
     * topic attribute introduction
     */
    public static final String INTRODUCTION = "introduction";

    /**
     * topic attribute sms sign id
     */
    public static final String SMS_SIGN_ID = "sms_sign_id";

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * attribute's name,may support specified string only
     */
    private String attributesName;

    /**
     * smn endpoint
     */
    private String smnEndpoint;

    /**
     * project id
     */
    private String projectId;

    /**
     * xAuthToken
     */
    private String xAuthToken;

    /**
     * build and get request url
     */
    public String getRequestUri() throws RuntimeException {

        if (StringUtils.isBlank(getProjectId())) {
            LOGGER.error("Delete topic request projectId is null.");
            throw new NullPointerException("Delete topic request projectId is null.");
        }

        if (StringUtils.isBlank(getTopicUrn())) {
            LOGGER.error("TopicUrn is null.");
            throw new NullPointerException("TopicUrn is null.");
        }

        if (StringUtils.isBlank(getAttributesName()) || !isValidAttributeName(getAttributesName())) {
            LOGGER.error("Attributte name is null or is not valid");
            throw new RuntimeException("Attributte name is null or is not valid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(getProjectId()).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(getTopicUrn()).append("/attributes").append(SmnConstants.URL_DELIMITER)
                .append(getAttributesName());

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
     * @param topicUrn
     *            the topicUrn to set
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
     * @param attributesName
     *            the attributesName to set
     */
    public void setAttributesName(String attributesName) {
        this.attributesName = attributesName;
    }

    /**
     * @return the smnEndpoint
     */
    public String getSmnEndpoint() {
        return smnEndpoint;
    }

    /**
     * @param smnEndpoint
     *            the smnEndpoint to set
     */
    public void setSmnEndpoint(String smnEndpoint) {
        this.smnEndpoint = smnEndpoint;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the xAuthToken
     */
    public String getxAuthToken() {
        return xAuthToken;
    }

    public void setxAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeleteTopicAttributeByNameRequest [topicUrn=").append(topicUrn).append(", attributesName=")
                .append(attributesName).append(", smnEndpoint=").append(smnEndpoint).append(", projectId=")
                .append(projectId).append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }

}
