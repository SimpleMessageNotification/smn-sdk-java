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
 */
public interface SubscriptionService extends CommonService {

    /**
     * subscribe
     * 
     * @param subcriptionRequest
     * @return Map
     *         {@value request_id}
     *         {@value subscription_urn}
     *         {@value status}
     * @throws RuntimeException
     */
	HttpResponse subscribe(SubcriptionRequest subcriptionRequest) throws RuntimeException;

    /**
     * unSubcription
     * 
     * @param unSubcriptionRequest
     * @return Map
     *         {@value request_id}
     *         {@value status}
     * @throws RuntimeException
     */
	HttpResponse unsubscribe(UnSubcriptionRequest unSubcriptionRequest) throws RuntimeException;

    /**
     * list subscribers
     * 
     * @param listSubscriptionsRequest
     * @return Map
     *         {@value request_id}
     *         {@value subscription_count}
     *         {@value subscriptions}
     *         {@value status}
     * @throws RuntimeException
     */
	HttpResponse listSubscriptions(ListSubscriptionsRequest listSubscriptionsRequest) throws RuntimeException;

    /**
     * list subscribers of designated topic
     * 
     * @param listSubscriptionsByTopicRequest
     * @return Map
     *         {@value request_id}
     *         {@value subscription_count}
     *         {@value subscriptions}
     *         {@value status}
     * @throws RuntimeException
     */
	HttpResponse listSubscriptionsByTopic(ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest)
            throws RuntimeException;

}
