package com.huawei.smn.common.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.huawei.smn.common.AccessPolicyConstants;

import junit.framework.TestCase;

public class JsonUtilTest extends TestCase {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetJsonStringByMap() throws Exception {

		List<LinkedHashMap<String, Object>> statements = new ArrayList<LinkedHashMap<String, Object>>();
		LinkedHashMap<String, Object> singleStatement = new LinkedHashMap<String, Object>();
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
		singleStatement.put(AccessPolicyConstants.POLICY_STATEMENT_ID, uuid);
		singleStatement.put(AccessPolicyConstants.POLICY_EFFECT, AccessPolicyConstants.POLICY_EFFECT_ALLOW);
		singleStatement.put(AccessPolicyConstants.POLICY_RESOURCE,
				"urn:smn:regionId:8bad8a40e0f7462f8c1676e3f93a8183:zhaoli");

		statements.add(singleStatement);
		LinkedHashMap<String, Object> acessPolicy = new LinkedHashMap<String, Object>();
		acessPolicy.put(AccessPolicyConstants.POLICY_ID, AccessPolicyConstants.DEFAULT_POLICY_ID);
		acessPolicy.put(AccessPolicyConstants.POLICY_VERSION, AccessPolicyConstants.DEFAULT_VERSION);
		acessPolicy.put(AccessPolicyConstants.POLICY_STATEMENT, statements);
		String accessPolicyJsonString = JsonUtil.getJsonStringByMap(acessPolicy);
		System.out.println(accessPolicyJsonString);
	}

	@Test
	public void testparseJsonMessage() throws Exception {
		String response = "{\"request_id\":\"7af80d04bb3647e6b4c0ae3d9d24aef1\",\"topic_urn\":\"urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:publishMsgWithStruct02\"}";
		Map<String, Object> respMap = JsonUtil.parseJsonMessage(response);

		System.out.println(respMap);
		Assert.assertNotNull(respMap.get("request_id"));
		Assert.assertNotNull(respMap.get("topic_urn"));
	}

}
