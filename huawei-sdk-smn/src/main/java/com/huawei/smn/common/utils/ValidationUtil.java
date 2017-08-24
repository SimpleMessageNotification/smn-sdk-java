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
package com.huawei.smn.common.utils;

import java.util.regex.Pattern;

import com.huawei.smn.common.SmnConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author huangqiong
 * @date 2017年8月22日 上午11:35:28
 * @version 0.1
 */
public class ValidationUtil {

    /**
     * 判断主题名的正则表达式
     */
    final static Pattern PATTERN_TOPIC = Pattern.compile("^[a-zA-Z0-9]{1}[-_a-zA-Z0-9]{0,255}$");

    /**
     * 判断电话的正则表达式
     */
    final static Pattern PATTERN_TELEPHONE = Pattern.compile("^\\+?[0-9]+$");

    /**
     * 判断短信签名名称的正则表达式
     */
    final static Pattern PATTERN_SMS_SIGN_NAME = Pattern.compile("[a-zA-Z0-9\\u4e00-\\u9fa5]{2,8}");

    /**
     * 判断区位的正则表达式
     */
    final static Pattern PATTERN_LOCALE = Pattern.compile("^[a-z]{2}-[a-z]{2}$");

    /**
     * 判断Email的正则表达式
     */
    final  static Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9]+([._\\-]*[a-zA-Z0-9])*@([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+.){1,63}[a-zA-Z0-9]+$");

    /**
     * validate locale if is conformed with specification
     * 
     * @param locale
     * @return boolean <code>true</code> conform to rule will be true,or
     *         false.if empty defalut true
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
     * @param topicName
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
     * @param telephone
     *            phone number to be validated
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
     * @param smsSignName
     *            signName to be validated
     * @return boolean <code>true</code> conform to rule will be true,or false
     */
    public static final boolean validateSmsSignName(String smsSignName) {

        if (StringUtils.isEmpty(smsSignName)) {
            return false;
        }
        return PATTERN_SMS_SIGN_NAME.matcher(smsSignName).matches();
    }

    /**
     * validate attributValue
     * <p>
     * 
     * @param attributValue
     * @return boolean
     */
    public static boolean validateAttributValue(String attributValue) {

        return false;
    }

    /**
     * validate project_id
     * @param project_id
     * @return boolean
     */
    public static boolean validateProjectId(String project_id){
        if(StringUtils.isBlank(project_id)){
            return false;
        }
        return true;
    }

    /**
     * validate TopicUrn
     * @param topic_urn
     * @return boolean
     */
    public static boolean validateTopicUrn(String topic_urn){
        if(StringUtils.isBlank(topic_urn)){
            return false;
        }
            return true;
    }

    /**
     * validate EndPoint
     * @param endPoint
     * @param protocol
     * @return boolean
     */
    public static boolean validateEndPoint(String  endPoint,String protocol){
        if(StringUtils.isBlank(endPoint)){
            return false;
        }
        if(protocol.equals("http") && endPoint.startsWith("http://")){
            return true;
        }
        if(protocol.equals("email") && validateEmail(endPoint)){
            return true;
        }
        if(protocol.equals("sms") && validateTelephone(endPoint)){
            return true;
        }
        return false;
    }

    /**
     * validate Email
     * @param email
     * @return boolean
     */
    public static boolean validateEmail(String email){
        if(StringUtils.isEmpty(email)){
            return  false;
        }
        return  PATTERN_EMAIL.matcher(email).matches();
    }
    public static boolean validateProtocol(String protocol){
        if (StringUtils.isEmpty(protocol)) {
            return  false;
        }
        if(protocol.equals(SmnConstants.SMN_SUB_PROTOCOL_EMAIL) || protocol.equals(SmnConstants.SMN_SUB_PROTOCOL_SMS) || protocol.equals(SmnConstants.SMN_SUB_PROTOCOL_HTTPS) || protocol.equals(SmnConstants.SMN_SUB_PROTOCOL_HTTP)){
            return true;
        }
        return  false;
    }



}
