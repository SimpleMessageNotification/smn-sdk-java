package com.huawei.smn.model.request.template;

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
 * Delete message template
 * 
 * @author huangqiong
 *
 */
public class DeleteMessageTemplateRequest extends AbstractSmnRequest {
    private static Logger logger = LoggerFactory.getLogger(DeleteMessageTemplateRequest.class);

    /**
     * template's unique identifier
     */
    private String messageTemplateId;

    @Override
    public String getRequestUrl() throws RuntimeException {
        if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
            logger.error("project id is null");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getMessageTemplateId())) {
            logger.warn("Message template id is null");
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
                .append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE)
                .append(SmnConstants.URL_DELIMITER).append(getMessageTemplateId());
        logger.info("Delete message template request url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    /**
     * @return the messageTemplateId
     */
    public String getMessageTemplateId() {
        return messageTemplateId;
    }

    /**
     * @param messageTemplateId
     *            the messageTemplateId to set
     */
    public void setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
