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
package com.smn.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json utils
 * 
 * @author huangqiong
 * @version 0.1
 */
public class JsonUtil {

    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * Object mapper root
     */
    private static final ObjectMapper OBJMAPPER = new ObjectMapper();

    /**
     * Parsing a JSON string into a map object
     * <p>
     * If the string is empty, an empty string is returned
     * 
     * @param message
     *            message content in JSON format
     * @return {@code Map}
     */
    @SuppressWarnings("unchecked")
    public static final Map<String, Object> parseJsonMessage(String message) {

        if (message == null) {
            return new HashMap<String, Object>();
        }

        try {
            return OBJMAPPER.readValue(message, Map.class);
        } catch (Exception e) {
            LOGGER.error("Failed to parse json message.", e);
            return new HashMap<String, Object>();
        }
    }

    /**
     * Convert Map to String content
     * 
     * @param messageMap
     *            Need to convert JSON format string map
     * @return {@code String} message content
     */
    public static final String getJsonStringByMap(Map<String, Object> messageMap) {

        if (messageMap == null) {
            return "{}";
        }

        try {
            return OBJMAPPER.writeValueAsString(messageMap);
        } catch (Exception e) {
            LOGGER.error("Failed to generator message.", e);
        }

        return "{}";
    }

}
