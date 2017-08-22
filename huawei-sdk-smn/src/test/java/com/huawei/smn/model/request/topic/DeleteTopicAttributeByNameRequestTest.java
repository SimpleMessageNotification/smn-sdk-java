package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class DeleteTopicAttributeByNameRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(DeleteTopicAttributeByNameRequestTest.class);
    DeleteTopicAttributeByNameRequest deleteTopicAttributeByNameRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        deleteTopicAttributeByNameRequest = mock(DeleteTopicAttributeByNameRequest.class);
        SmnConfiguration smnConfiguration = mock(SmnConfiguration.class);
        authenticationBean = mock(AuthenticationBean.class);
        when(authenticationBean.getProjectId()).thenReturn(PROJECT_ID);
        when(smnConfiguration.getRegionId()).thenReturn(REGION_ID);
        when(smnConfiguration.getSmnEndpoint()).thenReturn(SMN_ENDPOINT);
    }

    public void testGetRequestUrl() throws Exception {
        logger.info("starting test");
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        when(deleteTopicAttributeByNameRequest.getTopicUrn()).thenReturn(topicUrn);
        when(deleteTopicAttributeByNameRequest.getAttributesName()).thenReturn("access_policy");
        when(deleteTopicAttributeByNameRequest.getRequestUrl()).thenCallRealMethod();
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate/attributes/access_policy";
        Assert.assertEquals(requestURL, deleteTopicAttributeByNameRequest.getRequestUrl());
    }

}
