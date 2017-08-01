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

    /**
     * @return the topicUrn
     */
    public String getTopicUrn() {
        return topicUrn;
    }

    /**
     * @param topicUrn
     *            the topicUrn to set
     */
    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol
     *            the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the subscriptionUrn
     */
    public String getSubscriptionUrn() {
        return subscriptionUrn;
    }

    /**
     * @param subscriptionUrn
     *            the subscriptionUrn to set
     */
    public void setSubscriptionUrn(String subscriptionUrn) {
        this.subscriptionUrn = subscriptionUrn;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint
     *            the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
