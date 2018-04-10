package com.kxjl.brain.police.common.utils;
/**
 * 字符串工具类
 * @Author DengWei
 * @Date 2017/5/25 15:26
 */
public class StringUtil {
	
	/**
	 * 字符数组转字符串
	 * 
	 * @param chars
	 * @return
	 */
	public static String charsToString(char[] chars){
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			str.append(chars[i] + "");
		}
		return str.toString();
	}
	
	/**
	 * 为空默认值
	 * 
	 * @param src
	 * @param defaultVal
	 * @return
	 */
	public static String nullDefaultVal(Object src, String defaultVal){
		return src == null ? defaultVal : src.toString();
	}
	
	/**
	 * 对象转string
	 * 
	 * @param obj 目标对象
	 * @return 转换之后的字符串
	 */
	public static String obj2String(Object obj){
		if(obj != null){
			return obj.toString().trim();
		}else{
			return "";
		}
	}
	
	/**
	 * 判断字符串为null或者空白串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(Object str){
		return str == null || "".equals(str.toString().trim());
	}
}
