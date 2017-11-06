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

import com.smn.common.SmnConstants;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * the request to query message template detail
 *
 * @author huangqiong
 * @author yangyanping
 * @version 0.2
 */
public class QueryMessageTemplateDetailRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(QueryMessageTemplateDetailRequest.class);

    /**
     * template's unique identifier
     */
    private String messageTemplateId;

    /**
     * check parmas
     */
    private void validation() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List message template detail request projectId is null.");
            throw new NullPointerException("List message template detail request projectId is null.");
        }
        if (StringUtils.isBlank(messageTemplateId)) {
            LOGGER.error("Template getMessageTemplateId is null");
            throw new NullPointerException("Template getMessageTemplateId is null");
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
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE)
                .append(SmnConstants.URL_DELIMITER).append(messageTemplateId);

        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();
    }

    /**
     * @return the messageTemplateId
     */
    public String getMessageTemplateId() {
        return messageTemplateId;
    }

    /**
     * @param messageTemplateId the messageTemplateId to set
     */
    public void setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QueryMessageTemplateDetailRequest [messageTemplateId=").append(messageTemplateId)
                .append(", smnEndpoint=").append(smnEndpoint).append(", projectId=").append(projectId)
                .append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }

}
