package com.huawei.smn.model.request.template;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class CreateMessageTemplateRequestTest extends TestCase {
	private static Logger logger = LoggerFactory.getLogger(CreateMessageTemplateRequestTest.class);
	CreateMessageTemplateRequest createMessageTemplateRequest;
	static AuthenticationBean authenticationBean;
	final static String projectId = "cffe4fc4c9a54219b60dbaf7b586e132";

	@Before
	public void setUp() {
		createMessageTemplateRequest = mock(CreateMessageTemplateRequest.class);
		authenticationBean = mock(AuthenticationBean.class);
		when(authenticationBean.getProjectId()).thenReturn(projectId);
	}

	@Test
	public void testGetRequestUrl() throws Exception {
		logger.info("starting test publish");
		when(createMessageTemplateRequest.getAuthenticationBean()).thenReturn(authenticationBean);
		when(createMessageTemplateRequest.getRequestUrl()).thenCallRealMethod();
		String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/message_template";
		Assert.assertEquals(requestURL, createMessageTemplateRequest.getRequestUrl());

	}

	@Test
	public void testGetRequestParameterMap() throws Exception {
		when(createMessageTemplateRequest.getContent()).thenReturn("hello");
		when(createMessageTemplateRequest.getMessageTemplateName()).thenReturn("hello");
		when(createMessageTemplateRequest.getProtocol()).thenReturn("default");
		when(createMessageTemplateRequest.getRequestParameterMap()).thenCallRealMethod();
		Assert.assertNotNull(createMessageTemplateRequest.getRequestParameterMap().get("protocol"));
	}

}
