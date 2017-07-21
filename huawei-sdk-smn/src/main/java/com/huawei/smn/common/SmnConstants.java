package com.huawei.smn.common;

/**
 * Smn Constants
 * 
 * @author huangqiong
 *
 */
public interface SmnConstants {

	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
	public final String X_AUTH_TOKEN = "X-Auth-Token";
	public final String X_PROJECT_ID = "X-Project-Id";
	public static final String TENANT_ID = "tenant_id";
	public final String CONTENT_TYPE_TAG = "Content-Type";

	public static final String TOPIC_TAG = "Topic";
	public static final String QUEUE_NAME_TAG = "QueueName";
	public static final String TOPIC_NAME_TAG = "TopicName";
	public static final String SUBSCRIPTION_TAG = "Subscription";
	public static final String LASTMODIFYTIME_TAG = "LastModifyTime";
	public static final String CREATE_TIME_TAG = "CreateTime";

	public static final Long MAX_MESSAGE_SIZE = 65536L;
	public static final Long DEFAULT_MESSAGE_RETENTION_PERIOD = 86400L;
	public static final Long MAX_MESSAGE_RETENTION_PERIOD = 86400L;
	public static final Long MIN_MESSAGE_RETENTION_PERIOD = 60L;

	public static final String REGION_TAG = "region";
	public static final String DEFAULT_REGION = "cn-north-1";

	public static final String URL_DELIMITER = "/";
	public static final String HTTPS_PREFFIX = "https://";

	public static final String IAM_HOST_NAME = "https://iam.cn-north-1.myhwclouds.com";
	public static final String IAM_URI = "/v3/auth/tokens";
	public static final String SMN_HOST_NAME = "https://smn.cn-north-1.myhwclouds.com";
	public static final String SMN_ENDPOINT = "smn.cn-north-1.myhwclouds.com";
	public static final String SMN_TOPIC_URI = "/notifications/topics";

	public static final String V2_VERSION = "v2";
	public static final String V1_VERSION = "v1";

	public static final String USER_NAME = "iam.user.name";
	public static final String PASSWORD = "iam.user.password";
	public static final String DOMAIN_NAME = "iam.domain.name";
	public static final String REGION_ID = "region.id";
	public static final String TOPIC_URN = "topic_urn";
	public static final String PROJECT_ID = "project_id";
	public static final String SMN_PROTOCOL = "protocol";
	public static final String SMN_ENDPOINT_TAG = "endpoint";
	public static final String SMN_SUBCRIBE_REMARK = "remark";

	public static final String SMN_SUBSCRIPTIONS = "subscriptions";
	public static final String SMN_NOTIFICATIONS = "notifications";
	public static final String SMN_PUBLISH = "publish";
	public static final String SMN_MESSAGE_TEMPLATE = "message_template";

	public static final String SMN_SUB_PROTOCOL_EMAIL = "email";
	public static final String SMN_SUB_PROTOCOL_SMS = "sms";
	public static final String SMN_SUB_PROTOCOL_HTTP = "http";
	public static final String SMN_SUB_PROTOCOL_HTTPS = "https";
	public static final String SMN_SUB_PROTOCOL_APPLICATION = "application";
	public static final String SMN_SUB_PROTOCOL_LAMBDA = "lambda";

	public static final String SMN_TOPIC_ATTRIBTUES = "attributes";
	public static final String SMN_TOPIC_ATTRIBUTE_VALUE = "value";
}
