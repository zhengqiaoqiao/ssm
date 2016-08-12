package com.qiao.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiao.domain.Classes;
import com.qiao.service.ClassService;
import com.qiao.vo.UserFormVo;

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
	private  HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;  
	@Resource
    protected HttpSession session;  
	@Resource
	private ClassService classService;
	
	@ResponseBody
	@RequestMapping(value="/get/{cid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Classes getClassById(@PathVariable int cid){
		LOGGER.error("get");
		Classes classes = null;
		try {
			classes = classService.getClassById(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	@ResponseBody
	@RequestMapping(value="/get2/{cid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Classes getClassById2(@PathVariable int cid){
		LOGGER.error("get2");
		Classes classes = null;
		try {
			classes = classService.getClassById2(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	@ResponseBody
	@RequestMapping(value="/get3/{cid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Classes getClassById3(@PathVariable int cid){
		LOGGER.error("get3");
		Classes classes = null;
		try {
			classes = classService.getClassById3(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	@ResponseBody
	@RequestMapping(value="/get4/{cid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Classes getClassById4(@PathVariable int cid){
		LOGGER.error("get4");
		Classes classes = null;
		try {
			classes = classService.getClassById4(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	@ResponseBody
	@RequestMapping(value="/a",  
		method = RequestMethod.POST,
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public String[] getClassById(@RequestBody String[] a){
		LOGGER.error("a");
		return a;
	}
	
	@ResponseBody
	@RequestMapping(value="/b",  
		method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public void b (UserFormVo userFormVo){
		String name = request.getParameter("name");
		LOGGER.error(name);

	}
}
