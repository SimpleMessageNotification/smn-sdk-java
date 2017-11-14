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
import com.smn.model.request.topic.*;

/**
 * topic service
 *
 * @author huangqiong
 * @version 0.1
 */
public interface TopicService extends CommonService {

    /**
     * create topic
     * <p>
     * failed，return request_id and status
     *
     * @param smnRequest {@link CreateTopicRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse createTopic(CreateTopicRequest smnRequest) throws RuntimeException;

    /**
     * delete topic
     * <p>
     * failed，return request_id and status
     *
     * @param smnRequest {@link DeleteTopicRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse deleteTopic(DeleteTopicRequest smnRequest) throws RuntimeException;

    /**
     * query topic list
     * <p>
     *
     * @param smnRequest {@link ListTopicsRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse listTopics(ListTopicsRequest smnRequest) throws RuntimeException;

    /**
     * query topic detail
     *
     * @param smnRequest {@link QueryTopicDetailRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse queryTopicDetail(QueryTopicDetailRequest smnRequest) throws RuntimeException;

    /**
     * update topic display name
     *
     * @param smnRequest {@link UpdateTopicRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse updateTopic(UpdateTopicRequest smnRequest) throws RuntimeException;

    /**
     * query topic attributes
     *
     * @param smnRequest {@link ListTopicAttributesRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse listTopicAttributes(ListTopicAttributesRequest smnRequest) throws RuntimeException;

    /**
     * update topic attributes
     *
     * @param smnRequest {@link UpdateTopicAttributeRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse updateTopicAttribute(UpdateTopicAttributeRequest smnRequest) throws RuntimeException;

    /**
     * delete designated topic attribute by name
     *
     * @param smnRequest {@link DeleteTopicAttributeByNameRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse deleteTopicAttributeByName(DeleteTopicAttributeByNameRequest smnRequest)
            throws RuntimeException;

    /**
     * delete all topic attributes
     * <p>
     *
     * @param smnRequest {@link DeleteTopicAttributesRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse deleteTopicAttributes(DeleteTopicAttributesRequest smnRequest) throws RuntimeException;

}
