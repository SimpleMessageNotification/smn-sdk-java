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

/**
 * the configuration for client, contain http configuration
 *
 * @author zhangyx
 * @version 0.9
 */
public class ClientConfiguration {

    /**
     * proxy host
     */
    private String proxyHost;

    /**
     * proxy port
     */
    private int proxyPort;

    /**
     * proxy username
     */
    private String proxyUserName;

    /**
     * proxy password
     */
    private String proxyPassword;

    /**
     * connect time out ,in millisecond
     */
    private int connectTimeOut = 10000;

    /**
     * read time out,in millisecond
     */
    private int socketTimeOut = 10000;

    /**
     * @return the proxy host
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * @param proxyHost the proxy host to set
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * @return the proxy port
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * @param proxyPort the proxy port to set
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * @return the proxy username
     */
    public String getProxyUserName() {
        return proxyUserName;
    }

    /**
     * @param proxyUserName the proxy username to set
     */
    public void setProxyUserName(String proxyUserName) {
        this.proxyUserName = proxyUserName;
    }

    /**
     * @return the proxy password
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * @param proxyPassword the proxy password to set
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /**
     * @return the connect timeout
     */
    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    /**
     * @param connectTimeOut the connection timeout to set
     */
    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    /**
     * @return the socket timeout
     */
    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    /**
     * @param socketTimeOut the socket timeout to set
     */
    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }
}
