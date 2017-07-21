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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public Map<String, List<String>> getPrincipal() {
		return principal;
	}

	public void setPrincipal(Map<String, List<String>> principal) {
		this.principal = principal;
	}

	public Map<String, List<String>> getNotPrincipal() {
		return notPrincipal;
	}

	public void setNotPrincipal(Map<String, List<String>> notPrincipal) {
		this.notPrincipal = notPrincipal;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public List<String> getNotActions() {
		return notActions;
	}

	public void setNotActions(List<String> notActions) {
		this.notActions = notActions;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getNotResource() {
		return notResource;
	}

	public void setNotResource(String notResource) {
		this.notResource = notResource;
	}

	public List<PolicyCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<PolicyCondition> conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
