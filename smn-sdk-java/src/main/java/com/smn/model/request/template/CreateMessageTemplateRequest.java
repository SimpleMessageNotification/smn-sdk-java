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
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the request to create message template
 *
 * @author huangqiong
 * @author yangyanping
 * @version 0.1
 * @version 0.2
 */
public class CreateMessageTemplateRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(CreateMessageTemplateRequest.class);

    /**
     * protocol
     */
    private String protocol;
    /**
     * template content ,support txt only currently
     */
    private String content;
    /**
     * template name
     */
    private String messageTemplateName;

    /**
     * check params
     */
    private void validation() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Create message template request projectId is null.");
            throw new NullPointerException("Create message template request projectId is null.");
        }
        if (!ValidationUtil.validateTemplateMessageContent(content)) {
            LOGGER.error("Create message template content is invalid.");
            throw new RuntimeException("Create message template content is invalid.");
        }

        if (!ValidationUtil.validateTemplateName(messageTemplateName)) {
            LOGGER.error("Create message template name is invalid.");
            throw new RuntimeException("Create message template name is invalid.");
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
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE);

        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put("content", content);
        requestParameterMap.put("message_template_name", messageTemplateName);
        requestParameterMap.put("protocol", protocol);

        return requestParameterMap;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the messageTemplateName
     */
    public String getMessageTemplateName() {
        return messageTemplateName;
    }

    /**
     * @param messageTemplateName the messageTemplateName to set
     */
    public void setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreateMessageTemplateRequest [protocol=").append(protocol)
                .append(", content=").append(content)
                .append(", messageTemplateName=").append(messageTemplateName)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}
