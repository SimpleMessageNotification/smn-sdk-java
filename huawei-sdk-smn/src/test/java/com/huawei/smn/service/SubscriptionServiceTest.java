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
import com.huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.huawei.smn.model.request.subscription.ListSubscriptionsRequest;
import com.huawei.smn.model.request.subscription.SubcriptionRequest;
import com.huawei.smn.model.request.subscription.UnSubcriptionRequest;
import com.huawei.smn.service.impl.SubscriptionServiceImpl;

import junit.framework.TestCase;

/**
 * @author huangqiong
 * @date 2017年8月14日 下午8:14:44
 * @version 0.1
 */
public class SubscriptionServiceTest extends TestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceTest.class);

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

    // 测试订阅功能 pass
    @Test
    public void testSubcription() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        SubcriptionRequest subcriptionRequest = new SubcriptionRequest();
        subcriptionRequest.setTopicUrn(topicUrn);
        subcriptionRequest.setProtocol("sms");
        subcriptionRequest.setEndpoint("18588432306");
        subcriptionRequest.setRemark("api订阅接口测试成功");
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        Map<String, Object> res = subscriptionService.subscribe(subcriptionRequest);
        Assert.assertEquals(201, res.get("status"));
        LOGGER.info(res.toString());
        System.out.println(res);

    }

    // 查询订阅者列表 pass
    public void testListSubscriptions() {
        ListSubscriptionsRequest listSubscriptionsRequest = new ListSubscriptionsRequest();
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        Map<String, Object> res = subscriptionService.listSubscriptions(listSubscriptionsRequest);
        Assert.assertEquals(201, res.get("status"));
        LOGGER.info(res.toString());
    }

    // 查询指定主题的订阅者列表 pass
    public void testListSubscriptionsByTopic() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();
        listSubscriptionsByTopicRequest.setTopicUrn(topicUrn);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        Map<String, Object> res = subscriptionService.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
        Assert.assertEquals(201, res.get("status"));
        System.out.println(res);
    }

    // 取消订阅 pass
    public void testUnSubcription() {
        String subscriptionUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:162a44968d894e6fa5f3385e6dee5e0b";
        UnSubcriptionRequest deleteSubcription = new UnSubcriptionRequest();
        deleteSubcription.setSubscriptionUrn(subscriptionUrn);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        Map<String, Object> res = subscriptionService.unsubscribe(deleteSubcription);
        Assert.assertEquals(404, res.get("status"));
        LOGGER.info(res.toString());
    }

}
