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

import java.util.HashMap;
import java.util.Map;

/**
 * delete sms sign request message
 *
 * @author zhangyx
 * @version 0.9
 */
public class DeleteSmsSignRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteSmsSignRequest.class);

    /**
     * signature identitier
     */
    private String signId;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Delete sms sign request projectId is null.");
            throw new RuntimeException("Delete sms sign request projectId is null.");
        }

        if (StringUtils.isBlank(signId)) {
            LOGGER.error("SignId is null");
            throw new RuntimeException("SignId is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMS_SIGNATURE)
                .append(SmnConstants.URL_DELIMITER).append(signId);

        LOGGER.info("Request url is {}. ", sb.toString());
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

    @Override
    public String toString() {
        return "DeleteSmsSignRequest{" +
                "signId='" + signId + '\'' +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
