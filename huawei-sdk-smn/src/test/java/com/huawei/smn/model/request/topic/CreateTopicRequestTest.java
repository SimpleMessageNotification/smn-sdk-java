package com.huawei.smn.model.request.topic;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class CreateTopicRequestTest extends TestCase {
    private static Logger LOGGER = LoggerFactory.getLogger(CreateTopicRequestTest.class);
    CreateTopicRequest createTopicRequest;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        createTopicRequest = new CreateTopicRequest();
        createTopicRequest.setProjectId(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        LOGGER.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics";
        Assert.assertEquals(requestURL, createTopicRequest.getRequestUri());
    }

    public void testGetRequestParameterMap() throws Exception {

        Assert.assertEquals(createTopicRequest.getName(), createTopicRequest.getRequestParameterMap().get("name"));
        Assert.assertNull(createTopicRequest.getRequestParameterMap().get("displayName"));
    }

}
