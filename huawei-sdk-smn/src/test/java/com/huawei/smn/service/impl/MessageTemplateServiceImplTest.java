package com.huawei.smn.service.impl;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.request.template.CreateMessageTemplateRequest;
import com.huawei.smn.model.request.template.DeleteMessageTemplateRequest;
import com.huawei.smn.model.request.template.ListMessageTemplatesRequest;
import com.huawei.smn.model.request.template.QueryMessageTemplateDetailRequest;
import com.huawei.smn.model.request.template.UpdateMessageTemplateRequest;
import com.huawei.smn.service.MessageTemplateService;

import junit.framework.TestCase;

public class MessageTemplateServiceImplTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(MessageTemplateServiceImplTest.class);

    @Test
    public void testCreateMessageTemplateRequest() {
        CreateMessageTemplateRequest createMessageTemplateRequest = new CreateMessageTemplateRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        createMessageTemplateRequest.setMessageTemplateName("dcragie");
        String content = "hello {name},hello {word},hello {gingo}";
        createMessageTemplateRequest.setContent(content);
        createMessageTemplateRequest.setProtocol("sms");// default,sms,http,https,email
        messageTemplateService.setSmnRequest(createMessageTemplateRequest);
        Map<String, Object> res = messageTemplateService.createMessageTemplate();
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("message_template_id"));
        Assert.assertNotNull(res.get("status"));
        logger.info(res.toString());
    }

    @Test
    public void testDeleteMessageTemplateRequest() {
        String messageTemplateId = "6459b65502c1410a926fbc7d1f6aef0a";
        DeleteMessageTemplateRequest deleteMessageTemplateRequest = new DeleteMessageTemplateRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        deleteMessageTemplateRequest.setMessageTemplateId(messageTemplateId);
        messageTemplateService.setSmnRequest(deleteMessageTemplateRequest);
        Map<String, Object> res = messageTemplateService.deleteMessageTemplate();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    @Test
    public void testUpdateMessageTemplateRequest() {
        String messageTemplateId = "350a3937dbfa4f56834c97fd9e475e4e";
        UpdateMessageTemplateRequest updateMessageTemplateRequest = new UpdateMessageTemplateRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        updateMessageTemplateRequest.setMessageTemplateId(messageTemplateId);
        String content = "this year is {year},welcom {name} to join the {company} to create new life";
        updateMessageTemplateRequest.setContent(content);
        messageTemplateService.setSmnRequest(updateMessageTemplateRequest);
        Map<String, Object> res = messageTemplateService.updateMessageTemplate();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    @Test
    public void testListMessageTemplatesRequest() {
        ListMessageTemplatesRequest listMessageTemplatesRequest = new ListMessageTemplatesRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        listMessageTemplatesRequest.setMessageTemplateName("createMessageTemplate");
        listMessageTemplatesRequest.setLimit(100);
        messageTemplateService.setSmnRequest(listMessageTemplatesRequest);
        Map<String, Object> res = messageTemplateService.listMessageTemplates();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    public void testQueryMessageTemplateDetailRequest() {
        String messageTemplateId = "155f0e586490411db3258afe777a5add";
        QueryMessageTemplateDetailRequest queryMessageTemplateDetailRequest = new QueryMessageTemplateDetailRequest();
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
        queryMessageTemplateDetailRequest.setMessageTemplateId(messageTemplateId);
        messageTemplateService.setSmnRequest(queryMessageTemplateDetailRequest);
        Map<String, Object> res = messageTemplateService.queryMsgTemplateDetail();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("message_template_id"));
        Assert.assertNotNull(res.get("status"));

    }

}
