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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.request.sms.SmsPublishRequest;
import com.huawei.smn.service.impl.SmsServiceImpl;

import junit.framework.TestCase;

/**
 * @author huangqiong
 * @date 2017年8月14日 下午8:17:08
 * @version 0.1
 */
public class SmsServiceTest extends TestCase {

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
    public void smsPublishTest() {

        SmsService smsService = new SmsServiceImpl();
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
        String endpoint = "13302965216";
        String message = "send sms";

        smsPublishRequest.setEndpoint(endpoint);
        smsPublishRequest.setMessage(message);
        // 企业用户可以使用自定义签名，如果不设置短信签名，默认签名【华为云】
        smsPublishRequest.setSignId("6be340e91e5241e4b5d85837e6709104");

        // 发送短信
        Map<String, Object> res = smsService.smsPublish(smsPublishRequest);
        System.out.println(res);

    }

}
