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
package com.smn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * property loading configuration
 * 
 * @author yangyanping
 * @date 2017年8月24日 下午5:15:58
 * @version 0.1
 * @author yangyanping
 * @date 2017年8月24日
 * @version 0.2
 */
public class SmnConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SmnConfiguration.class);

    /**
     * smn String
     */
    private static String SMN = "smn";

    /**
     * iam String
     */
    private static String IAM = "iam";

    /**
     * region name
     */
    private static String ENDPOINT = "myhwclouds.com";

    private final String CONFIG = "config";
    private final String CONFIG_PROPERTIES = "configuration.properties";
    private final String SMN_CONFIGURATION_PROPERTIES = CONFIG + File.separator + CONFIG_PROPERTIES;
    private Properties properties = new Properties();

    /**
     * property file configured path
     * 
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
     * @param filepath
     */
    public SmnConfiguration(String filepath) {
        LOGGER.info("New smnConfiguration.File path is {}.", filepath);
        this.filepath = filepath;
        reload();
    }

    /**
     * default construction
     * 
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
     * @throws RuntimeException
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
     * @param properties
     *            the properties to set
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
     * @param filepath
     *            the filepath to set
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
     * @param userName
     *            the userName to set
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
     * @param password
     *            the password to set
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
     * @param domainName
     *            the domainName to set
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
     * @param regionId
     *            the regionId to set
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
     * @param smnEndpoint
     *            the smnEndpoint to set
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
     * @param iamEndpoint
     *            the iamEndpoint to set
     */
    public void setIamEndpoint(String iamEndpoint) {
        this.iamEndpoint = iamEndpoint;
    }

    /*
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
