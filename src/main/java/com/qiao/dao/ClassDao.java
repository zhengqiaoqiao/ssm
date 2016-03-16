package com.qiao.dao;

import org.springframework.stereotype.Repository;

import com.qiao.domain.Classes;

/**
 * <p>Title: ClassDao</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月15日
 */
@Repository
public interface ClassDao {
	public Classes getClass(int cid);
	public Classes getClass2(int cid);
	public Classes getClass3(int cid);
	public Classes getClass4(int cid);
}
