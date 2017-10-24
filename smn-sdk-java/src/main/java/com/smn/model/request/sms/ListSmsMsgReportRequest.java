/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
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
     * 短信报告查询的开始时间
     */
    private String startTime;

    /**
     * 短信报告查询的结束时间
     */
    private String endTime;

    /**
     * signature identitier
     */
    private String signId;

    /**
     * 接收短信的电话号码
     */
    private String mobile;

    /**
     * 短信发送状态
     * 2 已发送无状态
     * 1 发送成功
     * 0 发送失败
     */
    private String status;

    /**
     * 短信发送状态查询的开始页
     */
    private int offset = 0;

    /**
     * 短信发送状态查询的每页数量限制
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

        // 设置get参数
        String params = getRequestParamString();
        if (StringUtils.isEmpty(params)) {
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

    @Override
    public String toString() {
        return "ListSmsMsgReportRequest{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", signId='" + signId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status='" + status + '\'' +
                ", offset=" + offset +
                ", limit='" + limit + '\'' +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
