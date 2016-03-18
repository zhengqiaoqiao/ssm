package com.qiao.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qiao.domain.ButtonEventSet;


/**
 * <p>Title: ButtonEventSetDao</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月16日
 */
@Repository
public interface ButtonEventSetDao {
	public List<ButtonEventSet> list(ButtonEventSet buttonEventSet);
}
