package com.qiao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>Title: Main</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年6月1日
 */
public class Main {
	
	// 优购API_URL  http://10.0.30.163:8080/mms/api.sc   http://localhost:8089/mms/api.sc  http://open.yougou.com/mms/api.sc
	private static final String API_URL = "http://10.0.30.163:8080/mms/api.sc";
	
	// 优购APP_KEY _58e7e441_154bc6e3c8c__7ff6   _39a23834_154ffb84b04__7ff5
//	_1967f529_1554243a657__7fde    4b4371ed_154ec808e4a__7ff4 	
	private static final String APP_KEY = "_1967f529_1554243a657__7fde";
	
	// 优购APP_SECRET  ace09201c51cd52e632420774390aac5   03ca2d0118db4b58b205b1cb97b8480d
//	846fe38440f92921f79a3ca28559b4d0  ffd4d36d305afab8e344d86b307f92e9
	private static final String APP_SECRET = "846fe38440f92921f79a3ca28559b4d0";

	// 十六进制字符
	private static final String HEX_STRING = "0123456789abcdef";

	public static void main(String[] args) throws Exception {
		
		// 实例化参数字典对象
		Map<String, String> parameterDict = new TreeMap<String, String>();
//		yougou.warehouse.return.get   yougou.order.query   yougou.order.completed.update  yougou.commodity.query
//		yougou.inventory.update   yougou.inventory.query
		// 系统级输入参数
		parameterDict.put("app_key", APP_KEY);
		parameterDict.put("app_version", "1.0");
		parameterDict.put("format", "JSON");
		parameterDict.put("method", "yougou.order.query");
		parameterDict.put("sign_method", "md5");
		parameterDict.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		// 应用级输入参数
		//订单下载
//				parameterDict.put("order_sub_no", "OT560706140024_1");
				parameterDict.put("order_status", "2");
				parameterDict.put("page_index", "1");
				parameterDict.put("page_size", "20");
				parameterDict.put("start_created", "2016-06-23 14:30:34");
				parameterDict.put("end_created", "2016-06-23 15:29:00");
				
		
		//订单发货
//				parameterDict.put("order_sub_nos", "A560708170022_1");
//				parameterDict.put("product_infos", "TBLCJT32DA1AM9230:1");
//				parameterDict.put("express_codes", "2222222");
//				parameterDict.put("ship_times", "2016-07-07 21:18:30");
//				parameterDict.put("logistics_company_codes", "WL026");
//		
		//获取退货地址
//				parameterDict.put("order_sub_no", "C560408170006_1");
		//商品下载
//				parameterDict.put("start_modified", "2016-07-08 14:03:51");
//				parameterDict.put("end_modified", "2016-07-08 14:03:55");
//		
//				parameterDict.put("commodity_no", "100195846");
//				parameterDict.put("status", "2");
//				parameterDict.put("page_index", "1");
//				parameterDict.put("page_size", "20");
				
				//库存同步
//				parameterDict.put("third_party_code", "TBLCJT31DA1AM9220");
//				parameterDict.put("update_type", "0");
//				parameterDict.put("quantity", "10");
				//查询库存
//				parameterDict.put("third_party_code", "TBLCJT31DA1AM9220");
//				parameterDict.put("page_index", "1");
//				parameterDict.put("page_size", "20");
		
		//查询订单拦截申请信息
//		parameterDict.put("order_sub_no", "A560622150004_1");

		
		// 输入参数构建者
		StringBuilder parameterBuilder = new StringBuilder();
		
		// 输入参数签名构建者
		StringBuilder signatureBuilder = new StringBuilder(APP_SECRET);
		
		for (Map.Entry<String, String> entry : parameterDict.entrySet())
		{
			signatureBuilder.append(MessageFormat.format("{0}{1}", entry.getKey(), entry.getValue()));
			parameterBuilder.append(MessageFormat.format("{0}={1}&", entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8")));
		}
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(signatureBuilder.toString().getBytes("UTF-8"));
		byte[] bytes = md.digest();
		
		parameterBuilder.append("sign=");
		
		for (int i = 0; i < bytes.length; i++)
        {
			char high = HEX_STRING.charAt((bytes[i] & 0xf0) >> 4);// 高4位
			char low = HEX_STRING.charAt((bytes[i] & 0x0f));// 低4位
            parameterBuilder.append(high).append(low);
        }
		
		byte[] parameterBytes = parameterBuilder.toString().getBytes("UTF-8");
		
		// 创建WEB请求对象
		URL url = new URL(API_URL);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		// 设置读取输出流
		urlConnection.setDoInput(true);
		// 设置读取输入流
		urlConnection.setDoOutput(true);
		// 设置请求方法
		urlConnection.setRequestMethod("POST");
		// 设置请求内容编码
		urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		// 设置请求内容长度
		urlConnection.setRequestProperty("Content-Length", Integer.toString(parameterBytes.length));
		// 获取WEB请求输入流
		OutputStream os = urlConnection.getOutputStream();
		// 将请求内容写入WEB请求输入流
		os.write(parameterBytes, 0, parameterBytes.length);
		os.flush();
		os.close();
		// 获取WEB响应输出流(即调用优购API返回内容)
		InputStream is = urlConnection.getInputStream();
		char[] buffer = new char[2048];
		StringWriter sw = new StringWriter();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		for (int len = -1; (len = isr.read(buffer)) != -1;) {
			sw.write(buffer, 0, len);
		}
		// 关闭WEB响应输出流
		isr.close();
		is.close();
		
		// 打印优购返回内容
		System.out.println("################################################################");
		System.out.println(sw.toString());
		System.out.println("################################################################");
	}
	
	


}
