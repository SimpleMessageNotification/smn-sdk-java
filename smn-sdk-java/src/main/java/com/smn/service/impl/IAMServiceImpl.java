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
package com.smn.service.impl;

import com.smn.common.ClientConfiguration;
import com.smn.common.SmnConfiguration;
import com.smn.common.SmnConstants;
import com.cloud.sdk.http.HttpMethodName;
import com.smn.common.*;
import com.smn.common.utils.DateUtil;
import com.smn.common.utils.HttpUtil;
import com.smn.common.utils.JsonUtil;
import com.smn.model.AuthenticationBean;
import com.smn.model.request.iam.GetProjectIdsRequest;
import com.smn.service.IAMService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import com.smn.signer.Signer;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class IAMServiceImpl implements IAMService {
    private static Logger LOGGER = LoggerFactory.getLogger(IAMServiceImpl.class);

    /**
     * the URL of IAM
     */
    private String iamUrl;

    /**
     * Token expired 5 minutes in advance
     */
    private long expiredInterval = 5 * 60 * 1000;

    /**
     * the request to get token
     */
    private String requestMessage = null;

    /**
     * cache authentication bean
     */
    private AuthenticationBean authenticationBean;

    /**
     * client config
     */
    private ClientConfiguration clientConfiguration;

    /**
     * smn configuration
     */
    private SmnConfiguration smnConfiguration;

    /**
     * iam token url
     */
    private String iamTokenUrl;
    private ClientConfiguration clientConfiguration;

    /**
     * cache projectId
     */
    private String projectId;

    /**
     * smn config
     */
    private SmnConfiguration smnConfiguration;

    /**
     * constructor
     *
     * @param smnConfiguration    smn configuration
     * @param iamUrl              iamUrl
     * @param smnConfiguration    the smn configuration
     * @param clientConfiguration the client configuration
     */
    public IAMServiceImpl(SmnConfiguration smnConfiguration, String iamUrl, ClientConfiguration clientConfiguration) {
        setIamUrl(iamUrl);
        this.clientConfiguration = clientConfiguration;
        this.smnConfiguration = smnConfiguration;
    public IAMServiceImpl(SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = smnConfiguration;
        this.clientConfiguration = clientConfiguration;

        requestMessage = "{" +
                "    \"auth\": {" +
                "        \"identity\": {" +
                "            \"methods\": [" +
                "                \"password\"" +
                "            ]," +
                "            \"password\": {" +
                "                \"user\": {" +
                "                    \"name\": \"" + smnConfiguration.getUserName() + "\"," +
                "                    \"password\": \"" + smnConfiguration.getPassword() + "\"," +
                "                    \"domain\": {" +
                "                        \"name\": \"" + domainName + "\"" +
                "                    }" +
                "                }" +
                "            }" +
                "        }," +
                "        \"scope\": {" +
                "            \"project\": {" +
                "                \"name\": \"" + smnConfiguration.getRegionId() + "\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";

        iamTokenUrl = new StringBuilder().append(SmnConstants.HTTPS_PREFFIX).append(smnConfiguration.getIamEndpoint())
                .append(SmnConstants.URL_DELIMITER).append(IAM_TOKEN_URI).toString();
        LOGGER.info("Iam token url is{}.", iamTokenUrl);

    }

    /**
     * Obtain authorization information from the IAM service, which includes
     * projectId, user token, and token expiration time
     *
     * @return {@link AuthenticationBean} User token information
     * @throws RuntimeException Failed to get token, then ran out of the exception
     */
    public AuthenticationBean getAuthentication() throws RuntimeException {

        AuthenticationBean authenticationBean = null;
        try {
            authenticationBean = postForIamToken(iamTokenUrl, requestMessage, clientConfiguration);
            // parse time
            Date tempDate = DateUtil.parseDate(authenticationBean.getExpiresAt());
            authenticationBean.setExpiresTime(tempDate.getTime() - expiredInterval);
            return authenticationBean;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Faied to get token from iam.e:{}", e);
            throw new RuntimeException("Failed to get token from iam.", e);
        }
    }

    public String getProjectId() throws Exception {
        if(!StringUtils.isEmpty(projectId)) {
            return projectId;
        }

        GetProjectIdsRequest request = new GetProjectIdsRequest();
        request.setName(smnConfiguration.getRegionId());
        Map<String, String> requestHeaderMap = request.getRequestHeaderMap();
        String url = buildRequestUrl(request.getRequestUri());

        if (SmnConfiguration.AKSK_AUTH_TYPE.equals(smnConfiguration.getAuthType())) {
            Signer signer = new Signer(smnConfiguration, "IAM");
            Map<String, String> headers = signer.get(new URL(url), HttpMethodName.GET);
            for (String key : headers.keySet()) {
                if (key.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH.toString())) {
                    continue;
                }
                requestHeaderMap.put(key, headers.get(key));
            }
            HttpResponse httpResponse = HttpUtil.sendRequest(requestHeaderMap,
                    request.getRequestParameterMap(), url, HttpMethod.GET, clientConfiguration);
            projectId = (String)((HashMap)((ArrayList)httpResponse.getBody().get("projects")).get(0)).get("id");
        }
        return projectId;
    }

    /**
     * Obtain authorization information
     * <p>
     * if exist, return
     * or get from iam service
     *
     * @return {@link AuthenticationBean} User token information
     */
    public AuthenticationBean getAuthenticationBean() {
        // get authenticationBean thread safe
        if (null == authenticationBean || authenticationBean.isExpired()) {
            synchronized (this) {
                if (authenticationBean == null || authenticationBean.isExpired()) {
                    authenticationBean = getAuthentication();
                }
            }
        }
        return authenticationBean;
    }

    /**
     * Get auth info from IAM service
     *
     * @param iamUrl              the URL of IAM service
     * @param bodyMessage         the body of message
     * @param clientConfiguration the client configuration
     * @return {@code AuthBean}
     * @throws Exception Failed to get IAM information, throw an exception
     */
    public long getExpiredInterval() {
        return expiredInterval;
    }

    /**
     * @param expiredInterval the expiredInterval to set
     */
    public void setExpiredInterval(long expiredInterval) {
        this.expiredInterval = expiredInterval;
    }

    /**
     * @return the iamUrl
     */
    public String getIamUrl() {
        return iamUrl;
    }

    /**
     * @param iamUrl the iamUrl to set
     */
    public void setIamUrl(String iamUrl) {
        if (iamUrl == null) {
            LOGGER.error("IAMurl is null.");
            throw new NullPointerException("iamUrl is null.");
        }
        this.iamUrl = iamUrl;
    }

    /**
     * the http configuration to set
     *
     * @param clientConfiguration http configuration
     */
    public void setClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    protected String buildRequestUrl(String uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.HTTPS_PREFFIX).append(smnConfiguration.getIamEndpoint()).append(uri);
        return sb.toString();
    }
    private AuthenticationBean postForIamToken(String iamUrl, String bodyMessage, ClientConfiguration clientConfiguration) throws Exception {
        LOGGER.debug("Start to get iam token. IamUrl is {}.", iamUrl);
        CloseableHttpClient httpclient = HttpUtil.getHttpClient(clientConfiguration);
        try {
            HttpPost httpPost = new HttpPost(iamUrl);
            httpPost.setConfig(HttpUtil.getRequestConfig(clientConfiguration));
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(bodyMessage, ContentType.APPLICATION_JSON));
            // execute HTTPS post
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                int status = response.getStatusLine().getStatusCode();
                // The length of the response will not be too long
                HttpEntity entity = response.getEntity();
                String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
                if (status >= 200 && status < 300) {
                    AuthenticationBean authBean = new AuthenticationBean();
                    // get IAM token
                    authBean.setAuthToken(response.getFirstHeader(X_SUBJECT_TOKEN).getValue());
                    Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                    // set projectId
                    authBean.setProjectId(((Map) ((Map) messageMap.get(TOKEN)).get(PROJECT)).get(ID).toString());
                    // set expires at
                    authBean.setExpiresAt(((Map) messageMap.get(TOKEN)).get(EXPIRES_AT).toString());
                    LOGGER.debug("End to get iam token. Status is {}. AuthBean is {}.", status, authBean);
                    return authBean;
                } else {
                    LOGGER.error("Unexpected response status: {}.  ErrorMessage is {}.", status, responseMessage);
                    throw new RuntimeException(
                            "Unexpected response status: " + status + ", ErrorMessage is " + responseMessage);
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
