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
 * @date 2017年8月3日 下午5:22:46
 * @version 0.1
 * 
 */
package com.huawei.smn.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json utils
 * 
 * @author huangqiong
 * @date 2017年8月3日 下午5:22:46
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
