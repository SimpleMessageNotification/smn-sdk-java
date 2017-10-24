
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
import com.smn.model.request.sms.*;
import com.smn.service.SmsService;
import com.smn.service.impl.SmsServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangqiong
 * @version 0.1
 * @date 2017年8月3日 下午10:19:18
 */
public class SmsServiceExample {

    public static void main(String[] args) {
        smsPublish();
    }

    /**
     * 发送短信
     */
    public static void smsPublish() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

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
        String phone = "+8613688807587";
        // 短信内容
        String message = "您的验证码是:1234，请查收";
        // 短信签名必填,需要在消息通知服务的自助页面申请签名，申请办理时间约2天
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

    /**
     * 查询短信发生的状态统计的demo
     */
    public static void listSmsMsgStatistic() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("liuqiangqiang");
        smnConfiguration.setUserName("liuqiangqiang");
        smnConfiguration.setPassword("lj@19871114");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        ListSmsMsgStatisticRequest listSmsMsgStatisticRequest = new ListSmsMsgStatisticRequest();

        // 设置短信签名
        listSmsMsgStatisticRequest.setSignId("6be340e91e5241e4b5d85837e6709104");

        // 设置查询类型, 按小时统计不需要设置startDate和endDate
        listSmsMsgStatisticRequest.setType("hourly");

        // 查询短信发送的状态统计
        HttpResponse res = smsService.listSmsMsgStatistic(listSmsMsgStatisticRequest);
        System.out.println(res);
    }

    /**
     * 查询短信的发生状态的demo
     */
    public static void listSmsMsgReport() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("liuqiangqiang");
        smnConfiguration.setUserName("liuqiangqiang");
        smnConfiguration.setPassword("lj@19871114");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        ListSmsMsgReportRequest listSmsMsgReportRequest = new ListSmsMsgReportRequest();

        // 设置短信签名
        listSmsMsgReportRequest.setSignId("6be340e91e5241e4b5d85837e6709104");

        // 设置查询开始时间
        listSmsMsgReportRequest.setStartTime(String.valueOf(System.currentTimeMillis() - 24 * 60 * 60 * 1000L));

        // 设置查询结束时间
        listSmsMsgReportRequest.setEndTime(String.valueOf(System.currentTimeMillis()));

        // 设置短信的发送状态
        listSmsMsgReportRequest.setStatus("1");

        // 设置接收短信的手机
        listSmsMsgReportRequest.setStatus("13688800000");

        // 设置每页的最大条目数
        listSmsMsgReportRequest.setLimit(100);

        // 设置分页列表的起始页
        listSmsMsgReportRequest.setOffset(0);

        // 查询短信发送状态
        HttpResponse res = smsService.listSmsMsgReport(listSmsMsgReportRequest);
        System.out.println(res);
    }

    /**
     * 查询已发生短信的内容的demo
     */
    public static void getSmsMessage() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("liuqiangqiang");
        smnConfiguration.setUserName("liuqiangqiang");
        smnConfiguration.setPassword("lj@19871114");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        GetSmsMessageRequest getSmsMessageRequest = new GetSmsMessageRequest();

        // 设置消息唯一ID
        getSmsMessageRequest.setMessageId("6be340e91e5241e4b5d85837e6709104");

        // 查询已发送的短信内容
        HttpResponse res = smsService.getSmsMessage(getSmsMessageRequest);
        System.out.println(res);
    }

    /**
     * 查询短信回调事情的demo
     */
    public static void listSmsCallbackEvent() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("liuqiangqiang");
        smnConfiguration.setUserName("liuqiangqiang");
        smnConfiguration.setPassword("lj@19871114");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        ListSmsCallbackEventRequest listSmsCallbackEventRequest = new ListSmsCallbackEventRequest();

        // 设置回调事件类型，非必选
        listSmsCallbackEventRequest.setEventType("sms_success_event");

        // 查询短信回调事件
        HttpResponse res = smsService.listSmsCallbackEvent(listSmsCallbackEventRequest);
        System.out.println(res);
    }

    /**
     * 更新短信回调时间
     */
    public static void updateSmsCallbackEvent() {

        // 短信发送服务
        SmsService smsService = new SmsServiceImpl();
        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("liuqiangqiang");
        smnConfiguration.setUserName("liuqiangqiang");
        smnConfiguration.setPassword("lj@19871114");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        smsService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        UpdateSmsCallbackEventRequest updateSmsCallbackEventRequest = new UpdateSmsCallbackEventRequest();

        // 设置回调事件列表
        List<SmsCallback> callbacks = new ArrayList<SmsCallback>();
        callbacks.add(new SmsCallback("sms_fail_event", "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi"))

        updateSmsCallbackEventRequest.setCallbacks(callbacks);

        // 更新短信回调事件
        HttpResponse res = smsService.updateSmsCallbackEvent(updateSmsCallbackEventRequest);
        System.out.println(res);
    }

}
