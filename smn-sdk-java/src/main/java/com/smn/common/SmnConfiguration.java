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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * property loading configuration
 *
 * @author yangyanping
 * @author yangyanping
 * @version 0.2
 */
public class SmnConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SmnConfiguration.class);

    /**
     * token auth type
     */
    public final static String TOKEN_AUTH_TYPE = "token";

    /**
     * ak/sk auth type
     */
    public final static String AKSK_AUTH_TYPE = "aksk";

    /**
     * smn String
     */
    private final static String SMN = "smn";

    /**
     * iam String
     */
    private final static String IAM = "iam";

    /**
     * region name
     */
    private static String ENDPOINT = "myhuaweicloud.com";

    private final String CONFIG = "config";
    private final String CONFIG_PROPERTIES = "configuration.properties";
    private final String SMN_CONFIGURATION_PROPERTIES = CONFIG + File.separator + CONFIG_PROPERTIES;
    private Properties properties = new Properties();

    /**
     * 认证方式
     */
    private String authType;

    /**
     * property file configured path
     */
    private String filepath;

    /**
     * user name
     */
    private String userName;

    /**
     * user's password
     */
    private String password;

    /**
     * domain name ,is same with user name mostly,but sometimes may differ
     */
    private String domainName;

    /**
     * region id
     */
    private String regionId;

    /**
     * iam host name
     */
    private String iamEndpoint;

    /**
     * smn endpoint
     */
    private String smnEndpoint;

    /**
     * access key id
     */
    private String accessKeyId;

    /**
     * secret access key
     */
    private String secretAccessKey;

    /**
     * max tag length
     */
    private int maxTagLength = 1024;

    /**
     * max message length
     */
    private int maxMessageLength = 256 * 1024;

    /**
     * Get max message length
     *
     * @return maxMessageLength
     */
    public int getMaxMessageLength() {
        return maxMessageLength;
    }

    /**
     * get max subject length
     *
     * @return maxSubjectLength
     */
    public int getMaxSubjectLength() {
        return maxSubjectLength;
    }

    /**
     * max subject length
     */
    private int maxSubjectLength = 512;

    /**
     * smn remark
     */
    private int maxRemarkLength = 126;

    /**
     * max templateMessageContext length
     */
    private int maxTemplateMessageContextLength = 256 * 1024;

    /**
     * get ax templateMessageContext length
     *
     * @return max templateMessageContext length
     */
    public int getMaxTemplateMessageContextLength() {
        return maxTemplateMessageContextLength;
    }

    /**
     * get max remark length
     *
     * @return maxLength
     */
    public int getMaxRemarkLength() {
        return maxRemarkLength;
    }

    /**
     * configure property file path
     *
     * @param filepath the properties file path
     */
    public SmnConfiguration(String filepath) {
        LOGGER.info("New smnConfiguration.File path is {}.", filepath);
        this.filepath = filepath;
        reload();
    }

    /**
     * default construction
     */
    public SmnConfiguration() {
        LOGGER.info("New smnConfiguration.");
    }

    /**
     * new smnConfiguration
     *
     * @param userName   the userName to set
     * @param password   the password to set
     * @param domainName the domain name to set
     * @param regionId   the region id to set
     */
    public SmnConfiguration(String userName, String password, String domainName, String regionId) {
        this.userName = userName;
        this.password = password;
        this.domainName = domainName;
        this.regionId = regionId;
        this.authType = TOKEN_AUTH_TYPE;
    }

    /**
     * new smnConfiguration
     *
     * @param secretAccessKey the secretAccessKey id to set
     * @param accessKeyId     the accessKeyId id to set
     * @param regionId        the region id to set
     */
    public SmnConfiguration(String accessKeyId, String secretAccessKey, String regionId) {
        this.regionId = regionId;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.authType = AKSK_AUTH_TYPE;
    }

    /**
     * Get max tag length.
     *
     * @return maxTagLength
     */
    public int getMaxTagLength() {
        return maxTagLength;
    }

    /**
     * Load configured file
     *
     * @return {@code bool}
     * @throws RuntimeException failed to load properties throw exception
     */
    public boolean reload() throws RuntimeException {
        LOGGER.info("Smn configuration reloading.");

        if (StringUtils.isBlank(filepath)) {
            filepath = SMN_CONFIGURATION_PROPERTIES;
        }
        LOGGER.info("Properties filepath is:{}.", filepath);

        try {
            FileInputStream fis = new FileInputStream(new File(this.filepath));
            properties.load(fis);
        } catch (IOException e) {
            LOGGER.error("Fail to reload config properties,filepath is:{}.", filepath);
            throw new RuntimeException(e);
        }

        userName = properties.getProperty(SmnConstants.USER_NAME);
        password = properties.getProperty(SmnConstants.PASSWORD);
        domainName = properties.getProperty(SmnConstants.DOMAIN_NAME);
        regionId = properties.getProperty(SmnConstants.REGION_ID);

        if (StringUtils.isBlank(userName)) {
            LOGGER.error("User name in configuration properties is empty.");
            throw new RuntimeException("User name in configuration properties is empty.");
        }

        if (StringUtils.isBlank(password)) {
            LOGGER.error("Password in configuration properties is empty.");
            throw new RuntimeException("Password in configuration properties is empty.");
        }

        if (StringUtils.isBlank(domainName)) {
            LOGGER.error("Domain name in configuration properties is empty.");
            throw new RuntimeException("Domain name in configuration properties is empty.");
        }

        if (StringUtils.isBlank(regionId)) {
            LOGGER.error("RegionId in configuration properties is empty.");
            throw new RuntimeException("RegionId in configuration properties is empty.");
        }
        LOGGER.info("Smn.endpoint is {}.Iam.endpoint is {},", smnEndpoint, iamEndpoint);
        return true;
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName the domainName to set
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return the regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the smnEndpoint
     */
    public String getSmnEndpoint() {
        if (smnEndpoint == null) {
            StringBuilder smn = new StringBuilder();
            smn.append(SMN).append(".").append(regionId).append(".").append(ENDPOINT);
            smnEndpoint = smn.toString();
        }
        return smnEndpoint;
    }

    /**
     * @param smnEndpoint the smnEndpoint to set
     */
    public void setSmnEndpoint(String smnEndpoint) {
        this.smnEndpoint = smnEndpoint;
    }

    /**
     * @return the iamEndpoint
     */
    public String getIamEndpoint() {
        if (iamEndpoint == null) {
            StringBuilder iam = new StringBuilder();
            iam.append(IAM).append(".").append(regionId).append(".").append(ENDPOINT);
            iamEndpoint = iam.toString();
        }
        return iamEndpoint;
    }

    /**
     * @param iamEndpoint the iamEndpoint to set
     */
    public void setIamEndpoint(String iamEndpoint) {
        this.iamEndpoint = iamEndpoint;
    }

    /**
     * @return the accessKeyId
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     * @param accessKeyId the accessKeyId to set
     */
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    /**
     * @return the secretAccessKey
     */
    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    /**
     * @param secretAccessKey the secretAccessKey to set
     */
    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType the authType to set
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SmnConfiguration [filepath=").append(filepath).append(", domainName=").append(domainName)
                .append(", regionId=").append(regionId).append(", iamEndpoint=").append(iamEndpoint)
                .append(", smnEndpoint=").append(smnEndpoint).append("]");
        return builder.toString();
    }
}
