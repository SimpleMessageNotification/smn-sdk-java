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

    /**
     * @return the condtionOperator
     */
    public String getCondtionOperator() {
        return condtionOperator;
    }

    /**
     * @param condtionOperator
     *            the condtionOperator to set
     */
    public void setCondtionOperator(String condtionOperator) {
        this.condtionOperator = condtionOperator;
    }

    /**
     * @return the conditionEles
     */
    public Map<String, List<String>> getConditionEles() {
        return conditionEles;
    }

    /**
     * @param conditionEles
     *            the conditionEles to set
     */
    public void setConditionEles(Map<String, List<String>> conditionEles) {
        this.conditionEles = conditionEles;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);

    }

}
