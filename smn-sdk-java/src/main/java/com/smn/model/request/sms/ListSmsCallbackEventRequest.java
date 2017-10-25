package com.smn.model.request.sms;

import com.smn.common.AccessPolicyType;
import com.smn.common.SmnConstants;
import com.smn.common.SmsCallbackEventType;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the request to list the sms callback event
 *
 * @author zhangyx
 * @version 0.7
 */
public class ListSmsCallbackEventRequest extends AbstractSmnRequest {
    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ListSmsCallbackEventRequest.class);

    /**
     * 短信回调事件类型，取值
     * {@link SmsCallbackEventType#SMS_CALLBACK_SUCCESS}
     * {@link SmsCallbackEventType#SMS_CALLBACK_SUCCESS}
     * {@link SmsCallbackEventType#SMS_CALLBACK_REPLY}
     */
    private String eventType;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List sms event request projectId is null.");
            throw new RuntimeException("List sms event request projectId is null.");
        }

        if(!StringUtils.isBlank(projectId) && !isValidEventType()){
            LOGGER.error("List sms event request event_type is invalid.");
            throw new RuntimeException("List sms event request event_type is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.CALLBACK_REQUEST);

        // 设置get参数
        String params = getRequestParamString();
        if (!StringUtils.isEmpty(params)) {
            sb.append("?").append(params);
        }
        LOGGER.info("Request url is {}. ", sb.toString());
        return sb.toString();
    }

    /**
     * check eventType is invalid
     */
    private boolean isValidEventType() {
        if (SmsCallbackEventType.SMS_CALLBACK_FAIL.equals(eventType)
                || SmsCallbackEventType.SMS_CALLBACK_REPLY.equals(eventType)
                || SmsCallbackEventType.SMS_CALLBACK_SUCCESS.equals(eventType)) {
            return true;
        }
        return false;
    }

    /**
     * build and get request parameters
     */
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    /**
     * obtain the get request param
     *
     * @return the param string
     */
    private String getRequestParamString() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (!StringUtils.isBlank(eventType)) {
            nameValuePairs.add(new BasicNameValuePair(SmnConstants.EVENT_TYPE, eventType));
        }

        String param = "";
        if (!nameValuePairs.isEmpty()) {
            try {
                param = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("UTF-8")));
            } catch (IOException e) {
                throw new RuntimeException("get request param error");
            }
        }
        return param;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "ListSmsCallbackEventRequest{" +
                "eventType='" + eventType + '\'' +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
