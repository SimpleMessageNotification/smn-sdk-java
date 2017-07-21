package com.huawei.smn.model;

import java.io.Serializable;

/**
 * Subscription structure
 * 
 * @author huangqiong
 *
 */
public class Subscription implements Serializable {

	private static final long serialVersionUID = 8176146873972477948L;
	/**
	 * topic's unique resource identifier
	 */
	private String topicUrn;
	/**
	 * protocol
	 */
	private String protocol;
	/**
	 * subscripter's unique resource identifier
	 */
	private String subscriptionUrn;
	/**
	 * topic creator's projectid
	 */
	private String owner;
	/**
	 * message access point
	 */
	private String endpoint;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * subscriber's status.0:unconfirmed，1:confirmed,3 cancel confirmed
	 */
	private int status;

	public String getTopicUrn() {
		return topicUrn;
	}

	public void setTopicUrn(String topicUrn) {
		this.topicUrn = topicUrn;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSubscriptionUrn() {
		return subscriptionUrn;
	}

	public void setSubscriptionUrn(String subscriptionUrn) {
		this.subscriptionUrn = subscriptionUrn;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
