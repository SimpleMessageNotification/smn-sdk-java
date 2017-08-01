package com.huawei.smn.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * PolicyStatement
 * 
 * @author huangqiong
 *
 */
public class PolicyStatement implements Serializable {

    private static final long serialVersionUID = -3993085253960566633L;
    /**
     * statement id
     */
    private String sid;
    /**
     * statement effect
     * 
     * Allow or Deny
     */
    private String effect;
    /**
     * 授权的对象
     */
    private Map<String, List<String>> principal;
    /**
     * 排除的授权对象
     */
    private Map<String, List<String>> notPrincipal;
    /**
     * 授权的操作列表
     */
    private List<String> actions;
    /**
     * 排除授权的操作列表
     */
    private List<String> notActions;
    /**
     * 授权的资源
     */
    private String resource;
    /**
     * 排除授权的资源
     */
    private String notResource;
    /**
     * 条件列表
     */
    private List<PolicyCondition> conditions;

    public PolicyStatement() {

    }

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid
     *            the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the effect
     */
    public String getEffect() {
        return effect;
    }

    /**
     * @param effect
     *            the effect to set
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }

    /**
     * @return the principal
     */
    public Map<String, List<String>> getPrincipal() {
        return principal;
    }

    /**
     * @param principal
     *            the principal to set
     */
    public void setPrincipal(Map<String, List<String>> principal) {
        this.principal = principal;
    }

    /**
     * @return the notPrincipal
     */
    public Map<String, List<String>> getNotPrincipal() {
        return notPrincipal;
    }

    /**
     * @param notPrincipal
     *            the notPrincipal to set
     */
    public void setNotPrincipal(Map<String, List<String>> notPrincipal) {
        this.notPrincipal = notPrincipal;
    }

    /**
     * @return the actions
     */
    public List<String> getActions() {
        return actions;
    }

    /**
     * @param actions
     *            the actions to set
     */
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    /**
     * @return the notActions
     */
    public List<String> getNotActions() {
        return notActions;
    }

    /**
     * @param notActions
     *            the notActions to set
     */
    public void setNotActions(List<String> notActions) {
        this.notActions = notActions;
    }

    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @param resource
     *            the resource to set
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * @return the notResource
     */
    public String getNotResource() {
        return notResource;
    }

    /**
     * @param notResource
     *            the notResource to set
     */
    public void setNotResource(String notResource) {
        this.notResource = notResource;
    }

    /**
     * @return the conditions
     */
    public List<PolicyCondition> getConditions() {
        return conditions;
    }

    /**
     * @param conditions
     *            the conditions to set
     */
    public void setConditions(List<PolicyCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
