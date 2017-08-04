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
 * @date 2017年8月3日 下午5:25:10
 * @version 0.1
 * 
 */
package com.huawei.smn.model;

import java.io.Serializable;

/**
 * @author huangqiong
 * @date 2017年8月3日 下午5:25:10
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
