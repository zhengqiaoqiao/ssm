package com.qiao.domain;

import java.io.Serializable;

/**
 * <p>Title: Student</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月15日
 */
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义属性，和student表中的字段对应
    private int id;            //id===>s_id
    private String name;    //name===>s_name
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}
