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

import java.io.Serializable;

/**
 * the info of the authentication
 *
 * @author huangqiong
 * @version 0.1
 */

public class AuthenticationBean implements Serializable {

    private static final long serialVersionUID = -2193148275607260536L;

    /**
     * the auth of token
     */
    private String authToken;

    /**
     * the id of project
     */
    private String projectId;

    /**
     * the expire time stamp
     */
    private String expiresAt;

    /**
     * the expires of time
     */
    private long expiresTime;

    /**
     * To determine whether token has expired
     * 
     * @return ture if expired
     */
    public boolean isExpired() {
        return expiresTime <= System.currentTimeMillis();
    }

    /**
     * @return the authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken
     *            the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the expiresTime
     */
    public long getExpiresTime() {
        return expiresTime;
    }

    /**
     * @param expiresTime
     *            the expiresTime to set
     */
    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    /**
     * @return the expiresAt
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * @param expiresAt
     *            the expiresAt to set
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AuthBean [projectId=" + projectId + ", expiresAt=" + expiresAt + ", expiresTime=" + expiresTime + "]";
    }
}
