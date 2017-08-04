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
/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:41:10
 * @version 0.1
 * 
 */
package com.huawei.smn.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.request.sms.SmsPublishRequest;
import com.huawei.smn.service.AbstractCommonService;
import com.huawei.smn.service.SmsService;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:41:10
 * @version 0.1
 */
public class SmsServiceImpl extends AbstractCommonService implements SmsService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * smn host url
     */
    private String smnEndpoint;

    /**
     * project id
     */
    private String projectId;

    /**
     * send sms directly
     * 
     * @param smnRequest
     * @return {@link Map}
     *         {@value}request_id
     *         {@value}status
     * @throws RuntimeException
     */
    public Map<String, Object> smsPublish(SmsPublishRequest smnRequest) throws RuntimeException {
        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Failed to send sms.", e);
            throw new RuntimeException("Failed to send sms.", e);
        }
    }
}
