package com.huawei.smn.model.request.template;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.AuthenticationBean;
import com.huawei.smn.model.request.publish.PublishMsgRequestTest;

import junit.framework.TestCase;

public class ListMessageTemplatesRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(PublishMsgRequestTest.class);
    ListMessageTemplatesRequest listMessageTemplatesRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        listMessageTemplatesRequest = mock(ListMessageTemplatesRequest.class);
        SmnConfiguration smnConfiguration = mock(SmnConfiguration.class);
        authenticationBean = mock(AuthenticationBean.class);
        when(authenticationBean.getProjectId()).thenReturn(PROJECT_ID);
        when(smnConfiguration.getRegionId()).thenReturn(REGION_ID);
        when(smnConfiguration.getSmnEndpoint()).thenReturn(SMN_ENDPOINT);
    }

    public void testGetRequestUrl() throws Exception {
        logger.info("starting testGetRequestUrl");
        when(listMessageTemplatesRequest.getRequestUrl()).thenCallRealMethod();
        when(listMessageTemplatesRequest.getMessageTemplateName()).thenReturn("createMessageTemplate");
        when(listMessageTemplatesRequest.getLimit()).thenReturn(1);
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/message_template?offset=0&limit=1&message_template_name=createMessageTemplate";
        Assert.assertEquals(requestURL, listMessageTemplatesRequest.getRequestUrl());

    }

    public void testGetRequestParameterMap() throws Exception {
        when(listMessageTemplatesRequest.getProtocol()).thenReturn("default");
        when(listMessageTemplatesRequest.getRequestParameterMap()).thenCallRealMethod();
        Assert.assertNotNull(listMessageTemplatesRequest.getRequestParameterMap());
    }

}
