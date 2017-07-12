package com.smn.client.token.impl;

import com.smn.client.bean.AuthBean;

import junit.framework.Assert;
import junit.framework.TestCase;

public class IAMServiceImplTest extends TestCase {

	public void testIAMServiceImpl() {
		String userName = "liuqiangqiang";
		String password = "******";
		String domainName = "liuqiangqiang";
		String iamUrl = "https://iam.cn-north-1.myhwclouds.com/v3/auth/tokens";
		String regionId = "cn-north-1";
		IAMServiceImpl iamServiceImpl = new IAMServiceImpl(userName, password, domainName, regionId, iamUrl);
		AuthBean bean = iamServiceImpl.getAuthentication();
		
		Assert.assertNotNull(bean);
	}

}
