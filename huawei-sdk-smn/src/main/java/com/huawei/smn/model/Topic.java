/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.huawei.smn.model;

import java.io.Serializable;

/**
 * topic structure
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
 */
public class Topic implements Serializable {

    private static final long serialVersionUID = 5804872621152448143L;
    /**
     * topic's unique resource identifier
     */
    private String topicUrn;
    /**
     * topic name
     */
    private String name;
    /**
     * topic's descriptions
     */
    private String displayName;
    /**
     * push policy
     */
    private int pushPolicy;

    /**
     * @return the topicUrn
     */
    public String getTopicUrn() {
        return topicUrn;
    }

    /**
     * @param topicUrn
     *            the topicUrn to set
     */
    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the pushPolicy
     */
    public int getPushPolicy() {
        return pushPolicy;
    }

    /**
     * @param pushPolicy
     *            the pushPolicy to set
     */
    public void setPushPolicy(int pushPolicy) {
        this.pushPolicy = pushPolicy;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Topic [topicUrn=").append(topicUrn).append(", name=").append(name).append(", displayName=")
                .append(displayName).append(", pushPolicy=").append(pushPolicy).append("]");
        return builder.toString();
    }

}
