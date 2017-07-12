package com.smn.client.token.impl;

import junit.framework.TestCase;

public class MessageServiceImplTest extends TestCase {

	public void testSendSMSMessage() {
		String userName = "liuqiangqiang";
		String password = "******";
		String domainName = "liuqiangqiang";
		String iamUrl = "https://iam.cn-north-1.myhwclouds.com/v3/auth/tokens";
		String regionId = "cn-north-1";
		IAMServiceImpl iamServiceImpl = new IAMServiceImpl(userName, password, domainName, regionId, iamUrl);

		MessageServiceImpl impl = new MessageServiceImpl();
		impl.setSmnEndpoint("https://smn.cn-north-1.myhwclouds.com");
		impl.setIamService(iamServiceImpl);
		impl.init();
		impl.sendSMSMessage("+8618565889669", "测试一下，是不是可以"+ System.currentTimeMillis());
		// impl.sendSMSMessage("+8618565889669", "测试一下，是不是可以"+ System.currentTimeMillis());
	}

}
