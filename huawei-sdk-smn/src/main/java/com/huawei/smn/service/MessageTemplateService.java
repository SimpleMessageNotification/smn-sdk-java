package com.huawei.smn.service;

import java.util.Map;

import com.huawei.smn.model.SmnRequest;

/**
 * Message template service
 * 
 * @author huangqiong
 *
 */
public interface MessageTemplateService {

    // set request
    void setSmnRequest(SmnRequest smnRequest);

    // init
    public void init();

    // create template
    Map<String, Object> createMessageTemplate() throws RuntimeException;

    // update template
    Map<String, Object> updateMessageTemplate() throws RuntimeException;

    // delete template
    Map<String, Object> deleteMessageTemplate() throws RuntimeException;

    // query template list
    Map<String, Object> listMessageTemplates() throws RuntimeException;

    // query template detail
    Map<String, Object> queryMsgTemplateDetail() throws RuntimeException;

}
