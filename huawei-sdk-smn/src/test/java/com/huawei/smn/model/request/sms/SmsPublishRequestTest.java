package com.huawei.smn.model.request.sms;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.AuthenticationBean;

@RunWith(PowerMockRunner.class)
public class SmsPublishRequestTest {

    private static Logger logger = LoggerFactory.getLogger(SmsPublishRequestTest.class);
    SmsPublishRequest smsPublishRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        smsPublishRequest = mock(SmsPublishRequest.class);
        SmnConfiguration smnConfiguration = mock(SmnConfiguration.class);
        authenticationBean = mock(AuthenticationBean.class);
        when(authenticationBean.getProjectId()).thenReturn(PROJECT_ID);
        when(smnConfiguration.getRegionId()).thenReturn(REGION_ID);
        when(smnConfiguration.getSmnEndpoint()).thenReturn(SMN_ENDPOINT);
    }

    @Test
    public void testGetRequestUrl() throws Exception {
        logger.info("Starting test sms");
        when(smsPublishRequest.getRequestUrl()).thenCallRealMethod();
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/sms";
        Assert.assertEquals(requestURL, smsPublishRequest.getRequestUrl());

    }

    @Test
    public void testGetRequestParameterMap() throws Exception {
        when(smsPublishRequest.getMessage()).thenReturn("Mock test for sms");
        when(smsPublishRequest.getEndpoint()).thenReturn("18388432306");
        when(smsPublishRequest.getRequestParameterMap()).thenCallRealMethod();
        Assert.assertNotNull(smsPublishRequest.getRequestParameterMap().get("endpoint"));
        Assert.assertNotNull(smsPublishRequest.getRequestParameterMap().get("message"));
    }

}
