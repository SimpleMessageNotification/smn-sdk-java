package com.smn.common;

public final class SmsCallbackEventType {

    private SmsCallbackEventType() {

    }

    /**
     * 短信回调事件类型
     */
    public final static String SMS_CALLBACK_SUCCESS = "sms_success_event";

    /**
     * 短信回调事件类型
     */
    public final static String SMS_CALLBACK_FAIL = "sms_fail_event";

    /**
     * 短信回调事件类型
     */
    public final static String SMS_CALLBACK_REPLY = "sms_reply_event";
}
