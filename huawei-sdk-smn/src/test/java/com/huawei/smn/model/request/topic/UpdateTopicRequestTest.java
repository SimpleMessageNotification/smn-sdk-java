package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class UpdateTopicRequestTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(UpdateTopicRequestTest.class);
    UpdateTopicRequest updateTopicRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        updateTopicRequest = mock(UpdateTopicRequest.class);
        when(updateTopicRequest.getProjectId()).thenReturn(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        when(updateTopicRequest.getTopicUrn()).thenReturn(topicUrn);
        when(updateTopicRequest.getRequestUri()).thenCallRealMethod();
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        Assert.assertEquals(requestURL, updateTopicRequest.getRequestUri());
    }

    public void testGetRequestParameterMap() throws Exception {
        when(updateTopicRequest.getRequestParameterMap()).thenCallRealMethod();
        when(updateTopicRequest.getDisplayName()).thenReturn("CreateTopicRequestTest");
        Assert.assertNotNull(updateTopicRequest.getRequestParameterMap());
    }

}
