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
 * @date 2017年8月7日 上午10:39:20
 * @version 0.1
 * 
 */
package com.huawei.smn.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;

/**
 * @author huangqiong
 * @date 2017年8月7日 上午10:39:20
 * @version 0.1
 */
public class DesUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(DesUtil.class);

    /**
     * encrypt
     * 
     * @param src
     *            date source
     * @param key
     *            key length shoud be times of 8
     * @return encrypted data
     * @throws Exception
     */
    private static byte[] encrypt(byte[] src, byte[] key) throws Exception {

        /**
         * secure random source is needed by DES
         */
        SecureRandom sr = new SecureRandom();

        /**
         * create a DESKeySpec object from original key
         */
        DESKeySpec dks = new DESKeySpec(key);

        /**
         * create a secretKey factory ,then change the DESKeySpec object to a
         * SecretKey object
         */
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SmnConstants.DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        /**
         * Cipher get instance
         */
        Cipher cipher = Cipher.getInstance(SmnConstants.DES);

        /**
         * init Cipher object by securekey
         */
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        /**
         * get date and then encrypt
         */
        return cipher.doFinal(src);

    }

    /**
     * decrypt
     * 
     * @param src
     *            data source
     * @param key
     *            secret,the result must be 0 when lenth be divided by 8
     * @return byte[]
     *         decrypt original data byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        /**
         * secure random source is needed by DES
         */
        SecureRandom sr = new SecureRandom();

        /**
         * create a DESKeySpec object from original key
         */
        DESKeySpec dks = new DESKeySpec(key);

        /**
         * create a secretKey factory ,then change the DESKeySpec object to a
         * SecretKey object
         */
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SmnConstants.DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        /**
         * Cipher get instance
         */
        Cipher cipher = Cipher.getInstance(SmnConstants.DES);

        /**
         * init Cipher object by securekey
         */
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        /**
         * get data and decrypt
         */
        return cipher.doFinal(src);
    }

    /**
     * date decrypt
     * 
     * @param data
     * @return String
     *         decrypt string
     * @throws Exception
     */
    public final static String decrypt(String data) {

        try {
            return new String(decrypt(hex2byte(data.getBytes()), SmnConstants.SMN_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            LOGGER.error("Decrypt failed.", e);
            throw new RuntimeException("Decrypt failed.", e);
        }
    }

    /**
     * encrypt
     * 
     * @param str
     * @return String
     *         encrypt string
     * @throws Exception
     */
    public final static String encrypt(String str) {
        try {
            return byte2hex(encrypt(str.getBytes(), SmnConstants.SMN_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            LOGGER.error("Encrypt failed.", e);
            throw new RuntimeException("Encrypt failed.", e);
        }
    }

    /**
     * byte to hex string
     * 
     * @param inputBytes
     * 
     * @return String
     *         hex string
     */
    private static String byte2hex(byte[] inputBytes) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < inputBytes.length; n++) {
            stmp = (java.lang.Integer.toHexString(inputBytes[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * hex String to byte array
     * 
     * @param inputBytes
     * @return byte[]
     */
    private static byte[] hex2byte(byte[] inputBytes) {
        if ((inputBytes.length % 2) != 0)
            throw new IllegalArgumentException("Length is not even.");
        byte[] b2 = new byte[inputBytes.length / 2];

        for (int n = 0; n < inputBytes.length; n += 2) {
            String item = new String(inputBytes, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

}
