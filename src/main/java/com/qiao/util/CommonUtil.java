package com.qiao.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;


public abstract class CommonUtil {
	/**
	 * 根据Request获取客户端IP
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 电话号码/身份证/银行卡号的加密处理
	 * @param s_num  前面显示的字符个数
	 * @param e_num  后面显示的字符格式
	 * @param str    被处理的字符串
	 * @return
	 */
	public static String convertEncryptionStr(int s_num, int e_num, String str) {
		
		if(StringUtils.isEmpty(str)){
			return "";
		}
		if(str.length() < s_num + e_num){
			return "";
		}
		String str1 = str.substring(0, s_num);
		String str2 = str.substring(str.length()-(e_num));
		int count = str.length()-(s_num + e_num);
		String rs = str1;
		for(int i=0; i<count; i++){
			rs += '*';
		}
		rs += str2;
		return rs;
	}
	
	public static String base64Encoder(InputStream ins) throws IOException{
		String str = "";
		str = Base64.encodeBase64String(recvByteFromStream(ins));
		return str;
	}
	
	public static InputStream base64Decoder(String base64String){
		InputStream is = null;
		is = new ByteArrayInputStream(Base64.decodeBase64(base64String));  
		return is;
	}
	
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public static InputStream resize(InputStream stream, int w, int h) throws IOException {
		BufferedImage img = ImageIO.read(stream);      // 构造Image对象
		int width = img.getWidth(null);    // 得到源图宽
		int height = img.getHeight(null);  // 得到源图长
		if (width / height > w / h) {
			h = (int) (height * w / width);
		} else {
			w = (int) (width * h / height);
		}
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB ); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		ImageIO.write(image, "jpg", os);  
		InputStream is = new ByteArrayInputStream(os.toByteArray());  
		return is;
	}
	
	/**
	 *  InputStream转Byte
	 * @param ins
	 * @return
	 */
	public static byte[] recvByteFromStream(InputStream ins) {
		/* 接收从服务器端返回的信息 */
		byte[] rlt = null;
		DataInputStream clientIn = null;
		ByteBuffer paramOutBuf = ByteBuffer.allocate(8192);
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		try {
			int tempLen = 0;
			clientIn = new DataInputStream(ins);
			while (true) {
				paramOutBuf.clear();
				tempLen = clientIn.read(paramOutBuf.array());
				if (tempLen == -1) {
					break;
				}
				paramOutBuf.flip();
				byteOutStream.write(paramOutBuf.array(), 0, tempLen);

			}
			rlt = byteOutStream.toByteArray();
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (clientIn != null) {
					clientIn.close();
				}
				if (byteOutStream != null) {
					byteOutStream.close();
				}
				paramOutBuf = null;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		return rlt;
	}

	/**
	 * InputStream转String
	 * 
	 * @param inputStream
	 *            传递过来的数据流
	 * @return 字符串
	 */
	public static String recvStringFromStream(InputStream inputStream) {
		String rlt = "";
		ByteBuffer paramOutBuf = null;
		byte[] temp = null;
		int bytesRead = 0;
		try {
			while (true) {
				paramOutBuf = ByteBuffer.allocate(4096);
				temp = paramOutBuf.array();
				bytesRead = inputStream.read(temp, 0, 4096);
				if (bytesRead == -1) {
					break;
				}
				rlt += new String(temp, 0, bytesRead, "UTF-8");
			}
			paramOutBuf.clear();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return rlt.trim();
	}

}
