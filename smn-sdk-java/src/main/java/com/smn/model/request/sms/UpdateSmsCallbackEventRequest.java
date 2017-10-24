package com.smn.model.request.sms;

import com.smn.common.SmnConstants;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UpdateSmsCallbackEventRequest extends AbstractSmnRequest {

    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(UpdateSmsCallbackEventRequest.class);

    /**
     * 查询结果列表
     */
    private List<SmsCallback> callbacks;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Update sms event request projectId is null.");
            throw new RuntimeException("Update sms event request projectId is null.");
        }

        if(!validate()){
            LOGGER.error("Update sms event request callbacks is invalid.");
            throw new RuntimeException("Update sms event request callbacks is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.CALLBACK_REQUEST);

        LOGGER.info("Request url is {}. ", sb.toString());
        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        List<Map<String, String>> callBackList = new ArrayList<Map<String, String>>();
        for (SmsCallback smsCallback : callbacks) {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put(SmnConstants.TOPIC_URN, smsCallback.getTopicUrn());
            tempMap.put(SmnConstants.EVENT_TYPE, smsCallback.getEventType());
            callBackList.add(tempMap);
        }
        requestParameterMap.put(SmnConstants.CALLBACK_FIELD, callBackList);
        return requestParameterMap;
    }

    /**
     * 验证参数的是否有效
     *
     * @return 参数有效返回true, 无效返回false
     */
    private boolean validate() {
        if (null == callbacks || callbacks.size() == 0) {
            return false;
        }
        for (SmsCallback smsCallback : callbacks) {
            if (StringUtils.isEmpty(smsCallback.getEventType())
                    || StringUtils.isEmpty(smsCallback.getTopicUrn())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the callback list
     */
    public List<SmsCallback> getCallbacks() {
        return callbacks;
    }

    /**
     * @param callbacks the callback list to set
     */
    public void setCallbacks(List<SmsCallback> callbacks) {
        this.callbacks = callbacks;
    }

    /**
     * toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "UpdateSmsCallbackEventRequest{" +
                "callbacks=" + callbacks +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
