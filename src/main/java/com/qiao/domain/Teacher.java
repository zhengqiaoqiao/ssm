package com.qiao.domain;

import java.io.Serializable;

/**
 * <p>Title: Teacher</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月15日
 */
public class Teacher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义实体类的属性，与teacher表中的字段对应
    private int id;            //id===>t_id
    private String name;    //name===>t_name

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
        return "Teacher [id=" + id + ", name=" + name + "]";
    }
}
