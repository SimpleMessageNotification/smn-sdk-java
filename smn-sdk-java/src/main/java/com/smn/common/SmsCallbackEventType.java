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
 * sms callback event type
 *
 * @author zhangyx
 * @version 0.9
 */
public final class SmsCallbackEventType {

    private SmsCallbackEventType() {

    }

    /**
     * sms callback event type
     */
    public final static String SMS_CALLBACK_SUCCESS = "sms_success_event";

    /**
     * sms callback event type
     */
    public final static String SMS_CALLBACK_FAIL = "sms_fail_event";

    /**
     * sms callback event type
     */
    public final static String SMS_CALLBACK_REPLY = "sms_reply_event";
}
