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

	public String getTopicUrn() {
		return topicUrn;
	}

	public void setTopicUrn(String topicUrn) {
		this.topicUrn = topicUrn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getPushPolicy() {
		return pushPolicy;
	}

	public void setPushPolicy(int pushPolicy) {
		this.pushPolicy = pushPolicy;
	}

}
