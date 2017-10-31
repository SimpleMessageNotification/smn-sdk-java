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
package com.smn.service;

import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import com.smn.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于构造获取service
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
     * 给定配置文件，构造factory实例
     *
     * @param smnConfiguration smn配置
     */
    public ServiceFactory(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }

    /**
     * get iamService
     * <p>
     * 如果已经实例化，直接返回
     *
     * @return iamService iam操作服务类
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
                    iamService = new IAMServiceImpl(smnConfiguration.getUserName(), smnConfiguration.getPassword(),
                            smnConfiguration.getDomainName(), smnConfiguration.getRegionId(), iamUrl);
                }
            }
        }

        return iamService;
    }

    /**
     * get smnService
     *
     * @return SmsService sms 操作类
     */
    public SmsService getSmsService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (smsService == null) {
            synchronized (ServiceFactory.class) {
                if (smsService == null) {
                    smsService = new SmsServiceImpl(iamService, smnConfiguration);
                }
            }
        }
        return smsService;
    }

    /**
     * get messageTemplateService
     *
     * @return MessageTemplateService MessageTemplate 操作类
     */
    public MessageTemplateService getMessageTemplateService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (messageTemplateService == null) {
            synchronized (ServiceFactory.class) {
                if (messageTemplateService == null) {
                    messageTemplateService = new MessageTemplateServiceImpl(iamService, smnConfiguration);
                }
            }
        }
        return messageTemplateService;
    }

    /**
     * get subscriptionService
     *
     * @return MessageTemplateService MessageTemplate 操作类
     */
    public SubscriptionService getSubscriptionService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (subscriptionService == null) {
            synchronized (ServiceFactory.class) {
                if (subscriptionService == null) {
                    subscriptionService = new SubscriptionServiceImpl(iamService, smnConfiguration);
                }
            }
        }
        return subscriptionService;
    }

    /**
     * get publishService
     *
     * @return MessageTemplateService MessageTemplate 操作类
     */
    public PublishService getPublishService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (publishService == null) {
            synchronized (ServiceFactory.class) {
                if (publishService == null) {
                    publishService = new PublishServiceImpl(iamService, smnConfiguration);
                }
            }
        }
        return publishService;
    }

    /**
     * get topicService
     *
     * @return MessageTemplateService MessageTemplate 操作类
     */
    public TopicService getTopicService() {
        IAMService iamService = getIAMService();

        if (iamService == null) {
            throw new RuntimeException("get iam service fail");
        }

        if (topicService == null) {
            synchronized (ServiceFactory.class) {
                if (topicService == null) {
                    topicService = new TopicServiceImpl(iamService, smnConfiguration);
                }
            }
        }
        return topicService;
    }
}
