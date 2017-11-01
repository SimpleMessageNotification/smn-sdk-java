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
 */
package com.smn.service;

import com.smn.common.HttpMethod;
import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import com.smn.common.utils.HttpUtil;
import com.smn.common.ClientConfiguration;
import com.smn.model.AbstractSmnRequest;
import com.smn.model.AuthenticationBean;
import com.smn.service.impl.IAMServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
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
     * client config
     */
    protected ClientConfiguration clientConfiguration;

    /**
     * 无参构造函数
     */
    public AbstractCommonService() {

    }

    /**
     * 给定iamService和smnConfiguration构造实例
     *
     * @param iamService       the iamService to set
     * @param smnConfiguration the smnConfiguration to set
     */
    public AbstractCommonService(IAMService iamService, SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        this.iamService = iamService;
        this.smnConfiguration = smnConfiguration;
        this.clientConfiguration = clientConfiguration;
    }

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
            smnConfiguration = new SmnConfiguration();
        }

        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        if (iamService == null) {
            String iamUrl = new StringBuilder().append(SmnConstants.HTTPS_PREFFIX)
                    .append(smnConfiguration.getIamEndpoint()).append(SmnConstants.URL_DELIMITER)
                    .append(SmnConstants.IAM_URI).toString();
            LOGGER.info("Iam url is{}.", iamUrl);
            iamService = new IAMServiceImpl(smnConfiguration.getUserName(), smnConfiguration.getPassword(),
                    smnConfiguration.getDomainName(), smnConfiguration.getRegionId(), iamUrl, clientConfiguration);
        }

        return iamService;
    }

    /**
     * request for authenticationBean
     *
     * @return AuthenticationBean
     * {@value} authToken
     * {@value} projectId
     * {@value} expiresAt
     * {@value} expiresTime
     */
    protected AuthenticationBean getAuthenticationBean() {
        return getIAMService().getAuthenticationBean();
    }

    /**
     * build request header
     *
     * @param requestHeader {@value}region
     *                      {@value}X-Project-Id
     *                      {@value}X-Auth-Token
     *                      {@value}content-type
     */
    protected void buildRequestHeader(Map<String, String> requestHeader) {
        if (null == getAuthenticationBean()) {
            throw new RuntimeException("The authenticationBean is null.");
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

    /**
     * send http request
     *
     * @param smnRequest the request to send
     * @return response {@link HttpResponse}
     * @throws Exception connect error throw exception
     */
    protected <Request extends AbstractSmnRequest> HttpResponse sendRequest(Request smnRequest, HttpMethod httpMethod) throws Exception {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
        Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
        String projectId = getAuthenticationBean().getProjectId();
        String smnEndpoint = smnConfiguration.getSmnEndpoint();
        smnRequest.setSmnEndpoint(smnEndpoint);
        smnRequest.setProjectId(projectId);
        String url = buildRequestUrl(smnRequest.getRequestUri());
        buildRequestHeader(requestHeader);

        HttpResponse httpResponse = HttpUtil.sendRequest(requestHeader, requestParam, url, httpMethod, clientConfiguration);

        return httpResponse;
    }
}
