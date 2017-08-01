package com.huawei.smn.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.smn.model.request.publish.PublishMsgRequest;
import com.huawei.smn.service.PublishService;

import junit.framework.TestCase;

public class PublishServiceImplTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(PublishServiceImplTest.class);

    // 测试消息发布 pass
    public void testPublishMsg() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
        publishMsgRequest.setTopicUrn(topicUrn);
        publishMsgRequest.setMessage("测试sdk发布消息 ，7.18");
        publishMsgRequest.setSubject("ready to release");
        PublishService publishService = new PublishServiceImpl();
        publishService.setSmnRequest(publishMsgRequest);
        Map<String, Object> res = publishService.publish();
        logger.info(res.toString());
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
        publishService.setSmnRequest(publishMsgRequest);
        Map<String, Object> res = publishService.publish();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("message_id"));
        Assert.assertNotNull(res.get("status"));
    }

    // 使用消息结构体方式的消息发布 pass
    public void testPublishMsgWithStruct() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
        publishMsgRequest.setTopicUrn(topicUrn);

        Map<String, Object> structMsgMap = new HashMap<String, Object>();
        structMsgMap.put("default", "hello 2017,welcome struct test");
        structMsgMap.put("sms", "hello 2017,welcome struct test");
        structMsgMap.put("http", "hello 2017,welcome struct test");
        structMsgMap.put("https", "hello 2017,welcome struct test");
        structMsgMap.put("email", "hello 2017,welcome struct test");
        JSONObject jsonObject = new JSONObject(structMsgMap);
        publishMsgRequest.setMessageStructure(jsonObject.toJSONString());
        PublishService publishService = new PublishServiceImpl();
        publishService.setSmnRequest(publishMsgRequest);
        Map<String, Object> res = publishService.publish();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("message_id"));
        Assert.assertNotNull(res.get("status"));
    }
}
