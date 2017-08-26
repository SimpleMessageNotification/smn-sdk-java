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
package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.request.template.CreateMessageTemplateRequest;
import com.huawei.smn.model.request.template.DeleteMessageTemplateRequest;
import com.huawei.smn.model.request.template.ListMessageTemplatesRequest;
import com.huawei.smn.model.request.template.QueryMessageTemplateDetailRequest;
import com.huawei.smn.model.request.template.UpdateMessageTemplateRequest;

/**
 * Message template service
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public interface MessageTemplateService extends CommonService {

    /**
     * create message template
     * 
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error throw exception
     */
    Map<String, Object> createMessageTemplate(CreateMessageTemplateRequest smnRequest) throws RuntimeException;

    /**
     * update message template
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error throw exception
     */
    Map<String, Object> updateMessageTemplate(UpdateMessageTemplateRequest smnRequest) throws RuntimeException;

    /**
     * delete message template
     * <p>
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error throw exception
     */
    Map<String, Object> deleteMessageTemplate(DeleteMessageTemplateRequest smnRequest) throws RuntimeException;

    /**
     * query message template list
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error throw exception
     */
    Map<String, Object> listMessageTemplates(ListMessageTemplatesRequest smnRequest) throws RuntimeException;

    /**
     * query message template detail
     * <p>
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> queryMsgTemplateDetail(QueryMessageTemplateDetailRequest smnRequest) throws RuntimeException;

}
