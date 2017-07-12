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

/**
 * The {@link IMessageSerivce} defines the operation of sending messages
 * 
 * @author Liuqiangqiang
 * 
 * @version 0.5.0
 * 
 */
public interface IMessageSerivce {

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
	String sendSMSMessage(String phoneNumber, String message) throws RuntimeException;
}
