/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.service;

import com.smn.common.*;
import com.smn.common.utils.HttpUtil;
import com.smn.common.utils.JsonUtil;
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
     * constructor
     */
    public AbstractCommonService() {
        this.clientConfiguration = new ClientConfiguration();
    }

    /**
     * constructor
     *
     * @param iamService          the iamService to set
     * @param smnConfiguration    the smnConfiguration to set
     * @param clientConfiguration the clientConfiguration to set
     */
    public AbstractCommonService(IAMService iamService, SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        this.iamService = iamService;
        this.smnConfiguration = smnConfiguration;
        this.clientConfiguration = clientConfiguration;
    }

    /**
     * (non-Javadoc)
     *
     * @see CommonService#setSmnConfiguration(SmnConfiguration)
     */
    public void setSmnConfiguration(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }

    /**
     * get iamService
     *
     * @return iamService
     */
    private IAMService getIAMService() {

        if (smnConfiguration == null) {
            smnConfiguration = new SmnConfiguration();
        }

        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        if (iamService == null) {
            iamService = new IAMServiceImpl(smnConfiguration, clientConfiguration);
        }

        return iamService;
    }

    /**
     * request for authenticationBean
     *
     * @return AuthenticationBean
     * {@link AuthenticationBean} the info of the authentication
     */
    private AuthenticationBean getAuthenticationBean() {
        return getIAMService().getAuthenticationBean();
    }

    /**
     * build request header
     *
     * @param requestHeader contains the following parameters:
     *                      <code>region</code>
     *                      <code>X-Project-Id</code>
     *                      <code>X-Auth-Token</code>
     */
    private void buildRequestHeader(Map<String, String> requestHeader) {
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
     * @param uri the uri to request
     * @return String url
     */
    private String buildSmnRequestUrl(String uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.HTTPS_PREFFIX).append(smnConfiguration.getSmnEndpoint()).append(uri);
        return sb.toString();
    }

    /**
     * send http request
     *
     * @param smnRequest the request to send
     * @param httpMethod the http method
     * @return response {@link HttpResponse}
     * @throws Exception connect error throw exception
     */
    protected <Request extends AbstractSmnRequest> HttpResponse sendRequest(Request smnRequest, HttpMethod httpMethod) throws Exception {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
        String bodyString = JsonUtil.getJsonStringByMap(smnRequest.getRequestParameterMap());
        smnRequest.setProjectId(getAuthenticationBean().getProjectId());
        String url = buildSmnRequestUrl(smnRequest.getRequestUri());
        buildRequestHeader(requestHeader);

        HttpResponse httpResponse = HttpUtil.sendRequest(requestHeader, bodyString, url, httpMethod, clientConfiguration);
        return httpResponse;
    }
}
