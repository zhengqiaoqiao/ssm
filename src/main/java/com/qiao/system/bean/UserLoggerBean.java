package com.qiao.system.bean;

import java.util.Map;

public class UserLoggerBean {
	//方法名称
	private String methodName;
	//方法说明
	private String methodDesc;
	//请求IP
	private String requestIp;
	//方法执行开始时间
	private String requestStartTime;
	//方法执行结束时间
	private String requestEndTime;
	//方法执行时间（毫秒）
	private Long executionTime;
	//方法的入参
	private Map<String, Object> params;
	//方法执行结果
	private Object result;
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodDesc() {
		return methodDesc;
	}
	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public String getRequestStartTime() {
		return requestStartTime;
	}
	public void setRequestStartTime(String requestStartTime) {
		this.requestStartTime = requestStartTime;
	}
	public String getRequestEndTime() {
		return requestEndTime;
	}
	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public Long getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
