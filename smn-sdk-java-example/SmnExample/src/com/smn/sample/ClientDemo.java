package com.smn.sample;

import com.smn.account.CloudAccount;
import com.smn.client.SmnClient;
import com.smn.common.HttpResponse;
import com.smn.common.utils.JsonUtil;
import com.smn.model.request.publish.PublishMsgRequest;
import com.smn.model.request.sms.*;
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;
import com.smn.model.request.template.*;
import com.smn.model.request.topic.*;

import java.util.*;

public class ClientDemo {
    private SmnClient smnClient;

    /**
     * 构造函数
     */
    public ClientDemo() {
        CloudAccount cloudAccount = new CloudAccount(
                "******",
                "******",
                "******",
                "cn-north-1");

        // if you want custom HTTP parameters
        // or use http proxy, you can use like this
        //ClientConfiguration clientConfiguration = new ClientConfiguration();
        // if you want not use http proxy ,the proxyHost, proxyPort, proxyUserName
        // and proxyPassword not need to set
        //clientConfiguration.setProxyHost("proxycn2.huawei.com");
        //clientConfiguration.setProxyPort(8080);
        // password or username is optional
        //clientConfiguration.setProxyUserName("******");
        //clientConfiguration.setProxyPassword("******");
        // timeout params
        //clientConfiguration.setConnectTimeOut(300000);
        //clientConfiguration.setSocketTimeOut(300000);
        //CloudAccount cloudAccount = new CloudAccount(
        //        "******",
        //        "******",
        //       "******",
        //       "cn-north-1",
        //       clientConfiguration);

        smnClient = cloudAccount.getSmnClient();
    }

    public static void main(String[] args) {
        ClientDemo clientDemo = new ClientDemo();
        clientDemo.publishMsgWithTemplate();
        clientDemo.listTopics();
        clientDemo.smsPublish();
        clientDemo.listMessageTemplate();
        clientDemo.listSubscriptions();
    }

    /**
     * 发送短信
     */
    public void smsPublish() {

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
        HttpResponse res = smnClient.smsPublish(smsPublishRequest);
        System.out.println(res);
    }

    /**
     * 创建topic的demo
     */
    public void createTopic() {
        // 构造请求对象
        CreateTopicRequest createTopicRequest = new CreateTopicRequest();

        // 设置创建topic的名字
        createTopicRequest.setName("firsttopic_v1");
        // 设置topic的描述信息
        createTopicRequest.setDisplayName("thefirstcreatetopic");

        // 创建topic
        HttpResponse res = smnClient.createTopic(createTopicRequest);
        System.out.println(res);
    }

    /**
     * 更新topic的demo
     */
    public void updateTopic() {
        // 构造请求对象
        UpdateTopicRequest updateTopicRequest = new UpdateTopicRequest();

        // 设置topic的唯一资源标示
        updateTopicRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:firsttopic_v1");
        // 设置topic的描述信息
        updateTopicRequest.setDisplayName("thefirstcreatetopic_v2");

        // 更新topic
        HttpResponse res = smnClient.updateTopic(updateTopicRequest);
        System.out.println(res);
    }

    /**
     * 删除topic的demo
     */
    public void deleteTopic() {
        // 构造请求对象
        DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest();

        // 设置topic的唯一资源标示
        deleteTopicRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:firsttopic_v1");

        // 删除topic
        HttpResponse res = smnClient.deleteTopic(deleteTopicRequest);
        System.out.println(res);
    }

    /**
     * 查询topic 列表的demo
     */
    public void listTopics() {
        // 构造请求对象
        ListTopicsRequest listTopicsRequest = new ListTopicsRequest();

        // 设置每页的最大条目数
        listTopicsRequest.setLimit(100);

        // 设置分页列表的起始页
        listTopicsRequest.setOffset(0);

        // 查询topic列表
        HttpResponse res = smnClient.listTopics(listTopicsRequest);
        System.out.println(res);
    }

