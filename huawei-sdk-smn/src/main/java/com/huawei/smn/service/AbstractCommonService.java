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
 * @date 2017年8月3日 下午5:33:12
 * @version 0.1
 * 
 */
package com.huawei.smn.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AuthenticationBean;
import com.huawei.smn.service.impl.IAMServiceImpl;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:33:12
 * @version 0.1
 */
public abstract class AbstractCommonService implements CommonService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommonService.class);

    /**
     * smn config
     */
    protected SmnConfiguration smnConfiguration;

    /**
     * iam service
     */
    protected IAMService iamService;

    /**
     * authentication bean
     */
    protected AuthenticationBean authenticationBean;

    /*
     * (non-Javadoc)
     * @see
     * com.huawei.smn.service.CommonService#setSmnConfiguration(com.huawei.smn.
     * common.SmnConfiguration)
     */
    public void setSmnConfiguration(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }

    /**
     * get iamService
     * 
     * @return iamService
     */
    protected IAMService getIAMService() {

        if (smnConfiguration == null) {
            LOGGER.error("SmnConfiguration is null.");
            throw new RuntimeException("SmnConfiguration is null.");
        }
        if (iamService == null) {
            String iamUrl = new StringBuilder().append(SmnConstants.HTTPS_PREFFIX)
                    .append(smnConfiguration.getIamEndpoint()).append(SmnConstants.URL_DELIMITER)
                    .append(SmnConstants.IAM_URI).toString();
            LOGGER.info("Iam url: " + iamUrl);
            iamService = new IAMServiceImpl(smnConfiguration.getUserName(), smnConfiguration.getPassword(),
                    smnConfiguration.getDomainName(), smnConfiguration.getRegionId(), iamUrl);
        }

        return iamService;
    }

    /**
     * request for authenticationBean
     * 
     * @return AuthenticationBean
     *         {@value} authToken
     *         {@value} projectId
     *         {@value} expiresAt
     *         {@value} expiresTime
     */
    protected AuthenticationBean getAuthenticationBean() {
        // 获取authenticationBean线程安全
        if (null == authenticationBean) {
            synchronized (this) {
                if (authenticationBean == null) {
                    authenticationBean = iamService.getAuthentication();
                }
            }
        }
        return authenticationBean;
    }

    /**
     * build request header
     * 
     * @param requestHeader
     *            {@value}region
     *            {@value}X-Project-Id
     *            {@value}X-Auth-Token
     *            {@value}content-type
     */
    protected void buildRequestHeader(Map<String, String> requestHeader) {
        if (null == getAuthenticationBean()) {
            throw new RuntimeException("The authenticationBean is null");
        }
        requestHeader.put(SmnConstants.REGION_TAG, smnConfiguration.getRegionId());
        requestHeader.put(SmnConstants.X_PROJECT_ID, getAuthenticationBean().getProjectId());
        requestHeader.put(SmnConstants.X_AUTH_TOKEN, getAuthenticationBean().getAuthToken());
    }

    /**
     * build request url
     * 
     * @param uri
     * @return String url
     */
    protected String buildRequestUrl(String uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.HTTPS_PREFFIX).append(smnConfiguration.getSmnEndpoint()).append(uri);
        return sb.toString();
    }
}
