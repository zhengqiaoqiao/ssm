package com.qiao.domain;

import java.io.Serializable;


/**
 * <p>Title: ButtonEvent</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月16日
 */
public class ButtonEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonEventId;
	private String buttonEventName;
	private String buttonEventMethod;
	private ButtonEventSet buttonEventSet;
	/**
	 * @return the buttonEventId
	 */
	public String getButtonEventId() {
		return buttonEventId;
	}
	/**
	 * @param buttonEventId the buttonEventId to set
	 */
	public void setButtonEventId(String buttonEventId) {
		this.buttonEventId = buttonEventId;
	}
	/**
	 * @return the buttonEventName
	 */
	public String getButtonEventName() {
		return buttonEventName;
	}
	/**
	 * @param buttonEventName the buttonEventName to set
	 */
	public void setButtonEventName(String buttonEventName) {
		this.buttonEventName = buttonEventName;
	}
	/**
	 * @return the buttonEventMethod
	 */
	public String getButtonEventMethod() {
		return buttonEventMethod;
	}
	/**
	 * @param buttonEventMethod the buttonEventMethod to set
	 */
	public void setButtonEventMethod(String buttonEventMethod) {
		this.buttonEventMethod = buttonEventMethod;
	}
	/**
	 * @return the buttonEventSet
	 */
	public ButtonEventSet getButtonEventSet() {
		return buttonEventSet;
	}
	/**
	 * @param buttonEventSet the buttonEventSet to set
	 */
	public void setButtonEventSet(ButtonEventSet buttonEventSet) {
		this.buttonEventSet = buttonEventSet;
	}
}
