package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.SmnRequest;

/**
 * 订阅服务
 * 
 * @author huangqiong
 *
 */
public interface SubscriptionService {

    // set request
    void setSmnRequest(SmnRequest smnRequest);

    // init
    public void init();

    // subscribe
    Map<String, Object> subscribe() throws RuntimeException;

    // cancel subscribe
    Map<String, Object> unsubscribe() throws RuntimeException;

    // query list of subscribers
    Map<String, Object> listSubscriptions() throws RuntimeException;

    // query list of subscribers for designated topic
    Map<String, Object> listSubscriptionsByTopic() throws RuntimeException;

}
