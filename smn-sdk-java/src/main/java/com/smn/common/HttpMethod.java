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
package com.smn.common;

/**
 * http method list enum
 *
 * @author zhangyx
 * @version 0.7
 */
public enum HttpMethod {
    DELETE("delete"),
    PUT("put"),
    POST("post"),
    GET("get"),
    HEAD("head");

    private String name;

    private HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
