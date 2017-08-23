package com.huawei.smn.model.request.topic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.AccessPolicyConstants;

import junit.framework.TestCase;

public class UpdateTopicAttributeRequestTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(UpdateTopicAttributeRequestTest.class);
    UpdateTopicAttributeRequest updateTopicAttributeRequest;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        updateTopicAttributeRequest = new UpdateTopicAttributeRequest();
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        updateTopicAttributeRequest.setProjectId(PROJECT_ID);
        updateTopicAttributeRequest.setTopicUrn(topicUrn);
        updateTopicAttributeRequest.setAttributesName("access_policy");
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate/attributes/access_policy";
        Assert.assertEquals(requestURL, updateTopicAttributeRequest.getRequestUri());
    }

    public void testGetRequestParameterMap() throws Exception {

        List<LinkedHashMap<String, Object>> statements = new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String, Object> singleStatement = new LinkedHashMap<String, Object>();
        // action List
        List<String> actionList = new ArrayList<String>();
        actionList.add("SMN:UpdateTopic");
        actionList.add("SMN:DeleteTopic");
        actionList.add("SMN:QueryTopicDetail");
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

        updateTopicAttributeRequest.setAcessPolicy(acessPolicy);

        Assert.assertNotNull(updateTopicAttributeRequest.getRequestParameterMap().get("value"));
    }

}
