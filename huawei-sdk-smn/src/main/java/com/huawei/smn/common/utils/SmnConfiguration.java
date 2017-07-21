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
		logger.debug("Reloading ...");
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
		setUserName(userName);
		setPassword(password);
		setDomainName(domainName);
		setRegionId(regionId);

		return true;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
