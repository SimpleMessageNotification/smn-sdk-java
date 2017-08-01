package com.huawei.smn.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;

/**
 * property loading configuration
 * 
 * @author huangqiong
 *
 */
public class SmnConfiguration {

    private static Logger logger = LoggerFactory.getLogger(SmnConfiguration.class);

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
     * iam url
     */
    private String iamUrl;

    public SmnConfiguration(String filepath) {
        this.filepath = filepath;
        reload();
    }

    /**
     * default construction
     * 
     */
    public SmnConfiguration() {
        reload();
    }

    /**
     * Load configured file
     */
    public boolean reload() throws RuntimeException {
        logger.debug("Smn configuration reloading ...");
        if (StringUtils.isBlank(filepath)) {
            filepath = SMN_CONFIGURATION_PROPERTIES;
        }
        logger.info("Properties filepath is:" + filepath);
        try {
            FileInputStream fis = new FileInputStream(new File(this.filepath));
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Fail to reload config properties,filepath={}:" + filepath);
            throw new RuntimeException(e);
        }

        userName = properties.getProperty(SmnConstants.USER_NAME);
        password = properties.getProperty(SmnConstants.PASSWORD);
        domainName = properties.getProperty(SmnConstants.DOMAIN_NAME);
        regionId = properties.getProperty(SmnConstants.REGION_ID);
        iamUrl = properties.getProperty(SmnConstants.IAMURL);
        if (StringUtils.isBlank(userName)) {
            logger.error("User name in configuration properties is empty!");
            throw new RuntimeException("User name in configuration properties is empty!");
        }
        if (StringUtils.isBlank(password)) {
            logger.error("Password in configuration properties is empty!");
            throw new RuntimeException("Password in configuration properties is empty!");
        }
        if (StringUtils.isBlank(domainName)) {
            logger.error("DomainName in configuration properties is empty!");
            throw new RuntimeException("DomainName in configuration properties is empty!");
        }
        if (StringUtils.isBlank(regionId)) {
            logger.error("RegionId in configuration properties is empty!");
            throw new RuntimeException("RegionId in configuration properties is empty!");
        }
        if (StringUtils.isBlank(iamUrl)) {
            logger.error("IamUrl in configuration properties is empty!");
            throw new RuntimeException("IamUrl in configuration properties is empty!");
        }

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
