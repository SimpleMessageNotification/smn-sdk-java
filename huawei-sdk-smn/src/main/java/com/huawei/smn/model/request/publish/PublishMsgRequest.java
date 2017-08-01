package com.huawei.smn.model.request.publish;

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
 * publish message with three ways: --with json structure, --with message
 * template,--with common string message
 * 
 * @author huangqiong
 *
 */
public class PublishMsgRequest extends AbstractSmnRequest {
    private static Logger logger = LoggerFactory.getLogger(PublishMsgRequest.class);

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;
    /**
     * message's title, will be the subject when sent to mail subscribers
     */
    private String subject;
    /**
     * label tag in message template,custom label by user
     */
    private Map<String, Object> tags;
    /**
     * message template name
     */
    private String messageTemplateName;
    /**
     * message structure string with Json
     */
    private String messageStructure;
    /**
     * message to send
     */
    private String message;

    public PublishMsgRequest() {
    }

    public PublishMsgRequest(String topicUrn, String subject, Map<String, Object> tags, String messageTemplateName,
            String messageStructure, String message, Map<String, Object> requestParameterMap) {
        super();
        this.topicUrn = topicUrn;
        this.subject = subject;
        this.tags = tags;
        this.messageTemplateName = messageTemplateName;
        this.messageStructure = messageStructure;
        this.message = message;
    }

    @Override
    public String getRequestUrl() throws RuntimeException {
        if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
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
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_PUBLISH);
        logger.info("Request url is: " + sb.toString());
        return sb.toString();
    }

    /**
     * 三种消息发送方式
     * 
     * message message_structure message_template_name
     * 
     * 只需要设置其中一个，如果同时设置，生效的优先级为
     * 
     * message_structure >
     * 
     * message_template_name >
     * 
     * message url地址是一样的，在构造body参数时概据优先级确定哪一种发送方式
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(getSubject())) {
            requestParameterMap.put("subject", getSubject());
        }
        // message structure has highest priority
        if (StringUtils.isNoneBlank(getMessageStructure())) {
            requestParameterMap.put("message_structure", getMessageStructure());
            logger.info(requestParameterMap.toString());
            return requestParameterMap;
        }
        // message template has secondary priority
        if (StringUtils.isNoneBlank(getMessageTemplateName())) {
            if (Objects.isNull(getTags())) {
                logger.error("Tags is null");
                throw new RuntimeException();
            }
            requestParameterMap.put("message_template_name", getMessageTemplateName());
            requestParameterMap.put("tags", getTags());
            logger.info(requestParameterMap.toString());
            return requestParameterMap;
        }
        // common message ,least priority
        if (StringUtils.isBlank(getMessage())) {
            throw new RuntimeException("Message is null");
        }
        requestParameterMap.put("message", getMessage());
        logger.info("message: " + getMessage());
        return requestParameterMap;
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
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the tags
     */
    public Map<String, Object> getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
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
     * @return the messageStructure
     */
    public String getMessageStructure() {
        return messageStructure;
    }

    /**
     * @param messageStructure
     *            the messageStructure to set
     */
    public void setMessageStructure(String messageStructure) {
        this.messageStructure = messageStructure;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
