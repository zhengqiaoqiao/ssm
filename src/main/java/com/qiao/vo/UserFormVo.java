package com.qiao.vo;

import java.util.List;

/**
 * <p>Title: UserFormVo</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月22日
 */
public class UserFormVo {
	private String name;
	private String sex;
	private int age;
	private List<ContactInfoVo> contacts;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the contacts
	 */
	public List<ContactInfoVo> getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(List<ContactInfoVo> contacts) {
		this.contacts = contacts;
	}
	
}
