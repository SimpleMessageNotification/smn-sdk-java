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
package com.huawei.smn.model.request.topic;

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
 * Query topic
 * 
 * @author huangqiong
 *
 */
public class ListTopicsRequest extends AbstractSmnRequest {
    private static Logger logger = LoggerFactory.getLogger(ListTopicsRequest.class);
    /**
     * paging list's starting page,default 0
     */
    private int offset;
    /**
     * max returned items for a request,default 100
     */
    private int limit;

    public String getRequestUrl() throws RuntimeException {
        if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
            logger.error("project id is null");
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
                .append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
                .append(SmnConstants.SMN_TOPIC_URI);

        if (getOffset() > 0) {
            sb.append("?offset=" + getOffset());
        } else {
            sb.append("?offset=" + "0");
        }
        if (getLimit() > 0 && getLimit() < 100) {
            sb.append("&limit=" + getLimit());
        } else {
            sb.append("&limit=").append("100");
        }
        logger.info("Request url is: " + sb.toString());
        return sb.toString();
    }

    @Override
    public Map<String, Object> getRequestParameterMap() throws RuntimeException {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        if (offset > 0) {
            this.offset = offset;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 0 && limit < 100) {
            this.limit = limit;
        }
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
