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
package com.smn.model.request.sms;

/**
 * sms callback entity
 *
 * @author zhangyx
 * @version 0.8
 */
public class SmsCallback {
    /**
     * callback event type
     */
    private String eventType;

    /**
     * topic urn
     */
    private String topicUrn;

    /**
     * no args construct
     */
    public SmsCallback() {

    }

    /**
     * construct
     *
     * @param eventType the eventType to set
     * @param topicUrn  the topicUrn to st
     */
    public SmsCallback(String eventType, String topicUrn) {
        this.eventType = eventType;
        this.topicUrn = topicUrn;
    }

    /**
     * @return the event_type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return eht topic_urn
     */
    public String getTopicUrn() {
        return topicUrn;
    }

    /**
     * @param topicUrn the topic_urn to set
     */
    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    /**
     * @return string
     */
    @Override
    public String toString() {
        return "SmsCallback{" +
                "eventType='" + eventType + '\'' +
                ", topicUrn='" + topicUrn + '\'' +
                '}';
    }
}
