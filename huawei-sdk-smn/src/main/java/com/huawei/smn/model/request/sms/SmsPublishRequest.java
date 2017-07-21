package com.huawei.smn.model.request.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.SmnConstants;
import com.huawei.smn.model.AbstractSmnRequest;

/**
 * Send SMS directly
 * 
 * @author huangqiong
 *
 */
public class SmsPublishRequest extends AbstractSmnRequest {
	private static Logger logger = LoggerFactory.getLogger(SmsPublishRequest.class);
	/**
	 * phones's regex
	 */
	private static final Pattern PATTERN_TELTPHONE = Pattern.compile("^\\+?[0-9]{1,31}");
	/**
	 * message access point
	 */
	private String endpoint;
	/**
	 * message to send
	 */
	private String message;

	@Override
	public String getRequestUrl() throws RuntimeException {
		if (Objects.isNull(getAuthenticationBean()) || StringUtils.isBlank(getAuthenticationBean().getProjectId())) {
			logger.error("project id is null");
			throw new RuntimeException();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(SmnConstants.SMN_HOST_NAME).append(SmnConstants.URL_DELIMITER).append(SmnConstants.V2_VERSION)
				.append(SmnConstants.URL_DELIMITER).append(getAuthenticationBean().getProjectId())
				.append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_NOTIFICATIONS)
				.append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_SUB_PROTOCOL_SMS);
		logger.info("Request url is: " + sb.toString());
		return sb.toString();
	}

	@Override
	public Map<String, Object> getRequestParameterMap() {
		checkPhoneNumber(getEndpoint());
		checkMessage(getMessage());
		Map<String, Object> requestParameterMap = new HashMap<String, Object>();
		requestParameterMap.put("endpoint", getEndpoint());
		requestParameterMap.put("message", getMessage());
		return requestParameterMap;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private void checkPhoneNumber(String endpoint) {
		if (endpoint == null) {
			logger.error("PhoneNumber is null.");
			throw new NullPointerException("endpoint is null.");
		}

		if (!PATTERN_TELTPHONE.matcher(endpoint).matches()) {
			logger.error("Wrong phone number format");
			throw new RuntimeException(
					"The wrong phone number format, the correct number format is +8600000000000 or 00000000000");
		}
	}

	private void checkMessage(String message) {

		if (message == null) {
			logger.error("Message is null.");
			throw new NullPointerException("Message is null.");
		}

		// 短信发送时，需要注意短信长度,CMPP协议短信最多500字符
		if (message.length() > 500) {
			logger.warn("SMS content is too long, more than {} characters of the message content will be cut off.",
					500);
		}
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
