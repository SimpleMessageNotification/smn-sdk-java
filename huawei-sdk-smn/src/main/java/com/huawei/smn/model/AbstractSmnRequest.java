package com.huawei.smn.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.common.utils.SmnConfiguration;
import com.huawei.smn.service.impl.IAMServiceImpl;

/**
 * Abstract class for smn request,encapsulate request's common methods:request
 * header,IAM ,IAM url address
 * 
 * @author huangqiong
 *
 */
public abstract class AbstractSmnRequest implements SmnRequest {

	private static Logger logger = LoggerFactory.getLogger(AbstractSmnRequest.class);

	private static AuthenticationBean authenticationBean = null;

	private static IAMServiceImpl iamService = null;

	public final static String IAMURL = "https://iam.cn-north-1.myhwclouds.com/v3/auth/tokens";

	
	public void init() throws RuntimeException {
		getAuthenticationBean();
	}

	/**
	 * get IAM service
	 */
	@Override
	public IAMServiceImpl getIamService() {
		if (iamService == null) {
			SmnConfiguration smnConfiguration = new SmnConfiguration();
			iamService = new IAMServiceImpl(smnConfiguration.getUserName(), smnConfiguration.getPassword(),
					smnConfiguration.getDomainName(), smnConfiguration.getRegionId(), IAMURL);
		}
		return iamService;
	}

	/**
	 * when property's configuration changed, refresh and get IAM service
	 */
	@Override
	public IAMServiceImpl refreshIamService() {
		SmnConfiguration smnConfiguration = new SmnConfiguration();
		iamService = new IAMServiceImpl(smnConfiguration.getUserName(), smnConfiguration.getPassword(),
				smnConfiguration.getDomainName(), smnConfiguration.getRegionId(), IAMURL);
		return iamService;
	}

	/**
	 * get authentication POJO
	 */
	@Override
	public AuthenticationBean getAuthenticationBean() throws RuntimeException {
		authenticationBean = checkAuthenticationBean();
		return authenticationBean;
	}

	/**
	 * check user's authentication
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	protected AuthenticationBean checkAuthenticationBean() throws RuntimeException {
		if (authenticationBean == null) {
			// may in concurrent
			synchronized (AbstractSmnRequest.class) {
				if (authenticationBean == null) {
					logger.debug("AuthInfo is null. Try to get it.");
					// get correct bean ,or throw exception
					authenticationBean = getIamService().getAuthentication();
				}
			}
		} else {
			// if expired
			if (authenticationBean.isExpired()) {
				synchronized (AbstractSmnRequest.class) {
					if (authenticationBean.isExpired()) {
						logger.info("AuthInfo is expired. Try to get it. Old authInfo is {}.", authenticationBean);
						// get correct bean ,or throw exception
						authenticationBean = getIamService().getAuthentication();
					}
				}
			}
		}
		return authenticationBean;
	}

	/**
	 * Build common http's request header
	 */
	@Override
	public Map<String, String> getRequestHeaderMap() throws RuntimeException {
		Map<String, String> requestHeaderMap = new HashMap<String, String>();
		requestHeaderMap.put(SmnConstants.REGION_TAG, SmnConstants.DEFAULT_REGION);
		requestHeaderMap.put(SmnConstants.X_PROJECT_ID, getAuthenticationBean().getProjectId());
		requestHeaderMap.put(SmnConstants.X_AUTH_TOKEN, getAuthenticationBean().getAuthToken());
		requestHeaderMap.put(SmnConstants.CONTENT_TYPE_TAG, "application/json");
		return requestHeaderMap;
	}

	/**
	 * Get request URL from different API
	 */
	public abstract String getRequestUrl();

	/**
	 * Get request body parameters from different API
	 */
	public abstract Map<String, Object> getRequestParameterMap();

}
