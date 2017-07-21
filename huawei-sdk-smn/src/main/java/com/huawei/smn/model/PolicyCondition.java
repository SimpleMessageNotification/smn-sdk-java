package com.huawei.smn.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * huangqiong
 * 
 * @author huangqiong
 *
 */
public class PolicyCondition implements Serializable {

	private static final long serialVersionUID = -9076611619072831664L;
	/**
	 * condition operator
	 */
	private String condtionOperator;
	/**
	 * condition elements list
	 */
	private Map<String, List<String>> conditionEles;

	public PolicyCondition() {

	}

	public String getCondtionOperator() {
		return condtionOperator;
	}

	public void setCondtionOperator(String condtionOperator) {
		this.condtionOperator = condtionOperator;
	}

	public Map<String, List<String>> getConditionEles() {
		return conditionEles;
	}

	public void setConditionEles(Map<String, List<String>> conditionEles) {
		this.conditionEles = conditionEles;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);

	}

}
