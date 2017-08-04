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
 * @date 2017年8月3日 下午5:32:42
 * @version 0.1
 * 
 */
package com.huawe.smn.service;

import com.huawe.smn.common.SmnConfiguration;

/**
 * common service interface
 * 
 * @author huangqiong
 * @date 2017年8月3日 下午5:32:42
 * @version 0.1
 */
public interface CommonService {

    /**
     * configure runtime environment
     * 
     * @param smnConfiguration
     *            configure runtime parameters
     */
    void setSmnConfiguration(SmnConfiguration smnConfiguration);
}