package com.qiao.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对微信的emoji表情进行编码和解码
 * 由于emoji的编码为utf8base64格式的，mysql数据库设置的是utf8，所以不能保存
 * 所以在保存前进行编码，获取时进行转解码
 * @author wang.zf
 *
 */
public class WxEmojiUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WxEmojiUtil.class);
	
	public static String emojiEncode(String str){
		if(null == str){
			return null;
		}
		String nName = "";
		String nStr = str;
		try{
			String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(str);
			StringBuffer sb = new StringBuffer();
			while(matcher.find()) {
				matcher.appendReplacement(sb, "[[EMOJI:" + URLEncoder.encode(matcher.group(1),"UTF-8") + "]]");
			}
			matcher.appendTail(sb);
			nName = sb.toString();
		}catch(Exception e){
			nName = filterOffUtf8Mb4(nStr);
			logger.error("微信用户名["+str+"]转换成普通用户名出错");
		}
        return nName;
    }
	
	public static String emojiDecode(String str){
		if(null == str){
			return null;
		}
		String nStr = str;
		try {
			String patternString = "\\[\\[EMOJI:(.*?)\\]\\]";
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(str);
			StringBuffer sb = new StringBuffer();
			while(matcher.find()) {
				matcher.appendReplacement(sb, URLDecoder.decode(matcher.group(1),"UTF-8"));
			}
			matcher.appendTail(sb);
			nStr = sb.toString();
		} catch (Exception e) {
			logger.error("微信普通用户名["+str+"]转换成带表情用户名出错");
		}
        return nStr;
    }
	
	/**
	 * 过滤掉超过3个字节的UTF8字符
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String filterOffUtf8Mb4(String text) {
		byte[] bytes;
		try {
			bytes = text.getBytes("utf-8");
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[i++]);
					continue;
				}

				b += 256; // 去掉符号位

				if (((b >> 5) ^ 0x6) == 0) {
					buffer.put(bytes, i, 2);
					i += 2;
				} else if (((b >> 4) ^ 0xE) == 0) {
					buffer.put(bytes, i, 3);
					i += 3;
				} else if (((b >> 3) ^ 0x1E) == 0) {
					i += 4;
				} else if (((b >> 2) ^ 0x3E) == 0) {
					i += 5;
				} else if (((b >> 1) ^ 0x7E) == 0) {
					i += 6;
				} else {
					buffer.put(bytes[i++]);
				}
			}
			buffer.flip();
			return new String(buffer.array(), "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}
	
	public static void main(String[] args) {
		String name = "[[EMOJI:%F0%9F%8C%BC]][[EMOJI:%F0%9F%8C%BA]][[EMOJI:%F0%9F%90%9D]]蜜蜂";
		String nName = WxEmojiUtil.emojiEncode(name);
		System.out.println(nName);
	}
}
