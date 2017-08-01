package com.huawei.smn.model.request.topic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * Delete designated attribute's topic
 * 
 * @author huangqiong
 *
 */
public class DeleteTopicAttributeByNameRequest extends AbstractSmnRequest {

    private static Logger logger = LoggerFactory.getLogger(DeleteTopicAttributeByNameRequest.class);
    /**
     * topic's unique resource identifier
     */
    private String topicUrn;
    /**
     * attribute's name,may support specified string only
     */
    private String attributesName;

    public String getRequestUrl() throws RuntimeException {
        if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
            logger.error("project id is null");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getTopicUrn())) {
            logger.error("getTopicUrn() is null");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getAttributesName()) || !isValidAttributeName(getAttributesName())) {
            logger.error("Attributte name is null or is not valid");
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
                .append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
                .append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER).append(getTopicUrn())
                .append("/attributes").append(SmnConstants.URL_DELIMITER).append(getAttributesName());

        logger.info("delete topic url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        // requestParameterMap.put(SmnConstants.TOPIC_URN, getTopicUrn());
        return requestParameterMap;
    }

    private boolean isValidAttributeName(String attribute) {
        if ("access_policy".equals(attribute) || "introduction".equals(attribute)) {
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
     * @return the attributesName
     */
    public String getAttributesName() {
        return attributesName;
    }

    /**
     * @param attributesName
     *            the attributesName to set
     */
    public void setAttributesName(String attributesName) {
        this.attributesName = attributesName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
