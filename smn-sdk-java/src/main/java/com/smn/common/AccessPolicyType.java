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
 * access policy
 *
 * @author huangqiong
 * @version 0.1
 */
public final class AccessPolicyType {

    private AccessPolicyType() {

    }

    /**
     * set access policy to access topic resource
     */
    public final static String ACCESS_POLICY = "access_policy";

    /**
     * set profile of topic,to confirm for mail and sms
     */
    public final static String INTRODUCTION = "introduction";

    /**
     * support sms sign id
     */
    public final static String SMS_SIGN_ID = "sms_sign_id";

}
