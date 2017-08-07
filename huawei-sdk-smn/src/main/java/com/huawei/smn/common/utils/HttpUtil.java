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
 * @date 2017年8月3日 下午5:19:22
 * @version 0.1
 * 
 */
package com.huawei.smn.common.utils;

import java.net.ConnectException;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:19:22
 * @version 0.1
 */
public class HttpUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * get token from header
     */
    private static final String X_SUBJECT_TOKEN = "X-Subject-Token";

    /**
     * token/project/id from IAM response
     */
    private static final String ID = "id";

    /**
     * token/project from IAM response
     */
    private static final String PROJECT = "project";

    /**
     * token/expires_at from IAM response
     */
    private static final String EXPIRES_AT = "expires_at";

    /**
     * token from IAM response
     */
    private static final String TOKEN = "token";

    /**
     * connect time out ,in millisecond
     */
    private static int connectTimeOut = 10000;

    /**
     * read time out,in millisecond
     */
    private static int socketTimeOut = 10000;

    /**
     * config timeout milliseconds
     */
    private static RequestConfig requestConfig = null;

    /**
     * Get auth info from IAM service
     * 
     * @param iamUrl
     *            the URL of IAM service
     * @param bodyMessage
     *            the body of message
     * @return {@code AuthBean}
     * @throws Exception
     *             Failed to get IAM information, throw an exception
     */
    @SuppressWarnings("rawtypes")
    public static AuthenticationBean postForIamToken(String iamUrl, String bodyMessage) throws Exception {
        LOGGER.debug("Start to get iam token. IamUrl is {}.", iamUrl);
        CloseableHttpClient httpclient = getHttpClient();
        try {
            HttpPost httpPost = new HttpPost(iamUrl);
            httpPost.setConfig(getRequestConfig());
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
                            "Unexpected response status: " + status + "。 ErrorMessage is " + responseMessage);
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 
     * @param paramsMap
     *            http request including header , reqeusted url and body params
     * @param url
     *            restful post request
     * @return {@code Map} request_id,status
     * @throws Exception
     */
    public static Map<String, Object> post(Map<String, String> headerParams, Map<String, Object> bodyParams, String url)
            throws Exception {
        LOGGER.debug("Start to post request,requestUrl is {}.", url);

        CloseableHttpClient httpclient = getHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(getRequestConfig());
            Map<String, String> requestHeader = headerParams;
            buildHttpHeader(requestHeader, httpPost);
            String bodyString = JsonUtil.getJsonStringByMap(bodyParams);
            httpPost.setEntity(new StringEntity(bodyString, ContentType.APPLICATION_JSON));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                int status = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                messageMap.put("status", status);
                return messageMap;
            } catch (ConnectException e) {
                LOGGER.error("Post request connected error.", e);
                throw new Exception(e);
            } catch (Exception e) {
                LOGGER.error("Post request error.", e);
                throw new Exception(e);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 
     * @param paramsMap
     *            http request including header , reqeusted url and body params
     * @param url
     *            restful put request
     * @return {@code Map} request_id,status
     * @throws Exception
     */
    public static Map<String, Object> put(Map<String, String> headerParams, Map<String, Object> bodyParams, String url)
            throws Exception {
        LOGGER.debug("Start to put request,requestUrl is {}.", url);

        CloseableHttpClient httpclient = getHttpClient();
        try {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(getRequestConfig());
            Map<String, String> requestHeader = headerParams;
            buildHttpHeader(requestHeader, httpPut);
            String bodyString = JsonUtil.getJsonStringByMap(bodyParams);
            LOGGER.debug("Put request body String:" + bodyString);
            httpPut.setEntity(new StringEntity(bodyString, ContentType.APPLICATION_JSON));
            CloseableHttpResponse response = httpclient.execute(httpPut);
            try {
                int status = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                messageMap.put("status", status);
                return messageMap;
            } catch (ConnectException e) {
                LOGGER.error("Smn post request connected error.", e);
                throw new Exception(e);
            } catch (Exception e) {
                LOGGER.error("Smn post request error.", e);
                throw new Exception(e);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 
     * @param paramsMap
     *            http request including header and reqeusted url
     * @param url
     *            restful delete request
     * @return {@code Map} request_id,status
     * @throws Exception
     */
    public static Map<String, Object> delete(Map<String, String> headerParams, String url) throws Exception {
        LOGGER.debug("Start to delete request,requestUrl is {}.", url);

        CloseableHttpClient httpclient = getHttpClient();
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setConfig(getRequestConfig());
            Map<String, String> requestHeader = headerParams;
            buildHttpHeader(requestHeader, httpDelete);
            CloseableHttpResponse response = httpclient.execute(httpDelete);
            try {
                int status = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                messageMap.put("status", status);
                return messageMap;
            } catch (ConnectException e) {
                LOGGER.error("Smn delete request connected error.", e);
                throw new Exception(e);
            } catch (Exception e) {
                LOGGER.error("Smn delete request error.", e);
                throw new Exception(e);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * 
     * @param paramsMap
     *            http request including header and reqeusted url
     * @param url
     *            restful get request
     * @return {@code Map} request_id,status
     * @throws Exception
     */
    public static Map<String, Object> get(Map<String, String> headerParams, String url) throws Exception {
        CloseableHttpClient httpclient = getHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(getRequestConfig());
            buildHttpHeader(headerParams, httpGet);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                int status = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                messageMap.put("status", status);
                return messageMap;
            } catch (ConnectException e) {
                LOGGER.error("Smn get request connected error.", e);
                throw new Exception(e);
            } catch (Exception e) {
                LOGGER.error("Smn get request error.", e);
                throw new Exception(e);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    /**
     * Construct http header
     * 
     * @param requestHeaderMap
     * @param httpMethod
     */
    public static void buildHttpHeader(Map<String, String> requestHeaderMap, HttpRequestBase httpMethod) {
        if (requestHeaderMap == null || requestHeaderMap.isEmpty()) {
            LOGGER.error("Request header map is empty.");
            throw new RuntimeException("Request header map is empty.");
        }
        for (String headerKey : requestHeaderMap.keySet()) {
            httpMethod.addHeader(headerKey, requestHeaderMap.get(headerKey));
        }
    }

    /**
     * Construct httpclient with SSL protocol
     * 
     * @return {@code CloseableHttpClient}
     * @throws Exception
     */
    public static CloseableHttpClient getHttpClient() throws Exception {
        SSLContext sslContext = SSLContexts.custom().useProtocol("TLSV1.1")
                .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new NoopHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
        return httpclient;
    }

    /**
     * Config request timeout milliseconds ,socket timeout milliseconds
     * 
     * @return {@code RequestConfig}
     */
    public static RequestConfig getRequestConfig() {
        requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut).setSocketTimeout(socketTimeOut)
                .build();
        return requestConfig;
    }

}
