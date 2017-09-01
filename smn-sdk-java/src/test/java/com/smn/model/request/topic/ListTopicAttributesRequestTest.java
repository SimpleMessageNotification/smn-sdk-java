/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http: //www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.smn.model.request.topic;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smn.model.request.topic.ListTopicAttributesRequest;

import junit.framework.TestCase;

/**
 * 
 * @author huangqiong
 * @date 2017年8月23日 下午4:00:54
 * @version 0.1
 */
public class ListTopicAttributesRequestTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(ListTopicAttributesRequestTest.class);
    ListTopicAttributesRequest listTopicAttributesRequest;
    /**
     * project id
     */
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    /**
     * final string "access_policy"
     */
    final static String ACCESS_POLICY = "access_policy";

    @Before
    public void setUp() {
        String topicUrn = "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate";
        listTopicAttributesRequest = new ListTopicAttributesRequest();
        listTopicAttributesRequest.setProjectId(PROJECT_ID);
        listTopicAttributesRequest.setAttributesName(ACCESS_POLICY);
        listTopicAttributesRequest.setTopicUrn(topicUrn);
    }

    public void testGetRequestUri() throws Exception {
        logger.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics/urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:createMessageTemplate/attributes?name=access_policy";
        Assert.assertEquals(requestURL, listTopicAttributesRequest.getRequestUri());
    }

}