    /**
     * 查询topic详情的demo
     */
    public void queryTopicDetail() {
        // 构造请求对象
        QueryTopicDetailRequest queryTopicDetailRequest = new QueryTopicDetailRequest();

        // 设置topic的唯一资源标示
        queryTopicDetailRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 查询topic详情
        HttpResponse res = smnClient.queryTopicDetail(queryTopicDetailRequest);
        System.out.println(res);
    }

    /**
     * 查询topic属性的demo
     */
    public void listTopicAttributes() {
        // 构造请求对象
        ListTopicAttributesRequest listTopicAttributesRequest = new ListTopicAttributesRequest();

        // 设置topic的唯一资源标示
        listTopicAttributesRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置属性名称
        listTopicAttributesRequest.setAttributesName("access_policy");

        // 查询topic属性列表
        HttpResponse res = smnClient.listTopicAttributes(listTopicAttributesRequest);
        System.out.println(res);
    }

    /**
     * 更新topic属性的demo
     */
    public void updateTopicAttribute() {
        // 构造请求对象
        UpdateTopicAttributeRequest updateTopicAttributeRequest = new UpdateTopicAttributeRequest();

        // 设置topic的唯一资源标示
        updateTopicAttributeRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置属性名称
        updateTopicAttributeRequest.setAttributesName("access_policy");

        // 设置属性值
        LinkedHashMap<String, Object> attributeValue = new LinkedHashMap<>();
        attributeValue.put("Version", "2016-09-07");
        attributeValue.put("Id", "__default_policy_ID");

        List<LinkedHashMap<String, Object>> statements = new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String, Object> statement1 = new LinkedHashMap<String, Object>();
        statement1.put("Sid", "__user_pub_0");
        statement1.put("Effect", "Allow");

        LinkedHashMap<String, Object> csp1 = new LinkedHashMap<String, Object>();
        csp1.put("CSP", Arrays.asList("urn:csp:iam::1040774eae344b78b14f2939863d4ede:root"));
        statement1.put("Principal", csp1);
        statement1.put("Action", Arrays.asList("SMN:Publish", "SMN:QueryTopicDetail"));
        statement1.put("Resource", "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");
        statements.add(statement1);

        attributeValue.put("Statement", statements);
        updateTopicAttributeRequest.setAttributeValue(attributeValue);

        // 更新topic属性
        HttpResponse res = smnClient.updateTopicAttribute(updateTopicAttributeRequest);
        System.out.println(res);
    }

    /**
     * 删除指定名称的topic属性的demo
     */
    public void deleteTopicAttributeByName() {
        // 构造请求对象
        DeleteTopicAttributeByNameRequest deleteTopicAttributeByNameRequest = new DeleteTopicAttributeByNameRequest();

        // 设置topic的唯一资源标示
        deleteTopicAttributeByNameRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置属性名称
        deleteTopicAttributeByNameRequest.setAttributesName("access_policy");

        // 删除topic属性
        HttpResponse res = smnClient.deleteTopicAttributeByName(deleteTopicAttributeByNameRequest);
        System.out.println(res);
    }

    /**
     * 删除所有的topic属性的demo
     */
    public void deleteTopicAttributes() {
        // 构造请求对象
        DeleteTopicAttributesRequest deleteTopicAttributesRequest = new DeleteTopicAttributesRequest();

        // 设置topic的唯一资源标示
        deleteTopicAttributesRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 删除topic属性
        HttpResponse res = smnClient.deleteTopicAttributes(deleteTopicAttributesRequest);
        System.out.println(res);
    }

    /**
     * 消息发布
     */
    public void publishWithMessage() {

        //构造请求对象
        PublishMsgRequest publishMsgRequest = new PublishMsgRequest();

        // 设置要发送的topic
        publishMsgRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置要发送的message大小
        publishMsgRequest.setMessage("测试sdk发布消息，10.24");

        // 设置主题
        publishMsgRequest.setSubject("ready to release");

        HttpResponse res = smnClient.publish(publishMsgRequest);
        System.out.println(res);

    }

