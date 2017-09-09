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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.common.utils.JsonUtil;
import com.smn.model.request.publish.PublishMsgRequest;
import com.smn.service.impl.PublishServiceImpl;

import junit.framework.TestCase;

/**
 * @author huangqiong
 * @date 2017年8月14日 下午9:44:25
 * @version 0.1
 */
public class PublishServiceTest extends TestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishServiceTest.class);

    SmnConfiguration smnConfiguration = null;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        smnConfiguration = new SmnConfiguration();
        smnConfiguration.setFilepath("config/configuration.properties");
        smnConfiguration.reload();
    }

    // 测试消息发布 pass
    public void testPublishMsg() throws UnsupportedEncodingException {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
        publishMsgRequest.setTopicUrn(topicUrn);
        publishMsgRequest.setMessage("测试sdk发布消息 ，7.18");
        publishMsgRequest.setSubject("ready to release");

        PublishService publishService = new PublishServiceImpl();
        publishService.setSmnConfiguration(smnConfiguration);
        HttpResponse res = null;
        res = publishService.publish(publishMsgRequest);

        LOGGER.info(res.toString());

        // check topic urn
        publishMsgRequest.setTopicUrn(null);
        try {
            publishService.publish(publishMsgRequest);
            fail("check topic urn expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        publishMsgRequest.setTopicUrn(topicUrn);

        // check message
        String message = publishMsgRequest.getMessage();
        publishMsgRequest.setMessage(null);
        try {
            publishService.publish(publishMsgRequest);
            fail("check message expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        publishMsgRequest.setMessage(message);

    }

    // 使用消息模板方式的消息发布 pass
    public void testPublishMsgWithTemplate() throws JsonProcessingException {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
        publishMsgRequest.setTopicUrn(topicUrn);
        publishMsgRequest.setMessageTemplateName("createMessageTemplate");
        Map<String, Object> tagsMap = new HashMap<String, Object>();
        tagsMap.put("year", "2017");
        tagsMap.put("name", "qiong");
        tagsMap.put("company", "huawei");
        publishMsgRequest.setTags(tagsMap);
        publishMsgRequest.setSubject("publish msg with template");
        PublishService publishService = new PublishServiceImpl();
        publishService.setSmnConfiguration(smnConfiguration);
        HttpResponse res = null;
        try {
            res = publishService.publish(publishMsgRequest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LOGGER.info(res.toString());
        Assert.assertNotNull(res.getBody().get("request_id"));
        Assert.assertNotNull(res.getBody().get("message_id"));

        // check tag
        Map<String, Object> tagTemp = publishMsgRequest.getTags();
        Map<String, Object> tag = tagTemp;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1025; i++) {
            sb.append(1);
        }
        tag.put("tests0015", sb.toString());
        publishMsgRequest.setTags(tag);
        try {
            try {
                publishService.publish(publishMsgRequest);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            fail("check messageTemplate expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        publishMsgRequest.setTags(tagTemp);
    }

    // 使用消息结构体方式的消息发布 pass
    public void testPublishMsgWithStruct() throws UnsupportedEncodingException {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
        publishMsgRequest.setTopicUrn(topicUrn);

        Map<String, Object> structMsgMap = new HashMap<String, Object>();
        structMsgMap.put("default", "hello 2017,welcome struct test");
        structMsgMap.put("sms", "hello 2017,welcome struct test");
        structMsgMap.put("http", "hello 2017,welcome struct test");
        structMsgMap.put("https", "hello 2017,welcome struct test");
        structMsgMap.put("email", "hello 2017,welcome struct test");
        publishMsgRequest.setMessageStructure(JsonUtil.getJsonStringByMap(structMsgMap));
        PublishService publishService = new PublishServiceImpl();
        publishService.setSmnConfiguration(smnConfiguration);
        HttpResponse res = null;

        res = publishService.publish(publishMsgRequest);

        LOGGER.info(res.toString());
        Assert.assertNotNull(res.getBody().get("request_id"));
        System.out.println(res.getBody().get("request_id") + "+++++" + res.getBody().get("status"));
        Assert.assertNotNull(res.getBody().get("message_id"));

    }

}
