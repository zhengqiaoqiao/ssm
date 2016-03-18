package com.hollycrm.service.customer.service.impl;

import com.hollycrm.ecallcenter.hollycc.channel.base.util.TaskExtendParamUtil;
import com.hollycrm.framework.dao.support.VOResult;
import com.hollycrm.service.customer.model.TaiaerBean;
import com.hollycrm.service.customer.service.TaiaerWSService;
import com.yougou.ordercenter.api.order.ICallCenterMemberService;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.stereotype.Service;


/**
 * <p>Title: A</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月17日
 */
@Service
public class TaiaerWSServiceImpl implements TaiaerWSService {
	public static Log logger = LogFactory.getLog(TaiaerWSServiceImpl.class);
	@Resource
	private ICallCenterMemberService callCenterMemberService;
	
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
	
	private String[] getArray(String n1, String n2) {
        String result[] = new String[2];
        result[0] = n1;
        result[1] = n2;
        return result;
    }
	
	private String getInputXml(String phone, String customerNo, String pageSize, String begainPage) {
        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<XMLInfo>");
        sb.append("<Qinfo callNo=\"");
        sb.append((new StringBuilder(String.valueOf(phone))).append("\" customerNo=\"").toString());
        sb.append((new StringBuilder(String.valueOf(customerNo))).append("\" pageSize=\"").toString());
        sb.append((new StringBuilder(String.valueOf(pageSize))).append("\" begainPage=\"").toString());
        sb.append((new StringBuilder(String.valueOf(begainPage))).append("\"></Qinfo></XMInfo>").toString());
        return sb.toString();
    }

	public List<String[]> getNodeTextValue(String phone, String customerNo, String XPath) {
		// TODO Auto-generated method stub
		logger.debug("TaiaerWSServiceImpl:getNodeTextValue");
		List<String[]> ll = new ArrayList<String[]>();
	    StringBuffer nodeTextValue = new StringBuffer();
	    if (XPath!=null && XPath.indexOf("@") >= 0) {
	      return null;
	    }
	    if ("000000000000".equals(customerNo)) {
	    	customerNo = "";
	    }
	    String return_xml = callCenterMemberService.queryCustomers(phone, customerNo, "1", "1");
	    if(return_xml == null || "".equals(return_xml.trim()) || "null".equals(return_xml.trim()))
        {
            ll.add(getArray("<font style='FONT-SIZE: 12px; COLOR: #FF0000'>客户名称:</font>", "<font style='FONT-SIZE: 12px; COLOR: #FF0000'>虚拟客户</font>"));
            ll.add(getArray("<font style='FONT-SIZE: 12px; COLOR: #FF0000'>提示消息:</font>", "<font style='FONT-SIZE: 12px; COLOR: #FF0000'>请使用定位功能定位客户</font>"));
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


	public VOResult getNodeTextValueSearch(String phone, String customerNo, String starpage, String pagesize, String XPath) {
		// TODO Auto-generated method stub
		logger.debug("TaiaerWSServiceImpl:getNodeTextValueSearch");
		if (XPath!=null && XPath.indexOf("@") >= 0) {
	      return null;
	    }
	    if ("000000000000".equals(customerNo)) {
	    	customerNo = "";
	    }
	    String return_xml = callCenterMemberService.queryCustomers(phone, customerNo, starpage, pagesize);
	    if ((return_xml == null) || ("".equals(return_xml.trim())) || ("null".equals(return_xml.trim()))) {
	    	return_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLInfo><GlobalInfo><dostatus>0</dostatus><ErrorMessage>调用接口异常</ErrorMessage><total>0</total></GlobalInfo><customers><customer customerNo=\"000000000000\" customerName=\"虚拟客户\"/></customers></XMLInfo>";
	    }
	    
	    Document document = null;
	    VOResult re = null;
	    List retList = new ArrayList();
	    int flag = 0;
	    int total = 0;

	    try {
	    	document = DocumentHelper.parseText(return_xml);
	        
	        flag = Integer.parseInt(document.selectSingleNode("/XMLInfo/GlobalInfo/dostatus").getStringValue());
	        total = Integer.parseInt(document.selectSingleNode("/XMLInfo/GlobalInfo/total").getStringValue());
	       
	        if (flag == 0){
	        	logger.error("接口调用异常！异常原因:" + document.selectSingleNode("/XMLInfo/GlobalInfo/ErrorMessage").getStringValue());
	        }
	        
	        List list = document.selectNodes("/XMLInfo/customers/customer");
            Iterator it1 = list.iterator();
            TaiaerBean tb = null;
            while(it1.hasNext()){
            	StringBuffer nodeTextValue = new StringBuffer();
            	Element node = (Element)it1.next();
            	Iterator it2 = node.attributeIterator();
    	        while (it2.hasNext()){
    	        	DefaultAttribute attr = (DefaultAttribute)it2.next();
    	            nodeTextValue.append(attr.getName()).append("=").append(node.attributeValue(attr.getName()) != null ? node.attributeValue(attr.getName()) : "").append("&");
    	        }
    	        if (nodeTextValue.length() > 0){
    	        	nodeTextValue.deleteCharAt(nodeTextValue.length() - 1);
    	        }
    	        tb = new TaiaerBean();
    	        tb.setCustomerName(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerName"));
                tb.setCustomerNo(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerNo"));
                tb.setSex(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "sex"));
                tb.setCustomerLevel(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerLevel"));
                tb.setCustomerType(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerType"));
                tb.setMobile(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "mobile"));
                tb.setPhone(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "phone"));
                tb.setStatus(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "status"));
                tb.setScore(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "score"));
                tb.setAddress(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "address"));
                retList.add(tb);
            }
            
            re = new VOResult(retList);
            re.setTotalRow(total);
	    } catch(Exception e) {
            logger.error(e);
        }
	    return re;
	}

