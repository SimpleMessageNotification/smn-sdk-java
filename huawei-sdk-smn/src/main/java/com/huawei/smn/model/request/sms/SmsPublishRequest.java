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

package com.huawei.smn.model.request.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.common.utils.AesUtil;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:30:35
 * @version 0.1
 */
public class SmsPublishRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(SmsPublishRequest.class);

    /**
     * phones's regex
     */
    private static final Pattern PATTERN_TELTPHONE = Pattern.compile("^\\+?[0-9]{1,31}");

    /**
     * message access point
     */
    private String endpoint;

    /**
     * message to send
     */
    private String message;

    /**
     * message signature id
     */
    private String signId;

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
     * get request uri
     * 
     * @return String uri
     * 
     */
    public String getRequestUri() {

        if (StringUtils.isBlank(projectId)) {

            LOGGER.error("Publish sms projectId is null.");
            throw new NullPointerException("ProjectId is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(getProjectId()).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS);
        LOGGER.info("Sms request url is: " + sb.toString());
        return sb.toString();
    }

    /**
     * build and get request parameters
     * 
     * @return Map
     *         {@value}endpoint
     *         {@value}message
     *         {@value optional}sign_id
     * 
     */
    public Map<String, Object> getRequestParameterMap() {
        validatePhoneNumber(getEndpoint());
        validateMessage(getMessage());
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put(SmnConstants.ENDPOINT, getEndpoint());
        requestParameterMap.put(SmnConstants.MESSAGE, getMessage());
        if (StringUtils.isNoneBlank(getSignId())) {
            requestParameterMap.put(SmnConstants.SIGN_ID, getSignId());
        }
        return requestParameterMap;
    }

    /**
     * validate phonenumber
     * 
     * @param endpoint
     */
    private void validatePhoneNumber(String endpoint) {
        if (endpoint == null) {
            LOGGER.error("PhoneNumber is null.");
            throw new NullPointerException("PhoneNumber is null.");
        }

        if (!PATTERN_TELTPHONE.matcher(endpoint).matches()) {
            LOGGER.error("Wrong phone number format");
            throw new RuntimeException(
                    "The wrong phone number format, the correct number format is +8600000000000 or 00000000000");
        }
    }

    /**
     * validate sms message
     * 
     * @param message
     */
    private void validateMessage(String message) {

        if (message == null) {
            LOGGER.error("Message is null.");
            throw new NullPointerException("Message is null.");
        }

        // 短信发送时，需要注意短信长度,CMPP协议短信最多500字符
        if (message.length() > 500) {
            LOGGER.warn("SMS content is too long, more than {} characters of the message content will be cut off.",
                    500);
        }
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint
     *            the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the signId
     */
    public String getSignId() {
        return signId;
    }

    /**
     * @param signId
     *            the signId to set
     */
    public void setSignId(String signId) {
        this.signId = signId;
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

    /**
     * @param xAuthToken
     *            the xAuthToken to set
     */
    public void setxAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // endpoint encrypt
        String tmpEndpoint = AesUtil.encrypt(endpoint, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        builder.append("SmsPublishRequest [endpoint=").append(tmpEndpoint).append(", message=").append(message)
                .append(", signId=").append(signId).append("]");
        return builder.toString();
    }

}
