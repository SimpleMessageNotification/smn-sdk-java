/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package com.smn.client.util;

import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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

import com.smn.client.bean.AuthBean;

/**
 * The {@code HttpUtil} class is used to handle HTTP requests
 * 
 * @author liuqiangqiang
 * @version 0.5.0
 */
public class HttpUtil {

	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
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
	 * 链接超时时间，单位毫秒
	 */
	private static int connectTimeOut = 10000;

	/**
	 * 读取超时时间，单位毫秒
	 */
	private static int socketTimeOut = 10000;

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
	public static AuthBean postForIamToken(String iamUrl, String bodyMessage) throws Exception {
		LOGGER.debug("Start to get iam token. IamUrl is {}.", iamUrl);
		SSLContext sslContext = SSLContexts.custom().useProtocol("TLSV1.1").loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
				new NoopHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
		try {
			HttpPost httpPost = new HttpPost(iamUrl);
			httpPost.setConfig(
					RequestConfig.custom().setConnectTimeout(connectTimeOut).setSocketTimeout(socketTimeOut).build());
			// set content-type
			httpPost.addHeader("Content-Type", "application-json");
			// set body
			httpPost.setEntity(new StringEntity(bodyMessage, ContentType.APPLICATION_JSON));
			// execute HTTPS post
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// get HTTP code
				int status = response.getStatusLine().getStatusCode();
				// The length of the response will not be too long
				HttpEntity entity = response.getEntity();
				// get response message
				String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
				// check HTTP code
				if (status >= 200 && status < 300) {
					// create a auth bean
					AuthBean authBean = new AuthBean();
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
	 * 发送SMS短信信息
	 *
	 * @param smnUrl
	 *            smn的URL地址
	 * @param bodyMessage
	 *            消息内容
	 * @param projectId
	 *            租户的项目ID
	 * @param token
	 *            鉴权的token
	 * @return {@code Map} 返回json格式的消息，包括messageId，RequestId
	 * @throws Exception
	 *             发送短信失败，则抛出异常
	 */
	public static Map<String, Object> postForSmsMessage(String smnUrl, String bodyMessage, String projectId,
			String token) throws Exception {
		LOGGER.debug("Start to send sms message. SmnUrl is {}.", smnUrl);
		SSLContext sslContext = SSLContexts.custom().useProtocol("TLSV1.1").loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
				new NoopHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
		try {
			HttpPost httpPost = new HttpPost(smnUrl);
			httpPost.setConfig(
					RequestConfig.custom().setConnectTimeout(connectTimeOut).setSocketTimeout(socketTimeOut).build());
			// set content-type
			httpPost.addHeader("Content-Type", "application-json");
			httpPost.addHeader("X-Auth-Token", token);
			httpPost.addHeader("X-Project-Id", projectId);

			// set body
			httpPost.setEntity(new StringEntity(bodyMessage, ContentType.APPLICATION_JSON));
			// execute HTTPS post
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// get HTTP code
				int status = response.getStatusLine().getStatusCode();
				// The length of the response will not be too long
				HttpEntity entity = response.getEntity();
				// get response message
				String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
				// check HTTP code
				if (status >= 200 && status < 300) {
					// create a auth bean
					Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
					LOGGER.debug("End to send sms message. Status is {}. Message is {}.", status, responseMessage);
					return messageMap;
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
	 * @param connectTimeOut
	 *            the connectTimeOut to set
	 */
	public static void setConnectTimeOut(int connectTimeOut) {
		HttpUtil.connectTimeOut = connectTimeOut;
	}

	/**
	 * @param socketTimeOut
	 *            the socketTimeOut to set
	 */
	public static void setSocketTimeOut(int socketTimeOut) {
		HttpUtil.socketTimeOut = socketTimeOut;
	}
}
