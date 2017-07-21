package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class CreateTopicRequestTest extends TestCase {
	private static Logger logger = LoggerFactory.getLogger(CreateTopicRequestTest.class);
	CreateTopicRequest createTopicRequest;
	static AuthenticationBean authenticationBean;
	final static String projectId = "cffe4fc4c9a54219b60dbaf7b586e132";

	@Before
	public void setUp() {
		createTopicRequest = mock(CreateTopicRequest.class);
		authenticationBean = mock(AuthenticationBean.class);
		when(authenticationBean.getProjectId()).thenReturn(projectId);
	}

	public void testGetRequestUrl() throws Exception {
		logger.info("starting test");
		when(createTopicRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(createTopicRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics";
		Assert.assertEquals(requestURL, createTopicRequest.getRequestUrl());
	}

	public void testGetRequestParameterMap() throws Exception {
		when(createTopicRequest.getRequestParameterMap()).thenCallRealMethod();
		when(createTopicRequest.getName()).thenReturn("CreateTopicRequestTest");
		Assert.assertEquals(createTopicRequest.getName(), createTopicRequest.getRequestParameterMap().get("name"));
		Assert.assertNull(createTopicRequest.getRequestParameterMap().get("displayName"));
	}

}
