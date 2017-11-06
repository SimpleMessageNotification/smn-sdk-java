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
package com.smn.service;

import com.smn.common.HttpResponse;
import com.smn.model.request.sms.*;

/**
 * sms service interface
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public interface SmsService extends CommonService {

    /**
     * send sms
     * <p>
     * success,return<CODE>Map</CODE>including:request_id,message_id and status
     * <p>
     * send fail, return request_id and status
     *
     * @param smnRequest {@link SmsPublishRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token will throw exception
     */
    HttpResponse smsPublish(SmsPublishRequest smnRequest) throws RuntimeException;

    /**
     * query the report of the message
     *
     * @param smnRequest {@link ListSmsMsgReportRequest} request message
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     */
    HttpResponse listSmsMsgReport(ListSmsMsgReportRequest smnRequest);

    /**
     * query the content of the sms sent
     *
     * @param smnRequest {@link GetSmsMessageRequest} request message
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     */
    HttpResponse getSmsMessage(GetSmsMessageRequest smnRequest);

    /**
     * query message callback event list
     *
     * @param smnRequest {@link ListSmsCallbackEventRequest} request message
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     */
    HttpResponse listSmsCallbackEvent(ListSmsCallbackEventRequest smnRequest);

    /**
     * update sms message callback event
     *
     * @param smnRequest {@link ListSmsCallbackEventRequest} request message
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     */
    HttpResponse updateSmsCallbackEvent(UpdateSmsCallbackEventRequest smnRequest);

    /**
     * query sms signature
     *
     * @param smnRequest {@link ListSmsSignsRequest} request message
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     */
    HttpResponse listSmsSigns(ListSmsSignsRequest smnRequest);

    /**
     * delete sms signature
     *
     * @param smnRequest {@link DeleteSmsSignRequest} request message
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     */
    HttpResponse deleteSmsSign(DeleteSmsSignRequest smnRequest);
}
