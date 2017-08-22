package com.huawei.smn.common.utils;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.huawei.smn.common.SmnConfiguration;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
public class SmnConfigurationTest extends TestCase {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSmnConfiguration() throws Exception {
        SmnConfiguration smnConfiguration1 = new SmnConfiguration();
        Assert.assertTrue(smnConfiguration1.reload());
        SmnConfiguration smnConfiguration2 = new SmnConfiguration(" ");
        Assert.assertTrue(smnConfiguration2.reload());
        SmnConfiguration smnConfiguration3 = new SmnConfiguration(
                "config" + File.separator + "configuration.properties");
        Assert.assertTrue(smnConfiguration3.reload());

    }
}
