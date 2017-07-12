package com.smn.client.util;

import junit.framework.TestCase;

public class HttpUtilTest extends TestCase {

	public void testGetIamToken() {
		try {
			HttpUtil.postForIamToken("https://iam.cn-north-1.myhwclouds.com/v3/auth/tokens",
					"{\"auth\":{\"identity\":{\"methods\":[\"password\"],\"password\":{\"user\":{\"name\":\"liuqiangqiang\",\"password\":\"****\",\"domain\":{\"name\":\"liuqiangqiang\"}}}},\"scope\":{\"project\":{\"name\":\"cn-north-1\"}}}}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testMessageFormat() {

		String requestInfo = "{\"auth\":{\"identity\":{\"methods\":[\"password\"],\"password\":{\"user\":{\"name\":\"{0}\",\"password\":\"{1}\",\"domain\":{\"name\":\"{2}\"}}}},\"scope\":{\"project\":{\"name\":\"{3}\"}}}}"
				.replaceFirst("\\{0\\}", "liuqinagqiang").replaceFirst("\\{2\\}", "liuqinagqiang").replaceFirst("\\{3\\}", "cn-north-1").replaceFirst("\\{1\\}", "*****");

		System.out.println(requestInfo);
	}
}
