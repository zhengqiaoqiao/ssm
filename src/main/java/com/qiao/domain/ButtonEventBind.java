package com.qiao.domain;

import java.io.Serializable;

/**
 * <p>Title: ButtonEventBind</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月16日
 */
public class ButtonEventBind implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonEventBindId;
	private String nodeButtonBindId;
	private int buttonEventBindSortNo;
	private int buttonEventBindResultType;
	private int buttonEventBindType;
	private ButtonEventSet buttonEventSet;           
	private ButtonEvent buttonEvent;
	/**
	 * @return the buttonEventBindId
	 */
	public String getButtonEventBindId() {
		return buttonEventBindId;
	}
	/**
	 * @param buttonEventBindId the buttonEventBindId to set
	 */
	public void setButtonEventBindId(String buttonEventBindId) {
		this.buttonEventBindId = buttonEventBindId;
	}
	/**
	 * @return the nodeButtonBindId
	 */
	public String getNodeButtonBindId() {
		return nodeButtonBindId;
	}
	/**
	 * @param nodeButtonBindId the nodeButtonBindId to set
	 */
	public void setNodeButtonBindId(String nodeButtonBindId) {
		this.nodeButtonBindId = nodeButtonBindId;
	}
	/**
	 * @return the buttonEventBindSortNo
	 */
	public int getButtonEventBindSortNo() {
		return buttonEventBindSortNo;
	}
	/**
	 * @param buttonEventBindSortNo the buttonEventBindSortNo to set
	 */
	public void setButtonEventBindSortNo(int buttonEventBindSortNo) {
		this.buttonEventBindSortNo = buttonEventBindSortNo;
	}
	/**
	 * @return the buttonEventBindResultType
	 */
	public int getButtonEventBindResultType() {
		return buttonEventBindResultType;
	}
	/**
	 * @param buttonEventBindResultType the buttonEventBindResultType to set
	 */
	public void setButtonEventBindResultType(int buttonEventBindResultType) {
		this.buttonEventBindResultType = buttonEventBindResultType;
	}
	/**
	 * @return the buttonEventBindType
	 */
	public int getButtonEventBindType() {
		return buttonEventBindType;
	}
	/**
	 * @param buttonEventBindType the buttonEventBindType to set
	 */
	public void setButtonEventBindType(int buttonEventBindType) {
		this.buttonEventBindType = buttonEventBindType;
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
	/**
	 * @return the buttonEvent
	 */
	public ButtonEvent getButtonEvent() {
		return buttonEvent;
	}
	/**
	 * @param buttonEvent the buttonEvent to set
	 */
	public void setButtonEvent(ButtonEvent buttonEvent) {
		this.buttonEvent = buttonEvent;
	}
}
