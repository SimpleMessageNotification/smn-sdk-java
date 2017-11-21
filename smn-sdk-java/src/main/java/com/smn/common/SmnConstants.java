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
package com.smn.common;

/**
 * String constants
 *
 * @author huangqiong
 * @version 0.1
 */
public final class SmnConstants {

    private SmnConstants() {

    }

    /**
     * default charset
     */
    public final static String DEFAULT_CHARSET = "UTF-8";

    /**
     * default content type
     */
    public final static String DEFAULT_CONTENT_TYPE = "application/json; charset=UTF-8";

    /**
     * header segment X-Auth-Token
     */
    public final static String X_AUTH_TOKEN = "X-Auth-Token";

    /**
     * header segment X-Project-Id
     */
    public final static String X_PROJECT_ID = "X-Project-Id";

    /**
     * tenant id
     */
    public final static String TENANT_ID = "tenant_id";

    /**
     * content type tag
     */
    public final static String CONTENT_TYPE_TAG = "Content-Type";

    /**
     * topic tag
     */
    public final static String TOPIC_TAG = "Topic";

    /**
     * queue tag
     */
    public final static String QUEUE_NAME_TAG = "QueueName";

    /**
     * topic name tag
     */
    public final static String TOPIC_NAME_TAG = "TopicName";

    /**
     * subscription tag
     */
    public final static String SUBSCRIPTION_TAG = "Subscription";

    /**
     * last modify time tag
     */
    public final static String LASTMODIFYTIME_TAG = "LastModifyTime";

    /**
     * create time tag
     */
    public final static String CREATE_TIME_TAG = "CreateTime";

    /**
     * region tag
     */
    public final static String REGION_TAG = "region";

    /**
     * url delimiter
     */
    public final static String URL_DELIMITER = "/";

    /**
     * https preffix
     */
    public final static String HTTPS_PREFFIX = "https://";

    /**
     * smn region
     */
    public final static String REGION = "smn.region";

    /**
     * smn iam host name
     */
    public final static String IAM_ENDPOINT = "iam.endpoint";

    /**
     * smn endpoint
     */
    public final static String SMN_ENDPOINT = "smn.endpoint";

    /**
     * iam uri
     */
    public final static String IAM_URI = "/v3/auth/tokens";

    /**
     * smn topic uri
     */
    public final static String SMN_TOPIC_URI = "/notifications/topics";

    /**
     * v2 version
     */
    public final static String V2_VERSION = "v2";

    /**
     * user name
     */
    public final static String USER_NAME = "iam.user.name";

    /**
     * password
     */
    public final static String PASSWORD = "iam.user.password";

    /**
     * domain name
     */
    public final static String DOMAIN_NAME = "iam.domain.name";

    /**
     * region id
     */
    public final static String REGION_ID = "region.id";

    /**
     * topic urn
     */
    public final static String TOPIC_URN = "topic_urn";

    /**
     * project id
     */
    public final static String PROJECT_ID = "project_id";

    /**
     * protocol
     */
    public final static String SMN_PROTOCOL = "protocol";

    /**
     * endpoint
     */
    public final static String ENDPOINT = "endpoint";

    /**
     * remark
     */
    public final static String SMN_SUBCRIBE_REMARK = "remark";

    /**
     * message
     */
    public final static String MESSAGE = "message";

    /**
     * sign_id
     */
    public final static String SIGN_ID = "sign_id";

    /**
     * subscriptions
     */
    public final static String SMN_SUBSCRIPTIONS = "subscriptions";

    /**
     * notifications
     */
    public final static String SMN_NOTIFICATIONS = "notifications";

    /**
     * publish
     */
    public final static String SMN_PUBLISH = "publish";

    /**
     * message template
     */
    public final static String SMN_MESSAGE_TEMPLATE = "message_template";

    /**
     * message template name
     */
    public final static String SMN_MESSAGE_TEMPLATE_NAME = "message_template_name";

    /**
     * sms signature
     */
    public final static String SMS_SIGNATURE = "sms_sign";

    /**
     * email protocol
     */
    public final static String SMN_SUB_PROTOCOL_EMAIL = "email";

    /**
     * sms protocol
     */
    public final static String SMN_SUB_PROTOCOL_SMS = "sms";

    /**
     * http protocol
     */
    public final static String SMN_SUB_PROTOCOL_HTTP = "http";

    /**
     * https protocol
     */
    public final static String SMN_SUB_PROTOCOL_HTTPS = "https";

    /**
     * application protocol
     */
    public final static String SMN_SUB_PROTOCOL_APPLICATION = "application";

    /**
     * lambda protocol
     */
    public final static String SMN_SUB_PROTOCOL_LAMBDA = "functionStage";

    /**
     * attribute
     */
    public final static String SMN_TOPIC_ATTRIBTUES = "attributes";

    /**
     * attribute value
     */
    public final static String SMN_TOPIC_ATTRIBUTE_VALUE = "value";


    /**
     * smn statistic
     */
    public final static String STATISTIC = "statistic";

    /**
     * smn report
     */
    public final static String REPORT = "report";

    /**
     * callback request
     */
    public final static String CALLBACK_REQUEST = "callback";

    /**
     * callback field
     */
    public final static String CALLBACK_FIELD = "callback";

    /**
     * event_type field
     */
    public final static String EVENT_TYPE = "event_type";

    /**
     * smn offset field
     */
    public final static String OFFSET = "offset";

    /**
     * smn limit field
     */
    public final static String LIMIT = "limit";

    /**
     * smn crypt key
     */
    public final static String DEFAULT_SMN_CRYPT_KEY = "20170807";

    /**
     * ciper aes method
     */
    public final static String AES = "AES";

    /**
     * max length of topic's display name
     */
    public final static int MAX_TOPIC_DISPLAY_NAME = 192;

    /**
     * default message
     */
    public final static String DEFAULT_MESSAGE = "default";

    /**
     * message template tags
     */
    public final static String TAGS = "tags";

    /**
     * get iam projects url
     */
    public final static String IAM_PROJECT_URL = "/v3/projects";

    /**
     * name filed name
     */
    public final static String NAME = "name";
}
