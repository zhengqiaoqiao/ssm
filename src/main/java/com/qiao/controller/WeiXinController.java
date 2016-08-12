package com.qiao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiao.system.annotation.UserLogger;
import com.yougou.wfx.weixin.IWeiXinServiceApi;
import com.yougou.wfx.weixin.base.MessageText;
import com.yougou.wfx.weixin.message.req.TemplateMessageRequest;
import com.yougou.wfx.weixin.message.res.TemplateMessageResponse;
import com.yougou.wfx.weixin.template.MemberRegisterTemplate;
import com.yougou.wfx.weixin.user.res.UserInfoResponse;


/**
 * <p>Title: WeiXinController</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年7月21日
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	@Resource
	private IWeiXinServiceApi weiXinServiceApi;
	
	private String appId = "wxb80b89b0f0ae649c";
	private String secret = "4c1277061c7ae3460adcc30973a813f9";
	
	@RequestMapping(value = "test1", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="发送微信模板消息")
    public TemplateMessageResponse test1(String openId, String first, String memberId, String time, String remark ) throws Exception {
		String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
		System.out.println(accessToken);
    	TemplateMessageRequest<MemberRegisterTemplate> request = new TemplateMessageRequest<MemberRegisterTemplate>();
    	MemberRegisterTemplate template = new MemberRegisterTemplate();
    	template.setFirst(new MessageText(first, "#173177"));
    	template.setRemark(new MessageText(remark, "#173177"));
//    	template.setMemberNo(new MessageText(memberId, "#173177"));
//    	template.setTime(new MessageText(time, "#173177"));
    	request.setTemplateId("PW5v_iKIB4TWFwd922pvDE7PopFaVLZ37xmZBVWiP3E");
    	request.setTopColor("#173177");
    	request.setUrl("");
    	request.setToUser(openId);
    	request.setTemplate(template);
    	
    	return weiXinServiceApi.sendTemplateMessage(accessToken, request);
    }
	
	@RequestMapping(value = "test2", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="获取微信接口调用凭证")
    public String test2() {
		String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
		System.out.println(accessToken);
		return accessToken;
    }
	
	@RequestMapping(value = "test3", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="获取微信用户信息")
    public UserInfoResponse test3(String openId) {
		String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
		return weiXinServiceApi.getUserInfo(accessToken, openId);
		
    }

}
