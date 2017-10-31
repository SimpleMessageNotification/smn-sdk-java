package com.smn.sample.old;

import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;
import com.smn.service.SubscriptionService;
import com.smn.service.impl.SubscriptionServiceImpl;

/**
 * 订阅操作相关的demo
 *
 * @author zhangyx
 * @version 0.7
 */
public class SubscriptionServiceExample {

    public static void main(String[] args) {
        listSubscriptionsByTopic();
    }

    /**
     * 订阅demo
     */
    public static void subscribe() {
        // 构造订阅操作相关的服务
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
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
        subscriptionService.setSmnConfiguration(smnConfiguration);
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
        HttpResponse res = subscriptionService.subscribe(subcriptionRequest);
        System.out.println(res);
    }

    /**
     * 取消订阅demo
     */
    public static void unSubscribe() {
        // 构造订阅操作相关的服务
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
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
        subscriptionService.setSmnConfiguration(smnConfiguration);
        // 构造请求对象
        UnSubcriptionRequest deleteSubcription = new UnSubcriptionRequest();

        // 设置主题
        deleteSubcription.setSubscriptionUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi:deea96c2b82c4f0ebaf6f97ae8a9c347");

        // 取消订阅
        HttpResponse res = subscriptionService.unsubscribe(deleteSubcription);
        System.out.println(res);
    }

    /**
     * 查询订阅者列表demo
     */
    public static void listSubscriptions() {
        // 构造订阅操作相关的服务
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
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
        subscriptionService.setSmnConfiguration(smnConfiguration);
        // 构造请求对象
        ListSubscriptionsRequest listSubscriptionsRequest = new ListSubscriptionsRequest();

        // 设置分页列表offset，可选参数，默认0
        listSubscriptionsRequest.setOffset(0);

        // 设置一次列表返回的最大条目数， 可选参数，上限和默认100
        listSubscriptionsRequest.setLimit(100);

        // 查询订阅者列表
        HttpResponse res = subscriptionService.listSubscriptions(listSubscriptionsRequest);
        System.out.println(res);
    }

    /**
     * 查询指定主题订阅者列表demo
     */
    public static void listSubscriptionsByTopic() {
        // 构造订阅操作相关的服务
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
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
        subscriptionService.setSmnConfiguration(smnConfiguration);
        // 构造请求对象
        ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();

        // 设置主题topic
        listSubscriptionsByTopicRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置分页列表offset，可选参数，默认0
        listSubscriptionsByTopicRequest.setOffset(0);

        // 设置一次列表返回的最大条目数， 可选参数，上限和默认100
        listSubscriptionsByTopicRequest.setLimit(100);

        // 查询订阅者列表
        HttpResponse res = subscriptionService.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
        System.out.println(res);
    }
}
