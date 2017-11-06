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
package com.smn.service.impl;

import com.smn.common.utils.DateUtil;
import com.smn.common.utils.HttpUtil;
import com.smn.common.ClientConfiguration;
import com.smn.model.AuthenticationBean;
import com.smn.service.IAMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author huangqiong
 * @author zhangyx
 * @version 0.7
 */
public class IAMServiceImpl implements IAMService {
    private static Logger LOGGER = LoggerFactory.getLogger(IAMServiceImpl.class);

    /**
     * user's name
     */
    private String userName;

    /**
     * user's password
     */
    private String password;

    /**
     * user's domain name
     */
    private String domainName;

    /**
     * region Id
     */
    private String regionId;

    /**
     * the URL of IAM
     */
    private String iamUrl;

    /**
     * Token expired 5 minutes in advance
     */
    private long expiredInterval = 5 * 60 * 1000;

    /**
     * the request to get token
     */
    private String requestMessage = null;

    /**
     * cache authentication bean
     */
    protected AuthenticationBean authenticationBean;

    /**
     * client config
     */
    protected ClientConfiguration clientConfiguration;

    /**
     * constructor
     *
     * @param userName          userName
     * @param password          password
     * @param domainName        domainName
     * @param regionId          regionId
     * @param iamUrl            iamUrl
     * @param clientConfiguration the client configuration
     */
    public IAMServiceImpl(String userName, String password, String domainName,
                          String regionId, String iamUrl, ClientConfiguration clientConfiguration) {
        setUserName(userName);
        setPassword(password);
        setDomainName(domainName);
        setRegionId(regionId);
        setIamUrl(iamUrl);
        setClientConfiguration(clientConfiguration);

        requestMessage = "{" +
                "    \"auth\": {" +
                "        \"identity\": {" +
                "            \"methods\": [" +
                "                \"password\"" +
                "            ]," +
                "            \"password\": {" +
                "                \"user\": {" +
                "                    \"name\": \"" + userName + "\"," +
                "                    \"password\": \"" + password + "\"," +
                "                    \"domain\": {" +
                "                        \"name\": \"" + domainName + "\"" +
                "                    }" +
                "                }" +
                "            }" +
                "        }," +
                "        \"scope\": {" +
                "            \"project\": {" +
                "                \"name\": \"" + regionId + "\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";
    }

    /**
     * Obtain authorization information from the IAM service, which includes
     * projectId, user token, and token expiration time
     *
     * @return {@link AuthenticationBean} User token information
     * @throws RuntimeException Failed to get token, then ran out of the exception
     */
    public AuthenticationBean getAuthentication() throws RuntimeException {

        AuthenticationBean authenticationBean = null;
        try {
            authenticationBean = HttpUtil.postForIamToken(iamUrl, requestMessage, clientConfiguration);
            // parse time
            Date tempDate = DateUtil.parseDate(authenticationBean.getExpiresAt());
            authenticationBean.setExpiresTime(tempDate.getTime() - expiredInterval);
            return authenticationBean;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Faied to get token from iam.e:{}", e);
            throw new RuntimeException("Failed to get token from iam.", e);
        }
    }

    /**
     * Obtain authorization information
     * <p>
     * if exist, return
     * or get from iam service
     *
     * @return {@link AuthenticationBean} User token information
     */
    public AuthenticationBean getAuthenticationBean() {
        // get authenticationBean thread safe
        if (null == authenticationBean || authenticationBean.isExpired()) {
            synchronized (this) {
                if (authenticationBean == null || authenticationBean.isExpired()) {
                    authenticationBean = getAuthentication();
                }
            }
        }
        return authenticationBean;
    }

    /**
     * @return the expiredInterval
     */
    public long getExpiredInterval() {
        return expiredInterval;
    }

    /**
     * @param expiredInterval the expiredInterval to set
     */
    public void setExpiredInterval(long expiredInterval) {
        this.expiredInterval = expiredInterval;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @return the regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * @return the iamUrl
     */
    public String getIamUrl() {
        return iamUrl;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        if (userName == null) {
            LOGGER.error("Username is null.");
            throw new NullPointerException("username is null.");
        }
        this.userName = userName;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        if (password == null) {
            LOGGER.error("Password is null.");
            throw new NullPointerException("password is null.");
        }
        this.password = password;
    }

    /**
     * @param domainName the domainName to set
     */
    public void setDomainName(String domainName) {
        if (domainName == null) {
            LOGGER.error("Domainname is null.");
            throw new NullPointerException("domainName is null.");
        }
        this.domainName = domainName;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(String regionId) {
        if (regionId == null) {
            LOGGER.error("Regionid is null.");
            throw new NullPointerException("regionId is null.");
        }
        this.regionId = regionId;
    }

    /**
     * @param iamUrl the iamUrl to set
     */
    public void setIamUrl(String iamUrl) {
        if (iamUrl == null) {
            LOGGER.error("IAMurl is null.");
            throw new NullPointerException("iamUrl is null.");
        }
        this.iamUrl = iamUrl;
    }

    /**
     * the http configuration to set
     *
     * @param clientConfiguration http configuration
     */
    public void setClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    public String getRequestMessage() {
        return requestMessage;
    }
}
