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
package com.smn.service.impl;

import java.io.UnsupportedEncodingException;

import com.smn.common.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.HttpResponse;
import com.smn.model.request.publish.PublishMsgRequest;
import com.smn.service.AbstractCommonService;
import com.smn.service.PublishService;

/**
 * Publish service implemented
 * 
 * @author huangqiong
 * @author zhangyx
 * @version 0.6
 * @version 0.7
 */
public class PublishServiceImpl extends AbstractCommonService implements PublishService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishServiceImpl.class);

    /**
     * (non-Javadoc)
     *
     * @see PublishService#publish(PublishMsgRequest)
     */
	public HttpResponse publish(PublishMsgRequest smnRequest) throws RuntimeException, UnsupportedEncodingException {
        LOGGER.info("Start to publish message.");

        try {
			return sendRequest(smnRequest, HttpMethod.POST);
        } catch (Exception e) {
            LOGGER.error("Failed to publish message.", e);
            throw new RuntimeException("Failed to publish message.", e);
        }
    }

}
