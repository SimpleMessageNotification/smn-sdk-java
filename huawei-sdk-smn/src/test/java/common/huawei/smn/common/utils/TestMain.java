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
 * @date 2017年8月9日 上午10:13:31
 * @version 0.1
 * 
 */
package common.huawei.smn.common.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.request.sms.SmsPublishRequest;
import com.huawei.smn.service.SmsService;
import com.huawei.smn.service.impl.SmsServiceImpl;

/**
 * @author huangqiong
 * @date 2017年8月9日 上午10:13:31
 * @version 0.1
 */
public class TestMain {

    private static Logger LOGGER = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        String endpoint = "13302965216";
        String message = "reform smn sdk to release";
        SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        smnConfiguration.reload();
        LOGGER.info(smnConfiguration.toString());
        smsPublishRequest.setEndpoint(endpoint);
        smsPublishRequest.setMessage(message);
        smsPublishRequest.setSignId("6be340e91e5241e4b5d85837e6709104");
        SmsService smsService = new SmsServiceImpl();
        smsService.setSmnConfiguration(smnConfiguration);
        Map<String, Object> res = smsService.smsPublish(smsPublishRequest);
        System.out.println(res);
    }

}
