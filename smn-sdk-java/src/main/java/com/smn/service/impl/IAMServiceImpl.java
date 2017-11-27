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

import com.smn.common.*;
import com.smn.common.utils.DateUtil;
import com.smn.common.utils.HttpUtil;
import com.smn.common.utils.JsonUtil;
import com.smn.model.AuthenticationBean;
import com.smn.model.request.iam.GetProjectIdsRequest;
import com.smn.service.IAMService;
import com.smn.signer.AkskSigner;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;

/**
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class IAMServiceImpl implements IAMService {
    private static Logger LOGGER = LoggerFactory.getLogger(IAMServiceImpl.class);

    /**
     * Token expired 30 minutes in advance
     */
    private long expiredInterval = 30 * 60 * 1000;

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

    /**
     * cache projectId
     */
    private String projectId;

    /**
     * http signer auth
     */
    private AkskSigner signer;

    /**
     * constructor
     *
     * @param smnConfiguration    the smn configuration
     * @param clientConfiguration the client configuration
     */
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
                "                        \"name\": \"" + smnConfiguration.getDomainName() + "\"" +
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

        signer = new AkskSigner(smnConfiguration, SmnConstants.IAM_SERVICE_NAME);
        LOGGER.info("Iam token url is{}.", iamTokenUrl);

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
     * Obtain project id
     *
     * @return the project id
     */
    public String getProjectId() {
        if (StringUtils.isEmpty(projectId)) {
            synchronized (this) {
                if (StringUtils.isEmpty(projectId)) {
                    if (SmnConfiguration.AKSK_AUTH_TYPE.equals(smnConfiguration.getAuthType())) {
                        projectId = postForProjectId();
                    } else {
                        projectId = getAuthenticationBean().getProjectId();
                    }
                }
            }
        }
        return projectId;
    }

    /**
     * Obtain authorization information from the IAM service, which includes
     * projectId, user token, and token expiration time
     *
     * @return {@link AuthenticationBean} User token information
     * @throws RuntimeException Failed to get token, then ran out of the exception
     */
    private AuthenticationBean getAuthentication() throws RuntimeException {

        AuthenticationBean authenticationBean = null;
        try {
            authenticationBean = postForIamToken(iamTokenUrl, requestMessage, clientConfiguration);
            // parse time
            Date tempDate = DateUtil.parseUTCDate(authenticationBean.getExpiresAt());
            authenticationBean.setExpiresTime(tempDate.getTime() - expiredInterval);
            return authenticationBean;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Faied to get token from iam.e:{}", e);
            throw new RuntimeException("Failed to get token from iam.", e);
        }
    }

    private String buildRequestUrl(String uri) {
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
                    authBean.setProjectId(parseProjectId(messageMap));
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

    private String parseProjectId(Map<String, Object> messageMap) {
        try {
            return ((Map) ((Map) messageMap.get(TOKEN)).get(PROJECT)).get(ID).toString();
        } catch (Exception e) {
            return null;
        }
    }

    private String postForProjectId() {
        try {
            String id;
            // 构造请求参数
            GetProjectIdsRequest request = new GetProjectIdsRequest();
            request.setName(smnConfiguration.getRegionId());
            String url = buildRequestUrl(request.getRequestUri());
            // 签名处理
            signer.get(request, new URL(url));
            Map<String, String> requestHeaderMap = request.getRequestHeaderMap();

            HttpResponse httpResponse = HttpUtil.sendRequest(requestHeaderMap,
                    JsonUtil.getJsonStringByMap(request.getRequestParameterMap()), url, HttpMethod.GET, clientConfiguration);
            if (httpResponse.isSuccessed()) {
                List projectList = (ArrayList) httpResponse.getBody().get("projects");
                if (projectList == null || projectList.size() == 0) {
                    LOGGER.error("Fail to get project id by aksk auth. projects is empty.");
                    throw new RuntimeException("Fail to get project id by aksk auth. projects is empty.");
                }

                id = (String) ((HashMap) (projectList).get(0)).get("id");
            } else {
                LOGGER.error("Fail to get project id by aksk auth. http response status: {}", httpResponse.getHttpCode());
                throw new RuntimeException("Fail to get project id by aksk auth. http response status: " + httpResponse.getHttpCode());
            }

            if (StringUtils.isEmpty(id)) {
                LOGGER.error("Fail to get project id by aksk auth. project id is null");
                throw new RuntimeException("Fail to get project id by aksk auth. project id is null");

            }
            return id;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Failed to get project id from iam by aksk auth.", e);
            throw new RuntimeException("Failed to get project id from iam by aksk auth.", e);
        }
    }

}
