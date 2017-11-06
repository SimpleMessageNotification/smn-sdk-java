/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http: //www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
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
