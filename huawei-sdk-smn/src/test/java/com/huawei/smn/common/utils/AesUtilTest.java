/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/**
 * @author huangqiong
 * @date 2017年8月7日 下午2:53:26
 * @version 0.1
 * 
 */
package com.huawei.smn.common.utils;

import org.junit.Assert;
import org.junit.Test;

import com.huawei.smn.common.SmnConstants;

import junit.framework.TestCase;

/**
 * @author huangqiong
 * @date 2017年8月7日 下午2:53:26
 * @version 0.1
 */
public class AesUtilTest extends TestCase {

    AesUtil desUtil = null;
    private String str1 = null;
    private String str2 = null;
    private String encrypedstr1 = null;
    private String encrypedstr2 = null;
    private String decrypedstr1 = null;
    private String decrypedstr2 = null;

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        desUtil = new AesUtil();
        str1 = "";
        str2 = "this is 我的 #$%^&()first encrypt program 知道吗?DES算法要求有一个可信任的随机数源 --//*。@@@$$";
    }

    @SuppressWarnings("static-access")
    @Test
    public void testDecryptedString1() {
        // test null String
        System.out.println("str1 is : " + str1);
        encrypedstr1 = desUtil.encrypt(str1, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        System.out.println("decryptstr1 is : " + encrypedstr1);
        decrypedstr1 = desUtil.decrypt(encrypedstr1, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        System.out.println("decryptstr1 is : " + decrypedstr1);
        Assert.assertEquals(str1, decrypedstr1);
    }

    @SuppressWarnings("static-access")
    @Test
    public void testDecryptedString2() {

        System.out.println("str2 is : " + str2);
        encrypedstr2 = desUtil.encrypt(str2, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        System.out.println("encrypedStr2 is : " + encrypedstr2);
        decrypedstr2 = desUtil.decrypt(encrypedstr2, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        System.out.println("decrypedstr2 is : " + encrypedstr2);
        Assert.assertEquals(str2, decrypedstr2);
    }

}
