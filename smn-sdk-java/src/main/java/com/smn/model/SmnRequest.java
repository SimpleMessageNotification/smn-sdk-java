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
package com.smn.model;

import java.util.Map;

/**
 * the interface of smn request
 *
 * @author huangqiong
 * @version 0.1
 */
public interface SmnRequest {

    /**
     * get smn request's url
     *
     * @return uri
     * example /v2/cffe4fc4c9a54219b****132/notifications/sms
     */
    String getRequestUri();

    /**
     * get the request parameters of smn
     *
     * @return Map
     */
    Map<String, Object> getRequestParameterMap();

    /**
     * get the request header's parameters of smn
     *
     * @return Map
     * contains the following parameters:
     * <code>Content-Type</code>
     * <code>X-Auth-Token</code>
     * <code>region</code>
     * <code>X-Project-Id</code>
     */
    Map<String, String> getRequestHeaderMap();
}