    /**
     * 使用消息结构体方式的消息发布
     */
    public void publishMsgWithStruct() {
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

        HttpResponse res = smnClient.publish(publishMsgRequest);
        System.out.println(res);
    }

    /**
     * 使用消息模板方式的消息发布
     */
    public void publishMsgWithTemplate() {
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
        publishMsgRequest.setSubject("ready to release_sdk");

        HttpResponse res = smnClient.publish(publishMsgRequest);
        System.out.println(res);
    }

    /**
     * 查询短信的发送状态的demo
     */
    public void listSmsMsgReport() {
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
        //listSmsMsgReportRequest.setMobile("13688800000");

        // 设置每页的最大条目数
        listSmsMsgReportRequest.setLimit(100);

        // 设置分页列表的起始页
        listSmsMsgReportRequest.setOffset(0);

        // 查询短信发送状态
        HttpResponse res = smnClient.listSmsMsgReport(listSmsMsgReportRequest);
        System.out.println(res);
    }

    /**
     * 查询已发送短信的内容的demo
     */
    public void getSmsMessage() {

        // 构造请求对象
        GetSmsMessageRequest getSmsMessageRequest = new GetSmsMessageRequest();

        // 设置消息唯一ID
        getSmsMessageRequest.setMessageId("e4534305243248a4aa662dbcb6b12e51");

        // 查询已发送的短信内容
        HttpResponse res = smnClient.getSmsMessage(getSmsMessageRequest);
        System.out.println(res);
    }

    /**
     * 查询短信回调事件的demo
     */
    public void listSmsCallbackEvent() {
        // 构造请求对象
        ListSmsCallbackEventRequest listSmsCallbackEventRequest = new ListSmsCallbackEventRequest();

        // 设置回调事件类型，非必选
        listSmsCallbackEventRequest.setEventType("sms_success_event");

        // 查询短信回调事件
        HttpResponse res = smnClient.listSmsCallbackEvent(listSmsCallbackEventRequest);
        System.out.println(res);
    }

    /**
     * 更新短信回调事件
     */
    public void updateSmsCallbackEvent() {
        // 构造请求对象
        UpdateSmsCallbackEventRequest updateSmsCallbackEventRequest = new UpdateSmsCallbackEventRequest();

        // 设置回调事件列表
        List<SmsCallback> callbacks = new ArrayList<SmsCallback>();
        callbacks.add(new SmsCallback("sms_fail_event", "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi"));

        updateSmsCallbackEventRequest.setCallbacks(callbacks);

        // 更新短信回调事件
        HttpResponse res = smnClient.updateSmsCallbackEvent(updateSmsCallbackEventRequest);
        System.out.println(res);
    }

    /**
     * 创建模板demo
     */
    public void createMessageTemplate() {
        // 构造请求对象
        CreateMessageTemplateRequest createMessageTemplateRequest = new CreateMessageTemplateRequest();

        // 设置模板名称
        createMessageTemplateRequest.setMessageTemplateName("createbyzhangyx");

        // 设置模板内容
        createMessageTemplateRequest.setContent("hello {name},hello {word},hello {gingo}");

        // 设置模板的协议类型
        createMessageTemplateRequest.setProtocol("sms");// default,sms,http,https,email

        // 创建消息模板
        HttpResponse res = smnClient.createMessageTemplate(createMessageTemplateRequest);

        System.out.println(res);
    }

    /**
     * 删除模板demo
     */
    public void deleteMessageTemplate() {
        // 构造请求对象
        DeleteMessageTemplateRequest deleteMessageTemplateRequest = new DeleteMessageTemplateRequest();

        // 设置模板名称
        deleteMessageTemplateRequest.setMessageTemplateId("4d9da2f536044ff78e48726bf8dbfaf1");

        // 删除消息模板
        HttpResponse res = smnClient.deleteMessageTemplate(deleteMessageTemplateRequest);

        System.out.println(res);
    }

