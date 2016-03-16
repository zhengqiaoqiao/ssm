package com.qiao.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiao.common.JsonUtil;
import com.qiao.domain.Classes;
import com.qiao.service.ClassService;

/**
 * <p>Title: ClassController</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月15日
 */
@Controller
@RequestMapping("/class")
@Scope("prototype")
public class ClassController {
	private final Logger LOGGER = Logger.getLogger(ClassController.class);
	@Resource
	private ClassService classService;
	
	@ResponseBody
	@RequestMapping("/get/{cid}")
	public String getClassById(@PathVariable int cid){
		LOGGER.error("get");
		String msg = "";
		try {
			Classes classes = classService.getClassById(cid);
			msg = JsonUtil.obj2json(classes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/get2/{cid}")
	public String getClassById2(@PathVariable int cid){
		LOGGER.error("get2");
		String msg = "";
		try {
			Classes classes = classService.getClassById2(cid);
			msg = JsonUtil.obj2json(classes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/get3/{cid}")
	public String getClassById3(@PathVariable int cid){
		LOGGER.error("get3");
		String msg = "";
		try {
			Classes classes = classService.getClassById3(cid);
			msg = JsonUtil.obj2json(classes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/get4/{cid}")
	public String getClassById4(@PathVariable int cid){
		LOGGER.error("get4");
		String msg = "";
		try {
			Classes classes = classService.getClassById4(cid);
			msg = JsonUtil.obj2json(classes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
}
