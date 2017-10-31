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
package com.smn.client;

import com.smn.common.HttpResponse;
import com.smn.model.request.publish.PublishMsgRequest;
import com.smn.model.request.sms.*;
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;
import com.smn.model.request.template.*;
import com.smn.model.request.topic.*;
import com.smn.service.*;

/**
 * {@link SmnClient} 的默认实现
 *
 * @author zhangyx
 * @version 0.9
 */
public class DefaultSmnClient implements SmnClient {

    /**
     * service factory
     */
    private ServiceFactory serviceFactory;

    /**
     * 给定serviceFacory创建实例
     *
     * @param serviceFactory
     */
    public DefaultSmnClient(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listTopics(ListTopicsRequest)
     */
    public HttpResponse listTopics(ListTopicsRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.listTopics(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#deleteTopic(DeleteTopicRequest)
     */
    public HttpResponse deleteTopic(DeleteTopicRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.deleteTopic(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#createTopic(CreateTopicRequest)
     */
    public HttpResponse createTopic(CreateTopicRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.createTopic(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#queryTopicDetail(QueryTopicDetailRequest)
     */
    public HttpResponse queryTopicDetail(QueryTopicDetailRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.queryTopicDetail(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#updateTopic(UpdateTopicRequest)
     */
    public HttpResponse updateTopic(UpdateTopicRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.updateTopic(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listTopicAttributes(ListTopicAttributesRequest)
     */
    public HttpResponse listTopicAttributes(ListTopicAttributesRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.listTopicAttributes(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#updateTopicAttribute(UpdateTopicAttributeRequest)
     */
    public HttpResponse updateTopicAttribute(UpdateTopicAttributeRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.updateTopicAttribute(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest)
     */
    public HttpResponse deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.deleteTopicAttributeByName(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#deleteTopicAttributes(DeleteTopicAttributesRequest)
     */
    public HttpResponse deleteTopicAttributes(DeleteTopicAttributesRequest smnRequest) {
        TopicService topicService = serviceFactory.getTopicService();
        return topicService.deleteTopicAttributes(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#subscribe(SubcriptionRequest)
     */
    public HttpResponse subscribe(SubcriptionRequest smnRequest) {
        SubscriptionService subscriptionService = serviceFactory.getSubscriptionService();
        return subscriptionService.subscribe(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#unsubscribe(UnSubcriptionRequest)
     */
    public HttpResponse unsubscribe(UnSubcriptionRequest smnRequest) {
        SubscriptionService subscriptionService = serviceFactory.getSubscriptionService();
        return subscriptionService.unsubscribe(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listSubscriptions(ListSubscriptionsRequest)
     */
    public HttpResponse listSubscriptions(ListSubscriptionsRequest smnRequest) {
        SubscriptionService subscriptionService = serviceFactory.getSubscriptionService();
        return subscriptionService.listSubscriptions(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listSubscriptionsByTopic(ListSubscriptionsByTopicRequest)
     */
    public HttpResponse listSubscriptionsByTopic(ListSubscriptionsByTopicRequest smnRequest) {
        SubscriptionService subscriptionService = serviceFactory.getSubscriptionService();
        return subscriptionService.listSubscriptionsByTopic(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#createMessageTemplate(CreateMessageTemplateRequest)
     */
    public HttpResponse createMessageTemplate(CreateMessageTemplateRequest smnRequest) {
        MessageTemplateService messageTemplateService = serviceFactory.getMessageTemplateService();
        return messageTemplateService.createMessageTemplate(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#updateMessageTemplate(UpdateMessageTemplateRequest)
     */
    public HttpResponse updateMessageTemplate(UpdateMessageTemplateRequest smnRequest) {
        MessageTemplateService messageTemplateService = serviceFactory.getMessageTemplateService();
        return messageTemplateService.updateMessageTemplate(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#deleteMessageTemplate(DeleteMessageTemplateRequest)
     */
    public HttpResponse deleteMessageTemplate(DeleteMessageTemplateRequest smnRequest) {
        MessageTemplateService messageTemplateService = serviceFactory.getMessageTemplateService();
        return messageTemplateService.deleteMessageTemplate(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listMessageTemplates(ListMessageTemplatesRequest)
     */
    public HttpResponse listMessageTemplates(ListMessageTemplatesRequest smnRequest) {
        MessageTemplateService messageTemplateService = serviceFactory.getMessageTemplateService();
        return messageTemplateService.listMessageTemplates(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#queryMsgTemplateDetail(QueryMessageTemplateDetailRequest)
     */
    public HttpResponse queryMsgTemplateDetail(QueryMessageTemplateDetailRequest smnRequest) {
        MessageTemplateService messageTemplateService = serviceFactory.getMessageTemplateService();
        return messageTemplateService.queryMsgTemplateDetail(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#publish(PublishMsgRequest)
     */
    public HttpResponse publish(PublishMsgRequest smnRequest) {
        PublishService publishService = serviceFactory.getPublishService();
        return publishService.publish(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#smsPublish(SmsPublishRequest)
     */
    public HttpResponse smsPublish(SmsPublishRequest smnRequest) {
        SmsService smsService = serviceFactory.getSmsService();
        return smsService.smsPublish(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listSmsMsgReport(ListSmsMsgReportRequest)
     */
    public HttpResponse listSmsMsgReport(ListSmsMsgReportRequest smnRequest) {
        SmsService smsService = serviceFactory.getSmsService();
        return smsService.listSmsMsgReport(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#getSmsMessage(GetSmsMessageRequest)
     */
    public HttpResponse getSmsMessage(GetSmsMessageRequest smnRequest) {
        SmsService smsService = serviceFactory.getSmsService();
        return smsService.getSmsMessage(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#listSmsCallbackEvent(ListSmsCallbackEventRequest)
     */
    public HttpResponse listSmsCallbackEvent(ListSmsCallbackEventRequest smnRequest) {
        SmsService smsService = serviceFactory.getSmsService();
        return smsService.listSmsCallbackEvent(smnRequest);
    }

    /**
     * (non-Javadoc)
     *
     * @see SmnClient#updateSmsCallbackEvent(UpdateSmsCallbackEventRequest)
     */
    public HttpResponse updateSmsCallbackEvent(UpdateSmsCallbackEventRequest smnRequest) {
        SmsService smsService = serviceFactory.getSmsService();
        return smsService.updateSmsCallbackEvent(smnRequest);
    }
}
