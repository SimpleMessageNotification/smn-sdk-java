package com.huawei.smn.model.request.topic;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ListTopicAttributesRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(ListTopicAttributesRequestTest.class);
    ListTopicAttributesRequest listTopicAttributesRequest;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        listTopicAttributesRequest = new ListTopicAttributesRequest();
        listTopicAttributesRequest.setProjectId(PROJECT_ID);
        listTopicAttributesRequest.setAttributesName("access_policy");
        listTopicAttributesRequest.setTopicUrn(topicUrn);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate/attributes?name=access_policy";
        Assert.assertEquals(requestURL, listTopicAttributesRequest.getRequestUri());
    }

    public void testGetRequestParameterMap() throws Exception {
        Assert.assertNotNull(listTopicAttributesRequest.getRequestParameterMap());
        Assert.assertNull(listTopicAttributesRequest.getRequestParameterMap().get("displayName"));
    }

}
