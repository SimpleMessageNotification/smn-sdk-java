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
 * @date 2017年8月3日 下午5:41:10
 * @version 0.1
 */
package com.smn.service.impl;

import com.smn.common.HttpMethod;
import com.smn.model.request.sms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.common.HttpResponse;
import com.smn.service.AbstractCommonService;
import com.smn.service.SmsService;

/**
 * send sms directly
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.1
 * @version 0.7
 */
public class SmsServiceImpl extends AbstractCommonService implements SmsService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * (non-Javadoc)
     *
     * @see SmsService#smsPublish(SmsPublishRequest)
     */
    public HttpResponse smsPublish(SmsPublishRequest smnRequest) throws RuntimeException {
        try {
            return sendRequest(smnRequest, HttpMethod.POST);
        } catch (Exception e) {
            LOGGER.error("Failed to send sms.", e);
            throw new RuntimeException("Failed to send sms.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SmsService#listSmsMsgReport(ListSmsMsgReportRequest)
     */
    public HttpResponse listSmsMsgReport(ListSmsMsgReportRequest smnRequest) {
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Failed to list sms msg report.", e);
            throw new RuntimeException("Failed to list sms msg report.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SmsService#getSmsMessage(GetSmsMessageRequest)
     */
    public HttpResponse getSmsMessage(GetSmsMessageRequest smnRequest) {
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Failed to get sms message.", e);
            throw new RuntimeException("Failed to get sms message.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SmsService#listSmsCallbackEvent(ListSmsCallbackEventRequest)
     */
    public HttpResponse listSmsCallbackEvent(ListSmsCallbackEventRequest smnRequest) {
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Failed to list sms callback event.", e);
            throw new RuntimeException("Failed to list sms callback event.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SmsService#updateSmsCallbackEvent(UpdateSmsCallbackEventRequest)
     */
    public HttpResponse updateSmsCallbackEvent(UpdateSmsCallbackEventRequest smnRequest) {
        try {
            return sendRequest(smnRequest, HttpMethod.PUT);
        } catch (Exception e) {
            LOGGER.error("Failed to update sms callback event.", e);
            throw new RuntimeException("Failed to update sms callback event.", e);
        }
    }
}
