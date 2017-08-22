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
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.AccessPolicyType;
import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.common.utils.JsonUtil;
import com.huawei.smn.common.utils.ValidationUtil;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public class UpdateTopicAttributeRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(UpdateTopicAttributeRequest.class);

    /**
     * attributes
     */
    private static final String ATTRIBUTES = "attributes";

    /**
     * attribute name
     */
    private String attributesName;

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;
    /**
     * acess policy
     */
    private LinkedHashMap<String, Object> acessPolicy = null;

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
     * attribute value
     */
    private String value;

    /**
     * build and get request url
     */
    public String getRequestUri() throws RuntimeException {

        if (StringUtils.isBlank(getProjectId())) {
            LOGGER.error("Update topic attribute request projectId is null.");
            throw new NullPointerException("Update topic attribute request projectId is null.");
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
                .append(getTopicUrn()).append(SmnConstants.URL_DELIMITER).append(ATTRIBUTES)
                .append(SmnConstants.URL_DELIMITER).append(getAttributesName());

        LOGGER.info("Request url is {}.", sb.toString());
        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put("value", JsonUtil.getJsonStringByMap(getAcessPolicy()));
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
     * @return the acessPolicy
     */
    public LinkedHashMap<String, Object> getAcessPolicy() {
        return acessPolicy;
    }

    /**
     * @param acessPolicy
     *            the acessPolicy to set
     */
    public void setAcessPolicy(LinkedHashMap<String, Object> acessPolicy) {
        this.acessPolicy = acessPolicy;
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

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {

        if (StringUtils.isNoneBlank(value)) {
            if (!ValidationUtil.validateAttributValue(value)) {
                throw new RuntimeException("Attribute value is illegal.");
            } else {
                this.value = value;
            }
        }

        this.value = value;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UpdateTopicAttributeRequest [attributesName=").append(attributesName).append(", topicUrn=")
                .append(topicUrn).append(", acessPolicy=").append(acessPolicy).append(", smnEndpoint=")
                .append(smnEndpoint).append(", projectId=").append(projectId).append(", xAuthToken=").append(xAuthToken)
                .append(", value=").append(value).append("]");
        return builder.toString();
    }

}
