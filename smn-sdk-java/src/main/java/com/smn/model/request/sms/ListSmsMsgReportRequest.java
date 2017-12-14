/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.model.request.sms;

import com.smn.common.SmnConstants;
import com.smn.common.utils.ValidationUtil;
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
 *  sms msg report request message
 *
 *  @author zhangyx
 *  @version 0.7
 */
public class ListSmsMsgReportRequest extends AbstractSmnRequest {

    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ListSmsMsgReportRequest.class);

    /**
     * smn start_time field
     */
    public static final String START_TIME = "start_time";

    /**
     * smn end_time field
     */
    public static final String END_TIME = "end_time";

    /**
     * smn mobile field
     */
    public static final String MOBILE = "mobile";

    /**
     * smn status field
     */
    public static final String STATUS = "status";

    /**
     * query start time
     */
    private String startTime;

    /**
     * query end time
     */
    private String endTime;

    /**
     * signature id
     */
    private String signId;

    /**
     * mobile
     */
    private String mobile;

    /**
     * sms send status
     * 2 sent without state
     * 1 send success
     * 0 send fail
     */
    private String status;

    /**
     * the offset of the query
     */
    private int offset = 0;

    /**
     * per page size limit
     */
    private int limit = 100;

    /**
     * build and get request url
     */
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List sms msg report request projectId is null.");
            throw new RuntimeException("List sms msg report request projectId is null.");
        }

        if(!ValidationUtil.validateOffset(offset)){
            LOGGER.error("List sms msg report request offset is invalid.");
            throw new RuntimeException("List sms msg report request offset is invalid.");
        }

        if (!ValidationUtil.validateLimit(limit)){
            LOGGER.error("List sms msg report request limit is invalid.");
            throw new RuntimeException("List sms msg report request limit is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.REPORT);

        // set the request parameter
        String params = getRequestParamString();
        if (!StringUtils.isEmpty(params)) {
            sb.append("?").append(params);
        }
        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();
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
        if (!StringUtils.isBlank(signId)) {
            nameValuePairs.add(new BasicNameValuePair(SmnConstants.SIGN_ID, signId));
        }

        if (!StringUtils.isBlank(startTime)) {
            nameValuePairs.add(new BasicNameValuePair(START_TIME, startTime));
        }

        if (!StringUtils.isBlank(endTime)) {
            nameValuePairs.add(new BasicNameValuePair(END_TIME, endTime));
        }

        if (!StringUtils.isBlank(mobile)) {
            nameValuePairs.add(new BasicNameValuePair(MOBILE, mobile));
        }

        if (!StringUtils.isBlank(status)) {
            nameValuePairs.add(new BasicNameValuePair(STATUS, status));
        }

        nameValuePairs.add(new BasicNameValuePair(SmnConstants.OFFSET, String.valueOf(offset)));
        nameValuePairs.add(new BasicNameValuePair(SmnConstants.LIMIT, String.valueOf(limit)));

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
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the signId
     */
    public String getSignId() {
        return signId;
    }

    /**
     * @param signId the signId to set
     */
    public void setSignId(String signId) {
        this.signId = signId;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ListSmsMsgReportRequest [startTime=").append(startTime)
                .append(", endTime=").append(endTime)
                .append(", signId=").append(signId)
                .append(", mobile=").append(mobile)
                .append(", status=").append(status)
                .append(", offset=").append(offset)
                .append(", limit=").append(limit)
                .append(", projectId=").append(projectId)
                .append("]");
        return builder.toString();
    }
}
