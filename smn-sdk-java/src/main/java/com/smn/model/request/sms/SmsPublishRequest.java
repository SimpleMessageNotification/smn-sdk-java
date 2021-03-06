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
import com.smn.common.utils.AesUtil;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * publish sms direct
 *
 * @author huangqiong
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
     * get request uri
     *
     * @return String uri
     */
    public String getRequestUri() {

        if (StringUtils.isBlank(projectId)) {

            LOGGER.error("Publish sms,projectId is null.");
            throw new NullPointerException("ProjectId is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS);

        LOGGER.info("Sms request url is {}.", sb.toString());
        return sb.toString();
    }

    /**
     * build and get request parameters
     *
     * @return Map
     * <code>endpoint</code>
     * <code>message</code>
     */
    public Map<String, Object> getRequestParameterMap() {
        validatePhoneNumber(getEndpoint());
        validateMessage(getMessage());
		validateSignId(getSignId());
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put(SmnConstants.ENDPOINT, endpoint);
        requestParameterMap.put(SmnConstants.MESSAGE, message);
        requestParameterMap.put(SmnConstants.SIGN_ID, signId);
        return requestParameterMap;
    }

    /**
     * validate phonenumber
     *
     * @param endpoint
     */
    private void validatePhoneNumber(String endpoint) {
        if (StringUtils.isBlank(endpoint)) {
            LOGGER.error("PhoneNumber is null.");
            throw new NullPointerException("PhoneNumber is null.");
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

        // attention to sms length, CMPP protocol up to 500 characters
        if (message.length() > 500) {
            LOGGER.warn("SMS content is too long, more than {} characters of the message content will be cut off.",
                    500);
        }
    }

    private void validateSignId(String signId) {

        if (StringUtils.isBlank(signId)) {
            LOGGER.error("SignId is null.");
            throw new NullPointerException("SignId is null.");
        }
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
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
     * @param signId the signId to set
     */
    public void setSignId(String signId) {
        this.signId = signId;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // endpoint encrypt
        String tmpEndpoint = AesUtil.encrypt(endpoint, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        builder.append("SmsPublishRequest [endpoint=").append(tmpEndpoint)
                .append(", message=").append(message)
                .append(", signId=").append(signId)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}
