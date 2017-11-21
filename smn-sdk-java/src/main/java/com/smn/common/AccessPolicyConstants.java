/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.common;

/**
 * Common configuration for access policy
 *
 * @author huangqiong
 * @version 0.1
 */
public final class AccessPolicyConstants {

    private AccessPolicyConstants() {

    }

    /**
     * sid
     */
    public final static String POLICY_STATEMENT_ID = "Sid";

    /**
     * effect
     */
    public final static String POLICY_EFFECT = "Effect";

    /**
     * resource
     */
    public final static String POLICY_RESOURCE = "Resource";

    /**
     * not resource
     */
    public final static String POLICY_NOT_RESOURCE = "NotResource";

    /**
     * action
     */
    public final static String POLICY_ACTION = "Action";

    /**
     * not action
     */
    public final static String POLICY_NOT_ACTION = "NotAction";

    /**
     * principal
     */
    public final static String POLICY_PRINCIPAL = "Principal";

    /**
     * not principal
     */
    public final static String POLICY_NOT_PRINCIPAL = "NotPrincipal";

    /**
     * condition
     */
    public final static String POLICY_CONDITION = "Condition";

    /**
     * id
     */
    public final static String POLICY_ID = "Id";

    /**
     * version
     */
    public final static String POLICY_VERSION = "Version";

    /**
     * statement
     */
    public final static String POLICY_STATEMENT = "Statement";

    /**
     * csp
     */
    public final static String POLICY_CSP = "CSP";

    /**
     * allow
     */
    public final static String POLICY_EFFECT_ALLOW = "Allow";

    /**
     * default policy
     */
    public final static String DEFAULT_POLICY_ID = "__default_policy_ID";

    /**
     * default version
     */
    public final static String DEFAULT_VERSION = "2016-09-07";

}
