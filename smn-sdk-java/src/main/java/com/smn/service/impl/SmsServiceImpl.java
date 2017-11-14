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

import com.smn.common.HttpMethod;
import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.common.ClientConfiguration;
import com.smn.model.request.sms.*;
import com.smn.service.AbstractCommonService;
import com.smn.service.IAMService;
import com.smn.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * send sms directly
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class SmsServiceImpl extends AbstractCommonService implements SmsService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * no args constructor
     */
    public SmsServiceImpl() {
        super();
    }

    /**
     * give iamService and smnConfiguration constructor
     *
     * @param iamService        the iamService to set
     * @param smnConfiguration  the smnConfiguration to set
     * @param clientConfiguration the client configuration
     */
    public SmsServiceImpl(IAMService iamService, SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        super(iamService, smnConfiguration, clientConfiguration);
    }

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

    /**
     * (non-Javadoc)
     *
     * @see SmsService#listSmsSigns(ListSmsSignsRequest)
     */
    public HttpResponse listSmsSigns(ListSmsSignsRequest smnRequest) {
        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Failed to list sms signs.", e);
            throw new RuntimeException("Failed to list sms signs", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see SmsService#deleteSmsSign(DeleteSmsSignRequest)
     */
    public HttpResponse deleteSmsSign(DeleteSmsSignRequest smnRequest) {
        try {
            return sendRequest(smnRequest, HttpMethod.DELETE);
        } catch (Exception e) {
            LOGGER.error("Failed to delete sms sign.", e);
            throw new RuntimeException("Failed to delete sms sign", e);
        }
    }
}
