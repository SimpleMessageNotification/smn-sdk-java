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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the request to update sms callback event
 *
 * @author zhangyx
 * @version 0.7
 */
public class UpdateSmsCallbackEventRequest extends AbstractSmnRequest {

    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(UpdateSmsCallbackEventRequest.class);

    /**
     * query result list
     */
    private List<SmsCallback> callbacks;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Update sms event request projectId is null.");
            throw new RuntimeException("Update sms event request projectId is null.");
        }

        if (!validate()) {
            LOGGER.error("Update sms event request callbacks is invalid.");
            throw new RuntimeException("Update sms event request callbacks is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.CALLBACK_REQUEST);

        LOGGER.info("Request url is {}. ", sb.toString());
        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        List<Map<String, String>> callBackList = new ArrayList<Map<String, String>>();
        for (SmsCallback smsCallback : callbacks) {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put(SmnConstants.TOPIC_URN, smsCallback.getTopicUrn());
            tempMap.put(SmnConstants.EVENT_TYPE, smsCallback.getEventType());
            callBackList.add(tempMap);
        }
        requestParameterMap.put(SmnConstants.CALLBACK_FIELD, callBackList);
        return requestParameterMap;
    }

    /**
     * verify the callback param is valid
     *
     * @return parameter valid return true, else return false
     */
    private boolean validate() {
        if (null == callbacks || callbacks.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * @return the callback list
     */
    public List<SmsCallback> getCallbacks() {
        return callbacks;
    }

    /**
     * @param callbacks the callback list to set
     */
    public void setCallbacks(List<SmsCallback> callbacks) {
        this.callbacks = callbacks;
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "UpdateSmsCallbackEventRequest{" +
                "callbacks=" + callbacks +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
