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
/**
 * @author huangqiong
 * @date 下午5:04:43
 * @version 0.1
 * 
 */
package com.huawei.smn.common;

/**
 * String constants
 * 
 * @author huangqiong
 * @date 2017年8月3日 下午5:16:42
 * @version 0.1
 */
public interface SmnConstants {

    /**
     * default charset
     */
    String DEFAULT_CHARSET = "UTF-8";

    /**
     * default content type
     */
    String DEFAULT_CONTENT_TYPE = "application/json; charset=UTF-8";

    /**
     * header segment X-Auth-Token
     */
    String X_AUTH_TOKEN = "X-Auth-Token";

    /**
     * header segment X-Project-Id
     */
    String X_PROJECT_ID = "X-Project-Id";

    /**
     * tenant id
     */
    String TENANT_ID = "tenant_id";

    /**
     * content type tag
     */
    String CONTENT_TYPE_TAG = "Content-Type";

    /**
     * topic tag
     */
    String TOPIC_TAG = "Topic";

    /**
     * queue tag
     */
    String QUEUE_NAME_TAG = "QueueName";

    /**
     * topic name tag
     */
    String TOPIC_NAME_TAG = "TopicName";

    /**
     * subscription tag
     */
    String SUBSCRIPTION_TAG = "Subscription";

    /**
     * last modify time tag
     */
    String LASTMODIFYTIME_TAG = "LastModifyTime";

    /**
     * create time tag
     */
    String CREATE_TIME_TAG = "CreateTime";

    /**
     * region tag
     */
    String REGION_TAG = "region";

    /**
     * url delimiter
     */
    String URL_DELIMITER = "/";

    /**
     * https preffix
     */
    String HTTPS_PREFFIX = "https://";

    /**
     * smn region
     */
    String REGION = "smn.region";

    /**
     * smn iam host name
     */
    String IAM_ENDPOINT = "iam.endpoint";

    /**
     * smn endpoint
     */
    String SMN_ENDPOINT = "smn.endpoint";

    /**
     * iam uri
     */
    String IAM_URI = "/v3/auth/tokens";

    /**
     * smn topic uri
     */
    String SMN_TOPIC_URI = "/notifications/topics";

    /**
     * v2 version
     */
    String V2_VERSION = "v2";

    /**
     * user name
     */
    String USER_NAME = "iam.user.name";

    /**
     * password
     */
    String PASSWORD = "iam.user.password";

    /**
     * domain name
     */
    String DOMAIN_NAME = "iam.domain.name";

    /**
     * region id
     */
    String REGION_ID = "region.id";

    /**
     * topic urn
     */
    String TOPIC_URN = "topic_urn";

    /**
     * project id
     */
    String PROJECT_ID = "project_id";

    /**
     * protocol
     */
    String SMN_PROTOCOL = "protocol";

    /**
     * endpoint
     */
    String ENDPOINT = "endpoint";

    /**
     * remark
     */
    String SMN_SUBCRIBE_REMARK = "remark";

    /**
     * message
     */
    String MESSAGE = "message";

    /**
     * sign_id
     */
    String SIGN_ID = "sign_id";

    /**
     * subscriptions
     */
    String SMN_SUBSCRIPTIONS = "subscriptions";

    /**
     * notifications
     */
    String SMN_NOTIFICATIONS = "notifications";

    /**
     * publish
     */
    String SMN_PUBLISH = "publish";

    /**
     * message template
     */
    String SMN_MESSAGE_TEMPLATE = "message_template";

    /**
     * sms signature
     */
    String SMS_SIGNATURE = "sms_sign";

    /**
     * email protocol
     */
    String SMN_SUB_PROTOCOL_EMAIL = "email";

    /**
     * sms protocol
     */
    String SMN_SUB_PROTOCOL_SMS = "sms";

    /**
     * http protocol
     */
    String SMN_SUB_PROTOCOL_HTTP = "http";

    /**
     * https protocol
     */
    String SMN_SUB_PROTOCOL_HTTPS = "https";

    /**
     * application protocol
     */
    String SMN_SUB_PROTOCOL_APPLICATION = "application";

    /**
     * lambda protocol
     */
    String SMN_SUB_PROTOCOL_LAMBDA = "functionStage";

    /**
     * attribute
     */
    String SMN_TOPIC_ATTRIBTUES = "attributes";

    /**
     * attribute value
     */
    String SMN_TOPIC_ATTRIBUTE_VALUE = "value";

    /**
     * smn crypt key
     */
    String DEFAULT_SMN_CRYPT_KEY = "20170807";

    /**
     * ciper aes method
     */
    String AES = "AES";

    /**
     * max length of topic's display name
     */
    int MAX_TOPIC_DISPLAY_NAME = 192;
}
