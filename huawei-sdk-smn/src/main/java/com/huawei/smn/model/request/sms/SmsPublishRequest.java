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
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
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

    @Override
    public String getRequestUrl() {
        if (Objects.isNull(getSmnConfiguration().getAuthenticationBean())
                || StringUtils.isBlank(getSmnConfiguration().getAuthenticationBean().getProjectId())
                || StringUtils.isBlank(getSmnConfiguration().getSmnEndpoint())) {
            LOGGER.error("Smn configuration error");
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getSmnConfiguration().getSmnEndpoint()).append(SmnConstants.URL_DELIMITER)
                .append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(getSmnConfiguration().getAuthenticationBean().getProjectId()).append(SmnConstants.URL_DELIMITER)
                .append(SmnConstants.SMN_NOTIFICATIONS).append(SmnConstants.URL_DELIMITER)
                .append(SmnConstants.SMN_SUB_PROTOCOL_SMS);
        LOGGER.info("Request url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() {
        checkPhoneNumber(getEndpoint());
        checkMessage(getMessage());
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put("endpoint", getEndpoint());
        requestParameterMap.put("message", getMessage());
        if (StringUtils.isNoneBlank(getSignId())) {
            requestParameterMap.put("sign_id", getSignId());
        }
        return requestParameterMap;
    }

    private void checkPhoneNumber(String endpoint) {
        if (endpoint == null) {
            LOGGER.error("PhoneNumber is null.");
            throw new NullPointerException("endpoint is null.");
        }

        if (!PATTERN_TELTPHONE.matcher(endpoint).matches()) {
            LOGGER.error("Wrong phone number format");
            throw new RuntimeException(
                    "The wrong phone number format, the correct number format is +8600000000000 or 00000000000");
        }
    }

    private void checkMessage(String message) {

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SmsPublishRequest [endpoint=").append(endpoint).append(", message=").append(message)
                .append(", signId=").append(signId).append("]");
        return builder.toString();
    }

}
