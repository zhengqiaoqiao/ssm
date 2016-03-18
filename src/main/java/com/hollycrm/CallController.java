package com.hollycrm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hollycrm.ecallcenter.hollycc.channel.call.util.TaiaerWS;

/**
 * <p>Title: ClassController</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月15日
 */
@Controller
@RequestMapping("/call")
public class CallController {
	private final Logger LOGGER = Logger.getLogger(CallController.class);
	@Resource
	private TaiaerWS taiaerWs;
	@ResponseBody
	@RequestMapping(value="/get",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String getClassById(){
		LOGGER.error("get");
		String callNo = "18770070103";
		String customerNo = "";
		String pageSize = "1";
		String begainPage = "1";
		String XPath = "";
		String msg = "";
		try {
			List list = taiaerWs.getNodeTextValue(callNo, customerNo, pageSize, begainPage, XPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	
}
