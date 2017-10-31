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
package com.smn.account;

import com.smn.client.DefaultSmnClient;
import com.smn.client.SmnClient;
import com.smn.common.SmnConfiguration;
import com.smn.service.ServiceFactory;

/**
 * hw cloud account
 *
 * @author zhangyx
 * @version 0.9
 */
public class HwCloudAccount {

    /**
     * user name
     */
    private String userName;

    /**
     * user's password
     */
    private String password;

    /**
     * domain name ,is same with user name mostly,but sometimes may differ
     */
    private String domainName;

    /**
     * region id
     */
    private String regionId;

    /**
     * smn configuration
     */
    private SmnConfiguration smnConfiguration;

    /**
     * smn client
     */
    private SmnClient smnClient;

    /**
     * 构造函数
     *
     * @param userName   the userName to set
     * @param password   the password to set
     * @param domainName the domainName to set
     * @param regionId   the regionId to set
     */
    public HwCloudAccount(String userName, String password, String domainName, String regionId) {
        this.userName = userName;
        this.password = password;
        this.domainName = domainName;
        this.regionId = regionId;

        smnConfiguration = new SmnConfiguration(userName, password, domainName, regionId);
    }

    /**
     * get smn client
     *
     * @return the smnClient instance
     */
    public SmnClient getSmnClient() {
        if (smnClient == null) {
            synchronized (this) {
                if (smnClient == null) {
                    ServiceFactory serviceFactory = new ServiceFactory(smnConfiguration);
                    smnClient = new DefaultSmnClient(serviceFactory);
                }
            }
        }
        return smnClient;
    }
}
