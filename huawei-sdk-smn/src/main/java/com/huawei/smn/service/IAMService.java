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
 * @date 2017年8月3日 下午5:33:48
 * @version 0.1
 * 
 */
package com.huawei.smn.service;

import com.huawei.smn.model.AuthenticationBean;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:33:48
 * @version 0.1
 */

public interface IAMService {

    /**
     * Obtain authorization information from the IAM service, which includes
     * projectId, user token, and token expiration time
     * 
     * @return {@code AuthBean} User token information
     * @throws RuntimeException
     *             Failed to get token, then ran out of the exception
     */
    AuthenticationBean getAuthentication() throws RuntimeException;

    /**
     * set the user of name.
     * 
     * @param userName
     *            the name of user
     */
    void setUserName(String userName);

    /**
     * set the password of user
     * 
     * @param password
     *            the password of user
     */
    void setPassword(String password);

    /**
     * set the name of domain
     * 
     * @param domainName
     *            the name of domain
     */
    void setDomainName(String domainName);

    /**
     * set the id of region
     * 
     * @param regionId
     *            the id of region
     */
    void setRegionId(String regionId);

    /**
     * set the url of iam
     * 
     * @param iamUrl
     *            the url of iam
     */
    void setIamUrl(String iamUrl);
}
