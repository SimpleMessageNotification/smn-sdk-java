package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class DeleteTopicRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(DeleteTopicRequestTest.class);
    DeleteTopicRequest deleteTopicRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        deleteTopicRequest = mock(DeleteTopicRequest.class);
        when(deleteTopicRequest.getProjectId()).thenReturn(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:publishMsgWithStruct02";
        when(deleteTopicRequest.getTopicUrn()).thenReturn(topicUrn);
        when(deleteTopicRequest.getRequestUri()).thenCallRealMethod();
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:publishMsgWithStruct02";
        Assert.assertEquals(requestURL, deleteTopicRequest.getRequestUri());
    }

}
