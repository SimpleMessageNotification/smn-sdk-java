package com.huawei.smn.service.impl;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.huawei.smn.model.request.subscription.ListSubscriptionsRequest;
import com.huawei.smn.model.request.subscription.SubcriptionRequest;
import com.huawei.smn.model.request.subscription.UnSubcriptionRequest;
import com.huawei.smn.service.SubscriptionService;

import junit.framework.TestCase;

public class SubscriptionServiceImplTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImplTest.class);

    @Test
    public void testSubcription() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        SubcriptionRequest createSubcription = new SubcriptionRequest();
        createSubcription.setTopicUrn(topicUrn);
        // createSubcription.setEndpoint("+8613302965216");
        createSubcription.setProtocol("sms");
        createSubcription.setEndpoint("18588432306");
        // createSubcription.setEndpoint("970380859@qq.com");
        // createSubcription.setProtocol("email");
        createSubcription.setRemark("api订阅接口测试成功");
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnRequest(createSubcription);
        Map<String, Object> res = subscriptionService.subscribe();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("subscription_urn"));
        Assert.assertNotNull(res.get("status"));

    }

    @Test
    public void testListSubscriptions() {
        ListSubscriptionsRequest listSubscriptionsRequest = new ListSubscriptionsRequest();
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnRequest(listSubscriptionsRequest);
        Map<String, Object> res = subscriptionService.listSubscriptions();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    @Test
    public void testListSubscriptionsByTopic() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
        ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();
        listSubscriptionsByTopicRequest.setTopicUrn(topicUrn);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnRequest(listSubscriptionsByTopicRequest);
        Map<String, Object> res = subscriptionService.listSubscriptionsByTopic();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }

    @Test
    public void testUnSubcription() {
        String subscriptionUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:162a44968d894e6fa5f3385e6dee5e0b";
        UnSubcriptionRequest deleteSubcription = new UnSubcriptionRequest();
        deleteSubcription.setSubscriptionUrn(subscriptionUrn);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        subscriptionService.setSmnRequest(deleteSubcription);
        Map<String, Object> res = subscriptionService.unsubscribe();
        logger.info(res.toString());
        Assert.assertNotNull(res.get("request_id"));
        Assert.assertNotNull(res.get("status"));
    }
}
