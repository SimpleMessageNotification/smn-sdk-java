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
import java.util.List;
import java.util.Map;

/**
 * PolicyCondition
 * 
 * @author huangqiong
 *
 * @date 2017年8月2日
 *
 * @version 0.1
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PolicyCondition [condtionOperator=").append(condtionOperator).append(", conditionEles=")
                .append(conditionEles).append("]");
        return builder.toString();
    }

}
