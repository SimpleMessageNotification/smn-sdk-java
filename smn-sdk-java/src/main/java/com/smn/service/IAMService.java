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

import com.smn.model.AuthenticationBean;

/**
 * @author huangqiong
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
     * Obtain authorization information
     * <p>
     * if exist, return
     * or get a new instance from iam service
     *
     * @return {@link AuthenticationBean} User token information
     */
    AuthenticationBean getAuthenticationBean();

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