    /**
     * 更新模板demo
     */
    public void updateMessageTemplate() {

        // 构造请求对象
        UpdateMessageTemplateRequest updateMessageTemplateRequest = new UpdateMessageTemplateRequest();

        // 设置模板名称
        updateMessageTemplateRequest.setMessageTemplateId("4d9da2f536044ff78e48726bf8dbfaf1");

        // 设置模板消息内容
        updateMessageTemplateRequest.setContent("this year is {year},welcom {name} to join the {company} to create new life");

        // 删除消息模板
        HttpResponse res = smnClient.updateMessageTemplate(updateMessageTemplateRequest);

        System.out.println(res);
    }

    /**
     * 查询消息模板列表demo
     */
    public void listMessageTemplate() {

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
        HttpResponse res = smnClient.listMessageTemplates(listMessageTemplatesRequest);

        System.out.println(res);
    }

    /**
     * 查询消息模板详情demo
     */
    public void queryMessageTemplateDetail() {
        // 构造请求对象
        QueryMessageTemplateDetailRequest queryMessageTemplateDetailRequest = new QueryMessageTemplateDetailRequest();

        // 设置模板唯一标示ID
        queryMessageTemplateDetailRequest.setMessageTemplateId("350a3937dbfa4f56834c97fd9e475e4e");

        // 删除消息模板
        HttpResponse res = smnClient.queryMsgTemplateDetail(queryMessageTemplateDetailRequest);

        System.out.println(res);
    }

    /**
     * 订阅demo
     */
    public void subscribe() {
        // 构造请求对象
        SubcriptionRequest subcriptionRequest = new SubcriptionRequest();

        // 设置主题
        subcriptionRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置终端推送的方式
        subcriptionRequest.setProtocol("sms");

        // 设置消息接入点
        subcriptionRequest.setEndpoint("13688807587");

        // 设置备注
        subcriptionRequest.setRemark("api订阅接口测试成功");

        // 订阅
        HttpResponse res = smnClient.subscribe(subcriptionRequest);
        System.out.println(res);
    }

    /**
     * 取消订阅demo
     */
    public void unSubscribe() {
        // 构造请求对象
        UnSubcriptionRequest deleteSubcription = new UnSubcriptionRequest();

        // 设置主题
        deleteSubcription.setSubscriptionUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi:29b23dbde98846bfae1bb40d6f166393");

        // 取消订阅
        HttpResponse res = smnClient.unsubscribe(deleteSubcription);
        System.out.println(res);
    }

    /**
     * 查询订阅者列表demo
     */
    public void listSubscriptions() {
        // 构造请求对象
        ListSubscriptionsRequest listSubscriptionsRequest = new ListSubscriptionsRequest();

        // 设置分页列表offset，可选参数，默认0
        listSubscriptionsRequest.setOffset(0);

        // 设置一次列表返回的最大条目数， 可选参数，上限和默认100
        listSubscriptionsRequest.setLimit(100);

        // 查询订阅者列表
        HttpResponse res = smnClient.listSubscriptions(listSubscriptionsRequest);
        System.out.println(res);
    }

    /**
     * 查询指定主题订阅者列表demo
     */
    public void listSubscriptionsByTopic() {
        // 构造请求对象
        ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();

        // 设置主题topic
        listSubscriptionsByTopicRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置分页列表offset，可选参数，默认0
        listSubscriptionsByTopicRequest.setOffset(0);

        // 设置一次列表返回的最大条目数， 可选参数，上限和默认100
        listSubscriptionsByTopicRequest.setLimit(100);

        // 查询订阅者列表
        HttpResponse res = smnClient.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
        System.out.println(res);
    }
}
