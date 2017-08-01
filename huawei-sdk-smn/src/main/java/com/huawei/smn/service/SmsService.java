package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.SmnRequest;

/**
 * Send SMS service
 * 
 * @author huangqiong
 *
 */
public interface SmsService {

    // set request
    void setSmnRequest(SmnRequest smnRequest);

    // init
    public void init();

    // send sms directly
    Map<String, Object> smsPublish() throws RuntimeException;

}