	public TaiaerBean getTaiaerBean(String phone, String customerNo, String XPath) {
		// TODO Auto-generated method stub
		logger.debug("TaiaerWSServiceImpl:getTaiaerBean");
		StringBuffer nodeTextValue = new StringBuffer();
		TaiaerBean tb = null;
		if (XPath!=null && XPath.indexOf("@") >= 0) {
	      return null;
	    }
	    if ("000000000000".equals(customerNo)) {
	    	customerNo = "";
	    }
	    String return_xml = callCenterMemberService.queryCustomers(phone, customerNo, "1", "1");
	    if(return_xml == null || "".equals(return_xml.trim()) || "null".equals(return_xml.trim()))
            return_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLInfo><GlobalInfo><dostatus>0</dostatus><ErrorMessage>调用接口异常</ErrorMessage><total>0</total></GlobalInfo><customers><customer customerNo=\"000000000000\" customerName=\"虚拟客户\"/></customers></XMLInfo>";
	    Document document = null;
	    int flag = 0;
	    int total = 0;

	    try {
	    	document = DocumentHelper.parseText(return_xml);
	        flag = Integer.parseInt(document.selectSingleNode("/XMLInfo/GlobalInfo/dostatus").getStringValue());
	        total = Integer.parseInt(document.selectSingleNode("/XMLInfo/GlobalInfo/total").getStringValue());
	        if(1 == flag && total == 1) {
	        	Element node = (Element)document.selectSingleNode("/XMLInfo/customers/customer");
		        Iterator it = node.attributeIterator();
		        while (it.hasNext()){
		        	DefaultAttribute attr = (DefaultAttribute)it.next();
		            nodeTextValue.append(attr.getName()).append("=").append(node.attributeValue(attr.getName()) != null ? node.attributeValue(attr.getName()) : "").append("&");
		        }
		        if (nodeTextValue.length() > 0){
		        	nodeTextValue.deleteCharAt(nodeTextValue.length() - 1);
		        }
		        tb = new TaiaerBean();
	        	tb.setCustomerName(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerName"));
                tb.setCustomerNo(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerNo"));
                tb.setSex(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "sex"));
                tb.setCustomerLevel(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerLevel"));
                tb.setCustomerType(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "customerType"));
                tb.setMobile(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "mobile"));
                tb.setPhone(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "phone"));
                tb.setStatus(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "status"));
                tb.setScore(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "score"));
                tb.setAddress(TaskExtendParamUtil.getSingleExtendParamValue(nodeTextValue.toString(), "address"));
            } else {
                tb = new TaiaerBean();
                tb.setCustomerName("虚拟客户");
                tb.setCustomerNo("000000000000");
            }
	       
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	    return tb;
	}
}
