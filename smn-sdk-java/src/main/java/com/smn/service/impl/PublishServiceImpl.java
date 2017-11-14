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
import com.smn.model.request.publish.PublishMsgRequest;
import com.smn.service.AbstractCommonService;
import com.smn.service.IAMService;
import com.smn.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Publish service implemented
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class PublishServiceImpl extends AbstractCommonService implements PublishService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishServiceImpl.class);

    /**
     * no arg constructor
     */
    public PublishServiceImpl() {
        super();
    }

    /**
     * give iamService and smnConfiguration constructor
     *
     * @param iamService        the iamService to set
     * @param smnConfiguration  the smnConfiguration to set
     * @param clientConfiguration the client configuration
     */
    public PublishServiceImpl(IAMService iamService, SmnConfiguration smnConfiguration, ClientConfiguration clientConfiguration) {
        super(iamService, smnConfiguration, clientConfiguration);
    }

    /**
     * (non-Javadoc)
     *
     * @see PublishService#publish(PublishMsgRequest)
     */
    public HttpResponse publish(PublishMsgRequest smnRequest) throws RuntimeException {
        LOGGER.info("Start to publish message.");

        try {
            return sendRequest(smnRequest, HttpMethod.POST);
        } catch (Exception e) {
            LOGGER.error("Failed to publish message.", e);
            throw new RuntimeException("Failed to publish message.", e);
        }
    }

}
