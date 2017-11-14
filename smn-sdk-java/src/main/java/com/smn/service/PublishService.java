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
import com.smn.model.request.publish.PublishMsgRequest;

/**
 * Message publish service
 *
 * @author huangqiong
 * @version 0.6
 */
public interface PublishService extends CommonService {

    /**
     * publish message
     * <p>
     * success，return<CODE>HttpResponse</CODE>
     * <p>
     * failed，return request_id and status
     *
     * @param publishMsgRequest {@link PublishMsgRequest} request
     * @return {@link HttpResponse}
     * the response including:
     * <p>
     * {@code httpCode}
     * <p>
     * {@code body}Map&lt;String,Object&gt;
     * @throws RuntimeException connect error,fail to get iam token ,throw exception
     */
    HttpResponse publish(PublishMsgRequest publishMsgRequest) throws RuntimeException;

}
