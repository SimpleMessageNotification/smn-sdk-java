package com.huawei.smn.model.request.subscription;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

public class ListSubscriptionsRequestTest {
	private static Logger logger = LoggerFactory.getLogger(ListSubscriptionsRequestTest.class);
	ListSubscriptionsRequest listSubscriptionsRequest;
	static AuthenticationBean authenticationBean;
	final static String projectId = "cffe4fc4c9a54219b60dbaf7b586e132";

	@Before
	public void setUp() {
		listSubscriptionsRequest = mock(ListSubscriptionsRequest.class);
		authenticationBean = mock(AuthenticationBean.class);
		when(authenticationBean.getProjectId()).thenReturn(projectId);
	}

	@Test
	public void testGetRequestUrl() throws Exception {
		logger.info("listSubscriptionsRequest getRequestUrl");
		when(listSubscriptionsRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(listSubscriptionsRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/subscriptions?offset=0&limit=100";
		Assert.assertEquals(requestURL, listSubscriptionsRequest.getRequestUrl());

	}

	@Test
	public void testGetRequestParameterMap() throws Exception {
		when(listSubscriptionsRequest.getRequestParameterMap()).thenCallRealMethod();
		Assert.assertNotNull(listSubscriptionsRequest.getRequestParameterMap());
	}

}
