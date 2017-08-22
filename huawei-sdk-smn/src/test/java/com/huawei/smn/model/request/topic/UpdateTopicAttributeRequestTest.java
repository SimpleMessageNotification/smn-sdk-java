package com.huawei.smn.model.request.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.model.AuthenticationBean;

import junit.framework.TestCase;

public class UpdateTopicAttributeRequestTest extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(UpdateTopicAttributeRequestTest.class);
    UpdateTopicAttributeRequest updateTopicAttributeRequest;
    static AuthenticationBean authenticationBean;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";
    final static String REGION_ID = "cn-north-1";
    final static String SMN_ENDPOINT = "https://smn.cn-north-1.myhwclouds.com";

    @Before
    public void setUp() {
        updateTopicAttributeRequest = mock(UpdateTopicAttributeRequest.class);
        SmnConfiguration smnConfiguration = mock(SmnConfiguration.class);
        authenticationBean = mock(AuthenticationBean.class);
        when(authenticationBean.getProjectId()).thenReturn(PROJECT_ID);
        when(smnConfiguration.getRegionId()).thenReturn(REGION_ID);
        when(smnConfiguration.getSmnEndpoint()).thenReturn(SMN_ENDPOINT);
    }

    public void testGetRequestUrl() throws Exception {
        logger.info("starting test");
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        when(updateTopicAttributeRequest.getTopicUrn()).thenReturn(topicUrn);
        when(updateTopicAttributeRequest.getAttributesName()).thenReturn("access_policy");
        // when(updateTopicAttributeRequest.getAuthenticationBean()).thenReturn(authenticationBean);
        when(updateTopicAttributeRequest.getRequestUrl()).thenCallRealMethod();
        String requestURL = "https://smn.cn-north-1.myhwclouds.com/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate/attributes/access_policy";
        Assert.assertEquals(requestURL, updateTopicAttributeRequest.getRequestUrl());
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

        when(updateTopicAttributeRequest.getAcessPolicy()).thenReturn(acessPolicy);
        when(updateTopicAttributeRequest.getRequestParameterMap()).thenCallRealMethod();
        Assert.assertNotNull(updateTopicAttributeRequest.getRequestParameterMap().get("value"));
    }

}
