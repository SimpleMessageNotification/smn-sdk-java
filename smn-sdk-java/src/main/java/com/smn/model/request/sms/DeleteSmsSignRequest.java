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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * delete sms sign request message
 *
 * @author zhangyx
 * @version 0.9
 */
public class DeleteSmsSignRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteSmsSignRequest.class);

    /**
     * signature identitier
     */
    private String signId;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("Delete sms sign request projectId is null.");
            throw new RuntimeException("Delete sms sign request projectId is null.");
        }

        if (StringUtils.isBlank(signId)) {
            LOGGER.error("SignId is null");
            throw new RuntimeException("SignId is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
                .append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMS_SIGNATURE)
                .append(SmnConstants.URL_DELIMITER).append(signId);

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

    @Override
    public String toString() {
        return "DeleteSmsSignRequest{" +
                "signId='" + signId + '\'' +
                ", smnEndpoint='" + smnEndpoint + '\'' +
                ", projectId='" + projectId + '\'' +
                ", xAuthToken='" + xAuthToken + '\'' +
                '}';
    }
}