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
 * Create message template
 * 
 * @author huangqiong
 *
 */
public class CreateMessageTemplateRequest extends AbstractSmnRequest {
    private static Logger logger = LoggerFactory.getLogger(CreateMessageTemplateRequest.class);
    /**
     * protocol
     */
    private String protocol;
    /**
     * template content ,support txt only currently
     */
    private String content;
    /**
     * template name
     */
    private String messageTemplateName;

    @Override
    public String getRequestUrl() throws RuntimeException {
        if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
            logger.error("project id is null");
            throw new RuntimeException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
                .append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE);
        logger.info("Request url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() {
        if (StringUtils.isBlank(getContent())) {
            logger.error("Template content is null");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getProtocol()) || !isValidProtocol(getProtocol())) {
            logger.error("Protocol is null or invalid");
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(getMessageTemplateName())) {
            logger.error("Message template name is null");
            throw new RuntimeException();
        }
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put("content", getContent());
        requestParameterMap.put("message_template_name", getMessageTemplateName());
        requestParameterMap.put("protocol", getProtocol());
        return requestParameterMap;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the messageTemplateName
     */
    public String getMessageTemplateName() {
        return messageTemplateName;
    }

    /**
     * @param messageTemplateName
     *            the messageTemplateName to set
     */
    public void setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
    }

    /**
     * Verify the protocol if valid
     * 
     * @param protocol
     * @return
     */
    private boolean isValidProtocol(String protocol) {
        String protocols = protocol.trim();
        if (protocols.equals("default") || protocols.equals("sms") || protocols.equals("http")
                || protocols.equals("https") || protocols.equals("email")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
