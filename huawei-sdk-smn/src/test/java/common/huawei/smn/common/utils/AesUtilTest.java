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
package common.huawei.smn.common.utils;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.common.utils.AesUtil;

/**
 * @author huangqiong
 * @date 2017年8月7日 下午2:53:26
 * @version 0.1
 */
public class AesUtilTest {

    public static void main(String[] args) {

        AesUtil desUtil = new AesUtil();
        String str1 = "";
        String str3 = "this is 我的 #$%^&()first encrypt program 知道吗?DES算法要求有一个可信任的随机数源 --//*。@@@$$";
        String str = desUtil.encrypt(str3, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        String strr = desUtil.encrypt(str1, SmnConstants.DEFAULT_SMN_CRYPT_KEY);
        System.out.println("encrypt Str3 is : " + str);
        System.out.println("outStr4 is : " + desUtil.decrypt(str, SmnConstants.DEFAULT_SMN_CRYPT_KEY));
        System.out.println("when null is : " + desUtil.decrypt(strr, SmnConstants.DEFAULT_SMN_CRYPT_KEY));
    }

}
