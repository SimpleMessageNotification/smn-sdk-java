package com.huawei.smn.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.SmnRequest;
import com.huawei.smn.service.SubscriptionService;

/**
 * Subscribe service implemented
 * 
 * @author huangqiong
 *
 */
public class SubscriptionServiceImpl implements SubscriptionService {
    /**
     * LOGGER
     */
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

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
     * encapsulated request
     */
    private SmnRequest smnRequest = null;

    /**
     * 订阅
     * 
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    @Override
    public Map<String, Object> subscribe() throws RuntimeException {
        long startTime = System.currentTimeMillis();
        try {
            init();
            String url = smnRequest.getRequestUrl();
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, url);
            logger.info("End to subscribe. RequestId is {}. responseMap is {}. Cost is {}ms",
                    responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
            return responseMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to subscribe.", e);
            throw new RuntimeException("Failed to subscribe.", e);
        }
    }

    /**
     * cancel subscribe
     * 
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    @Override
    public Map<String, Object> unsubscribe() throws RuntimeException {
        long startTime = System.currentTimeMillis();
        try {
            init();
            String url = smnRequest.getRequestUrl();
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> responseMap = HttpUtil.delete(requestHeader, url);
            logger.info("End to unsubscribe. RequestId is {}. responseMap is {}. Cost is {}ms",
                    responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
            return responseMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to unsubscribe.", e);
            throw new RuntimeException("Failed to unsubscribe.", e);
        }
    }

    /**
     * query list of subscribers
     * 
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    @Override
    public Map<String, Object> listSubscriptions() throws RuntimeException {
        long startTime = System.currentTimeMillis();
        try {
            init();
            String url = smnRequest.getRequestUrl();
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            logger.info("End to list subscriptions. RequestId is {}. responseMap is {}. Cost is {}ms",
                    responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
            return responseMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to list subscriptions.", e);
            throw new RuntimeException("Failed to list subscriptions.", e);
        }
    }

    /**
     * query list of subscribers for designated topic
     * 
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    @Override
    public Map<String, Object> listSubscriptionsByTopic() throws RuntimeException {
        long startTime = System.currentTimeMillis();
        try {
            init();
            String url = smnRequest.getRequestUrl();
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> responseMap = HttpUtil.get(requestHeader, url);
            logger.info("End to list subscriptions by topic. RequestId is {}. responseMap is {}. Cost is {}ms",
                    responseMap.get("request_id"), responseMap, System.currentTimeMillis() - startTime);
            return responseMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to list subscriptions by topic.", e);
            throw new RuntimeException("Failed to list subscriptions by topic.", e);
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
