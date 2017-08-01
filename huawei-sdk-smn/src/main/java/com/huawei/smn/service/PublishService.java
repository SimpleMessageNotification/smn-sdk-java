package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.SmnRequest;

/**
 * Message publish service
 * 
 * @author huangqiong
 *
 */
public interface PublishService {

    // set request
    void setSmnRequest(SmnRequest smnRequest);

    public void init();

    // publish message
    Map<String, Object> publish() throws RuntimeException;

}
