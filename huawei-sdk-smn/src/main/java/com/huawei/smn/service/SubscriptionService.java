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

import com.huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.huawei.smn.model.request.subscription.ListSubscriptionsRequest;
import com.huawei.smn.model.request.subscription.SubcriptionRequest;
import com.huawei.smn.model.request.subscription.UnSubcriptionRequest;

/**
 * 订阅服务
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public interface SubscriptionService extends CommonService {

    /**
     * subscribe
     * <p>
     * success，return<CODE>Map</CODE>including: request_id,message_id,status
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> subscribe(SubcriptionRequest smnRequest) throws RuntimeException;

    /**
     * cancel subscribe
     * <p>
     * success，return<CODE>Map</CODE>including: request_id,message_id,status
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> unsubscribe(UnSubcriptionRequest smnRequest) throws RuntimeException;

    /**
     * query list of subscribers
     * <p>
     * success，return<CODE>Map</CODE>including: request_id,message_id,status
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> listSubscriptions(ListSubscriptionsRequest smnRequest) throws RuntimeException;

    /**
     * query list of subscribers for designated topic
     * <p>
     * success，return<CODE>Map</CODE>including: request_id,message_id,status
     * <p>
     * failed，return request_id and status
     * 
     * @param smnRequest
     *            request
     * @return {@link Map} return Map
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
    Map<String, Object> listSubscriptionsByTopic(ListSubscriptionsByTopicRequest smnRequest) throws RuntimeException;

}
