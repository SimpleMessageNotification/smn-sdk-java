package com.huawei.smn.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.SmnRequest;
import com.huawei.smn.service.SmsService;

/**
 * SMS service implemented
 * 
 * @author huangqiong
 *
 */
public class SmsServiceImpl implements SmsService {
    /**
     * LOGGER
     */
    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    /**
     * encapsulated request
     */
    private SmnRequest smnRequest = null;

    /**
     * init
     */
    public void init() {
        if (StringUtils.isBlank(smnRequest.getRequestUrl())) {
            logger.error("Request url is null.");
            throw new RuntimeException("Request url is null.");
        }
    }

    /**
     * send sms directly
     * 
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    @Override
    public Map<String, Object> smsPublish() throws RuntimeException {
        long startTime = System.currentTimeMillis();
        try {
            init();
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, smnRequest.getRequestUrl());
            logger.info("End to send sms. RequestId is {}. responseMap is {}. Cost is {}ms",
                    responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
            return responseMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to send sms.", e);
            throw new RuntimeException("Failed to send sms.", e);
        }
    }

    @Override
    public void setSmnRequest(SmnRequest smnRequest) {
        this.smnRequest = smnRequest;
    }

    public SmnRequest getSmnRequest() {
        return smnRequest;
    }

}
