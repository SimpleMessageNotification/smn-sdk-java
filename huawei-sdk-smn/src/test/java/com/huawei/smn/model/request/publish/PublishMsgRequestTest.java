package com.huawei.smn.model.request.publish;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
public class PublishMsgRequestTest extends TestCase {

	private static Logger logger = LoggerFactory.getLogger(PublishMsgRequestTest.class);
	PublishMsgRequest publishMsgRequest;
	static AuthenticationBean authenticationBean;
	final static String projectId = "cffe4fc4c9a54219b60dbaf7b586e132";

	@Before
	public void setUp() {
		publishMsgRequest = mock(PublishMsgRequest.class);
		authenticationBean = mock(AuthenticationBean.class);
		when(authenticationBean.getProjectId()).thenReturn(projectId);
	}

	public void testGetRequestUrl() throws Exception {
		logger.info("starting test publish");
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		when(publishMsgRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(publishMsgRequest.getTopicUrn()).thenReturn(topicUrn);
		when(publishMsgRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi/publish";
		Assert.assertEquals(requestURL, publishMsgRequest.getRequestUrl());

	}

	public void testGetRequestParameterMap() throws Exception {
		when(publishMsgRequest.getMessage()).thenReturn("hello");
		when(publishMsgRequest.getRequestParameterMap()).thenCallRealMethod();
		Assert.assertNotNull(publishMsgRequest.getRequestParameterMap());
	}

}
