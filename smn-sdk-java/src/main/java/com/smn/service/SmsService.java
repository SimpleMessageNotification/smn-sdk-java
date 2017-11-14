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
