package com.hollycrm.ecallcenter.hollycc.channel.call.util;

import com.hollycrm.ecallcenter.hollycc.channel.base.util.TaskExtendParamUtil;
import com.yougou.ordercenter.api.order.ICallCenterMemberService;
import com.yougou.pc.api.ICommodityBaseApiService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.stereotype.Service;


/**
 * <p>Title: TaiaerWS</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月16日
 */
@Service
public class TaiaerWS {
	public static Log logger = LogFactory.getLog(TaiaerWS.class);
	@Resource
	private ICallCenterMemberService callCenterMemberService;
	
	@Resource
	private ICommodityBaseApiService commodityBaseApiService;
	
	public List getNodeTextValue(String callNo, String customerNo, String pageSize, String begainPage, String XPath){ 
		List ll = new ArrayList();
	    StringBuffer nodeTextValue = new StringBuffer();
	    if (XPath!=null && XPath.indexOf("@") >= 0) {
	      return null;
	    }
	    if ("000000000000".equals(customerNo)) {
	    	customerNo = "";
	    }
	    String return_xml = callCenterMemberService.queryCustomers(callNo, customerNo, begainPage, pageSize);
	    if ((return_xml == null) || ("".equals(return_xml.trim())) || ("null".equals(return_xml.trim()))) {
	    	return_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLInfo><GlobalInfo><dostatus>0</dostatus><ErrorMessage>调用接口异常</ErrorMessage><total>0</total></GlobalInfo><customers><customer customerNo=\"000000000000\" customerName=\"虚拟客户\"/></customers></XMLInfo>";
	    }
	    Document document = null;
	    int flag = 0;
	    int total = 0;
	    try {
	    	document = DocumentHelper.parseText(return_xml);
	        flag = Integer.parseInt(document.selectSingleNode("/XMLInfo/GlobalInfo/dostatus").getStringValue());
	        total = Integer.parseInt(document.selectSingleNode("/XMLInfo/GlobalInfo/total").getStringValue());
	        if (flag == 0){
	        	logger.error("接口调用异常！异常原因:" + document.selectSingleNode("/XMLInfo/GlobalInfo/ErrorMessage").getStringValue());
	            ll.add(getArray("接口异常", "<font color='red'>" + document.selectSingleNode("/XMLInfo/GlobalInfo/ErrorMessage").getStringValue() + "</font>"));
	            ll.add(getArray("客户姓名", "<font color='red'>虚拟客户</font>"));
	            return ll;
	        }
	        if ((2 == flag) || (total != 1))
	        {
	          ll.add(getArray("接口调用", "成功"));
	          ll.add(getArray("客户姓名", "<font color='red'>虚拟客户</font>"));
	          return ll;
	        }
	        Element node = (Element)document.selectSingleNode("/XMLInfo/customers/customer");
	        Iterator it = node.attributeIterator();
	        while (it.hasNext()){
	        	DefaultAttribute attr = (DefaultAttribute)it.next();
	            nodeTextValue.append(attr.getName()).append("=").append(node.attributeValue(attr.getName()) != null ? node.attributeValue(attr.getName()) : "").append("&");
	        }
	        if (nodeTextValue.length() > 0){
	        	nodeTextValue.deleteCharAt(nodeTextValue.length() - 1);
	        }
	        ll.add(getArray("客户姓名", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerName")));
	        ll.add(getArray("注册账号", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerNo")));
	        ll.add(getArray("会员状态", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "status")));
	        ll.add(getArray("会员类型", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerType")));
	        ll.add(getArray("会员等级", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerLevel")));
	        ll.add(getArray("会员积分", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "score")));
	        ll.add(getArray("收货人地址", TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "address")));
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	    return ll;
	}
	  
	private String[] getArray(String n1, String n2) {
		String[] result = new String[2];
	    result[0] = n1;
	    result[1] = n2;
	    return result;
	}

	/**
	 * @return the callCenterMemberService
	 */
	public ICallCenterMemberService getCallCenterMemberService() {
		return callCenterMemberService;
	}

	/**
	 * @param callCenterMemberService the callCenterMemberService to set
	 */
	public void setCallCenterMemberService(
			ICallCenterMemberService callCenterMemberService) {
		this.callCenterMemberService = callCenterMemberService;
	}
	
	
}
