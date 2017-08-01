package com.huawei.smn.model.request.subscription;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * Add subscribtions for designated topic,subscriber may receive topic's message
 * after confirm
 * 
 * @author huangqiong
 *
 */
public class SubcriptionRequest extends AbstractSmnRequest {
    private static Logger logger = LoggerFactory.getLogger(SubcriptionRequest.class);
    /**
     * topic's unique resource identifier
     */
    private String topicUrn;
    /**
     * protocol
     */
    private String protocol;
    /**
     * message access point
     */
    private String endpoint;
    /**
     * remark
     */
    private String remark;

    @Override
    public String getRequestUrl() throws RuntimeException {
        if (StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
            logger.error("project id is null");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getTopicUrn())) {
            logger.error("getTopicUrn() is null");
            throw new RuntimeException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
                .append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
                .append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER).append(getTopicUrn())
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUBSCRIPTIONS);
        logger.info("Request url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() {
        if (StringUtils.isBlank(getProtocol()) || !isSubscriptionProtocol(getProtocol())) {
            logger.error("protocol is not effective");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getEndpoint())) {
            logger.error("Endpoint is null");
            throw new RuntimeException();
        }
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put(SmnConstants.SMN_PROTOCOL, getProtocol());
        requestParameterMap.put(SmnConstants.SMN_ENDPOINT_TAG, getEndpoint());
        requestParameterMap.put(SmnConstants.SMN_SUBCRIBE_REMARK, getRemark());
        return requestParameterMap;
    }

    private boolean isSubscriptionProtocol(String protocol) {
        if (SmnConstants.SMN_SUB_PROTOCOL_EMAIL.equals(protocol) || SmnConstants.SMN_SUB_PROTOCOL_SMS.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_HTTPS.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_HTTP.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_LAMBDA.equals(protocol)
                || SmnConstants.SMN_SUB_PROTOCOL_APPLICATION.equals(protocol)) {
            return true;
        }
        return false;
    }

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
