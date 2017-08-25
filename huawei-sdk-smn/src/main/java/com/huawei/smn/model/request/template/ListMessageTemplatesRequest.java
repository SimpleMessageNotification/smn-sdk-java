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
package com.huawei.smn.model.request.template;

import java.util.HashMap;
import java.util.Map;

import com.huawei.smn.common.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 * @author yangyanping
 * @date 2017年8月25日
 * @version 0.2
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
    private String messageTemplateName;
    /**
     * paging list's starting page,default 0
     */
    private int offset = 0;
    /**
     * max returned items for a request,default 100
     */
    private int limit = 100;

    /**
     * smn endpoint
     */
    private String smnEndpoint;

    /**
     * project id
     */
    private String projectId;

    /**
     * xAuthToken
     */
    private String xAuthToken;

    /**
     * check params
     */
    private void validation(){
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List message template request projectId is null.");
            throw new NullPointerException("List message template request projectId is null.");
        }
        if (!ValidationUtil.validateProtocol(protocol)){
            LOGGER.error("List message template request protocol is invalid.");
            throw new NullPointerException("List message template request protoclo is invalid.");
        }
        if(!ValidationUtil.validateTemplateName(messageTemplateName)){
            LOGGER.error("List message template request messageTemplateName is invalid.");
            throw new NullPointerException("List message template request messageTemplateName is invalid.");
        }
        if(!ValidationUtil.validateOffset(offset)){
            LOGGER.error("List message template request offset is invalid.");
            throw new NullPointerException("List message template request offset is invalid.");
        }
        if (!ValidationUtil.validateLimit(limit)){
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
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset
     *            the offset to set
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
     * @param limit
     *            the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the smnEndpoint
     */
    public String getSmnEndpoint() {
        return smnEndpoint;
    }

    /**
     * @param smnEndpoint
     *            the smnEndpoint to set
     */
    public void setSmnEndpoint(String smnEndpoint) {
        this.smnEndpoint = smnEndpoint;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the xAuthToken
     */
    public String getxAuthToken() {
        return xAuthToken;
    }

    public void setxAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ListMessageTemplatesRequest [protocol=").append(protocol).append(", messageTemplateName=")
                .append(messageTemplateName).append(", offset=").append(offset).append(", limit=").append(limit)
                .append(", smnEndpoint=").append(smnEndpoint).append(", projectId=").append(projectId)
                .append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }

}
