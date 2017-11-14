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
import com.smn.model.request.template.*;
import com.smn.service.AbstractCommonService;
import com.smn.service.IAMService;
import com.smn.service.MessageTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message template service implemented
 *
 * @author huangqiong
 * @author yangyanping
 * @author zhangyx
 * @version 0.7
 */
public class MessageTemplateServiceImpl extends AbstractCommonService implements MessageTemplateService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTemplateServiceImpl.class);

    /**
     * no arg constructor
     */
    public MessageTemplateServiceImpl() {
        super();
    }

    /**
     * give iamService and smnConfiguration constructor
     *
     * @param iamService        the iamService to set
     * @param smnConfiguration  the smnConfiguration to set
     * @param clientConfiguration the client configuration
     */
    public MessageTemplateServiceImpl(IAMService iamService, SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        super(iamService, smnConfiguration, clientConfiguration);
    }

    /**
     * (non-Javadoc)
     *
     * @see MessageTemplateService#createMessageTemplate(CreateMessageTemplateRequest)
     */
    public HttpResponse createMessageTemplate(CreateMessageTemplateRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to create message template.");

        try {
            return sendRequest(smnRequest, HttpMethod.POST);
        } catch (Exception e) {
            LOGGER.error("Failed to create message template.", e);
            throw new RuntimeException("Failed to create message template.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see MessageTemplateService#updateMessageTemplate(UpdateMessageTemplateRequest)
     */
    public HttpResponse updateMessageTemplate(UpdateMessageTemplateRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to update message template.");

        try {
            return sendRequest(smnRequest, HttpMethod.PUT);
        } catch (Exception e) {
            LOGGER.error("Failed to update message template.", e);
            throw new RuntimeException("Failed to update message template.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see MessageTemplateService#deleteMessageTemplate(DeleteMessageTemplateRequest)
     */
    public HttpResponse deleteMessageTemplate(DeleteMessageTemplateRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to delete message template.");

        try {
            return sendRequest(smnRequest, HttpMethod.DELETE);
        } catch (Exception e) {
            LOGGER.error("Failed to delete message template.", e);
            throw new RuntimeException("Failed to delete message template.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see MessageTemplateService#listMessageTemplates(ListMessageTemplatesRequest)
     */
    public HttpResponse listMessageTemplates(ListMessageTemplatesRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to list message template.");

        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Failed to list message templates.", e);
            throw new RuntimeException("Failed to list message templates.", e);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see MessageTemplateService#queryMsgTemplateDetail(QueryMessageTemplateDetailRequest)
     */
    public HttpResponse queryMsgTemplateDetail(QueryMessageTemplateDetailRequest smnRequest)
            throws RuntimeException {
        LOGGER.info("Start to query message template detail.");

        try {
            return sendRequest(smnRequest, HttpMethod.GET);
        } catch (Exception e) {
            LOGGER.error("Failed to query message templates.", e);
            throw new RuntimeException("Failed to query message templates.", e);
        }
    }

}
