package com.kxjl.brain.police.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * json转换工具类
 * @Author DengWei
 * @Date 2017/5/11 18:40
 */
public class JsonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static{
		//忽略注解
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * 对象转换json
	 * @param object
	 * @return
	 */
	public static String obj2json(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json转对象
	 * @param <T>
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T json2Obj(String json,Class<?> clazz) {
		try {
			return (T)objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("json转换异常：{},{}", json, e);
		}
		return null;
	}
	
	/**
	 * 解析字典表json串
	 * @return
	 * @throws JSONException
	 */
	public static Map<String,Object> analysisJson(JSONArray jsonArray) {
		Map<String,Object> map = new HashMap<String, Object>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			map.put(jsonObj.get("value").toString(), jsonObj.get("name"));
		}
		return map;
	}
	
	/**
	 * java请求url返回json
	 * @param url
	 * @return
	 */
	public static String getResponseDataByID(String url, String postData) {
		String data = null;
		try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Proxy-Connection", "Keep-Alive");
			con.setDoOutput(true);
			con.setDoInput(true);
			OutputStream os = con.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.write(postData.getBytes());
			dos.flush();
			dos.close();
			InputStream is = con.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			byte d[] = new byte[dis.available()];
			dis.read(d);
			data = new String(d,"gbk");
			con.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}
}