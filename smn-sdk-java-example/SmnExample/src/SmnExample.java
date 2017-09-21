
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

    /**
     * 发送短信
     */
    public static void sendSms() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName
        smnConfiguration.setDomainName("liuqiangqiang");
        // 设置用户名
        smnConfiguration.setUserName("liuqiangqiang");
        // 设置密码
        smnConfiguration.setPassword("****");
        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
        // 发送手机号码 号码格式 (+)(国家码)(手机号码)
        String phone = "+8618565889669";
        // 短信内容
        String message = "test message";
        // 短信签名,需要在消息通知服务的自助页面申请签名，申请办理时间约2天
        String signId = "6be340e91e5241e4b5d85837e6709104";

        // 设置手机号码
        smsPublishRequest.setEndpoint(phone);
        // 设置短信内容，短信内容中不要出现【】或者[]
        smsPublishRequest.setMessage(message);
        // 设置短信签名
        smsPublishRequest.setSignId(signId);

        // 发送短信
        HttpResponse res = smsService.smsPublish(smsPublishRequest);
        System.out.println(res);

    }

    public static void main(String[] args) {
        SmnExample.sendSms();
    }

}
