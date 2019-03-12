package com.clothes.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class CloudInfDemo {
        
    //接口测试地址（未上线前测试环境使用）
	private static String url;
	//应用 app_key
	private static String APP_KEY;
	//应用 app_secret
	private static String APP_SECRET;
	//接口响应格式 json或xml
	private static String FORMAT;
	//调用方法
	private static String METHOD;
	
	private static void initParams(){
		//读取配置文件
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream("message.properties");
		Properties prop = new Properties();
		try {
			prop.load(stream);
			url = prop.getProperty("message.url");
			APP_KEY = prop.getProperty("message.appkey");
			APP_SECRET = prop.getProperty("message.appscret");
			FORMAT = prop.getProperty("message.formate");
			METHOD = prop.getProperty("message.method");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送模板短信
	 * @param orderNo 订单编号 可以为空
	 * @param phone 电话号码
	 * @param template 短信模板id
	 * @param msgparams 短信参数
	 * @throws Exception
	 */
	public static void sendSmsTpl(String orderNo,String phone,String template,String msgparams[]) throws Exception {
		initParams();
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_key", APP_KEY);
		params.put("view", FORMAT);
		params.put("method", METHOD);
		params.put("out_trade_no", orderNo);
		params.put("to", phone);
		params.put("template", template);
		String ps = "";
		for(int i = 0; i < msgparams.length;i++){
			if(i==msgparams.length-1){
				ps += msgparams[i];
			}else{
				ps = ps + (msgparams[i]+",");
			}
		}
		params.put("params", ps);
		params.put("sign", genSign(params));
		String result = HttpClientUtils.httpPost(url, params);
		System.out.println(result);
	}

	/**
	 * 生成签名
	 * 
	 * 根据参数名称的ASCII码表的顺序排序。如：foo=1, bar=2, foo_bar=3,   foobar=4排序后的顺序是bar=2, foo=1, foo_bar=3, foobar=4
	 * 将排序好的参数名和参数值(以k1=v1&k2=v2...)方式拼装在一起，根据上面的示例得到的结果为：bar=2&foo=1&foo_bar=3&foobar=4
	 * 把拼装好的字符串采用utf-8编码，在拼装的字符串后面加上应用的app_secret后，再进行摘要，如：md5(bar=2&foo=1&foo_bar=3&foobar=4+app_secret)
	 * 将摘要得到的字节流结果使用十六进制表示，如：hex("helloworld".getBytes("utf-8")) = "68656c6c6f776f726c64"
	 */
	private static String genSign(Map<String, String> params)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//TreeMap 默认按key 升序
		Map<String,String> sortMap = new TreeMap<String,String>();
		sortMap.putAll(params);
		//以k1=v1&k2=v2...方式拼接参数
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> s : sortMap.entrySet()) {
			String k = s.getKey();
			String v = s.getValue();
			if(StringUtils.isBlank(v)){//过滤空值
				continue;
			}
			builder.append(k).append("=").append(v).append("&");
		}
		if (!sortMap.isEmpty()) {
			builder.deleteCharAt(builder.length() - 1);
		}
		//拼接应用的app_secret
		builder.append(APP_SECRET);
		//摘要
		MessageDigest instance = MessageDigest.getInstance("MD5");
		byte[] digest = instance.digest(builder.toString().getBytes("UTF-8"));
		//十六进制表示
		return new String(encodeHex(digest));
	}

	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return out;
	}
}
