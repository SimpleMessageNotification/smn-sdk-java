/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
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
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SmsCallback [eventType=").append(eventType)
                .append(", topicUrn=").append(topicUrn)
                .append("]");
        return builder.toString();
    }
}
