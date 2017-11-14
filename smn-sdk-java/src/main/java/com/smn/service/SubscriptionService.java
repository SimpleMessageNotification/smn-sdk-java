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
import com.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.model.request.subscription.ListSubscriptionsRequest;
import com.smn.model.request.subscription.SubcriptionRequest;
import com.smn.model.request.subscription.UnSubcriptionRequest;

/**
 * subscription service
 *
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public interface SubscriptionService extends CommonService {

    /**
     * subscribe
     *
     * @param subcriptionRequest {@link SubcriptionRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse subscribe(SubcriptionRequest subcriptionRequest) throws RuntimeException;

    /**
     * unSubcription
     *
     * @param unSubcriptionRequest {@link UnSubcriptionRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse unsubscribe(UnSubcriptionRequest unSubcriptionRequest) throws RuntimeException;

    /**
     * list subscribers
     *
     * @param listSubscriptionsRequest {@link ListSubscriptionsRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse listSubscriptions(ListSubscriptionsRequest listSubscriptionsRequest) throws RuntimeException;

    /**
     * list subscribers of designated topic
     *
     * @param listSubscriptionsByTopicRequest {@link ListSubscriptionsByTopicRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse listSubscriptionsByTopic(ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest)
            throws RuntimeException;

}
