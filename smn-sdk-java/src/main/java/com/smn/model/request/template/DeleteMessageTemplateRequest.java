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
package com.smn.model.request.template;

import com.smn.common.SmnConstants;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the request to delete message template
 *
 * @author huangqiong
 * @author yangyanping
 * @version 0.1
 * @version 0.2
 */
public class DeleteMessageTemplateRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteMessageTemplateRequest.class);

    /**
     * template's unique identifier
     */
    private String messageTemplateId;

    /**
     * check params
     */
    private void validation() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Delete message template request projectId is null.");
            throw new NullPointerException("Delete message template request projectId is null.");
        }

        if (StringUtils.isBlank(messageTemplateId)) {
            LOGGER.error("Message template id is null");
            throw new NullPointerException("Message template id is null");
        }
    }

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() throws RuntimeException {
        validation();
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(getProjectId()).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE)
                .append(SmnConstants.URL_DELIMITER).append(messageTemplateId);
        LOGGER.info("Delete message template request url is: " + sb.toString());
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
     * @return the messageTemplateId
     */
    public String getMessageTemplateId() {
        return messageTemplateId;
    }

    /**
     * @param messageTemplateId the messageTemplateId to set
     */
    public void setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeleteMessageTemplateRequest [messageTemplateId=").append(messageTemplateId)
                .append(", smnEndpoint=").append(smnEndpoint).append(", projectId=").append(projectId)
                .append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }

}
