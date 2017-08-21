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
package com.huawei.smn.model.request.publish;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
public class PublishMsgRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(PublishMsgRequest.class);

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * message's title, will be the subject when sent to mail subscribers
     */
    private String subject;

    /**
     * label tag in message template,custom label by user
     */
    private Map<String, Object> tags;

    /**
     * message template name
     */
    private String messageTemplateName;

    /**
     * message structure string with Json
     */
    private String messageStructure;

    /**
     * message to send
     */
    private String message;

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
            LOGGER.error("Publish message request projectId is null.");
            throw new RuntimeException("Publish message request projectId is null.");
        }

        if (StringUtils.isBlank(topicUrn)) {
            LOGGER.error("TopicUrn is null.");
            throw new RuntimeException("TopicUrn is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(topicUrn).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_PUBLISH);

        LOGGER.info("Request url is {}.", sb.toString());
        return sb.toString();
    }

    /**
     * 三种消息发送方式
     * 
     * message message_structure message_template_name
     * 
     * 只需要设置其中一个，如果同时设置，生效的优先级为
     * 
     * message_structure >
     * 
     * message_template_name >
     * 
     * message url地址是一样的，在构造body参数时概据优先级确定哪一种发送方式
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {

        Map<String, Object> requestParameterMap = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(getSubject())) {
            requestParameterMap.put("subject", getSubject());
        }

        // message structure has highest priority
        if (StringUtils.isNoneBlank(getMessageStructure())) {
            requestParameterMap.put("message_structure", getMessageStructure());
            return requestParameterMap;
        }

        // message template has secondary priority
        if (StringUtils.isNoneBlank(getMessageTemplateName())) {
            if (Objects.isNull(getTags())) {
                LOGGER.error("Tags is null");
                throw new RuntimeException("Tags is null");
            }
            requestParameterMap.put("message_template_name", getMessageTemplateName());
            requestParameterMap.put("tags", getTags());
            LOGGER.info(requestParameterMap.toString());
            return requestParameterMap;
        }

        // common message ,least priority
        if (StringUtils.isBlank(getMessage())) {
            throw new RuntimeException("Message is null");
        }
        requestParameterMap.put("message", getMessage());
        LOGGER.info("message is {}." + message);
        return requestParameterMap;
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
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the tags
     */
    public Map<String, Object> getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
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
     * @return the messageStructure
     */
    public String getMessageStructure() {
        return messageStructure;
    }

    /**
     * @param messageStructure
     *            the messageStructure to set
     */
    public void setMessageStructure(String messageStructure) {
        this.messageStructure = messageStructure;
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
        builder.append("PublishMsgRequest [topicUrn=").append(topicUrn).append(", subject=").append(subject)
                .append(", tags=").append(tags).append(", messageTemplateName=").append(messageTemplateName)
                .append(", messageStructure=").append(messageStructure).append(", message=").append(message)
                .append(", smnEndpoint=").append(smnEndpoint).append(", projectId=").append(projectId)
                .append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }

}
