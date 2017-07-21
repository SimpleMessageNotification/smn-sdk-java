package com.huawei.smn.service.impl;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.model.request.sms.SmsPublishRequest;
import com.huawei.smn.service.SmsService;

import junit.framework.TestCase;

public class SmsServiceImplTest extends TestCase {
	private static Logger logger = LoggerFactory.getLogger(SmsServiceImplTest.class);

	@Test
	public void testSmsPublishRequest() {
		String endpoint = "13302965216";
		String message = "direct sending msg";
		SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
		SmsService smsService = new SmsServiceImpl();
		smsPublishRequest.setEndpoint(endpoint);
		smsPublishRequest.setMessage(message);
		smsService.setSmnRequest(smsPublishRequest);
		Map<String, Object> res = smsService.smsPublish(smsPublishRequest);
		logger.info(res.toString());
		Assert.assertNotNull(res.get("request_id"));
		Assert.assertNotNull(res.get("status"));
		Assert.assertNotNull(res.get("message_id"));
	}
}
