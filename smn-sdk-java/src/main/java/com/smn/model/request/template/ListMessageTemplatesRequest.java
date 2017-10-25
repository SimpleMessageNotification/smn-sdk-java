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
package com.smn.model.request.template;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.SmnConstants;
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 * @author yangyanping
 * @author zhangyx
 * @version 0.2
 * @version 0.8
 * @date 2017年8月2日
 * @date 2017年8月25日
 */
public class ListMessageTemplatesRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(ListMessageTemplatesRequest.class);

    /**
     * protocol
     */
    private String protocol;
    /**
     * template name
     */
    private String fuzzyMessageTemplateName;
    /**
     * paging list's starting page,default 0
     */
    private int offset = 0;
    /**
     * max returned items for a request,default 100
     */
    private int limit = 100;

    /**
     * check params
     */
    private void validation() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List message template request projectId is null.");
            throw new NullPointerException("List message template request projectId is null.");
        }
        if (!ValidationUtil.validateProtocol(protocol)) {
            LOGGER.error("List message template request protocol is invalid.");
            throw new NullPointerException("List message template request protoclo is invalid.");
        }
        if (!ValidationUtil.validateTemplateName(fuzzyMessageTemplateName)) {
            LOGGER.error("List message template request fuzzyMessageTemplateName is invalid.");
            throw new NullPointerException("List message template request fuzzyMessageTemplateName is invalid.");
        }
        if (!ValidationUtil.validateOffset(offset)) {
            LOGGER.error("List message template request offset is invalid.");
            throw new NullPointerException("List message template request offset is invalid.");
        }
        if (!ValidationUtil.validateLimit(limit)) {
            LOGGER.error("List message template request limit is invalid.");
            throw new NullPointerException("List message template request limit is invalid.");
        }
    }

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() throws RuntimeException {

        validation();
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE);

        // 设置get参数
        String params = getRequestParamString();
        if (!StringUtils.isEmpty(params)) {
            sb.append("?").append(params);
        }

        return sb.toString();
    }

    /**
     * obtain the get request param
     *
     * @return the param string
     */
    private String getRequestParamString() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (!StringUtils.isBlank(protocol)) {
            nameValuePairs.add(new BasicNameValuePair(SmnConstants.SMN_PROTOCOL, protocol));
        }

        if (!StringUtils.isBlank(fuzzyMessageTemplateName)) {
            nameValuePairs.add(new BasicNameValuePair(SmnConstants.SMN_FUZZY_MESSAGE_TEMPLATE_NAME, fuzzyMessageTemplateName));
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
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the fuzzyMessageTemplateName
     */
    public String getFuzzyMessageTemplateName() {
        return fuzzyMessageTemplateName;
    }

    /**
     * @param fuzzyMessageTemplateName the fuzzyMessageTemplateName to set
     */
    public void setFuzzyMessageTemplateName(String fuzzyMessageTemplateName) {
        this.fuzzyMessageTemplateName = fuzzyMessageTemplateName;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ListMessageTemplatesRequest [protocol=").append(protocol).append(", fuzzyMessageTemplateName=")
                .append(fuzzyMessageTemplateName).append(", offset=").append(offset).append(", limit=").append(limit)
                .append(", smnEndpoint=").append(smnEndpoint).append(", projectId=").append(projectId)
                .append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }
}
