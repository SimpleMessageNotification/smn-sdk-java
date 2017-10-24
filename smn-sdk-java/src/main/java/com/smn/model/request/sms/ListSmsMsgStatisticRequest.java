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

/***
 * sms statistic request message
 *
 * @author zhangyx
 * @version 0.7
 */
public class ListSmsMsgStatisticRequest extends AbstractSmnRequest {

    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ListSmsMsgStatisticRequest.class);

    /**
     * smn type field
     */
    public static final String TYPE = "type";

    /**
     * smn start_date field
     */
    public static final String START_DATE = "start_date";

    /**
     * smn end_date field
     */
    public static final String END_DATE = "end_date";

    /**
     * signature identitier
     */
    private String signId;

    /**
     * 查询类型 {@link SmnConstants#HOURLY} {@link SmnConstants#DAILY}
     * daily按天查询
     * hourly按小时查询
     */
    private String type;

    /**
     * 短信状态查询的起始时间，按小时查询该字段不填
     * 格式:yyyy-MM-dd
     * 样例:2016-08-03
     */
    private String startDate;

    /**
     * 短信状态查询的结束时间，按小时查询该字段不填
     * 格式:yyyy-MM-dd
     * 样例:2016-08-03
     */
    private String endDate;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List sms msg statistic request projectId is null.");
            throw new RuntimeException("List sms msg statistic request projectId is null.");
        }

        if (StringUtils.isBlank(type)) {
            LOGGER.error("List sms msg statistic request type is null");
            throw new RuntimeException("List sms msg statistic request type is null");
        }

        if (SmnConstants.DAILY.equals(type) && StringUtils.isBlank(startDate)) {
            LOGGER.error("List sms msg statistic request startDate is null");
            throw new RuntimeException("List sms msg statistic request startDate is null");
        }

        if (SmnConstants.DAILY.equals(type) && StringUtils.isBlank(endDate)) {
            LOGGER.error("List sms msg statistic request endDate is null");
            throw new RuntimeException("List sms msg statistic request endDate is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.STATISTIC);

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
    @Override
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

        if (!StringUtils.isBlank(type)) {
            nameValuePairs.add(new BasicNameValuePair(TYPE, type));
        }

        if (!StringUtils.isBlank(startDate)) {
            nameValuePairs.add(new BasicNameValuePair(START_DATE, startDate));
        }

        if (!StringUtils.isBlank(endDate)) {
            nameValuePairs.add(new BasicNameValuePair(END_DATE, endDate));
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ListSmsMsgStatisticRequest{" +
                "signId='" + signId + '\'' +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}
