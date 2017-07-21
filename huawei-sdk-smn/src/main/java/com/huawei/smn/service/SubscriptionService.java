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
	/**
	 * set request
	 * 
	 * @param smnRequest
	 */
	void setSmnRequest(SmnRequest smnRequest);

	public void init();

	// subscribe
	Map<String, Object> subscribe(SmnRequest smnRequest) throws RuntimeException;

	// cancel subscribe
	Map<String, Object> unsubscribe(SmnRequest smnRequest) throws RuntimeException;

	// query list of subscribers
	Map<String, Object> listSubscriptions(SmnRequest smnRequest) throws RuntimeException;

	// query list of subscribers for designated topic
	Map<String, Object> listSubscriptionsByTopic(SmnRequest smnRequest) throws RuntimeException;

}
