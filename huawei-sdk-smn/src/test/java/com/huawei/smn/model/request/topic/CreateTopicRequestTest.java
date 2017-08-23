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
package com.huawei.smn.model.request.topic;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/**
 * 
 * @author huangqiong
 * @date 2017年8月23日 下午4:00:16
 * @version 0.1
 */
public class CreateTopicRequestTest extends TestCase {
    private static Logger LOGGER = LoggerFactory.getLogger(CreateTopicRequestTest.class);
    CreateTopicRequest createTopicRequest;
    final static String PROJECT_ID = "cffe4fc4c9a54219b60dbaf7b586e132";

    @Before
    public void setUp() {
        createTopicRequest = new CreateTopicRequest();
        createTopicRequest.setProjectId(PROJECT_ID);
    }

    public void testGetRequestUri() throws Exception {
        LOGGER.info("starting test");
        String requestURL = "/v2/cffe4fc4c9a54219b60dbaf7b586e132/notifications/topics";
        Assert.assertEquals(requestURL, createTopicRequest.getRequestUri());
    }

    public void testGetRequestParameterMap() throws Exception {

        Assert.assertEquals(createTopicRequest.getName(), createTopicRequest.getRequestParameterMap().get("name"));
        Assert.assertNull(createTopicRequest.getRequestParameterMap().get("displayName"));
    }

}
