package com.huawei.smn.common.utils;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DateUtilTest extends TestCase {

	@Before
	public void setUp() {

	}

	@Test
	public void testparseDate() throws Exception {
		String dateString = "2017-07-19T09:00:50.431000Z";
		Date date = DateUtil.parseDate(dateString);
		Assert.assertNotNull(date);
	}

}
