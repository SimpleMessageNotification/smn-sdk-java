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
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;

/**
 * subscription service
 *
 * @author huangqiong
 * @version 0.1
 * @author zhangyx
 * @version 0.7
 */
public interface SubscriptionService extends CommonService {

    /**
     * subscribe
     *
     * @param subcriptionRequest
     *            {@link SubcriptionRequest} request
     * @return {@link HttpResponse}
     *         <p>
     *         {@code httpCode}
     *         <p>
     *         {@code body}Map&lt;String,String%gt;
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
	HttpResponse subscribe(SubcriptionRequest subcriptionRequest) throws RuntimeException;

    /**
     * unSubcription
     *
     * @param unSubcriptionRequest
     *            {@link UnSubcriptionRequest} request
     * @return {@link HttpResponse}
     *         <p>
     *         {@code httpCode}
     *         <p>
     *         {@code body}Map&lt;String,String%gt;
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
	HttpResponse unsubscribe(UnSubcriptionRequest unSubcriptionRequest) throws RuntimeException;

    /**
     * list subscribers
     *
     * @param listSubscriptionsRequest
     *            {@link ListSubscriptionsRequest} request
     * @return {@link HttpResponse}
     *         <p>
     *         {@code httpCode}
     *         <p>
     *         {@code body}Map&lt;String,String%gt;
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     */
	HttpResponse listSubscriptions(ListSubscriptionsRequest listSubscriptionsRequest) throws RuntimeException;

    /**
     * list subscribers of designated topic
     *
     * @param listSubscriptionsByTopicRequest
     *            {@link ListSubscriptionsByTopicRequest} request
     * @return {@link HttpResponse}
     *         <p>
     *         {@code httpCode}
     *         <p>
     *         {@code body}Map&lt;String,String%gt;
     * @throws RuntimeException
     *             connect error,fail to get iam token ,throw exception
     *
     */
	HttpResponse listSubscriptionsByTopic(ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest)
            throws RuntimeException;

}
