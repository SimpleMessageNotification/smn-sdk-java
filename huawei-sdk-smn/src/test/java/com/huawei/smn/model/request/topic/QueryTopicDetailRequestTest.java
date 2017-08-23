package com.huawei.smn.model.request.topic;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class QueryTopicDetailRequestTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(QueryTopicDetailRequestTest.class);
    QueryTopicDetailRequest queryTopicDetailRequest;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        queryTopicDetailRequest = new QueryTopicDetailRequest();
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        queryTopicDetailRequest.setProjectId(PROJECT_ID);
        queryTopicDetailRequest.setTopicUrn(topicUrn);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        Assert.assertEquals(requestURL, queryTopicDetailRequest.getRequestUri());
    }

}
