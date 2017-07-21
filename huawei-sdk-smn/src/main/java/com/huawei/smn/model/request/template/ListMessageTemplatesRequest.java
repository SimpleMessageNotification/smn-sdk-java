package com.huawei.smn.model.request.template;

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
 * Query message template list
 * 
 * @author huangqiong
 *
 */
public class ListMessageTemplatesRequest extends AbstractSmnRequest {
	private static Logger logger = LoggerFactory.getLogger(ListMessageTemplatesRequest.class);
	/**
	 * protocol
	 */
	private String protocol;
	/**
	 * template name
	 */
	private String messageTemplateName;
	/**
	 * paging list's starting page,default 0
	 */
	private int offset = 0;
	/**
	 * max returned items for a request,default 100
	 */
	private int limit = 100;

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
				.append(SmnConstants.URL_DELIMITER).append(SmnConstants.SMN_MESSAGE_TEMPLATE);

		// 这里需要重新检视 todo
		if (getOffset() > 0) {
			sb.append("?offset=" + getOffset());
		} else {
			sb.append("?offset=" + "0");
		}
		if (getLimit() > 0 && getLimit() < 100) {
			sb.append("&limit=" + getLimit());
		} else {
			sb.append("&limit=").append("100");
		}
		if (StringUtils.isNotBlank(getMessageTemplateName())) {
			sb.append("&message_template_name=" + getMessageTemplateName());
		}
		if (StringUtils.isNotBlank(getProtocol()) && isValidProtocol(getProtocol())) {
			sb.append("&protocol=" + getProtocol());
		}
		logger.info("Request url is: " + sb.toString());
		return sb.toString();
	}

	@Override
	public Map<String, Object> getRequestParameterMap() {
		Map<String, Object> requestParameterMap = new HashMap<String, Object>();
		return requestParameterMap;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getMessageTemplateName() {
		return messageTemplateName;
	}

	public void setMessageTemplateName(String messageTemplateName) {
		this.messageTemplateName = messageTemplateName;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 校验是否合法协议
	 * 
	 * @param protocol
	 * @return
	 */
	private boolean isValidProtocol(String protocol) {
		String protocols = protocol.trim();
		if (protocols.equals("default") || protocols.equals("sms") || protocols.equals("http")
				|| protocols.equals("https") || protocols.equals("email")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
