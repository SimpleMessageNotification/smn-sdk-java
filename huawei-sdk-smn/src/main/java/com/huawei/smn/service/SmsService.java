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
 * 
 */
package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.request.sms.SmsPublishRequest;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:32:17
 * @version 0.1
 */

public interface SmsService extends CommonService {

    /**
     * send sms
     * <p>
     * sucess,return<CODE>Map</CODE>including:request_id,message_id and status
     * <p>
     * 发送fail，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     *         {@value request_id}
     *         {@value message_id}
     *         {@value status}
     * @throws RuntimeException
     *             connect error,fail to get iam token will throw exception
     */
    Map<String, Object> smsPublish(SmsPublishRequest smnRequest) throws RuntimeException;

}
