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
package com.huawei.smn.model.request.template;

import java.util.HashMap;
import java.util.Map;

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
    @Override
    public String getRequestUri() throws RuntimeException {

        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Create message template request projectId is null.");
            throw new NullPointerException("Create message template request projectId is null.");
        }

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

        if (StringUtils.isBlank(content)) {
            LOGGER.error("Template content is null");
            throw new NullPointerException("Template content is null");
        }

        if (StringUtils.isBlank(protocol) || !isValidProtocol(protocol)) {
            LOGGER.error("Protocol is null or invalid");
            throw new RuntimeException("Protocol is null or invalid");
        }

        if (StringUtils.isBlank(getMessageTemplateName())) {
            LOGGER.error("Message template name is null");
            throw new NullPointerException("Message template name is null");
        }

        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put("content", getContent());
        requestParameterMap.put("message_template_name", getMessageTemplateName());
        requestParameterMap.put("protocol", getProtocol());

        return requestParameterMap;
    }

    /**
     * Verify the protocol if valid
     * 
     * @param protocol
     * @return
     */
    private boolean isValidProtocol(String protocol) {

        String protocols = protocol.trim();
        if (protocols.equals("default") || protocols.equals("sms") || protocols.equals("http")
                || protocols.equals("https") || protocols.equals("email")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol
     *            the protocol to set
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
     * @param content
     *            the content to set
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
     * @param messageTemplateName
     *            the messageTemplateName to set
     */
    public void setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
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
        builder.append("CreateMessageTemplateRequest [protocol=").append(protocol).append(", content=").append(content)
                .append(", messageTemplateName=").append(messageTemplateName).append(", smnEndpoint=")
                .append(smnEndpoint).append(", projectId=").append(projectId).append(", xAuthToken=").append(xAuthToken)
                .append("]");
        return builder.toString();
    }

}
