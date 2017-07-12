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
package com.smn.client.token.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.client.IMessageSerivce;
import com.smn.client.bean.AuthBean;
import com.smn.client.util.HttpUtil;
import com.smn.client.util.JsonUtil;

/**
 * 类{@link MessageServiceImpl}实现了{@link IMessageSerivce}接口,通过Token的方式进行消息的发送
 * 
 * @author liuqiangqiang
 * 
 * @version 0.5.0
 *
 */
public class MessageServiceImpl implements IMessageSerivce {

	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	/**
	 * 电话号码的正则表达式
	 */
	private static final Pattern PATTERN_TELTPHONE = Pattern.compile("^\\+?[0-9]{1,31}");

	/**
	 * 发送短信的URL
	 */
	private static final String SENDING_SMS_URL = "{smn_endpoint}/v2/{project_id}/notifications/sms";

	/**
	 * 接入终端
	 */
	private static final String ENDPOINT = "endpoint";

	/**
	 * 消息内容
	 */
	private static final String MESSAGE = "message";

	/**
	 * 请求ID
	 */
	private static final String REQUEST_ID = "request_id";

	/**
	 * 消息ID
	 */
	private static final String MESSAGE_ID = "message_id";

	/**
	 * 鉴权信息
	 */
	private AuthBean authBean = null;

	/**
	 * 获取token的服务
	 */
	private IAMServiceImpl iamService = null;

	/**
	 * 消息通知服务接入点
	 */
	private String smnEndpoint = null;

	/**
	 * 租户发送短信的请求URL
	 */
	private String smnSMSUrl = null;

	/**
	 * 服务初始化，判断服务初始化条件
	 */
	public void init() {
		if (smnEndpoint == null) {
			LOGGER.error("Smn endpoint is null.");
			throw new RuntimeException("Smn endpoint is null.");
		}

		if (iamService == null) {
			LOGGER.error("IamService is null.");
			throw new RuntimeException("IamService is null.");
		}
	}

	/**
	 * Send directly SMS Message.
	 * <p>
	 * if {@code phoneNumber} or {@code message} is null, then throws
	 * {@link NullPointerException}.
	 * <p>
	 * if the sending message fails, then throws runtime exception.
	 * 
	 * @param phoneNumber
	 *            phone number
	 * @param message
	 *            the content of SMS
	 * @return {@code String} the id of message
	 * 
	 * @throws RuntimeException
	 *             if the sending message fails, then throws runtime exception.
	 */
	public String sendSMSMessage(String phoneNumber, String message) throws RuntimeException {

		LOGGER.info("Start to send sms message.");
		long startTime = System.currentTimeMillis();
		// 检测手机号码
		checkPhoneNumber(phoneNumber);
		// 检测发送的内容
		checkMessage(message);
		// 从IAM获取token
		checkAuthInfo();

		if (smnSMSUrl == null) {
			smnSMSUrl = SENDING_SMS_URL.replaceFirst("\\{smn_endpoint\\}", smnEndpoint).replaceFirst("\\{project_id\\}",
					authBean.getProjectId());
		}
		try {
			Map<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put(ENDPOINT, phoneNumber);
			requestMap.put(MESSAGE, message);
			Map<String, Object> responseMap = HttpUtil.postForSmsMessage(smnSMSUrl,
					JsonUtil.getJsonStringByMap(requestMap), authBean.getProjectId(), authBean.getAuthToken());

			String requestId = responseMap.get(REQUEST_ID).toString();
			String messageId = responseMap.get(MESSAGE_ID).toString();
			LOGGER.info("End to send sms message. RequestId is {}. MessageId is {}. Cost is {}ms", requestId, messageId,
					System.currentTimeMillis() - startTime);
			return messageId;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Failed to send message.", e);
			throw new RuntimeException("Failed to send message.", e);
		}
	}

	private void checkPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			LOGGER.error("PhoneNumber is null.");
			throw new NullPointerException("PhoneNumber is null.");
		}

		if (!PATTERN_TELTPHONE.matcher(phoneNumber).matches()) {
			LOGGER.error("Wrong phone number format");
			throw new RuntimeException(
					"The wrong phone number format, the correct number format is +8600000000000 or 00000000000");
		}
	}

	private void checkMessage(String message) {

		if (message == null) {
			LOGGER.error("Message is null.");
			throw new NullPointerException("Message is null.");
		}

		// 短信发送时，需要注意短信长度,CMPP协议短信最多500字符
		if (message.length() > 500) {
			LOGGER.warn("SMS content is too long, more than {} characters of the message content will be cut off.",
					500);
		}
	}

	private void checkAuthInfo() {
		if (authBean == null) {
			// 可能存在并发
			synchronized (MessageServiceImpl.class) {
				if (authBean == null) {
					LOGGER.info("AuthInfo is null. Try to get it.");
					// 要么获取token异常，要么返回正确的bean
					authBean = iamService.getAuthentication();
				}
			}
		} else {
			// 如果authBean过期
			if (authBean.isExpired()) {
				synchronized (MessageServiceImpl.class) {
					if (authBean.isExpired()) {
						LOGGER.info("AuthInfo is expired. Try to get it. Old authInfo is {}.", authBean);
						// 要么获取token异常，要么返回正确的bean
						authBean = iamService.getAuthentication();
					}
				}
			}
		}
	}

	/**
	 * @return the iamService
	 */
	public IAMServiceImpl getIamService() {
		return iamService;
	}

	/**
	 * @return the smnEndpoint
	 */
	public String getSmnEndpoint() {
		return smnEndpoint;
	}

	/**
	 * @param iamService
	 *            the iamService to set
	 */
	public void setIamService(IAMServiceImpl iamService) {
		this.iamService = iamService;
	}

	/**
	 * @param smnEndpoint
	 *            the smnEndpoint to set
	 */
	public void setSmnEndpoint(String smnEndpoint) {
		this.smnEndpoint = smnEndpoint;
	}
}
