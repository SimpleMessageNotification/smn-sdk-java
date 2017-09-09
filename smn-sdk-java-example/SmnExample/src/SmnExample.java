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

import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.model.request.sms.SmsPublishRequest;
import com.smn.service.SmsService;
import com.smn.service.impl.SmsServiceImpl;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午10:19:18
 * @version 0.1
 */
public class SmnExample {
    public static void main(String[] args) {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 加载配置，未设置加载路径默认加载相对路径config/configuaration.properties
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        smnConfiguration.setFilepath("config/configuaration.properties");
        smnConfiguration.reload();
        System.out.println(smnConfiguration.toString());
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
        String endpoint = "1330296****";
        String message = "send sms";

        smsPublishRequest.setEndpoint(endpoint);
        smsPublishRequest.setMessage(message);
        // 企业用户需要先创建自定义签名,创建完签名后，设置签名id
        smsPublishRequest.setSignId("6be340e91e5241e4b5d85837e6709104");

        // 发送短信
        HttpResponse res = smsService.smsPublish(smsPublishRequest);
        System.out.println(res);
    }

}
