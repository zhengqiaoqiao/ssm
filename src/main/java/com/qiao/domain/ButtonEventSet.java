package com.qiao.domain;

import java.io.Serializable;
import java.util.List;


/**
 * <p>Title: ButtonEventSet</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月16日
 */
public class ButtonEventSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonEventTypeId;
	private String buttonEventTypeName;
	private String remark;
	private List<ButtonEvent> buttonEvents;
	/**
	 * @return the buttonEventTypeId
	 */
	public String getButtonEventTypeId() {
		return buttonEventTypeId;
	}
	/**
	 * @param buttonEventTypeId the buttonEventTypeId to set
	 */
	public void setButtonEventTypeId(String buttonEventTypeId) {
		this.buttonEventTypeId = buttonEventTypeId;
	}
	/**
	 * @return the buttonEventTypeName
	 */
	public String getButtonEventTypeName() {
		return buttonEventTypeName;
	}
	/**
	 * @param buttonEventTypeName the buttonEventTypeName to set
	 */
	public void setButtonEventTypeName(String buttonEventTypeName) {
		this.buttonEventTypeName = buttonEventTypeName;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the buttonEvents
	 */
	public List<ButtonEvent> getButtonEvents() {
		return buttonEvents;
	}
	/**
	 * @param buttonEvents the buttonEvents to set
	 */
	public void setButtonEvents(List<ButtonEvent> buttonEvents) {
		this.buttonEvents = buttonEvents;
	}
}
