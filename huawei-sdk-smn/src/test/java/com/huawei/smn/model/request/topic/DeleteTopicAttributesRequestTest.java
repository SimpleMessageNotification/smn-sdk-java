package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class DeleteTopicAttributesRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(DeleteTopicAttributesRequestTest.class);
    DeleteTopicAttributesRequest deleteTopicAttributesRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        deleteTopicAttributesRequest = mock(DeleteTopicAttributesRequest.class);
        when(deleteTopicAttributesRequest.getProjectId()).thenReturn(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        when(deleteTopicAttributesRequest.getTopicUrn()).thenReturn(topicUrn);
        when(deleteTopicAttributesRequest.getRequestUri()).thenCallRealMethod();
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate/attributes";
        Assert.assertEquals(requestURL, deleteTopicAttributesRequest.getRequestUri());
    }

}
