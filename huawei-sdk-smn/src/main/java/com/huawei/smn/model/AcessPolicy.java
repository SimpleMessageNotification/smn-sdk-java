package com.huawei.smn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.alibaba.fastjson.JSON;
import com.huawei.smn.common.AccessPolicyConstants;

/**
 * Model for storing strategy of policy string
 * 
 * @author huangqiong
 *
 */
public class AcessPolicy implements Serializable {

    private static final long serialVersionUID = 4901408514266312630L;
    /**
     * Version conform to policy regulation
     */
    private static final String version = "2016-09-07";
    /**
     * policy id
     */
    private String id = "__default_policy_ID";
    /**
     * policy's list for statement
     */
    private List<PolicyStatement> statements;

    public AcessPolicy() {

    }

    /**
     * Default constructor
     * 
     * @param id
     * @param statements
     */
    public AcessPolicy(String id, List<PolicyStatement> statements) {
        if (StringUtils.isNoneBlank(id)) {
            this.id = id;
        }
        this.statements = statements;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the statements
     */
    public List<PolicyStatement> getStatements() {
        return statements;
    }

    /**
     * @param statements
     *            the statements to set
     */
    public void setStatements(List<PolicyStatement> statements) {
        this.statements = statements;
    }

    /**
     * @return the version
     */
    public static String getVersion() {
        return version;
    }

    /***
     * Resolve and construct statement
     * 
     * @param policyStatements
     * @return
     */
    public List<LinkedHashMap<String, Object>> getStatementsMap(List<PolicyStatement> policyStatements) {
        List<LinkedHashMap<String, Object>> listStatementsMap = new ArrayList<LinkedHashMap<String, Object>>();
        for (PolicyStatement policyStatement : policyStatements) {
            LinkedHashMap<String, Object> statementMap = new LinkedHashMap<String, Object>();
            statementMap.put(AccessPolicyConstants.POLICY_STATEMENT_ID, policyStatement.getSid());
            statementMap.put(AccessPolicyConstants.POLICY_EFFECT, policyStatement.getEffect());
            if (StringUtils.isNotBlank(policyStatement.getNotResource())) {
                statementMap.put(AccessPolicyConstants.POLICY_NOT_RESOURCE, policyStatement.getNotResource());
            }
            if (StringUtils.isNotBlank(policyStatement.getResource())) {
                statementMap.put(AccessPolicyConstants.POLICY_RESOURCE, policyStatement.getResource());
            }
            if (Objects.nonNull(policyStatement.getActions())) {
                statementMap.put(AccessPolicyConstants.POLICY_ACTION, policyStatement.getActions());
            }
            if (Objects.nonNull(policyStatement.getNotActions())) {
                statementMap.put(AccessPolicyConstants.POLICY_NOT_ACTION, policyStatement.getNotActions());
            }
            if (Objects.nonNull(policyStatement.getConditions())) {
                statementMap.put(AccessPolicyConstants.POLICY_CONDITION,
                        getConditionsMap(policyStatement.getConditions()));
            }
            if (Objects.nonNull(policyStatement.getNotPrincipal())) {
                statementMap.put(AccessPolicyConstants.POLICY_NOT_PRINCIPAL, policyStatement.getNotPrincipal());
            }
            if (Objects.nonNull(policyStatement.getPrincipal())) {
                statementMap.put(AccessPolicyConstants.POLICY_PRINCIPAL, policyStatement.getPrincipal());
            }
            listStatementsMap.add(statementMap);

        }

        return listStatementsMap;

    }

    /**
     * Resolve and construct Condition
     * 
     * @param policyConditions
     * @return
     */
    private List<LinkedHashMap<String, Object>> getConditionsMap(List<PolicyCondition> policyConditions) {
        List<LinkedHashMap<String, Object>> listConditionMap = new ArrayList<LinkedHashMap<String, Object>>();
        for (PolicyCondition condition : policyConditions) {
            LinkedHashMap<String, Object> conditionMap = new LinkedHashMap<String, Object>();
            conditionMap.put(condition.getCondtionOperator(), condition.getConditionEles());
            listConditionMap.add(conditionMap);
        }
        return listConditionMap;
    }

    /**
     * Build accessPolicy to json string
     * 
     * @return
     */
    public String toJsonString() {
        Map<String, Object> mapForJson = new HashMap<String, Object>();
        mapForJson.put(AccessPolicyConstants.POLICY_ID, getId());
        mapForJson.put(AccessPolicyConstants.POLICY_VERSION, version);
        if (Objects.nonNull(this.getStatements())) {
            mapForJson.put(AccessPolicyConstants.POLICY_STATEMENT, getStatementsMap(this.getStatements()));
        }
        return JSON.toJSON(mapForJson).toString();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
