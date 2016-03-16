package com.qiao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiao.dao.ClassDao;
import com.qiao.domain.Classes;

/**
 * <p>Title: ClassService</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月15日
 */
@Service
public class ClassService {
	@Resource
	private ClassDao classDao;
	
	public Classes getClassById(int cid){
		Classes classes = classDao.getClass(cid);
		return classes;
	}
	
	public Classes getClassById2(int cid){
		Classes classes = classDao.getClass2(cid);
		return classes;
	}
	
	public Classes getClassById3(int cid){
		Classes classes = classDao.getClass3(cid);
		return classes;
	}
	
	public Classes getClassById4(int cid){
		Classes classes = classDao.getClass4(cid);
		return classes;
	}
}
