package com.smn.sample.old;

import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.common.utils.JsonUtil;
import com.smn.model.request.publish.PublishMsgRequest;
import com.smn.service.PublishService;
import com.smn.service.impl.PublishServiceImpl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * publishService发送消息示例
 */
public class PublishServiceExample {
    public static void main(String[] args) {
        publishMsgWithTemplate();
    }

    /**
     * 消息发布
     */
    public static void publishWithMessage() {
        // 设置信息发送服务
        PublishService publishService = new PublishServiceImpl();
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
        publishService.setSmnConfiguration(smnConfiguration);

        //构造请求对象
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();

        // 设置要发送的topic
        publishMsgRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置要发送的message大小
        publishMsgRequest.setMessage("测试sdk发布消息，10.24");

        // 设置主题
        publishMsgRequest.setSubject("ready to release");

        HttpResponse res = publishService.publish(publishMsgRequest);
        System.out.println(res);

    }

    /**
     * 使用消息结构体方式的消息发布
     */
    public static void publishMsgWithStruct() {
        // 设置信息发送服务
        PublishService publishService = new PublishServiceImpl();
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
        publishService.setSmnConfiguration(smnConfiguration);

        //构造请求对象
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();

        // 设置要发送的topic
        publishMsgRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 构造message_struct
        Map<String, Object> structMsgMap = new HashMap<String, Object>();
        structMsgMap.put("default", "hello 2017,welcome struct test");
        structMsgMap.put("sms", "hello 2017,welcome struct test");
        structMsgMap.put("http", "hello 2017,welcome struct test");
        structMsgMap.put("https", "hello 2017,welcome struct test");
        structMsgMap.put("email", "hello 2017,welcome struct test");
        publishMsgRequest.setMessageStructure(JsonUtil.getJsonStringByMap(structMsgMap));

        // 设置主题
        publishMsgRequest.setSubject("ready to release");

        HttpResponse res = publishService.publish(publishMsgRequest);
        System.out.println(res);

    }

    /**
     * 使用消息模板方式的消息发布
     */
    public static void publishMsgWithTemplate() {
        // 设置信息发送服务
        PublishService publishService = new PublishServiceImpl();
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
        publishService.setSmnConfiguration(smnConfiguration);

        //构造请求对象
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();

        // 设置要发送的topic
        publishMsgRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 构造模板标签对应的变量
        Map<String, Object> tagsMap = new HashMap<String, Object>();
        tagsMap.put("year", "2017");
        tagsMap.put("name", "qiong");
        tagsMap.put("company", "huawei");
        publishMsgRequest.setTags(tagsMap);

        // 设置消息模板名称
        publishMsgRequest.setMessageTemplateName("createMessageTemplate");

        // 设置主题
        publishMsgRequest.setSubject("ready to release");

        HttpResponse res = publishService.publish(publishMsgRequest);
        System.out.println(res);

    }
}
