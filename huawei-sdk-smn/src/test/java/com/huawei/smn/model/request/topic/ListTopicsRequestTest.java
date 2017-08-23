package com.huawei.smn.model.request.topic;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ListTopicsRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(ListTopicsRequestTest.class);
    ListTopicsRequest listTopicsRequest;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        listTopicsRequest = new ListTopicsRequest();
        listTopicsRequest.setProjectId(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics?offset=0&limit=100";
        Assert.assertEquals(requestURL, listTopicsRequest.getRequestUri());
    }

}
