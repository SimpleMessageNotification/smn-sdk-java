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
package com.smn.service;

import com.smn.common.ClientConfiguration;
import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import com.smn.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the factory to get service
 *
 * @author zhangyx
 * @version 0.9
 */
public class ServiceFactory {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFactory.class);

    /**
     * iam service
     */
    private IAMService iamService;

    /**
     * sms service
     */
    private SmsService smsService;

    /**
     * message template service
     */
    private MessageTemplateService messageTemplateService;

    /**
     * subscription service
     */
    private SubscriptionService subscriptionService;

    /**
     * publish service
     */
    private PublishService publishService;

    /**
     * topic service
     */
    private TopicService topicService;

    /**
     * smn configuration
     */
    private SmnConfiguration smnConfiguration;

    /**
     * client configuration
     */
    private ClientConfiguration clientConfiguration;

    /**
     * given smn configuration，get factory instance
     *
     * @param smnConfiguration smn configuration
     */
    public ServiceFactory(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
        this.clientConfiguration = new ClientConfiguration();
    }

    /**
     * given smn and http configuration，get factory instance
     *
     * @param smnConfiguration    smn configuration
     * @param clientConfiguration client configuration
     */
    public ServiceFactory(SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = smnConfiguration;
        this.clientConfiguration = clientConfiguration;
    }

    /**
     * get iamService
     * <p>
     * if not null, return directly
     *
     * @return iamService {@link IAMService} iam service
     */
    private IAMService getIAMService() {

        if (smnConfiguration == null) {
            throw new RuntimeException("smn configuration is null");
        }
        if (iamService == null) {
            synchronized (ServiceFactory.class) {
                if (iamService == null) {
                    String iamUrl = new StringBuilder().append(SmnConstants.HTTPS_PREFFIX)
                            .append(smnConfiguration.getIamEndpoint()).append(SmnConstants.URL_DELIMITER)
                            .append(SmnConstants.IAM_URI).toString();
                    LOGGER.info("Iam url is{}.", iamUrl);
                    iamService = new IAMServiceImpl(smnConfiguration, iamUrl, clientConfiguration);
                }
            }
        }

        return iamService;
    }

    /**
     * get smnService
     *
     * @return SmsService {@link SmsService} sms service
     */
    public SmsService getSmsService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (smsService == null) {
            synchronized (ServiceFactory.class) {
                if (smsService == null) {
                    smsService = new SmsServiceImpl(iamService, smnConfiguration, clientConfiguration);
                }
            }
        }
        return smsService;
    }

    /**
     * get messageTemplateService
     *
     * @return MessageTemplateService {@link MessageTemplateService} MessageTemplate service
     */
    public MessageTemplateService getMessageTemplateService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (messageTemplateService == null) {
            synchronized (ServiceFactory.class) {
                if (messageTemplateService == null) {
                    messageTemplateService = new MessageTemplateServiceImpl(iamService, smnConfiguration, clientConfiguration);
                }
            }
        }
        return messageTemplateService;
    }

    /**
     * get subscriptionService
     *
     * @return SubscriptionService {@link SubscriptionService} subscription service
     */
    public SubscriptionService getSubscriptionService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (subscriptionService == null) {
            synchronized (ServiceFactory.class) {
                if (subscriptionService == null) {
                    subscriptionService = new SubscriptionServiceImpl(iamService, smnConfiguration, clientConfiguration);
                }
            }
        }
        return subscriptionService;
    }

    /**
     * get publishService
     *
     * @return PublishService {@link PublishService} publish service
     */
    public PublishService getPublishService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (publishService == null) {
            synchronized (ServiceFactory.class) {
                if (publishService == null) {
                    publishService = new PublishServiceImpl(iamService, smnConfiguration, clientConfiguration);
                }
            }
        }
        return publishService;
    }

    /**
     * get topicService
     *
     * @return TopicService {@link TopicService} topic service
     */
    public TopicService getTopicService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (topicService == null) {
            synchronized (ServiceFactory.class) {
                if (topicService == null) {
                    topicService = new TopicServiceImpl(iamService, smnConfiguration, clientConfiguration);
                }
            }
        }
        return topicService;
    }
}
