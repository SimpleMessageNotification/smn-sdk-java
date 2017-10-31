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

import com.smn.common.HttpMethod;
import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.http.HttpConfiguration;
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
     * 无参构造函数
     */
    public MessageTemplateServiceImpl() {
        super();
    }

    /**
     * 给定iamService和smnConfiguration构造实例
     *
     * @param iamService        the iamService to set
     * @param smnConfiguration  the smnConfiguration to set
     * @param httpConfiguration the http configuration
     */
    public MessageTemplateServiceImpl(IAMService iamService, SmnConfiguration smnConfiguration, HttpConfiguration httpConfiguration) {
        super(iamService, smnConfiguration, httpConfiguration);
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
