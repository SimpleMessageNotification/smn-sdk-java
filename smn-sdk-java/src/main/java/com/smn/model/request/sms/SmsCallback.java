package com.smn.model.request.sms;

public class SmsCallback {
    /**
     * 回调事件类型
     */
    private String eventType;

    /**
     * topic资源唯一标示
     */
    private String topicUrn;

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
