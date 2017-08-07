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

import java.util.Map;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:27:23
 * @version 0.1
 */
public interface SmnRequest {

    /**
     * get smn request's url
     * 
     * @return uri
     *         example /v2/cffe4fc4c9a54219b****132/notifications/sms
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
     *         {@value} Content-Type
     *         {@value} X-Auth-Token
     *         {@value} region
     *         {@value} X-Project-Id
     */
    Map<String, String> getRequestHeaderMap();

    /**
     * set projectID
     * RequestParameterMap and RequestUri depend
     * 
     * @param projectId
     */
    void setProjectId(String projectId);

    /**
     * set xAuthToken
     * RequestUri depends
     * 
     * @param xAuthToken
     */
    void setxAuthToken(String xAuthToken);

}
