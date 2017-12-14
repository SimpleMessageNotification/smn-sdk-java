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
package com.smn.account;

import com.smn.client.DefaultSmnClient;
import com.smn.client.SmnClient;
import com.smn.common.ClientConfiguration;
import com.smn.common.SmnConfiguration;
import com.smn.service.ServiceFactory;

/**
 * smn cloud account
 *
 * @author zhangyx
 * @version 0.9
 */
public class CloudAccount {

    /**
     * smn configuration
     */
    private SmnConfiguration smnConfiguration;

    /**
     * smn client
     */
    private SmnClient smnClient;

    /**
     * http configuration
     */
    private ClientConfiguration clientConfiguration;

    /**
     * constructor for token authentication
     *
     * @param userName   the userName to set
     * @param password   the password to set
     * @param domainName the domainName to set
     * @param regionId   the regionId to set
     */
    public CloudAccount(String userName, String password, String domainName, String regionId) {
        this(userName, password, domainName, regionId, null);
    }

    /**
     * constructor for token authentication
     *
     * @param userName            the userName to set
     * @param password            the password to set
     * @param domainName          the domainName to set
     * @param regionId            the regionId to set
     * @param clientConfiguration the client configuration
     */
    public CloudAccount(String userName, String password, String domainName, String regionId, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = new SmnConfiguration(userName, password, domainName, regionId);
        this.clientConfiguration = clientConfiguration;

        if (clientConfiguration == null) {
            this.clientConfiguration = new ClientConfiguration();
        }
    }

    /**
     * constructor for aksk authentication
     *
     * @param accessKeyId     the accessKeyId to set
     * @param secretAccessKey the secretAccessKey to set
     * @param regionId        the regionId to set
     */
    public CloudAccount(String accessKeyId, String secretAccessKey, String regionId) {
        this.smnConfiguration = new SmnConfiguration(accessKeyId, secretAccessKey, regionId);
        this.clientConfiguration = new ClientConfiguration();
    }

    /**
     * constructor for aksk authentication
     *
     * @param accessKeyId         the accessKeyId to set
     * @param secretAccessKey     the secretAccessKey to set
     * @param regionId            the regionId to set
     * @param clientConfiguration the client configuration
     */
    public CloudAccount(String accessKeyId, String secretAccessKey, String regionId, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = new SmnConfiguration(accessKeyId, secretAccessKey, regionId);
        this.clientConfiguration = clientConfiguration;
        if (clientConfiguration == null) {
            this.clientConfiguration = new ClientConfiguration();
        }
    }

    /**
     * get smn client
     *
     * @return the smnClient instance
     */
    public SmnClient getSmnClient() {
        if (smnClient == null) {
            synchronized (this) {
                if (smnClient == null) {
                    ServiceFactory serviceFactory = new ServiceFactory(this.smnConfiguration, this.clientConfiguration);
                    smnClient = new DefaultSmnClient(serviceFactory);
                }
            }
        }
        return smnClient;
    }

    /**
     * @return the smn configuration
     */
    public SmnConfiguration getSmnConfiguration() {
        return smnConfiguration;
    }

    /**
     * @return the client configuration
     */
    public ClientConfiguration getClientConfiguration() {
        return clientConfiguration;
    }
}
