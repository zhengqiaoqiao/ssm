package com.qiao.controller;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiao.system.annotation.UserLogger;
import com.qiao.util.WxEmojiUtil;
import com.yougou.wfx.weixin.IWeiXinServiceApi;
import com.yougou.wfx.weixin.base.MessageText;
import com.yougou.wfx.weixin.message.req.TemplateMessageRequest;
import com.yougou.wfx.weixin.message.res.TemplateMessageResponse;
import com.yougou.wfx.weixin.template.MemberRegisterTemplate;
import com.yougou.wfx.weixin.user.res.UserInfoResponse;
import com.yougou.wfx.weixin.user.res.UserSearchResponse;


/**
 * <p>Title: WeiXinController</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年7月21日
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	private static final Logger logger = LoggerFactory.getLogger(WeiXinController.class);
	@Resource
	private IWeiXinServiceApi weiXinServiceApi;
	
	private String appId = "wx0e8a407aa994214a";
	private String secret = "88616fd6fc505abcc27ac18c3d3b85d3";
	
	@RequestMapping(value = "test1", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="发送微信模板消息", isPrintResult = true)
    public TemplateMessageResponse test1(String openId, String first, String memberId, String time, String remark ) throws Exception {
		String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
		System.out.println(accessToken);
    	TemplateMessageRequest<MemberRegisterTemplate> request = new TemplateMessageRequest<MemberRegisterTemplate>();
    	MemberRegisterTemplate template = new MemberRegisterTemplate();
    	template.setFirst(new MessageText(first, "#173177"));
    	template.setRemark(new MessageText(remark, "#173177"));
    	template.setMemberNo(new MessageText(memberId, "#173177"));
    	template.setTime(new MessageText(time, "#173177"));
    	request.setTemplateId("PW5v_iKIB4TWFwd922pvDE7PopFaVLZ37xmZBVWiP3E");
    	request.setTopColor("#173177");
    	request.setUrl("");
    	request.setToUser(openId);
    	request.setTemplate(template);
    	
    	return weiXinServiceApi.sendTemplateMessage(accessToken, request);
    }
	
	@RequestMapping(value = "test2", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="获取微信接口调用凭证", isPrintResult = false)
    public String test2() {
		String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
		System.out.println(accessToken);
		return accessToken;
    }
	
	@RequestMapping(value = "test3", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="获取微信用户信息", isPrintResult = false)
    public String test3(String openId) {
		String[] openIds1 = {"oIvbDv4GApM4gjTmclIcy5riIOcg",
				"oIvbDv5filjnWWo_D3G5lFuer_uA",
				"oIvbDv0H-u3BlmDh7ybyI_ZKR_j8",
				"oIvbDv2_Ur-n7EPhYwoo9oDDiYpQ",
				"oIvbDv9_-7uko_SH1g2DU4GMq2kk",
				"oIvbDv74QCD5iYkhXnBezuB1ctTU",
				"oIvbDv8JY8nWZ9Hr1tGoouNG_wV0",
				"oIvbDv80Fs2jaC7ljNt88dDTL9VI",
				"oIvbDv1xmx-FWLpY3ScEcVOMrsIE",
				"oIvbDv2-8FQTnsLg0Ea372mALa8I",
				"oIvbDvxyU7X9yJopnwF-x3q_dbXg",
				"oIvbDv489iPlKtPD3ydHgl65BU7M",
				"oIvbDv-H0hZnhhV2n5bZHHzseqUg",
				"oIvbDv-0JFnIxHZ4rqzhdGNnh4N8",
				"oIvbDvyGfBq3WujtatovXXNNxDY4",
				"oIvbDvwcbS0uTKMV10rnJBjZgxBA",
				"oIvbDv82L98zE4YnxWzuTSIvsaSk",
				"oIvbDv15toyLvV1nhQUSuQQFscsU",
				"oIvbDv4SVubJ81uNjAFs9BZXyihk",
				"oIvbDv_usTRX8xlCvX05P2tjcrZ0",
				"oIvbDv-ADg4XEto6s_oEocBfcmgE",
				"oIvbDv_nyq6lqqnHB7pwGnjenA5k",
				"oIvbDvzal51bjuyPVvix5wO_s7ew",
				"oIvbDv9QbAn7e1XA79LuUlJrEO28",
				"oIvbDv3PZJYgkqRGo9ca9k7PLDlc",
				"oIvbDv9Buksl2Ft0YGT_tv_STEFQ",
				"oIvbDv0tEiENv9owgvmZJCmzu2bw",
				"oIvbDvxgiz_XCbD5-Qr0iSixDD68",
				"oIvbDvwdczjW8bFDITOSagJHZ7Hs",
				"oIvbDv92CFTJJ4UJOS3PSM8PUKl8",
				"oIvbDv7I16cXtznEnFNAgM9GpkrY",
				"oIvbDv9Q5bt7ZFrLFdZwXUWCFeHk",
				"oIvbDv3oPuL-4f_5BBwyrkDxLF78",
				"oIvbDv8PUYifrQAWt1G_RW1XFmbE",
				"oIvbDvxmt5VCj_CGvsHJvxQ8ht-Q",
				"oIvbDv_dZVMOlXctd4pOAdVMaA_0",
				"oIvbDv0xKAVJGjXmqoECGyS7jD2Q",
				"oIvbDv2uKBI0oZp2ASq4bb9By1aw",
				"oIvbDv57NI64mNgysXJkgo2A9HFA",
				"oIvbDvzH-tI2zZYbJ_kaOhtAYjpM",
				"oIvbDv6o97cHbvSD35Kdgaa0zDlA",
				"oIvbDv2mt8cIuSyzNrzTPuNERPCw",
				"oIvbDv0f_D4qW9bE5P2Pk20Y36bk",
				"oIvbDv-UcJyrIMWZVlnQxsbDoX-g",
				"oIvbDv98Uej0HIN6McKi3HD8AvXE",
				"oIvbDvx-yTzeXav4pPGrl3kCXS8s",
				"oIvbDv8nggWjJS1BsR5JwYuAIZ68",
				"oIvbDvy0KetDfB87PeC-rbop3kQI",
				"oIvbDvzttKVO_QM7hxebEY38iavQ",
				"oIvbDv2UyGeJI1rlMcNqcmU00IlU",
				"oIvbDv3EBwLbTwupy678IJ4Ot_0c",
				"oIvbDv8SXv09ozHKt2iSrHDN4iEc",
				"oIvbDvwubw49eWp8Hg4byWuZm0Pk",
				"oIvbDv79ylITmgV3wdjJrwAlouQQ",
				"oIvbDvy9UmRS7WQNeqeb58ZrI36s",
				"oIvbDv_2MTfpVCV0ZJ6siN_wW2NQ",
				"oIvbDvxENUX-jyNFQrjLa9YLPqn8",
				"oIvbDv10tvdRs_F5T_n8cmdhRKPU",
				"oIvbDv1BVhuZgHvS8fI_ggVsFYkI",
				"oIvbDv1h_WjJSYhjUDOfd0bJ8VMY",
				"oIvbDvwYCLGB5MMQtWdZMzGn-2Xg",
				"oIvbDvzwe6eKJ4YUEal7mSeLrqek",
				"oIvbDv51KQRVtCX-Hn-U-qqB-eZQ",
				"oIvbDv9Ywc4awsu13KXf2v9YSk9E",
				"oIvbDv-eME_S5CeaHZaJv_xlGAT8",
				"oIvbDv4_v6iuA-rAP8kNkBbkNM9M",
				"oIvbDv-mRnFc6L18IL9rnJhqZoi8",
				"oIvbDvy6NRRCmGxRXZu1Ctb_oYsw",
				"oIvbDv0aI6ej0thLVf0Qq5zfmWAI",
				"oIvbDv7lCIeMBrqlqItEd2uQi_Rk",
				"oIvbDv49IotzLZx-cFkJOePzrlLA",
				"oIvbDv577TTcMTW7U5F7uJ-InVxo",
				"oIvbDv1JRMYl_-wdyis91bObC80w",
				"oIvbDv62EUrfd7hTYD-l_IQ2sKjg",
				"oIvbDv1PPNeUGdxO81XlIpsiOaao",
				"oIvbDv7IYEnaE35iByIwHQwReEbU",
				"oIvbDv18ddJ-nAf_LuU4KkVWW4vU",
				"oIvbDv0d3E7j1D1ICbSnzOeAVoME",
				"oIvbDv4wpE_626ilQapfnw8sugxA",
				"oIvbDv-zgAFcXVRSzwYi0o2MU65Y",
				"oIvbDv4EdQVxLCaXaXG-p82nnTuM",
				"oIvbDv5FsGJeEzH60UQ2VyS6jkno",
				"oIvbDv1HaBNJCjVv5KPaSlnJSz5c"};
		String[] openIds2 = {"oIvbDv35xOykt7kKt60vSibuuOh0",
				"oIvbDvxre0HgPfV3gmxTZKSs12TU",
				"oIvbDv9oQEUZqQ3ZMkq95Nls6-Bg",
				"oIvbDvxlc4VZfJISe2d30Fj3fJbA",
				"oIvbDv0VgALUWKROUzwqP7E1TBBs",
				"oIvbDv1HaBNJCjVv5KPaSlnJSz5c",
				"oIvbDv3mpaBvHLZJjN4qrE3SK-lM",
				"oIvbDvxBeOoOwNmRKHmCP_6DC9Ao",
				"oIvbDv9joP5lJS4043pkzdPlGxO8",
				"oIvbDvwxVD7IqRHNGkw70tyvza0Q",
				"oIvbDvzvl28Y84qvI0hXaDaaPrkM",
				"oIvbDv8d3m2mOY0RtbvQZYGx8SkY",
				"oIvbDv30kPl1f1-1Tq8DLHbqrutQ",
				"oIvbDv0ER6EbTksIXdmf0J8f9Z3s",
				"oIvbDv_XhF2zwiH_qzLgdmaxmi-g",
				"oIvbDvwpc9eb4mc58a2VyXnqUZpY",
				"oIvbDv_jZDcI4vc9Exl19h9rykSY",
				"oIvbDvxaXblmHArVmgqExzFyZY2k",
				"oIvbDv08GAtUB4CJRiNJ-zQO8PcE",
				"oIvbDv02k24eKN87rqCauADRNmxE",
				"oIvbDv0wuzDVHQmqtZQ5wJCtMs1A",
				"oIvbDvzwbyeVtu4prkE5WNIVNQzI",
				"oIvbDv_zn00PKMyZjCcyCkn0f9ww",
				"oIvbDvzYfLI7VAJauFC_ZVVeSjCw",
				"oIvbDvwCkpyo-GKT5wmNlAcwdA-o",
				"oIvbDv2NqNAOBaDcMOSghEWTKTtI",
				"oIvbDv3fngahniKBkgmuJqFjtBIY",
				"oIvbDv6MyTDRxX1PnnuZ8vK-Vbds",
				"oIvbDvySQEEryv7qM2PN2Iimy6f4",
				"oIvbDv9OKlg_Wfi38--dmhY9EQ6M",
				"oIvbDv7im0_z0az27pJYXgfzqCnk",
				"oIvbDv3Q-Ayz00A-pk15xEl_Vzr0",
				"oIvbDv4B8XVd6Q120mH1lJIudUFo",
				"oIvbDv2dhLKH7gIOPOe2ErmPKA9Q",
				"oIvbDv18eK_m64DzKO-P_48WcoV0",
				"oIvbDv5dw_u_70yhXZTedjVr9TWg",
				"oIvbDv2-QgHPaNI_Zc2ExJWP2Doo",
				"oIvbDv2U6fw6aoXurg1WUQUwF644",
				"oIvbDv-oBW_tTt7A16AqFfssexQg",
				"oIvbDv1vLOo7aafjfg7LXNjfzkfc",
				"oIvbDv_Pa7srNNj5i8R-a9fAiHyA",
				"oIvbDv15I35cjfGBssEtyO17D1CM",
				"oIvbDv2AJJpxRCVifi-ixyX4nN74",
				"oIvbDv3-Jl0HCv8N1XXJ3aG8AQo0",
				"oIvbDv-c_-T5PUMjpUvBzUyTpyUY",
				"oIvbDv7n6-V3u_0LPAfvDpspJP54",
				"oIvbDv7r0zOBXY4CkSpOEU-hFKbY",
				"oIvbDv2ZdATS9doYSEAgs60CmRrQ",
				"oIvbDv8QpFOD7zXteyqxIZEEaRCg",
				"oIvbDv0cGN9vDaq0O7edrlVLKavI",
				"oIvbDv5ikZPeSAAMLZyGWHYhYqWw",
				"oIvbDv0NeQocPlZT62vCxhawuwuQ",
				"oIvbDvwLp89jZhern8ZvmXgdnS1c",
				"oIvbDv45odZTx5abpa8MBWpBLHsQ",
				"oIvbDv6J2Cob_ARrEcoAgY2Rn4Bg",
				"oIvbDvwI9_zBY62qKzRgDkLS5Vq0",
				"oIvbDv0Zs7hI2ZsA2OPA9DI5ilh8",
				"oIvbDvymhNqvQP54ivB9mOGhZDnQ",
				"oIvbDvxpIzOoOUpRoxOt2auZtTZs",
				"oIvbDv9nTilpEznZyLHJgkECLUWg",
				"oIvbDvyW3fptc4fk4S_9WwPm_p_U",
				"oIvbDv94BfRUSxdCNgdiE8i2f4a4",
				"oIvbDv604ECO9c3pPVmVbD2Yb2j8",
				"oIvbDv1KYo6oY06bI7ErVyhUp4QY",
				"oIvbDv30SXZnUOfulDL4Yul6mU9Y",
				"oIvbDvwbsk7BWd_ZfJWi5MmXum90",
				"oIvbDv5iW6HzavsO-kLHk4jRRxnc",
				"oIvbDvzZRY0VGl3GPodAlafxsjjk",
				"oIvbDv3haGz5aSkkFexol2L8lVhA",
				"oIvbDv_CFcvrFLR82vvQevYM0bQE",
				"oIvbDv5yrWpC3m2Ivb6DVuOZRP_s",
				"oIvbDvw0bQl7Cnv62y1i19zyBCAQ",
				"oIvbDvyWxZWEvf0YGrpH-Iz6UQ4g",
				"oIvbDv4ZtcnjWuJkLzmyX4oYGmtw",
				"oIvbDv9FYjjivMwUrK2MNhSL8grc",
				"oIvbDv5KhlINPcGXqYbdigKUvtMY",
				"oIvbDv3Eiu-wtBPvjt6G-VbVS6po",
				"oIvbDv89Q-iKU-VfR_lise4C6y60",
				"oIvbDvzQuJwGtZO5sI4y3bbIPDxk",
				"oIvbDv1H4fXUw-p21gSSNgaxFlk8",
				"oIvbDv8wTnNiyfAdEYMQiWEL4DKM",
				"oIvbDv8YHsIXzZ97Y77_P7yTH3h0",
				"oIvbDvzcoT3sT5HDkqtBGMGDCLmE",
				"oIvbDv9ic05udu3NGPT-S6I6j_7E",
				"oIvbDv054FlvEdpsgQqRkx4v01tA",
				"oIvbDv9v7QvF_7Ge48UoxHMI7kpE",
				"oIvbDv4jma6Q_yy5Q5IWotzSYwxM"};
		
		
		
		
		String sql1 = "update tbl_wfx_member_account set platform_username={0} where h5_open_id={1};";
		String sql2 = "update tbl_wfx_member_profile p LEFT JOIN tbl_wfx_member_account a on p.loginaccount_id = a.id set p.head_show_img = {0} where a.h5_open_id = {1};";
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(String item : openIds1){
			String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
			UserInfoResponse info = weiXinServiceApi.getUserInfo(accessToken, item);
			String icon = info.getHeadimgurl();
			String name = WxEmojiUtil.emojiEncode(info.getNickname());
			if(icon == null){
				sb1.append(item).append(";   ");
			}else{
				String s = MessageFormat.format(sql1, "'" + name + "'", "'" + item + "'");
				sb2.append(s);
			}
		}
		logger.info("可以更新的用户：" + sb2.toString());
		logger.info("不可以更新的用户：" + sb1.toString());
		return sb2.toString().split(";").length + "";
		
    }
	
	@RequestMapping(value = "test4", method = RequestMethod.GET)
    @ResponseBody
    @UserLogger(methodNote="获取微信公众号用户关注列表", isPrintResult = false)
    public UserSearchResponse test4() {
		String accessToken = weiXinServiceApi.getAccessToken(appId, secret).getAccessToken();
		UserSearchResponse r = weiXinServiceApi.searchUser(accessToken, null);
		return r;
    }

}
