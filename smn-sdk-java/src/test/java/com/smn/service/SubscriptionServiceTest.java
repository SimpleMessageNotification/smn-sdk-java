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

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;
import com.smn.service.impl.SubscriptionServiceImpl;

import junit.framework.TestCase;

/**
 * @author yangyanping
 * @version 0.1
 * @date 2017年8月23日
 */
public class SubscriptionServiceTest extends TestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceTest.class);
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    @Test(expected = RuntimeException.class)
    public void testSubcription() {
        //功能测试ok
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        SubcriptionRequest subcriptionRequest = new SubcriptionRequest();
        String projectId = subcriptionRequest.getProjectId();
        subcriptionRequest.setTopicUrn(topicUrn);
        subcriptionRequest.setProtocol("sms");
        String endPoint = "18246161627";
        subcriptionRequest.setEndpoint(endPoint);
        subcriptionRequest.setRemark("api订阅接口测试成功");
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);

        HttpResponse res = subscriptionService.subscribe(subcriptionRequest);
        int resCode = res.getHttpCode();
        boolean flag = resCode >= 200 && resCode < 300;
        Assert.assertTrue(flag);
        LOGGER.info(res.toString());
       

        //check topic_urn
        try {
            subcriptionRequest.setTopicUrn(null);
            subscriptionService.subscribe(subcriptionRequest);
            fail("testSubcriptionSubscrible null topicUrn");
        } catch (RuntimeException e) {

        }
        subcriptionRequest.setTopicUrn(topicUrn);

        //check endpoint
        try {
            subcriptionRequest.setEndpoint("@@##");
            subscriptionService.subscribe(subcriptionRequest);
            fail("check endPoint Expected an RuntimeException");
        } catch (RuntimeException e) {

        }

        //check email
        try {
            subcriptionRequest.setProtocol("email");
            subcriptionRequest.setEndpoint("yangyanping3huawei.com");
            subscriptionService.subscribe(subcriptionRequest);
            fail("check email  Expected an RuntimeException");
        } catch (RuntimeException e) {

        }

         subcriptionRequest.setProtocol("email");
         subcriptionRequest.setEndpoint("liuqiangqiang@huawei.com");
        HttpResponse resEmail = subscriptionService.subscribe(subcriptionRequest);

        int resMailCode = (Integer) resEmail.getHttpCode();
         boolean emailFlag = resMailCode >= 200 && resMailCode < 300;
         Assert.assertTrue(emailFlag);
         LOGGER.info(res.toString());
        


        //check sms
        try {
            subcriptionRequest.setProtocol("sms");
            subcriptionRequest.setEndpoint("11sdf33@");
            subscriptionService.subscribe(subcriptionRequest);
            fail("sms email  Expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        //check protocol
        try {
            subcriptionRequest.setProtocol("emaill");
            subscriptionService.subscribe(subcriptionRequest);
            fail("check protocol Expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        subcriptionRequest.setProtocol("sms");

        //check remark
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 150; i++) {
                sb.append(1);
            }
            subcriptionRequest.setRemark(sb.toString());
            subscriptionService.subscribe(subcriptionRequest);
            fail("check remark Expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        subcriptionRequest.setRemark("1");

    }

    // 查询订阅者列表 pass
    public void testListSubscriptions() {
        
        ListSubscriptionsRequest listSubscriptionsRequest = new ListSubscriptionsRequest();
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        HttpResponse res = subscriptionService.listSubscriptions(listSubscriptionsRequest);
        int resCode = (Integer) res.getHttpCode();
        boolean flag = resCode >= 200 && resCode < 300;
        Assert.assertTrue(flag);
        LOGGER.info(res.toString());


        //check offset
        listSubscriptionsRequest.setOffset(-1);
        Boolean offsetFlag = listSubscriptionsRequest.getOffset() >= 0;
        Assert.assertTrue(offsetFlag);

        //check limit
        listSubscriptionsRequest.setLimit(-1);
        boolean limitFlag = listSubscriptionsRequest.getLimit() > 0 && listSubscriptionsRequest.getLimit() <= 100;
        Assert.assertTrue(limitFlag);
    }

    // 查询指定主题的订阅者列表 pass
    public void testListSubscriptionsByTopic() {
        
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();
        listSubscriptionsByTopicRequest.setTopicUrn(topicUrn);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        HttpResponse res = subscriptionService.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
        int resCode = (Integer) res.getHttpCode();
        boolean flag = resCode >= 200 && resCode < 300;
        Assert.assertTrue(flag);
        LOGGER.info(res.toString());

        //check token
        try {
            listSubscriptionsByTopicRequest.setTopicUrn(null);
            subscriptionService.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
            fail("test ListSubscriiptioonByTopic expected an RuntimeException");
        } catch (RuntimeException e) {

        }
        
    }

    // 取消订阅 pass
    public void testUnSubcription() {
        
        String subscriptionUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:162a44968d894e6fa5f3385e6dee5e0b";
        UnSubcriptionRequest deleteSubcription = new UnSubcriptionRequest();
        deleteSubcription.setSubscriptionUrn(subscriptionUrn);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnConfiguration(smnConfiguration);
        HttpResponse res = subscriptionService.unsubscribe(deleteSubcription);
        Assert.assertEquals(404, res.getHttpCode());
        LOGGER.info(res.toString());
    }

}
