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
package com.smn.service;

import com.smn.common.HttpResponse;
import com.smn.model.request.template.*;

/**
 * Message template service
 *
 * @author huangqiong
 * @version 0.1
 */
public interface MessageTemplateService extends CommonService {

    /**
     * create message template
     *
     * @param smnRequest {@link CreateMessageTemplateRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error throw exception
     */
    HttpResponse createMessageTemplate(CreateMessageTemplateRequest smnRequest) throws RuntimeException;

    /**
     * update message template
     *
     * @param smnRequest request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error throw exception
     */
    HttpResponse updateMessageTemplate(UpdateMessageTemplateRequest smnRequest) throws RuntimeException;

    /**
     * delete message template
     * <p>
     *
     * @param smnRequest {@link DeleteMessageTemplateRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error throw exception
     */
    HttpResponse deleteMessageTemplate(DeleteMessageTemplateRequest smnRequest) throws RuntimeException;

    /**
     * query message template list
     *
     * @param smnRequest {@link ListMessageTemplatesRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error throw exception
     */
    HttpResponse listMessageTemplates(ListMessageTemplatesRequest smnRequest) throws RuntimeException;

    /**
     * query message template detail
     * <p>
     *
     * @param smnRequest {@link QueryMessageTemplateDetailRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse queryMsgTemplateDetail(QueryMessageTemplateDetailRequest smnRequest) throws RuntimeException;

}
