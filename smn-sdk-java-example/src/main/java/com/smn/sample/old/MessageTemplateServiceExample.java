package com.smn.sample.old;

import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.model.request.template.*;
import com.smn.service.MessageTemplateService;
import com.smn.service.impl.MessageTemplateServiceImpl;

/**
 * 模板操作相关demo
 *
 * @author zhangyx
 * @version 0.7
 */
public class MessageTemplateServiceExample {
    public static void main(String[] args) {
        listMessageTemplate();
    }

    /**
     * 创建模板demo
     */
    public static void createMessageTemplate() {

        // 创建模板操作的service
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
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
        messageTemplateService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        CreateMessageTemplateRequest createMessageTemplateRequest = new CreateMessageTemplateRequest();

        // 设置模板名称
        createMessageTemplateRequest.setMessageTemplateName("createbyzhangyx");

        // 设置模板内容
        createMessageTemplateRequest.setContent("hello {name},hello {word},hello {gingo}");

        // 设置模板的协议类型
        createMessageTemplateRequest.setProtocol("sms");// default,sms,http,https,email

        // 创建消息模板
        HttpResponse res = messageTemplateService.createMessageTemplate(createMessageTemplateRequest);

        System.out.println(res);
    }

    /**
     * 删除模板demo
     */
    public static void deleteMessageTemplate() {

        // 创建模板操作的service
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
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
        messageTemplateService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        DeleteMessageTemplateRequest deleteMessageTemplateRequest = new DeleteMessageTemplateRequest();

        // 设置模板名称
        deleteMessageTemplateRequest.setMessageTemplateId("a9517ed453f044ceba4258053595ab54");

        // 删除消息模板
        HttpResponse res = messageTemplateService.deleteMessageTemplate(deleteMessageTemplateRequest);

        System.out.println(res);
    }

    /**
     * 更新模板demo
     */
    public static void updateMessageTemplate() {

        // 创建模板操作的service
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
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
        messageTemplateService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        UpdateMessageTemplateRequest updateMessageTemplateRequest = new UpdateMessageTemplateRequest();

        // 设置模板名称
        updateMessageTemplateRequest.setMessageTemplateId("a9517ed453f044ceba4258053595ab54");

        // 设置模板消息内容
        updateMessageTemplateRequest.setContent("this year is {year},welcom {name} to join the {company} to create new life");

        // 删除消息模板
        HttpResponse res = messageTemplateService.updateMessageTemplate(updateMessageTemplateRequest);

        System.out.println(res);
    }

    /**
     * 查询消息模板列表demo
     */
    public static void listMessageTemplate() {

        // 创建模板操作的service
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
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
        messageTemplateService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        ListMessageTemplatesRequest listMessageTemplatesRequest = new ListMessageTemplatesRequest();

        // 设置模板名称
        listMessageTemplatesRequest.setMessageTemplateName("createMessageTemplate");

        //设置模板支持的协议内省
        listMessageTemplatesRequest.setProtocol("sms");

        // 设置每页的最大条目数
        listMessageTemplatesRequest.setLimit(100);

        // 设置分页列表的起始页
        listMessageTemplatesRequest.setOffset(0);

        // 删除消息模板
        HttpResponse res = messageTemplateService.listMessageTemplates(listMessageTemplatesRequest);

        System.out.println(res);
    }

    /**
     * 查询消息模板详情demo
     */
    public static void queryMessageTemplateDetail() {

        // 创建模板操作的service
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
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
        messageTemplateService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        QueryMessageTemplateDetailRequest queryMessageTemplateDetailRequest = new QueryMessageTemplateDetailRequest();

        // 设置模板唯一标示ID
        queryMessageTemplateDetailRequest.setMessageTemplateId("a9517ed453f044ceba4258053595ab54");

        // 删除消息模板
        HttpResponse res = messageTemplateService.queryMsgTemplateDetail(queryMessageTemplateDetailRequest);

        System.out.println(res);
    }
}
