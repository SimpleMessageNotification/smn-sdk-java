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
package com.huawei.smn.service;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.request.template.CreateMessageTemplateRequest;
import com.huawei.smn.model.request.template.DeleteMessageTemplateRequest;
import com.huawei.smn.model.request.template.ListMessageTemplatesRequest;
import com.huawei.smn.model.request.template.QueryMessageTemplateDetailRequest;
import com.huawei.smn.model.request.template.UpdateMessageTemplateRequest;
import com.huawei.smn.service.impl.MessageTemplateServiceImpl;

import junit.framework.TestCase;

/**
 * @author huangqiong
 * @date 2017年8月14日 下午9:44:56
 * @version 0.1
 */
public class MessageTemplateServiceTest extends TestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceTest.class);

    SmnConfiguration smnConfiguration = null;

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        smnConfiguration = new SmnConfiguration();
        smnConfiguration.setFilepath("config/configuration.properties");
        smnConfiguration.reload();
    }

    @Test
    public void testCreateMessageTemplateRequest() {
        CreateMessageTemplateRequest createMessageTemplateRequest = new CreateMessageTemplateRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        messageTemplateService.setSmnConfiguration(smnConfiguration);
        createMessageTemplateRequest.setMessageTemplateName("dcragie");
        String content = "hello {name},hello {word},hello {gingo}";
        createMessageTemplateRequest.setContent(content);
        createMessageTemplateRequest.setProtocol("sms");// default,sms,http,https,email
        Map<String, Object> res = messageTemplateService.createMessageTemplate(createMessageTemplateRequest);
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("message_template_id"));
        Assert.assertNotNull(res.get("status"));
        LOGGER.info(res.toString());
    }

    @Test
    public void testDeleteMessageTemplateRequest() {
        String messageTemplateId = "6459b65502c1410a926fbc7d1f6aef0a";
        DeleteMessageTemplateRequest deleteMessageTemplateRequest = new DeleteMessageTemplateRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        messageTemplateService.setSmnConfiguration(smnConfiguration);
        deleteMessageTemplateRequest.setMessageTemplateId(messageTemplateId);
        Map<String, Object> res = messageTemplateService.deleteMessageTemplate(deleteMessageTemplateRequest);
        LOGGER.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    @Test
    public void testUpdateMessageTemplateRequest() {
        String messageTemplateId = "350a3937dbfa4f56834c97fd9e475e4e";
        UpdateMessageTemplateRequest updateMessageTemplateRequest = new UpdateMessageTemplateRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        messageTemplateService.setSmnConfiguration(smnConfiguration);
        updateMessageTemplateRequest.setMessageTemplateId(messageTemplateId);
        String content = "this year is {year},welcom {name} to join the {company} to create new life";
        updateMessageTemplateRequest.setContent(content);
        Map<String, Object> res = messageTemplateService.updateMessageTemplate(updateMessageTemplateRequest);
        LOGGER.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    @Test
    public void testListMessageTemplatesRequest() {
        ListMessageTemplatesRequest listMessageTemplatesRequest = new ListMessageTemplatesRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        messageTemplateService.setSmnConfiguration(smnConfiguration);
        listMessageTemplatesRequest.setMessageTemplateName("createMessageTemplate");
        listMessageTemplatesRequest.setLimit(100);
        Map<String, Object> res = messageTemplateService.listMessageTemplates(listMessageTemplatesRequest);
        LOGGER.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    public void testQueryMessageTemplateDetailRequest() {
        String messageTemplateId = "155f0e586490411db3258afe777a5add";
        QueryMessageTemplateDetailRequest queryMessageTemplateDetailRequest = new QueryMessageTemplateDetailRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        messageTemplateService.setSmnConfiguration(smnConfiguration);
        queryMessageTemplateDetailRequest.setMessageTemplateId(messageTemplateId);
        Map<String, Object> res = messageTemplateService.queryMsgTemplateDetail(queryMessageTemplateDetailRequest);
        LOGGER.info(res.toString());
        Assert.assertNotNull(res.get("message_template_id"));
        Assert.assertNotNull(res.get("status"));

    }

}
