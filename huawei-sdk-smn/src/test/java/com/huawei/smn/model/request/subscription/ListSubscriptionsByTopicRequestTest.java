package com.huawei.smn.model.request.subscription;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

public class ListSubscriptionsByTopicRequestTest {

	private static Logger logger = LoggerFactory.getLogger(ListSubscriptionsByTopicRequestTest.class);
	ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest;
	static AuthenticationBean authenticationBean;
	final static String projectId = "cffe4fc4c9a54219b60dbaf7b586e132";

	@Before
	public void setUp() {
		listSubscriptionsByTopicRequest = mock(ListSubscriptionsByTopicRequest.class);
		authenticationBean = mock(AuthenticationBean.class);
		when(authenticationBean.getProjectId()).thenReturn(projectId);
	}

	@Test
	public void testGetRequestUrl() throws Exception {
		logger.info("listSubscriptionsByTopicRequest getRequestUrl");
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		when(listSubscriptionsByTopicRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(listSubscriptionsByTopicRequest.getTopicUrn()).thenReturn(topicUrn);
		when(listSubscriptionsByTopicRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi/subscriptions?offset=0&limit=100";
		Assert.assertEquals(requestURL, listSubscriptionsByTopicRequest.getRequestUrl());

	}

	@Test
	public void testGetRequestParameterMap() throws Exception {
		when(listSubscriptionsByTopicRequest.getRequestParameterMap()).thenCallRealMethod();
		Assert.assertNotNull(listSubscriptionsByTopicRequest.getRequestParameterMap());
	}

}
