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
 * @date 2017年8月7日 下午2:13:59
 * @version 0.1
 * 
 */
package com.huawei.smn.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;

/**
 * @author huangqiong
 * @date 2017年8月7日 下午2:13:59
 * @version 0.1
 */
public class AesUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(AesUtil.class);

    /**
     * encrypt
     * 
     * @param content
     *            content to be encrpted
     * @param password
     *            encrypt password
     * @return String
     *         <p>
     *         encrypted content
     */
    public static String encrypt(String content, String password) {
        try {

            // create AES KeyGenerator
            KeyGenerator kgen = KeyGenerator.getInstance(SmnConstants.AES);

            // init 128 bit keygen utilizing password
            kgen.init(128, new SecureRandom(password.getBytes()));

            // gen secret key from password
            SecretKey secretKey = kgen.generateKey();

            // get basic encoding secret,unsupported throw exception
            byte[] enCodeFormat = secretKey.getEncoded();

            // transform to AES specialized secret key
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, SmnConstants.AES);

            // create cipher
            Cipher cipher = Cipher.getInstance(SmnConstants.AES);

            byte[] byteContent = content.getBytes(SmnConstants.DEFAULT_CHARSET);

            // init encrypt mode cipher
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] result = cipher.doFinal(byteContent);
            String encryptedStr = parseByte2HexStr(result);

            return encryptedStr;

        } catch (Exception e) {
            LOGGER.error("Aes encrypt failed.", e);
            throw new RuntimeException("Aes encrypt failed.", e);
        }

    }

    /**
     * decrypt aes string
     * 
     * @param content
     *            encrypted content
     * @param password
     * 
     * @return String
     *         <p>
     *         decrypted content
     */
    public static String decrypt(String content, String password) {
        try {
            byte[] encryptedContent = parseHexStr2Byte(content);

            // create AES KeyGenerator
            KeyGenerator kgen = KeyGenerator.getInstance(SmnConstants.AES);
            kgen.init(128, new SecureRandom(password.getBytes()));

            // gen secret key from password
            SecretKey secretKey = kgen.generateKey();

            // get basic encoding secret,unsupported throw exception
            byte[] enCodeFormat = secretKey.getEncoded();

            // transform to AES specialized secret key
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, SmnConstants.AES);

            // create cipher
            Cipher cipher = Cipher.getInstance("AES");

            // init decrypt mode cipher
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypt = cipher.doFinal(encryptedContent);

            // original content
            return new String(decrypt);

        } catch (Exception e) {
            LOGGER.error("Aes decrypt failed.", e);
            throw new RuntimeException("Aes decrypt failed.", e);
        }
    }

    /**
     * parse byte array to string
     * 
     * @param buf
     * @return String
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * parse hex string to byte array
     * 
     * @param hexStr
     * @return byte[]
     *         to decrypted bytes
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
