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
package com.smn.model.request.subscription;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.SmnConstants;
import com.smn.common.utils.ValidationUtil;
import com.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 * @date 2017年8月14日 下午4:32:42
 * @version 0.1
 * @author yangyanping
 * @date 2017年8月24日 下午4:32:42
 * @version 0.2
 */
public class ListSubscriptionsByTopicRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(ListSubscriptionsByTopicRequest.class);

    /**
     * paging list's starting page,default 0
     */
    private int offset = 0;
    /**
     * max returned items for a request,default 100
     */
    private int limit = 100;
    /**
     * topic's unique resource identifier
     */
    private String topicUrn;
    
    /**
     * 校验参数
     */
    private void validate(){
        
        if (!ValidationUtil.validateTopicUrn(topicUrn)){
            throw new RuntimeException("topic urn is illegal");
        }
        if (StringUtils.isBlank(projectId)) {
            LOGGER.error("List subscription by topic request projectId is null.");
            throw new RuntimeException("List subscription by topic request projectId is null.");
        }
    }

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() throws RuntimeException {
        validate();
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(getProjectId()).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(getTopicUrn()).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUBSCRIPTIONS);

        if (offset > 0) {
            sb.append("?offset=" + offset);
        } else {
            sb.append("?offset=" + "0");
        }

        if (limit> 0 && limit <= 100) {
            sb.append("&limit=" + limit);
        } else {
            sb.append("&limit=").append("100");
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
        if (offset > 0) {
            this.offset = offset;
        }
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
        
        if (100 >= limit && limit > 0) {
            this.limit = limit;
        }
    }

    /**
     * @return the topicUrn
     */
    public String getTopicUrn() {
        
        return topicUrn;
    }

    /**
     * @param topicUrn
     *            the topicUrn to set
     */
    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
   
        StringBuilder builder = new StringBuilder();
        builder.append("ListSubscriptionsByTopicRequest [offset=").append(offset).append(", limit=").append(limit)
                .append(", topicUrn=").append(topicUrn).append(", smnEndpoint=").append(smnEndpoint)
                .append(", projectId=").append(projectId).append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }

}
