package com.huawei.smn.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.smn.common.AccessPolicyConstants;
import com.huawei.smn.common.AccessPolicyType;
import com.huawei.smn.model.request.publish.PublishMsgRequest;
import com.huawei.smn.model.request.sms.SmsPublishRequest;
import com.huawei.smn.model.request.subscription.ListSubscriptionsByTopicRequest;
import com.huawei.smn.model.request.subscription.ListSubscriptionsRequest;
import com.huawei.smn.model.request.subscription.SubcriptionRequest;
import com.huawei.smn.model.request.subscription.UnSubcriptionRequest;
import com.huawei.smn.model.request.template.CreateMessageTemplateRequest;
import com.huawei.smn.model.request.template.DeleteMessageTemplateRequest;
import com.huawei.smn.model.request.template.ListMessageTemplatesRequest;
import com.huawei.smn.model.request.template.QueryMessageTemplateDetailRequest;
import com.huawei.smn.model.request.template.UpdateMessageTemplateRequest;
import com.huawei.smn.model.request.topic.CreateTopicRequest;
import com.huawei.smn.model.request.topic.DeleteTopicAttributeByNameRequest;
import com.huawei.smn.model.request.topic.DeleteTopicAttributesRequest;
import com.huawei.smn.model.request.topic.DeleteTopicRequest;
import com.huawei.smn.model.request.topic.ListTopicAttributesRequest;
import com.huawei.smn.model.request.topic.ListTopicsRequest;
import com.huawei.smn.model.request.topic.QueryTopicDetailRequest;
import com.huawei.smn.model.request.topic.UpdateTopicAttributeRequest;
import com.huawei.smn.model.request.topic.UpdateTopicRequest;
import com.huawei.smn.service.MessageTemplateService;
import com.huawei.smn.service.PublishService;
import com.huawei.smn.service.SmsService;
import com.huawei.smn.service.SubscriptionService;
import com.huawei.smn.service.TopicService;
import com.huawei.smn.service.impl.MessageTemplateServiceImpl;
import com.huawei.smn.service.impl.PublishServiceImpl;
import com.huawei.smn.service.impl.SmsServiceImpl;
import com.huawei.smn.service.impl.SubscriptionServiceImpl;
import com.huawei.smn.service.impl.TopicServiceImpl;

import junit.framework.TestCase;

public class TestRequest extends TestCase {
	private static Logger logger = LoggerFactory.getLogger(TestRequest.class);

	// 创建Topic pass
	public void testCreateTopic() {
		CreateTopicRequest createRequest = new CreateTopicRequest();
		createRequest.setName("publishMsgWithStruct02");
		logger.warn(createRequest.toString());
		logger.info(createRequest.getRequestUrl());
		TopicService topicService = new TopicServiceImpl();
		logger.info(createRequest.getRequestHeaderMap().toString());
		topicService.setSmnRequest(createRequest);
		Map<String, Object> res = topicService.createTopic(createRequest);
		logger.info(res.toString());
	}

