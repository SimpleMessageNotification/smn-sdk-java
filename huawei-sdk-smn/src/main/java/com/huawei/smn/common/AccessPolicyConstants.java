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
package com.huawei.smn.common;

/**
 * Common configuration for access policy
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public interface AccessPolicyConstants {

    /**
     * sid
     */
    String POLICY_STATEMENT_ID = "Sid";

    /**
     * effect
     */
    String POLICY_EFFECT = "Effect";

    /**
     * resource
     */
    String POLICY_RESOURCE = "Resource";

    /**
     * not resource
     */
    String POLICY_NOT_RESOURCE = "NotResource";

    /**
     * action
     */
    String POLICY_ACTION = "Action";

    /**
     * not action
     */
    String POLICY_NOT_ACTION = "NotAction";

    /**
     * principal
     */
    String POLICY_PRINCIPAL = "Principal";

    /**
     * not principal
     */
    String POLICY_NOT_PRINCIPAL = "NotPrincipal";

    /**
     * condition
     */
    String POLICY_CONDITION = "Condition";

    /**
     * id
     */
    String POLICY_ID = "Id";

    /**
     * version
     */
    String POLICY_VERSION = "Version";

    /**
     * statement
     */
    String POLICY_STATEMENT = "Statement";

    /**
     * csp
     */
    String POLICY_CSP = "CSP";

    /**
     * allow
     */
    String POLICY_EFFECT_ALLOW = "Allow";

    /**
     * default policy
     */
    String DEFAULT_POLICY_ID = "__default_policy_ID";

    /**
     * default version
     */
    String DEFAULT_VERSION = "2016-09-07";

}
