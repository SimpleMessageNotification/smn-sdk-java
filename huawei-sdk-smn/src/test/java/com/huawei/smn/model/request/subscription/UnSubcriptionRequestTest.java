package com.huawei.smn.model.request.subscription;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.AuthenticationBean;

public class UnSubcriptionRequestTest {

    private static Logger logger = LoggerFactory.getLogger(UnSubcriptionRequestTest.class);
    UnSubcriptionRequest unSubcriptionRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        unSubcriptionRequest = mock(UnSubcriptionRequest.class);
        SmnConfiguration smnConfiguration = mock(SmnConfiguration.class);
        authenticationBean = mock(AuthenticationBean.class);
        when(authenticationBean.getProjectId()).thenReturn(PROJECT_ID);
        when(smnConfiguration.getRegionId()).thenReturn(REGION_ID);
        when(smnConfiguration.getSmnEndpoint()).thenReturn(SMN_ENDPOINT);
    }

    @Test
    public void testGetRequestUrl() throws Exception {
        logger.info("unSubcriptionRequest getRequestUrl");
        String subscriptionUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:da12dff9f2844278806978d86a36c312";
        when(unSubcriptionRequest.getSubscriptionUrn()).thenReturn(subscriptionUrn);
        when(unSubcriptionRequest.getRequestUrl()).thenCallRealMethod();
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/subscriptions/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:da12dff9f2844278806978d86a36c312";
        Assert.assertEquals(requestURL, unSubcriptionRequest.getRequestUrl());
    }

    @Test(expected = RuntimeException.class)
    public void testGetRequestUrl2() throws Exception {
        logger.info("unSubcriptionRequest getRequestUrl");
        when(unSubcriptionRequest.getRequestUrl()).thenCallRealMethod();
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/subscriptions/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:da12dff9f2844278806978d86a36c312";
        Assert.assertEquals(requestURL, unSubcriptionRequest.getRequestUrl());
    }

}
