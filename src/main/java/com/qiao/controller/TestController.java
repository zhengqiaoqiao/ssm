package com.qiao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiao.service.TestService;
import com.yougou.pc.model.product.Product;

/**
 * <p>Title: TestController</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月30日
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@Resource
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value="/getProductByNo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductByNo(String no){
		return testService.getProductByNo(no);
	}
	
	@ResponseBody
	@RequestMapping(value="/getProductByThirdPartyCode",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProductByThirdPartyCode(String no){
		return testService.getProductByThirdPartyCode(no);
	}
}
