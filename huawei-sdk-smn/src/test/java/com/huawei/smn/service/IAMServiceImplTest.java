package com.huawei.smn.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;
import com.huawei.smn.service.impl.IAMServiceImpl;

import junit.framework.TestCase;

public class IAMServiceImplTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(IAMServiceImplTest.class);
    IAMServiceImpl iamServiceImpl;

    private String userName;
    private String password;
    private String domainName;
    private String regionId;
    private String iamUrl;

    @Before
    public void setUp() throws Exception {
        userName = "your account name";
        password = "your login password";
        domainName = "your account name";
        regionId = "cn-north-1";
        iamUrl = "https://iam.cn-north-1.myhwclouds.com/v3/auth/tokens";
        iamServiceImpl = new IAMServiceImpl(userName, password, domainName, regionId, iamUrl);
    }

    @Test
    public void testGetAuthentication() {
        String requestMessage = iamServiceImpl.getRequestMessage();
        Assert.assertNotNull(requestMessage);
        logger.info(requestMessage);
        AuthenticationBean authenticationBean;
        authenticationBean = iamServiceImpl.getAuthentication();
        Assert.assertNotNull(authenticationBean);
        logger.info(authenticationBean.toString());
    }
}
