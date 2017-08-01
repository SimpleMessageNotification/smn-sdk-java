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
 * Cancel subscribe
 * 
 * @author huangqiong
 *
 */
public class UnSubcriptionRequest extends AbstractSmnRequest {
    private static Logger logger = LoggerFactory.getLogger(UnSubcriptionRequest.class);
    /**
     * subscripter's unique resource identifier
     */
    private String subscriptionUrn;

    @Override
    public String getRequestUrl() throws RuntimeException {
        if (StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
            logger.error("project id is null");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getSubscriptionUrn())) {
            logger.error("getSubscriptionUrn() is null");
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
                .append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUBSCRIPTIONS)
                .append(SmnConstants.URL_DELIMITER).append(getSubscriptionUrn());
        logger.info("Request url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
