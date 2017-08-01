package com.huawei.smn.model;

import java.io.Serializable;

/**
 * topic structure
 * 
 * @author huangqiong
 *
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

}
