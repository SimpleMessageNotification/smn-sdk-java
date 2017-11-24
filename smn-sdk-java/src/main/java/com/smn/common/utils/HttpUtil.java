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
package com.smn.common.utils;

import com.smn.common.ClientConfiguration;
import com.smn.common.HttpMethod;
import com.smn.common.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author huangqiong
 * @author zhangyx
 * @version 0.9
 */
public class HttpUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * config timeout milliseconds
     */
    private static RequestConfig requestConfig = null;

    /**
     * to create http request instance
     *
     * @param headerParams        the header parmas
     * @param bodyParams          the body params
     * @param url                 the url
     * @param httpMethod          the request http method
     * @param clientConfiguration the client configuration
     * @return the request instance
     */
    private static HttpRequestBase createHttpRequest(Map<String, String> headerParams, String bodyParams,
                                                     String url, HttpMethod httpMethod, ClientConfiguration clientConfiguration) {
        HttpRequestBase httpRequestBase = null;
        if (httpMethod == HttpMethod.GET) {
            HttpGet httpGet = new HttpGet(url);
            httpRequestBase = httpGet;
        } else if (httpMethod == HttpMethod.DELETE) {
            HttpDelete httpDelete = new HttpDelete(url);
            httpRequestBase = httpDelete;
        } else if (httpMethod == HttpMethod.POST) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(bodyParams, ContentType.APPLICATION_JSON));
            httpRequestBase = httpPost;
        } else if (httpMethod == HttpMethod.PUT) {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(new StringEntity(bodyParams, ContentType.APPLICATION_JSON));
            httpRequestBase = httpPut;
        } else if (httpMethod == HttpMethod.HEAD) {
            HttpHead httpHead = new HttpHead(url);
            httpRequestBase = httpHead;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Unsupported HTTP method:%s .", httpMethod.getName()));
        }
        httpRequestBase.setConfig(getRequestConfig(clientConfiguration));
        buildHttpHeader(headerParams, httpRequestBase);
        return httpRequestBase;
    }

    /**
     * to send request
     *
     * @param headerParams        the header parmas
     * @param bodyParams          the body params
     * @param url                 the url
     * @param httpMethod          the request http method
     * @param clientConfiguration the client configuration, contain http configuration
     * @return the response
     * @throws Exception request error throw exception
     */
    public static HttpResponse sendRequest(Map<String, String> headerParams, String bodyParams, String url,
                                           HttpMethod httpMethod, ClientConfiguration clientConfiguration)
            throws Exception {
        LOGGER.debug("Start to post request,requestUrl is {}.", url);

        CloseableHttpClient httpclient = getHttpClient(clientConfiguration);
        try {
            HttpRequestBase httpRequestBase = createHttpRequest(headerParams, bodyParams, url, httpMethod, clientConfiguration);
            CloseableHttpResponse response = httpclient.execute(httpRequestBase);
            try {
                int status = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
                HttpResponse httpResponse = new HttpResponse();
                httpResponse.setHttpCode(status);
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                httpResponse.setBody(messageMap);
                return httpResponse;
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
     * Construct http header
     *
     * @param requestHeaderMap
     * @param httpMethod
     */
    private static void buildHttpHeader(Map<String, String> requestHeaderMap, HttpRequestBase httpMethod) {
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
     * @param clientConfiguration the client configuration
     * @return {@code CloseableHttpClient}
     * @throws Exception
     */
    public static CloseableHttpClient getHttpClient(ClientConfiguration clientConfiguration) throws Exception {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        SSLConnectionSocketFactory sslSocketFactory = createSslConnectionSocketFactory();
        HttpClientBuilder builder = HttpClients.custom();

        // set proxy
        String proxyHost = clientConfiguration.getProxyHost();
        int proxyPort = clientConfiguration.getProxyPort();

        if (!StringUtils.isEmpty(proxyHost) && proxyPort > 0) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            builder.setProxy(proxy);

            String username = clientConfiguration.getProxyUserName();
            String password = clientConfiguration.getProxyPassword();

            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                AuthScope authscope = new AuthScope(proxy);
                Credentials credentials = new UsernamePasswordCredentials(username,
                        password);
                credentialsProvider.setCredentials(authscope, credentials);
                builder.setDefaultCredentialsProvider(credentialsProvider);
            }
        }
        builder.setUserAgent(VersionUtil.getDefaultUserAgent());
        CloseableHttpClient httpclient = builder.setSSLSocketFactory(sslSocketFactory).build();
        return httpclient;
    }

    private static SSLConnectionSocketFactory createSslConnectionSocketFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContext sslContext = SSLContexts.custom().useProtocol("TLSV1.1")
                .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sslContext.init(null, new TrustManager[]{tm}, null);
        return new SSLConnectionSocketFactory(sslContext,
                new NoopHostnameVerifier());
    }

    /**
     * Config request timeout milliseconds ,socket timeout milliseconds
     *
     * @param clientConfiguration the client configuration
     * @return {@code RequestConfig}
     */
    public static RequestConfig getRequestConfig(ClientConfiguration clientConfiguration) {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(clientConfiguration.getConnectTimeOut())
                .setSocketTimeout(clientConfiguration.getSocketTimeOut())
                .build();
        return requestConfig;
    }
}
