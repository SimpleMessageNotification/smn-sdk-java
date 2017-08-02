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
package com.huawei.smn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;
import com.huawei.smn.service.impl.IAMServiceImpl;

/**
 * property loading configuration
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public class SmnConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SmnConfiguration.class);

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
     * authentication bean
     */
    private static AuthenticationBean authenticationBean;
    /**
     * iam service
     */
    private static IAMServiceImpl iamService;

    /**
     * iam url
     */
    private String iamUrl;

    /**
     * smn endpoint
     */
    private String smnEndpoint;

    public SmnConfiguration(String filepath) {
        this.filepath = filepath;
    }

    /**
     * default construction
     * 
     */
    public SmnConfiguration() {
    }

    /**
     * Load configured file
     */
    public boolean reload() throws RuntimeException {
        LOGGER.debug("Smn configuration reloading ...");
        if (StringUtils.isBlank(filepath)) {
            filepath = SMN_CONFIGURATION_PROPERTIES;
        }
        LOGGER.info("Properties filepath is:" + filepath);
        try {
            FileInputStream fis = new FileInputStream(new File(this.filepath));
            properties.load(fis);
        } catch (IOException e) {
            LOGGER.error("Fail to reload config properties,filepath={}:" + filepath);
            throw new RuntimeException(e);
        }

        userName = properties.getProperty(SmnConstants.USER_NAME);
        password = properties.getProperty(SmnConstants.PASSWORD);
        domainName = properties.getProperty(SmnConstants.DOMAIN_NAME);
        regionId = properties.getProperty(SmnConstants.REGION_ID);
        iamUrl = properties.getProperty(SmnConstants.IAMURL);
        smnEndpoint = properties.getProperty(SmnConstants.SMN_ENDPOINT);

        if (StringUtils.isBlank(userName)) {
            LOGGER.error("User name in configuration properties is empty!");
            throw new RuntimeException("User name in configuration properties is empty!");
        }
        if (StringUtils.isBlank(password)) {
            LOGGER.error("Password in configuration properties is empty!");
            throw new RuntimeException("Password in configuration properties is empty!");
        }
        if (StringUtils.isBlank(domainName)) {
            LOGGER.error("DomainName in configuration properties is empty!");
            throw new RuntimeException("DomainName in configuration properties is empty!");
        }
        if (StringUtils.isBlank(regionId)) {
            LOGGER.error("RegionId in configuration properties is empty!");
            throw new RuntimeException("RegionId in configuration properties is empty!");
        }
        if (StringUtils.isBlank(iamUrl)) {
            LOGGER.error("IamUrl in configuration properties is empty!");
            throw new RuntimeException("IamUrl in configuration properties is empty!");
        }
        if (StringUtils.isBlank(smnEndpoint)) {
            LOGGER.error("Smn endpoint in configuration properties is empty!");
            throw new RuntimeException("Smn endpoint in configuration properties is empty!");
        }

        return true;
    }

    /**
     * get IAM service
     */
    public IAMServiceImpl getIamService() {
        if (iamService == null) {
            iamService = new IAMServiceImpl(getUserName(), getPassword(), getDomainName(), getRegionId(), getIamUrl());
        }
        return iamService;
    }

    /**
     * when property's configuration changed, refresh and get IAM service
     */
    public IAMServiceImpl refreshIamService() {
        reload();
        iamService = new IAMServiceImpl(getUserName(), getPassword(), getDomainName(), getRegionId(), getIamUrl());
        return iamService;
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
     * @return the iamUrl
     */
    public String getIamUrl() {
        return iamUrl;
    }

    /**
     * @param iamUrl
     *            the iamUrl to set
     */
    public void setIamUrl(String iamUrl) {
        this.iamUrl = iamUrl;
    }

    /**
     * @return the authenticationBean
     */
    public AuthenticationBean getAuthenticationBean() {
        if (authenticationBean == null) {
            // may in concurrent
            synchronized (SmnConfiguration.class) {
                if (authenticationBean == null) {
                    LOGGER.debug("AuthInfo is null. Try to get it.");
                    // get correct bean ,or throw exception
                    authenticationBean = getIamService().getAuthentication();
                }
            }
        } else {
            // if expired
            if (authenticationBean.isExpired()) {
                synchronized (SmnConfiguration.class) {
                    if (authenticationBean.isExpired()) {
                        LOGGER.info("AuthInfo is expired. Try to get it. Old authInfo is {}.", authenticationBean);
                        // get correct bean ,or throw exception
                        authenticationBean = getIamService().getAuthentication();
                    }
                }
            }
        }
        return authenticationBean;
    }

    /**
     * @return the smnEndpoint
     */
    public String getSmnEndpoint() {
        return smnEndpoint;
    }

    /**
     * @param smnEndpoint
     *            the smnEndpoint to set
     */
    public void setSmnEndpoint(String smnEndpoint) {
        this.smnEndpoint = smnEndpoint;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SmnConfiguration [filepath=").append(filepath).append(", domainName=").append(domainName)
                .append(", regionId=").append(regionId).append(", authenticationBean=").append(authenticationBean)
                .append(", iamUrl=").append(iamUrl).append(", smnEndpoint=").append(smnEndpoint).append("]");
        return builder.toString();
    }

}
