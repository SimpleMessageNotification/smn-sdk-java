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
package com.smn.common.utils;

import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * @author huangqiong
 * @author yangyanping
 * @version 0.2
 */
public class ValidationUtil {

    /**
     * validate topic regex
     */
    final static Pattern PATTERN_TOPIC = Pattern.compile("^[a-zA-Z0-9]{1}[-_a-zA-Z0-9]{0,255}$");

    /**
     * validate telephone regex
     */
    final static Pattern PATTERN_TELEPHONE = Pattern.compile("^\\+?[0-9]+$");

    /**
     * validate sms sigature regex
     */
    final static Pattern PATTERN_SMS_SIGN_NAME = Pattern.compile("[a-zA-Z0-9\\u4e00-\\u9fa5]{2,8}");

    /**
     * validate local regex
     */
    final static Pattern PATTERN_LOCALE = Pattern.compile("^[a-z]{2}-[a-z]{2}$");

    /**
     * validate Email regex
     */
    final static Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9]+([._\\-]*[a-zA-Z0-9])*@([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+.){1,63}[a-zA-Z0-9]+$");

    /**
     * validate templateName
     */
    final static Pattern PATTERN_TMPLATE_NAME = Pattern.compile("^[a-zA-Z0-9]{1}([-_a-zA-Z0-9]){0,64}");

    /*
     * validate subjet regex 
     */
    final static Pattern PATTERN_SUBJECT = Pattern.compile("^[^\\r\\n\\t\\f]+$");

    /**
     * validate locale if is conformed with specification
     *
     * @param locale the locale string to validate
     * @return boolean <code>true</code> conform to rule will be true,or
     * false.if empty defalut true
     */
    public static final boolean validateLocale(String locale) {
        if (StringUtils.isEmpty(locale)) {
            return true;
        }
        return PATTERN_LOCALE.matcher(locale).matches();
    }

    /**
     * validate topic name if is conformed with specification
     *
     * @param topicName the topicName to validate
     * @return boolean <code>true</code> conform to rule will be true,or false
     */
    public static final boolean validateTopicName(String topicName) {

        if (StringUtils.isEmpty(topicName)) {
            return false;
        }

        return PATTERN_TOPIC.matcher(topicName).matches();
    }

    /**
     * validate telephone number if is conformed with specification
     * <p>
     *
     * @param telephone phone number to be validated
     * @return boolean <code>true</code> conform to rule will be true,or false
     */
    public static final boolean validateTelephone(String telephone) {

        if (StringUtils.isEmpty(telephone)) {
            return false;
        }

        return PATTERN_TELEPHONE.matcher(telephone).matches();
    }

    /**
     * validate signName if conform to rule
     * <p>
     * parameters smsSignName must be upper or lower ASCII characters,digits or
     * chinese.generally 3-8
     *
     * @param smsSignName signName to be validated
     * @return boolean <code>true</code> conform to rule will be true,or false
     */
    public static final boolean validateSmsSignName(String smsSignName) {

        if (StringUtils.isEmpty(smsSignName)) {
            return false;
        }
        return PATTERN_SMS_SIGN_NAME.matcher(smsSignName).matches();
    }

    /**
     * validate project_id
     *
     * @param projectId the project Id to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateProjectId(String projectId) {
        if (StringUtils.isBlank(projectId)) {
            return false;
        }
        return true;
    }

    /**
     * validate TopicUrn
     *
     * @param topicUrn the topic_urn to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateTopicUrn(String topicUrn) {
        if (StringUtils.isBlank(topicUrn)) {
            return false;
        }
        return true;
    }

    /**
     * validate EndPoint
     *
     * @param endPoint the endPoint to validate
     * @param protocol the protocol tto validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateEndPoint(String endPoint, String protocol) {
        if (StringUtils.isBlank(endPoint)) {
            return false;
        }
        if ("http".equals(protocol) && endPoint.startsWith("http://")) {
            return true;
        }
        if ("https".equals(protocol) && endPoint.startsWith("https://")) {
            return true;
        }
        if ("email".equals(protocol) && validateEmail(endPoint)) {
            return true;
        }
        if ("sms".equals(protocol) && !StringUtils.isBlank(endPoint)) {
            return true;
        }
        return false;
    }

    /**
     * validate Email
     *
     * @param email the email to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        return PATTERN_EMAIL.matcher(email).matches();
    }

    /**
     * validate protocol
     *
     * @param protocol the protocol to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateProtocol(String protocol) {
        if (StringUtils.isEmpty(protocol)) {
            return false;
        }
        if (SmnConstants.SMN_SUB_PROTOCOL_EMAIL.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_SMS.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_HTTPS.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_HTTP.equals(protocol)) {
            return true;
        }
        return false;
    }


    /**
     * Determine whether the topic meets the naming conventions, and the <code>true</code> indicates compliance with the specification, otherwise it does not conform to specifications
     * <p> need to meet the beginning must be self, numbers, punctuation ASCALL text service, cannot contain newline characters and control </p>
     *
     * @param subject the subject to validate
     * @return boolean  <code>true</code> conform to rule will be true,or false
     */
    public static boolean validateSubject(String subject) {
        if (StringUtils.isEmpty(subject)) {
            return true;
        }
        return PATTERN_SUBJECT.matcher(subject).matches();
    }

    /**
     * validate displayname
     *
     * @param displayName the displayName to validate
     * @return boolean <code>true</code> displayName is valid
     */
    public static boolean validateDisplayName(String displayName) {
        try {
            byte[] b = displayName.getBytes(SmnConstants.DEFAULT_CHARSET);
            if (b.length > SmnConstants.MAX_TOPIC_DISPLAY_NAME) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("validate display name error");
        }
        return true;
    }


    /**
     * validate template templateMessageContent
     *
     * @param content the content to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateTemplateMessageContent(String content) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        try {
            byte[] b = content.getBytes(SmnConstants.DEFAULT_CHARSET);
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            if (b.length > smnConfiguration.getMaxTemplateMessageContextLength()) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("validate template message content error");
        }

        return true;
    }

    /**
     * validate template name
     *
     * @param templateName the template name to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateTemplateName(String templateName) {
        if (StringUtils.isBlank(templateName)) {
            return false;
        }
        return PATTERN_TMPLATE_NAME.matcher(templateName).matches();
    }

    /**
     * validate offset
     *
     * @param offset the offset to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateOffset(int offset) {
        return offset >= 0 ? true : false;
    }

    /**
     * validate limit
     *
     * @param limit the limit to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateLimit(int limit) {
        return (limit > 0 && limit <= 100) ? true : false;
    }
}
