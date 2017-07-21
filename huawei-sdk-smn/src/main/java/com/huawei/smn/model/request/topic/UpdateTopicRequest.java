package com.huawei.smn.model.request.topic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * Update topic
 * 
 * support modify displayName
 * 
 * @author huangqiong
 *
 */
public class UpdateTopicRequest extends AbstractSmnRequest {

	private static Logger logger = LoggerFactory.getLogger(UpdateTopicRequest.class);
	/**
	 * topic's unique resource identifier
	 */
	private String topicUrn;
	/**
	 * topic's descriptions
	 */
	private String displayName;

	public String getRequestUrl() throws RuntimeException {
		if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
			logger.error("project id is null");
			throw new RuntimeException();
		}
		if (StringUtils.isBlank(getTopicUrn())) {
			logger.error("getTopicUrn() is null");
			throw new RuntimeException();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
				.append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
				.append(SmnConstants.SMN_TOPIC_URI).append(SmnConstants.URL_DELIMITER).append(getTopicUrn());

		logger.info("Request url is: " + sb.toString());
		return sb.toString();
	}

	@Override
	public Map<String, Object> getRequestParameterMap() {
		Map<String, Object> requestParameterMap = new HashMap<String, Object>();
		requestParameterMap.put("display_name", getDisplayName());
		return requestParameterMap;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getTopicUrn() {
		return topicUrn;
	}

	public void setTopicUrn(String topicUrn) {
		this.topicUrn = topicUrn;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