	// 删除Topic pass
	public void testDeleteTopic() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:publishMsgWithStruct02";
		DeleteTopicRequest deleteRequest = new DeleteTopicRequest();
		deleteRequest.setTopicUrn(topicUrn);
		logger.info(deleteRequest.getRequestUrl());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(deleteRequest);
		Map<String, Object> res = topicService.deleteTopic(deleteRequest);
		logger.info(res.toString());
	}

	// 查询Topic列表 pass
	public void testListTopics() {
		ListTopicsRequest listTopicsRequest = new ListTopicsRequest();
		logger.warn(listTopicsRequest.toString());
		logger.info(listTopicsRequest.getRequestUrl());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(listTopicsRequest);
		Map<String, Object> res = topicService.listTopics(listTopicsRequest);
		logger.info(res.toString());
	}

	// 查询Topic详情
	public void testQueryTopicDetail() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		QueryTopicDetailRequest queryTopicDetailRequest = new QueryTopicDetailRequest();
		queryTopicDetailRequest.setTopicUrn(topicUrn);
		logger.warn(queryTopicDetailRequest.toString());
		logger.info(queryTopicDetailRequest.getRequestUrl());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(queryTopicDetailRequest);
		Map<String, Object> res = topicService.queryTopicDetail(queryTopicDetailRequest);
		logger.info(res.toString());
	}

	// 查询Topic属性 pass
	public void testListTopicAttributesRequest() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		ListTopicAttributesRequest listTopicAttributesRequest = new ListTopicAttributesRequest();
		listTopicAttributesRequest.setTopicUrn(topicUrn);
		listTopicAttributesRequest.setAttributesName("access_policy");
		logger.warn(listTopicAttributesRequest.toString());
		logger.info(listTopicAttributesRequest.getRequestUrl());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(listTopicAttributesRequest);
		Map<String, Object> res = topicService.listTopicAttributes(listTopicAttributesRequest);
		logger.info(res.toString());
	}

	// 更新Topic属性 pass
	public void testUpdateTopicAttributeRequest() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		UpdateTopicAttributeRequest updateTopicAttributeRequest = new UpdateTopicAttributeRequest();
		updateTopicAttributeRequest.setTopicUrn(topicUrn);
		/**
		 * Topic attribute have two types currently: access_policy or
		 * introduction;other value is invalid
		 */
		updateTopicAttributeRequest.setAttributesName(AccessPolicyType.ACCESS_POLICY);

		/**
		 * 访问策略组成:访问策略是通过Statement语句来定义的。 一个访问策略可包含一条或多条Statement语句。
		 * 使用Statement语句可对其他用户或云服务授权对主题操作，包括查询主题订阅者列表、增加主题的发送消息模板、发布消息、修改主题属性、甚至删除该主题等操作。
		 */
		List<LinkedHashMap<String, Object>> statements = new ArrayList<LinkedHashMap<String, Object>>();

		LinkedHashMap<String, Object> singleStatement = new LinkedHashMap<String, Object>();

		/**
		 * currently effective actions incuded as following:
		 * SMN:UpdateTopic,SMN:DeleteTopic,SMN:QueryTopicDetail,
		 * SMN:ListTopicAttributes,SMN:UpdateTopicAttribute,
		 * SMN:DeleteTopicAttributes,SMN:DeleteTopicAttributeByName,
		 * SMN:ListSubscriptionsByTopic,SMN:Subscribe,SMN:Unsubscribe,SMN:Publish.
		 * 
		 */
		// action List
		List<String> actionList = new ArrayList<String>();
		actionList.add("SMN:UpdateTopic");
		actionList.add("SMN:DeleteTopic");
		actionList.add("SMN:QueryTopicDetail");
		actionList.add("SMN:ListTopicAttributes");
		actionList.add("SMN:UpdateTopicAttribute");
		actionList.add("SMN:DeleteTopicAttributes");
		actionList.add("SMN:DeleteTopicAttributeByName");
		actionList.add("SMN:ListSubscriptionsByTopic");
		actionList.add("SMN:Subscribe");
		actionList.add("SMN:Unsubscribe");
		actionList.add("SMN:Publish");
		singleStatement.put(AccessPolicyConstants.POLICY_ACTION, actionList);

		Map<String, List<String>> principal = new LinkedHashMap<String, List<String>>();
		List<String> cspPolicy = new ArrayList<String>();
		cspPolicy.add("*");
		principal.put(AccessPolicyConstants.POLICY_CSP, cspPolicy);
		singleStatement.put(AccessPolicyConstants.POLICY_PRINCIPAL, principal);

		String uuid = UUID.randomUUID().toString().replace("-", "");
		logger.info(uuid);
		singleStatement.put(AccessPolicyConstants.POLICY_STATEMENT_ID, uuid);
		singleStatement.put(AccessPolicyConstants.POLICY_EFFECT, AccessPolicyConstants.POLICY_EFFECT_ALLOW);
		singleStatement.put(AccessPolicyConstants.POLICY_RESOURCE,
				"urn:smn:regionId:8bad8a40e0f7462f8c1676e3f93a8183:zhaoli");

		// 策略里配置statement,可多个,这里以一个为例
		statements.add(singleStatement);

		LinkedHashMap<String, Object> acessPolicy = new LinkedHashMap<String, Object>();
		acessPolicy.put(AccessPolicyConstants.POLICY_ID, AccessPolicyConstants.DEFAULT_POLICY_ID);
		acessPolicy.put(AccessPolicyConstants.POLICY_VERSION, AccessPolicyConstants.DEFAULT_VERSION);
		acessPolicy.put(AccessPolicyConstants.POLICY_STATEMENT, statements);
		// 创建新的access policy,默认的id与version
		// 设置 acessPolicy
		updateTopicAttributeRequest.setAcessPolicy(acessPolicy);
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(updateTopicAttributeRequest);
		Map<String, Object> res = topicService.updateTopicAttribute(updateTopicAttributeRequest);
		logger.info(res.toString());
	}

	// 删除指定名称的Topic属性 pass
	public void testDeleteTopicAttributeByName() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		DeleteTopicAttributeByNameRequest deleteTopicAttributeByNameRequest = new DeleteTopicAttributeByNameRequest();
		deleteTopicAttributeByNameRequest.setTopicUrn(topicUrn);
		deleteTopicAttributeByNameRequest.setAttributesName("access_policy");
		logger.warn(deleteTopicAttributeByNameRequest.toString());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(deleteTopicAttributeByNameRequest);
		Map<String, Object> res = topicService.deleteTopicAttributeByName(deleteTopicAttributeByNameRequest);
		logger.info(res.toString());
	}

	// 删除所有Topic属性 pass
	public void testDeleteTopicAttributes() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		DeleteTopicAttributesRequest deleteTopicAttributeRequest = new DeleteTopicAttributesRequest();
		deleteTopicAttributeRequest.setTopicUrn(topicUrn);
		logger.warn(deleteTopicAttributeRequest.toString());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(deleteTopicAttributeRequest);
		Map<String, Object> res = topicService.deleteTopicAttributes(deleteTopicAttributeRequest);
		logger.info(res.toString());
	}

	// 更新Topic pass
	public void testUpdateTopic() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		UpdateTopicRequest updateTopicRequest = new UpdateTopicRequest();

		updateTopicRequest.setTopicUrn(topicUrn);
		updateTopicRequest.setDisplayName("cragie");
		logger.warn(updateTopicRequest.toString());
		TopicService topicService = new TopicServiceImpl();
		topicService.setSmnRequest(updateTopicRequest);
		Map<String, Object> res = topicService.updateTopic(updateTopicRequest);
		logger.info(res.toString());

	}

	// 测试订阅功能 pass
	public void testSubcription() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		SubcriptionRequest createSubcription = new SubcriptionRequest();
		createSubcription.setTopicUrn(topicUrn);
		createSubcription.setEndpoint("+8613302965216");
		createSubcription.setProtocol("sms");
		// createSubcription.setEndpoint("18588432306");
		// createSubcription.setEndpoint("970380859@qq.com");
		// createSubcription.setProtocol("email");
		createSubcription.setRemark("api订阅接口测试成功");
		SubscriptionService subscriptionService = new SubscriptionServiceImpl();
		subscriptionService.setSmnRequest(createSubcription);
		logger.warn(createSubcription.getRequestParameterMap().toString());
		Map<String, Object> res = subscriptionService.subscribe(createSubcription);
		logger.info(res.toString());

	}

	// 查询订阅者列表 pass
	public void testListSubscriptions() {
		ListSubscriptionsRequest listSubscriptionsRequest = new ListSubscriptionsRequest();
		logger.warn(listSubscriptionsRequest.getRequestUrl());
		SubscriptionService subscriptionService = new SubscriptionServiceImpl();
		subscriptionService.setSmnRequest(listSubscriptionsRequest);
		logger.warn(listSubscriptionsRequest.toString());
		Map<String, Object> res = subscriptionService.listSubscriptions(listSubscriptionsRequest);
		logger.info(res.toString());
	}

	// 查询指定主题的订阅者列表 pass
	public void testListSubscriptionsByTopic() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest();
		listSubscriptionsByTopicRequest.setTopicUrn(topicUrn);
		SubscriptionService subscriptionService = new SubscriptionServiceImpl();
		subscriptionService.setSmnRequest(listSubscriptionsByTopicRequest);
		Map<String, Object> res = subscriptionService.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);
		logger.info(res.toString());
	}

	// 取消订阅 pass
	public void testUnSubcription() {
		String subscriptionUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate:da12dff9f2844278806978d86a36c312";
		UnSubcriptionRequest deleteSubcription = new UnSubcriptionRequest();
		deleteSubcription.setSubscriptionUrn(subscriptionUrn);
		SubscriptionService subscriptionService = new SubscriptionServiceImpl();
		subscriptionService.setSmnRequest(deleteSubcription);
		logger.warn(deleteSubcription.toString());
		Map<String, Object> res = subscriptionService.unsubscribe(deleteSubcription);
		logger.info(res.toString());
	}

	// 测试消息发布 pass
	public void testPublishMsg() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
		publishMsgRequest.setTopicUrn(topicUrn);
		publishMsgRequest.setMessage("测试sdk发布消息 ，7.18");
		publishMsgRequest.setSubject("ready to release");
		PublishService publishService = new PublishServiceImpl();
		publishService.setSmnRequest(publishMsgRequest);
		logger.warn(publishMsgRequest.getRequestParameterMap().toString());
		Map<String, Object> res = publishService.publish(publishMsgRequest);
		logger.info(res.toString());
	}

	// 使用消息模板方式的消息发布 pass
	public void testPublishMsgWithTemplate() throws JsonProcessingException {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
		PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
		publishMsgRequest.setTopicUrn(topicUrn);

		publishMsgRequest.setMessageTemplateName("createMessageTemplate");
		Map<String, Object> tagsMap = new HashMap<String, Object>();
		tagsMap.put("year", "2017");
		tagsMap.put("name", "qiong");
		tagsMap.put("company", "huawei");
		publishMsgRequest.setTags(tagsMap);
		publishMsgRequest.setSubject("publish msg with template");
		PublishService publishService = new PublishServiceImpl();
		publishService.setSmnRequest(publishMsgRequest);
		logger.warn(publishMsgRequest.getRequestParameterMap().toString());
		Map<String, Object> res = publishService.publish(publishMsgRequest);
		logger.info(res.toString());
	}

	// 使用消息结构体方式的消息发布 pass
	public void testPublishMsgWithStruct() {
		String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi";
		PublishMsgRequest publishMsgRequest = new PublishMsgRequest();
		publishMsgRequest.setTopicUrn(topicUrn);

		Map<String, Object> structMsgMap = new HashMap<String, Object>();
		structMsgMap.put("default", "hello 2017,welcome struct test");
		structMsgMap.put("sms", "hello 2017,welcome struct test");
		structMsgMap.put("http", "hello 2017,welcome struct test");
		structMsgMap.put("https", "hello 2017,welcome struct test");
		structMsgMap.put("email", "hello 2017,welcome struct test");
		JSONObject jsonObject = new JSONObject(structMsgMap);
		logger.warn(jsonObject.toJSONString());
		publishMsgRequest.setMessageStructure(jsonObject.toJSONString());
		logger.warn(publishMsgRequest.toString());
		PublishService publishService = new PublishServiceImpl();
		publishService.setSmnRequest(publishMsgRequest);
		logger.warn(publishMsgRequest.getRequestParameterMap().toString());
		Map<String, Object> res = publishService.publish(publishMsgRequest);
		logger.info(res.toString());
	}

	// 创建消息模板 pass
	public void testCreateMessageTemplateRequest() {
		CreateMessageTemplateRequest createMessageTemplateRequest = new CreateMessageTemplateRequest();
		MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
		createMessageTemplateRequest.setMessageTemplateName("cragie");
		String content = "hello {name},hello {word},hello {gingo}";
		createMessageTemplateRequest.setContent(content);
		createMessageTemplateRequest.setProtocol("sms");// default,sms,http,https,email
		logger.warn(createMessageTemplateRequest.toString());
		messageTemplateService.setSmnRequest(createMessageTemplateRequest);
		logger.warn(createMessageTemplateRequest.getRequestParameterMap().toString());

		Map<String, Object> res = messageTemplateService.createMessageTemplate(createMessageTemplateRequest);
		logger.info(res.toString());
	}

	// 删除消息模板 pass
	public void testDeleteMessageTemplateRequest() {
		String messageTemplateId = "b23d85e4db804a88a916b3bb120671fd";
		DeleteMessageTemplateRequest deleteMessageTemplateRequest = new DeleteMessageTemplateRequest();
		MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
		deleteMessageTemplateRequest.setMessageTemplateId(messageTemplateId);
		logger.warn(deleteMessageTemplateRequest.toString());
		messageTemplateService.setSmnRequest(deleteMessageTemplateRequest);
		logger.warn(deleteMessageTemplateRequest.getRequestParameterMap().toString());

		Map<String, Object> res = messageTemplateService.deleteMessageTemplate(deleteMessageTemplateRequest);
		logger.info(res.toString());
	}

	// 更新消息模板 pass
	public void testUpdateMessageTemplateRequest() {
		String messageTemplateId = "350a3937dbfa4f56834c97fd9e475e4e";
		UpdateMessageTemplateRequest updateMessageTemplateRequest = new UpdateMessageTemplateRequest();
		MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
		updateMessageTemplateRequest.setMessageTemplateId(messageTemplateId);
		String content = "this year is {year},welcom {name} to join the {company} to create new life";
		updateMessageTemplateRequest.setContent(content);
		logger.warn(updateMessageTemplateRequest.toString());
		messageTemplateService.setSmnRequest(updateMessageTemplateRequest);
		logger.warn(updateMessageTemplateRequest.getRequestParameterMap().toString());

		Map<String, Object> res = messageTemplateService.updateMessageTemplate(updateMessageTemplateRequest);
		logger.info(res.toString());
	}

	// 查询消息模板列表 pass
	public void testListMessageTemplatesRequest() {
		ListMessageTemplatesRequest listMessageTemplatesRequest = new ListMessageTemplatesRequest();
		MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
		listMessageTemplatesRequest.setMessageTemplateName("createMessageTemplate");
		listMessageTemplatesRequest.setLimit(1);
		logger.warn(listMessageTemplatesRequest.toString());
		messageTemplateService.setSmnRequest(listMessageTemplatesRequest);
		Map<String, Object> res = messageTemplateService.listMessageTemplates(listMessageTemplatesRequest);
		logger.info(res.toString());
	}

	// 查询消息模板详情 pass
	public void testQueryMessageTemplateDetailRequest() {
		String messageTemplateId = "53013641b66d4ce4888b27f68e64bea3";
		QueryMessageTemplateDetailRequest queryMessageTemplateDetailRequest = new QueryMessageTemplateDetailRequest();
		MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();
		queryMessageTemplateDetailRequest.setMessageTemplateId(messageTemplateId);
		logger.warn(queryMessageTemplateDetailRequest.toString());
		messageTemplateService.setSmnRequest(queryMessageTemplateDetailRequest);
		Map<String, Object> res = messageTemplateService.queryMsgTemplateDetail(queryMessageTemplateDetailRequest);
		logger.info(res.toString());
	}

	// 短信直发 pass
	public void testSmsPublishRequest() {
		String endpoint = "13302965216";
		String message = "direct sending msg by qiong";
		SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
		SmsService smsService = new SmsServiceImpl();
		smsPublishRequest.setEndpoint(endpoint);
		smsPublishRequest.setMessage(message);
		logger.warn(smsPublishRequest.toString());
		smsService.setSmnRequest(smsPublishRequest);
		Map<String, Object> res = smsService.smsPublish(smsPublishRequest);
		logger.info(res.toString());
	}
}
