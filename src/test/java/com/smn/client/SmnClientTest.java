package com.smn.client;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SmnClientTest extends TestCase {

	public void testSmnClientString() {
		String filePath = "config/smn.properties";
		SmnClient client = new SmnClient(filePath);
		Assert.assertNotNull(client);
	}

	public void testSendSMSMessage() {
		String filePath = "config/smn.properties";
		SmnClient client = new SmnClient(filePath);
		client.sendSMSMessage("+8618565889669", "test messsage"+ System.currentTimeMillis());
	}

}
