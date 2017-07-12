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
package com.smn.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.client.token.impl.IAMServiceImpl;
import com.smn.client.token.impl.MessageServiceImpl;

/**
 * 消息通知服务的Client
 * <p>
 * 读取配置文件，注入响应的服务
 * 
 * @author liuqiangqiang
 * @version 0.5.0
 *
 */
public class SmnClient {

	/**
	 * LOGGER
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(SmnClient.class);

	/**
	 * IAM获取token的服务
	 */
	IAMServiceImpl iamService = null;

	/**
	 * 消息发送服务
	 */
	MessageServiceImpl messageService = null;

	/**
	 * 用户名常量
	 */
	private static final String IAM_USER_NAME = "iam.user.name";

	/**
	 * 密码常量
	 */
	private static final String IAM_USER_PASSWORD = "iam.user.password";

	/**
	 * domain常量
	 */
	private static final String IAM_DOMAIN_NAME = "iam.domain.name";

	/**
	 * token的URL常量
	 */
	private static final String IAM_TOKEN_URL = "iam.token.url";

	/**
	 * region_ID常量
	 */
	private static final String REGION_ID = "region.id";

	/**
	 * SMN接入常量
	 */
	private static final String SMN_ENDPOINT = "smn.endpoint";

	/**
	 * socket连接超时常量
	 */
	private static final String SOCKET_CONNECTOR_TIMEOUT = "socket.connector.timeout";

	/**
	 * socket读取超时常量
	 */
	private static final String SOCKET_READ_TIMEOUT = "socket.read.timeout";

	/**
	 * 默认的超时时间，单位毫秒
	 */
	private static final int DEFAULT_TIMEOUT = 10000;

	/**
	 * 通过读取配置文件初始化服务
	 * 
	 * @param filePath
	 *            文件绝对路径
	 * @throws RuntimeException
	 *             加载配置失败则抛出异常
	 */
	public SmnClient(String filePath) throws RuntimeException {
		LOGGER.info("Start to int smn client from file.");
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			File file = new File(filePath);
			if (file.exists()) {
				inputStream = new FileInputStream(new File(filePath));
			} else {
				inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
			}
			properties.load(inputStream);
		} catch (Exception e) {
			LOGGER.error("Failed to load smn config from file.", e);
			throw new RuntimeException("Failed to load smn config from file.", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// nothing
				}
			}
		}

		int connectorTimeOut = properties.getProperty(SOCKET_CONNECTOR_TIMEOUT) == null ? DEFAULT_TIMEOUT
				: Integer.parseInt(properties.getProperty(SOCKET_CONNECTOR_TIMEOUT));

		int readTimeOut = properties.getProperty(SOCKET_READ_TIMEOUT) == null ? DEFAULT_TIMEOUT
				: Integer.parseInt(properties.getProperty(SOCKET_READ_TIMEOUT));

		init(properties.getProperty(IAM_USER_NAME), properties.getProperty(IAM_USER_PASSWORD),
				properties.getProperty(IAM_DOMAIN_NAME), properties.getProperty(IAM_TOKEN_URL),
				properties.getProperty(REGION_ID), properties.getProperty(SMN_ENDPOINT), connectorTimeOut, readTimeOut);

		LOGGER.info("End to int smn client from file.");
	}

	/**
	 * 通过参数的方式初始化服务
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param domainName
	 *            用户的DomainId
	 * @param iamUrl
	 *            IAM获取token的地址
	 * @param regionId
	 *            RegionId信息
	 * @param smnEndpoint
	 *            消息服务的接入地址
	 * @param connectorTimeOut
	 *            连接超时时间
	 * @param readTimeOut
	 *            读取超时时间
	 * @throws RuntimeException
	 *             初始化服务失败则抛出异常
	 */
	public SmnClient(String userName, String password, String domainName, String iamUrl, String regionId,
			String smnEndpoint, int connectorTimeOut, int readTimeOut) throws RuntimeException {
		this.init(userName, password, domainName, iamUrl, regionId, smnEndpoint, connectorTimeOut, readTimeOut);
	}

	private void init(String userName, String password, String domainName, String iamUrl, String regionId,
			String smnEndpoint, int connectorTimeOut, int readTimeOut) {
		LOGGER.info(
				"Start to init smn client. UserName is {}. DomainName is {}. IamUrl is {}. RegionId is {}. SmnEndpoint is {}. ConnectorTimeOut is {}. ReadTimeOut is {}.",
				userName, domainName, iamUrl, regionId, smnEndpoint, connectorTimeOut, readTimeOut);
		iamService = new IAMServiceImpl(userName, password, domainName, regionId, iamUrl);
		messageService = new MessageServiceImpl();
		messageService.setSmnEndpoint(smnEndpoint);
		messageService.setIamService(iamService);
		messageService.init();
		LOGGER.info("End to init smn client.");
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
		if (messageService != null) {
			return messageService.sendSMSMessage(phoneNumber, message);
		}
		throw new NullPointerException("MessageService is null.");
	}

}
