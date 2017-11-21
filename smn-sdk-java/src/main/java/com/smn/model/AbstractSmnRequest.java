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

import com.smn.common.SmnConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * abstract request
 *
 * @author huangqiong
 * @version 0.1
 */
public abstract class AbstractSmnRequest implements SmnRequest {
    /**
     * project id
     */
    protected String projectId;

    /**
     * Build common http's request header
     */
    public Map<String, String> getRequestHeaderMap() {
        Map<String, String> requestHeaderMap = new HashMap<String, String>();
        requestHeaderMap.put(SmnConstants.CONTENT_TYPE_TAG, SmnConstants.DEFAULT_CONTENT_TYPE);
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

    /**
     * @return the projectId
     */
    public String getProjectId() {

        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
