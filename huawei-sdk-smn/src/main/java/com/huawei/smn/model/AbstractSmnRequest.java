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
package com.huawei.smn.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;

/**
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public abstract class AbstractSmnRequest implements SmnRequest {

    private static Logger LOGGER = LoggerFactory.getLogger(AbstractSmnRequest.class);

    /**
     * Build common http's request header
     */
    @Override
    public Map<String, String> getRequestHeaderMap() throws RuntimeException {
        Map<String, String> requestHeaderMap = new HashMap<String, String>();
        requestHeaderMap.put(SmnConstants.CONTENT_TYPE_TAG, "application/json");
        LOGGER.debug(requestHeaderMap.toString());
        return requestHeaderMap;
    }

    /**
     * Get request URL from different API
     */
    public abstract String getRequestUri();

    /**
     * Get request body parameters from different API
     */
    public abstract Map<String, Object> getRequestParameterMap();

}
