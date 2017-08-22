package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class ListTopicsRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(ListTopicsRequestTest.class);
    ListTopicsRequest listTopicsRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        listTopicsRequest = mock(ListTopicsRequest.class);
        when(listTopicsRequest.getProjectId()).thenReturn(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        when(listTopicsRequest.getRequestUri()).thenCallRealMethod();
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics?offset=0&limit=100";
        Assert.assertEquals(requestURL, listTopicsRequest.getRequestUri());
    }

}
