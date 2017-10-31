package com.smn.http;

public class HttpConfiguration {

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
