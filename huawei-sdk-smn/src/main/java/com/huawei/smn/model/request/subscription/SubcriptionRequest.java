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
package com.huawei.smn.model.request.subscription;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.common.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * @author huangqiong
 * @date 2017年8月14日 下午4:33:56
 * @version 0.1
 * @author yangyanping
 * @date 2017年8月24日 下午4:33:56
 * @version 0.2
 */
public class SubcriptionRequest extends AbstractSmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(SubcriptionRequest.class);

    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * protocol
     */
    private String protocol;

    /**
     * message access point
     */
    private String endpoint;

    /**
     * remark
     */
    private String remark;

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

    /*
     * (non-Javadoc)
     * @see com.huawei.smn.model.AbstractSmnRequest#getRequestUri()
     */
    @Override
    public String getRequestUri() {
        validate();
        if (StringUtils.isBlank(projectId) || StringUtils.isBlank(smnEndpoint)) {
            LOGGER.error("Subcription request projectId is null.");
            throw new NullPointerException("Subcription request projectId is null.");
        }
        if (StringUtils.isBlank(topicUrn)) {
            LOGGER.error("Subcription request topicUrn is null.");
            throw new NullPointerException("Subcription request topicUrn is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION).append(SmnConstants.URL_DELIMITER)
                .append(projectId).append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER)
                .append(topicUrn).append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUBSCRIPTIONS);

        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();

    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        
        if (!ValidationUtil.validateProtocol(protocol)) {
            LOGGER.error("Protocol is not valid.");
            throw new RuntimeException("Protocol is not valid.");
        }

        if (StringUtils.isBlank(endpoint)) {
            LOGGER.error("Endpoint is null.");
            throw new RuntimeException("Endpoint is null.");
        }

        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        requestParameterMap.put(SmnConstants.SMN_PROTOCOL, protocol);
        requestParameterMap.put(SmnConstants.ENDPOINT, endpoint);
        requestParameterMap.put(SmnConstants.SMN_SUBCRIBE_REMARK, remark);
        return requestParameterMap;
    }

    /**
     * 校验参数
     */
    private void validate(){
        if(!ValidationUtil.validateTopicUrn(topicUrn)){
            throw new RuntimeException(" subscribe request topic_urn is null.");
        }
    
        if (!ValidationUtil.validateEndPoint(endpoint,protocol)){
            throw  new RuntimeException("subscribe request smnEndpoint is illegal");
        }
        if(!checkRemark(remark)){
            throw  new RuntimeException("subscribe request remark is illegal");
        }
    
    }
    /**
     * check remark
     * @param remark
     * @return boolean
     */
    private boolean checkRemark(String remark){
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        if(remark == null){
            return  true;
        }
        try {
            byte[] b = remark.getBytes("utf-8");
            if (b.length > smnConfiguration.getMaxRemarkLength()){
                return  false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  true;
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
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint
     *            the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    /**
     * @param xAuthToken
     *            the xAuthToken to set
     */
    public void setxAuthToken(String xAuthToken) {
        
        this.xAuthToken = xAuthToken;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        validate();
        StringBuilder builder = new StringBuilder();
        builder.append("SubcriptionRequest [topicUrn=").append(topicUrn).append(", protocol=").append(protocol)
                .append(", remark=").append(remark).append(", smnEndpoint=").append(smnEndpoint).append(", projectId=")
                .append(projectId).append(", xAuthToken=").append(xAuthToken).append("]");
        return builder.toString();
    }
}
