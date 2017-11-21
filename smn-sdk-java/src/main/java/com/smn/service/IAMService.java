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
     * get token from header
     */
    String X_SUBJECT_TOKEN = "X-Subject-Token";
    /**
     * token/expires_at from IAM response
     */
    String EXPIRES_AT = "expires_at";

    /**
     * token from IAM response
     */
    String TOKEN = "token";

    /**
     * token/project/id from IAM response
     */
    String ID = "id";

    /**
     * token/project from IAM response
     */
    String PROJECT = "project";

    /**
     * iam token uri
     */
    String IAM_TOKEN_URI = "/v3/auth/tokens";

    /**
     * Obtain authorization information from the IAM service, which includes
     * projectId, user token, and token expiration time
     *
     * @return {@code AuthBean} User token information
     * @throws RuntimeException Failed to get token, then ran out of the exception
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
     * set the url of iam
     *
     * @param iamUrl
     *            the url of iam
     */
    void setIamUrl(String iamUrl);

    String getProjectId() throws Exception;
}
