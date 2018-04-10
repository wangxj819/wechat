/**
 * Copyright 2015 Iflytek, Inc. All rights reserved.
 */

package com.kxjl.brain.police.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * <p>
 * <code>MD5Utils</code>类定义常用的MD5方式加密方法,包含了双重MD5加密和加盐加密等方式.
 * </p>
 * 
 * <ul>
 * <li><b>getMD5Value</b> - 获取字符串、字节数组、或者文件的MD5校验值</li>
 * <li><b>getDualMD5Value</b> - 获取双重的MD5校验值</li>
 * <li><b>getRandomMD5Value</b> - 获取加盐算法的MD5校验值</li>
 * <li><b>verify</b> - 加盐算法的MD5校验值验证</li>
 * </ul>
 * 
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @since 1.0
 * @version 1.0
 */
public class MD5Utils {
	/* 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符 */
	protected static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/* {@code MessageDigest}对象 */
	protected static MessageDigest messageDigest = null;

	/* 静态初始化{@code MessageDigest}对象 */
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			// nsaex.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 生成字符串的MD5校验值
	 * </p>
	 * 
	 * @param st
	 *            字符串
	 * @return 字符串的MD5校验值
	 * @since 1.0
	 */
	public static String getMD5Value(String st) {
		if (null == st) {
			return null;
		}
		return getMD5Value(st.getBytes());
	}

	/**
	 * <p>
	 * 生成字节数组的MD5校验值
	 * </p>
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 字节数组的MD5校验值
	 * @since 1.0
	 */
	public static String getMD5Value(byte[] bytes) {
		if (null == bytes) {
			return null;
		}
		messageDigest.update(bytes);
		return bufferToHex(messageDigest.digest());
	}

	/**
	 * <p>
	 * 生成文件的MD5校验值
	 * </p>
	 * 
	 * @param file
	 *            文件
	 * @return 文件的MD5校验值
	 * @throws IOException
	 *             读取文件失败
	 * @since 1.0
	 */
	public static String getMD5Value(File file) throws IOException {
		if (null == file) {
			return null;
		}
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				messageDigest.update(buffer, 0, numRead);
			}
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if (null != fis) {
				fis.close();
				fis = null;
			}

		}
		return bufferToHex(messageDigest.digest());
	}

	/**
	 * <p>
	 * 生成双重字符串的MD5校验值,进行了两次MD5算法
	 * </p>
	 * 
	 * @param st
	 *            字符串
	 * @return 字符串的MD5校验值
	 * @since 1.0
	 */
	public static String getDualMD5Value(String st) {
		return getMD5Value(getMD5Value(st));
	}

	/**
	 * <p>
	 * 生成含有随机盐的MD5校验值
	 * </p>
	 * 
	 * @param st
	 *            字符串
	 * @return 含有随机盐的MD5校验值
	 * @since 1.0
	 */
	public static String getRandomMD5Value(String st) {
		if (null == st) {
			return null;
		}
		Random rand = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(rand.nextInt(99999999)).append(rand.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		messageDigest.update((st + salt).getBytes());
		st = bufferToHex(messageDigest.digest());
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = st.charAt(i / 3 * 2);
			char ch = salt.charAt(i / 3);
			cs[i + 1] = ch;
			cs[i + 2] = st.charAt(i / 3 * 2 + 1);
		}
		return String.valueOf(cs).toUpperCase();
	}

	/**
	 * <p>
	 * 针对随机盐生成的MD5校验值,提供的字符串验证功能.随机盐生成的校验值每次都不同,不能通过简单的比较进行验证.
	 * </p>
	 * 
	 * @param st
	 *            字符串
	 * @param md5
	 *            MD5校验值
	 * @return {@code true}表示验证通过,反之{@code false}
	 * @since 1.0
	 */
	public static boolean verify(String st, String md5) {
		if (null == st || null == md5 || md5.length() != 48) {
			return false;
		}
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		messageDigest.update((st + salt).getBytes());
		st = bufferToHex(messageDigest.digest());
		return st.equals(new String(cs1));
	}

	/**
	 * <p>
	 * 把字节数转换成字符串
	 * </p>
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 字节数组转换后的字符串
	 * @since 1.0
	 */
	private static String bufferToHex(byte[] bytes) {
		int length = bytes.length;
		StringBuffer sb = new StringBuffer(2 * length);
		for (int i = 0; i < length; i++) {
			// 取字节中高 4 位的数字转换
			char c0 = hexDigits[(bytes[i] & 0xf0) >> 4];
			// 取字节中低 4 位的数字转换
			char c1 = hexDigits[bytes[i] & 0xf];
			sb.append(c0);
			sb.append(c1);
		}
		return sb.toString().toUpperCase();
	}

}
