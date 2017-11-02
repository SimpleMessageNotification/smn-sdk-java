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
 * @date 2017年8月3日 下午5:32:17
 * @version 0.1
 */
package com.smn.service;

import com.smn.common.HttpResponse;
import com.smn.model.request.sms.*;

/**
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 * @date 2017年8月3日 下午5:32:17
 */
public interface SmsService extends CommonService {

    /**
     * send sms
     * <p>
     * sucess,return<CODE>Map</CODE>including:request_id,message_id and status
     * <p>
     * 发送fail，return request_id and status
     *
     * @param smnRequest {@link SmsPublishRequest} request
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     * @throws RuntimeException connect error,fail to get iam token will throw exception
     */
    HttpResponse smsPublish(SmsPublishRequest smnRequest) throws RuntimeException;

    /**
     * 查询短信的发送状态
     *
     * @param smnRequest {@link ListSmsMsgReportRequest} request message
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     */
    HttpResponse listSmsMsgReport(ListSmsMsgReportRequest smnRequest);

    /**
     * 查询已发送短信的内容
     *
     * @param smnRequest {@link GetSmsMessageRequest} request message
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     */
    HttpResponse getSmsMessage(GetSmsMessageRequest smnRequest);

    /**
     * 查询短信回调时间列表
     *
     * @param smnRequest {@link ListSmsCallbackEventRequest} request message
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     */
    HttpResponse listSmsCallbackEvent(ListSmsCallbackEventRequest smnRequest);

    /**
     * 查询短信回调时间列表
     *
     * @param smnRequest {@link ListSmsCallbackEventRequest} request message
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     */
    HttpResponse updateSmsCallbackEvent(UpdateSmsCallbackEventRequest smnRequest);

    /**
     * 查询短信签名
     *
     * @param smnRequest {@link ListSmsSignsRequest} request message
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     */
    HttpResponse listSmsSigns(ListSmsSignsRequest smnRequest);

    /**
     * 删除短信签名
     *
     * @param smnRequest {@link DeleteSmsSignRequest} request message
     * @return {@link HttpResponse}
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,String%gt;
     */
    HttpResponse deleteSmsSign(DeleteSmsSignRequest smnRequest);
}
