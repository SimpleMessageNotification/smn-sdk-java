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
package com.smn.model.request.publish;

import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import com.smn.common.utils.JsonUtil;
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * the request to publish message
 *
 * @author huangqiong
 * @author yangyanping
 * @version 0.2
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
     * check params
     */
    private void validate() throws UnsupportedEncodingException, RuntimeException {
        //check topic urn

        if (!ValidationUtil.validateTopicUrn(topicUrn)) {
            throw new RuntimeException("topic urn is illegal");
        }
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Publish message request projectId is null.");
            throw new RuntimeException("Publish message request projectId is null.");
        }

        //check subject
        if (!checkSubject(subject)) {
            throw new RuntimeException("subject is illegal");
        }

        //check message
        if (messageStructure != null) {
            if (!checkMessageStruct(messageStructure)) {
                throw new RuntimeException("messageStructure is illegal");
            }
        } else if (messageTemplateName != null) {
            if (!checkTags()) {
                throw new RuntimeException();
            }
        } else {
            if (!checkMessage(message)) {
                throw new RuntimeException("message is illegal");
            }
        }

    }

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() throws RuntimeException {

        try {
            validate();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(topicUrn).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_PUBLISH);

        LOGGER.info("Request url is {}.", sb.toString());
        return sb.toString();
    }

    /**
     * Three ways of sending messages
     * <p>
     * message message_structure message_template_name
     * <p>
     * You only need to set one of them, and if you set it at the same time, the priority is
     * <p>
     * message_structure
     * <p>
     * message_template_name
     * <p>
     * The message URL address is the same, which determines which route of transmission will be determined based on the priority when constructing the body parameter
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {

        Map<String, Object> requestParameterMap = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(getSubject())) {
            requestParameterMap.put("subject", getSubject());
        }

        // message structure has highest priority
        if (StringUtils.isNoneBlank(messageStructure)) {
            requestParameterMap.put("message_structure", getMessageStructure());
            return requestParameterMap;
        }

        // message template has secondary priority
        if (StringUtils.isNoneBlank(getMessageTemplateName())) {
            if (null == getTags()) {
                LOGGER.error("Tags is null");
                throw new RuntimeException("Tags is null");
            }
            requestParameterMap.put("message_template_name", getMessageTemplateName());
            requestParameterMap.put("tags", getTags());
            LOGGER.info(requestParameterMap.toString());
            if (!checkTags()) {
                throw new RuntimeException();
            }
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
     * @param topicUrn the topicUrn to set
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
     * @param subject the subject to set
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
     * @param tags the tags to set
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
     * @param messageTemplateName the messageTemplateName to set
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
     * @param messageStructure the messageStructure to set
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
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PublishMsgRequest [topicUrn=").append(topicUrn)
                .append(", subject=").append(subject)
                .append(", tags=").append(tags)
                .append(", messageTemplateName=").append(messageTemplateName)
                .append(", messageStructure=").append(messageStructure)
                .append(", message=").append(message)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }

    /**
     * Check that the subject conforms to specifications, and <code>true</> indicates compliance with specifications, otherwise it does not conform to specifications
     *
     * @param subject
     * @return boolean  <code>true</> indicates compliance with specifications, otherwise it does not conform to specifications
     */
    private boolean checkSubject(String subject) {
        if (subject == null) {
            LOGGER.debug("subject is null");
            return true;
        }
        if (!ValidationUtil.validateSubject(subject)) {
            LOGGER.error("Parameter: Subject is invalid. ");
            return false;
        }
        try {
            byte[] b = subject.getBytes(SmnConstants.DEFAULT_CHARSET);
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            if (b.length > smnConfiguration.getMaxSubjectLength()) {
                LOGGER.error("Parameter: Subject is invalid. . The Length of Subject is {}.", b.length);
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Check that the message conforms to specifications, and <code>true</> indicates compliance with specifications, otherwise it does not conform to specifications
     *
     * @param message
     * @return boolean  <code>true</> indicates compliance with specifications, otherwise it does not conform to specifications
     */
    private boolean checkMessage(String message) {
        if (message == null) {
            return false;
        }
        try {
            byte[] b = message.getBytes(SmnConstants.DEFAULT_CHARSET);
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            if (b.length > smnConfiguration.getMaxMessageLength()) {
                LOGGER.error("Parameter: message is invalid. . The Length of message is {}.", b.length);
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Check that messageStruct is legal
     *
     * @param messageStruct
     * @return boolean  <code>true</> indicates compliance with specifications, otherwise it does not conform to specifications
     */
    @SuppressWarnings("unchecked")
    private boolean checkMessageStruct(String messageStruct) {
        if (messageStruct == null) {
            LOGGER.error("Parameter:MessageStruct is invalid");
            return false;
        }
        try {
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            byte[] b = messageStruct.getBytes(SmnConstants.DEFAULT_CHARSET);
            if (b.length > smnConfiguration.getMaxMessageLength()) {
                LOGGER.error("Parameter:MessageStruct is invalid,it is too long");
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Object messageObject = JsonUtil.parseJsonMessage(messageStruct);
        if (!(messageObject instanceof Map<?, ?>)) {
            LOGGER.error("Parameter:MessageStruct is invalid, it is null");
            return false;
        }

        Map<String, Object> messageMap = (Map<String, Object>) messageObject;
        if (messageMap.size() == 0) {
            LOGGER.error("Parameter:MessageStruct is invalid. Failed to parse MessageStructure.");
            return false;
        }
        if (!(messageMap.get(SmnConstants.DEFAULT_MESSAGE) instanceof String)) {
            LOGGER.error("Parameter:MessageStruct is invalid. Default message isn't String.");
            return false;
        }
        return true;
    }

    /**
     * Check that tags is legal
     *
     * @return boolean  <code>true</> indicates compliance with specifications, otherwise it does not conform to specifications
     * @throws RuntimeException
     */
    @SuppressWarnings("unchecked")
    private boolean checkTags() throws RuntimeException {
        if (tags != null) {
            if (!(tags instanceof Map<?, ?>)) {
                LOGGER.error("Tag is error.");
                return false;
            }
            Map<?, ?> tagsmap = (Map<?, ?>) tags;
            Iterator<Object> tagValues = (Iterator<Object>) tagsmap.values().iterator();
            Object obj = null;
            byte[] b = null;
            while (tagValues.hasNext()) {
                obj = tagValues.next();
                try {
                    b = obj.toString().getBytes(SmnConstants.DEFAULT_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                SmnConfiguration smnConfiguration = new SmnConfiguration();
                if (b.length > smnConfiguration.getMaxTagLength()) {
                    LOGGER.error("Tag is erro . The tag length is {}.", b.length);
                    return false;
                }
            }
        } else {
            LOGGER.info("EmptyTag.");
            return false;
        }
        return true;
    }

}
