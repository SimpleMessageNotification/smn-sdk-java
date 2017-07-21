package com.huawei.smn.model.request.subscription;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

public class SubcriptionRequestTest {
	private static Logger logger = LoggerFactory.getLogger(SubcriptionRequestTest.class);
	SubcriptionRequest subcriptionRequest;
	static AuthenticationBean authenticationBean;
	final static String projectId = "cffe4fc4c9a54219b60dbaf7b586e132";

	@Before
	public void setUp() {
		subcriptionRequest = mock(SubcriptionRequest.class);
		authenticationBean = mock(AuthenticationBean.class);
		when(authenticationBean.getProjectId()).thenReturn(projectId);
	}

	@Test
	public void testGetRequestUrl() throws Exception {
		logger.info("subcriptionRequest getRequestUrl");
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		when(subcriptionRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(subcriptionRequest.getTopicUrn()).thenReturn(topicUrn);
		when(subcriptionRequest.getProtocol()).thenReturn("sms");
		when(subcriptionRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi/subscriptions";
		Assert.assertEquals(requestURL, subcriptionRequest.getRequestUrl());
	}

	@Test(expected = RuntimeException.class)
	public void testGetRequestUrl1() throws Exception {
		logger.info("subcriptionRequest getRequestUrl");
		when(subcriptionRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(subcriptionRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi/subscriptions";
		Assert.assertEquals(requestURL, subcriptionRequest.getRequestUrl());
	}


	@Test(expected = RuntimeException.class)
	public void testGetRequestParameterMap() throws Exception {

		when(subcriptionRequest.getRequestParameterMap()).thenCallRealMethod();
		Assert.assertNull(subcriptionRequest.getRequestParameterMap().get("protocol"));
		Assert.assertNull(subcriptionRequest.getRequestParameterMap().get("endpoint"));
		when(subcriptionRequest.getProtocol()).thenReturn("sms");
		when(subcriptionRequest.getEndpoint()).thenReturn("18388432306");
		Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("protocol"));
		Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("endpoint"));
	}

	@Test
	public void testGetRequestParameterMap2() throws Exception {
		when(subcriptionRequest.getRequestParameterMap()).thenCallRealMethod();
		when(subcriptionRequest.getProtocol()).thenReturn("sms");
		when(subcriptionRequest.getEndpoint()).thenReturn("18388432306");
		Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("protocol"));
		Assert.assertNotNull(subcriptionRequest.getRequestParameterMap().get("endpoint"));
	}

}
