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

import com.smn.model.request.iam.GetProjectIdsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * version util
 *
 * @author zhangyx
 * @version 1.0.0
 */
public class VersionUtil {
    /**
     * LOGGER
     */
    private static Logger LOGGER = LoggerFactory.getLogger(GetProjectIdsRequest.class);

    /**
     * version file
     */
    private static final String VERSION_FILE = "version.properties";

    /**
     * version
     */
    private static String version = null;

    /**
     * default user agent
     */
    private static String defaultUserAgent = null;

    /**
     * get version
     *
     * @return the version of the sdk
     */
    public static String getVersion() {
        if (version == null) {
            loadVersion();
        }
        return version;
    }

    /**
     * get default user agent
     *
     * @return the default user agent
     */
    public static String getDefaultUserAgent() {
        if (defaultUserAgent == null) {
            String osInfo = System.getProperty("os.name") + "/"
                    + System.getProperty("os.version") + "/"
                    + System.getProperty("os.arch") + ";"
                    + System.getProperty("java.version");
            defaultUserAgent = "smn-sdk-java/" + getVersion() + "(" + osInfo + ")";
        }
        return defaultUserAgent;
    }

    private static void loadVersion() {
        InputStream inputStream = VersionUtil.class.getClassLoader().getResourceAsStream(VERSION_FILE);
        Properties properties = new Properties();
        try {
            if (inputStream == null) {
                throw new Exception(VERSION_FILE + "not found on classpath");
            }
            properties.load(inputStream);
            version = properties.getProperty("version");
        } catch (Exception e) {
            LOGGER.info("Cannot load version for SDK:" + e.getMessage());
            version = "unknown";
        }
    }
}
