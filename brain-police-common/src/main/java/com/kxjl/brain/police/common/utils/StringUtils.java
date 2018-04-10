/**
 * Copyright 2015 Iflytek, Inc. All rights reserved.
 */
package com.kxjl.brain.police.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>
 * <code>StringUtils</code>提供常用的字符串处理方法.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * 字符串常量
     */
    private static final String MAIL = "mail.";

    /**
     * 环球信息网
     */
    private static final String WORLD_WIDE_WEB = "www.";

    /**
     * @param regex
     * @param content
     * @return
     * @description 正则校验
     * @author yjleng
     * @create 2015年12月15日下午2:16:26
     * @version 1.0
     */
    public static boolean checkRegex(String regex, String content) {
        if (StringUtils.isBlank(content)) {
            content = "";
        }
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(content.trim()).matches();
    }

    /**
     * @param str 字符串
     * @return
     * @description 获取字符串字节长度 汉字2 其他 1（需要trim）
     * @author yhsu
     * @create 2015年12月15日上午10:54:25
     * @version 1.0
     */
    public static int getByteLength(String str) {
        if (isBlank(str)) {
            return 0;
        }
        str = str.trim();
        char[] charArray = str.toCharArray();
        int count = 0;
        for (char c : charArray) {
            if (String.valueOf(c).matches("[\u4E00-\u9FA5]")) {
                count += 2;
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * @param str
     * @return
     * @description 获取字符串字节长度 汉字2 其他1（不需要trim）
     * @author yjleng
     * @create 2016年1月5日上午10:35:43
     * @version 1.0
     */
    public static int getNoTrimByteLength(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        char[] charArray = str.toCharArray();
        int count = 0;
        for (char c : charArray) {
            if (String.valueOf(c).matches("[\u4E00-\u9FA5]")) {
                count += 2;
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * 处理sql的特殊字符串
     *
     * @param param
     * @return
     */
    public static String escapeSql(String param) {
        if (isEmpty(param)) {
            return param;
        }
        // 单引号是oracle字符串的边界,oralce中用2个单引号代表1个单引号
        String escape = param.replaceAll("'", "''");
        // 由于使用了/作为ESCAPE的转义特殊字符,所以需要对该字符进行转义
        // 这里的作用是将"a/a"转成"a//a"
        escape = escape.replaceAll("/", "//");
        // 使用转义字符 /,对oracle特殊字符% 进行转义,只作为普通查询字符，不是模糊匹配
        escape = escape.replaceAll("%", "/%");
        // 使用转义字符 /,对oracle特殊字符_ 进行转义,只作为普通查询字符，不是模糊匹配
        escape = escape.replaceAll("_", "/_");
        return escape;
    }

    /**
     * @param content
     * @return
     * @description 转义ES的sql语句的关键字（+ - && || ! ( ) { } [ ] ^ " ~ * ? : \/）
     * @author yjleng
     * @create 2016年1月5日下午5:30:32
     * @version 1.0
     */
    public static String escapeESSql(String content) {
        if (isEmpty(content)) {
            return content;
        }
        content = content.replaceAll("\\\\", "\\\\\\\\");
        content = content.replaceAll("\\+", "\\\\+");
        content = content.replaceAll("\\-", "\\\\-");
        content = content.replaceAll("\\&", "\\\\&");
        content = content.replaceAll("\\|", "\\\\|");
        content = content.replaceAll("\\!", "\\\\!");
        content = content.replaceAll("\\(", "\\\\(");
        content = content.replaceAll("\\)", "\\\\)");
        content = content.replaceAll("\\{", "\\\\{");
        content = content.replaceAll("\\}", "\\\\}");
        content = content.replaceAll("\\[", "\\\\[");
        content = content.replaceAll("\\]", "\\\\]");
        content = content.replaceAll("\\^", "\\\\^");
        content = content.replaceAll("\\\"", "\\\\\\\"");
        content = content.replaceAll("\\~", "\\\\~");
        content = content.replaceAll("\\*", "\\\\*");
        content = content.replaceAll("\\?", "\\\\?");
        content = content.replaceAll("\\:", "\\\\:");
        content = content.replaceAll("\\/", "\\\\/");
        content = content.replaceAll("'", "''");
        content = content.replaceAll(" ", "\\\\" + " ");
        return content;
    }

    public static boolean containsQuote(String content) {
        if (isEmpty(content)) {
            return false;
        }
        return content.contains("'") || content.contains("\"");
    }

    /**
     * 根据邮箱获取邮件服务器地址
     *
     * @param mail
     * @return String
     * @author: yhsu
     * @createTime: 2016年1月6日 下午5:49:29
     */
    public static String getMailServiceURL(String mail) {
        if (isBlank(mail)) {
            return "";
        }
        String[] mailArray = mail.split("@");
        if (null == mailArray || mailArray.length != 2) {
            return "";
        }
        return MAIL + mailArray[1];
    }

    /**
     * 判断是否为一级域名
     *
     * @param visitDomain
     * @return boolean
     * @author: yhsu
     * @createTime: 2016年1月6日 下午6:04:04
     */
    public static boolean isW3Domain(String visitDomain) {
        if (isBlank(visitDomain)) {
            return true;
        }
        return visitDomain.startsWith(WORLD_WIDE_WEB);
    }

    /**
     * 将引擎传入modifiedFiles的JSON数组解析，并使用；拼接每个file的路径
     *
     * @param modifiedFiles
     * @return String
     * @author: yhsu
     * @createTime: 2016年1月7日 上午8:58:48
     */
    public static String parseModifiedFiles(String modifiedFiles) {
        if (StringUtils.isEmpty(modifiedFiles)) {
            return null;
        }
        JSONArray array = JSONArray.parseArray(modifiedFiles);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < array.size(); i++) {
            Object obj = array.get(i);
            JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(obj));
            str.append(jsonObj.getString("file" + i) + "#");
        }
        if (!StringUtils.isEmpty(str.toString())) {
            return str.substring(0, str.length() - 1);
        }
        return null;
    }

    /**
     * @param list
     * @return
     * @description 将Integer的集合List转换成以逗号分隔的字符串
     * @author yjleng
     * @create 2016年1月8日上午10:18:15
     * @version 1.0
     */
    public static String convertIntegerListToStrByComma(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        StringBuffer str = new StringBuffer();
        for (Integer i : list) {
            str.append(i).append(",");
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * 对需要高亮的字符串中包含的特殊字符进行转义
     *
     * @param word
     * @return String
     * @author: yjleng
     * @createTime: 2014-8-12 下午07:15:00
     */
    public static String escapeSpecialCharInRegExp(String word) {
        if (word.contains("\\")) {
            word = word.replaceAll("\\\\", "\\\\\\\\");
        }
        if (word.contains("$")) {
            word = word.replaceAll("\\$", "\\\\\\$");
        }
        if (word.contains("(")) {
            word = word.replaceAll("\\(", "\\\\\\(");
        }
        if (word.contains(")")) {
            word = word.replaceAll("\\)", "\\\\\\)");
        }
        if (word.contains("*")) {
            word = word.replaceAll("\\*", "\\\\\\*");
        }
        if (word.contains("?")) {
            word = word.replaceAll("\\?", "\\\\\\?");
        }
        if (word.contains("[")) {
            word = word.replaceAll("\\[", "\\\\\\[");
        }
        if (word.contains("]")) {
            word = word.replaceAll("\\]", "\\\\\\]");
        }
        if (word.contains("{")) {
            word = word.replaceAll("\\{", "\\\\\\{");
        }
        if (word.contains("}")) {
            word = word.replaceAll("\\}", "\\\\\\}");
        }
        if (word.contains("|")) {
            word = word.replaceAll("\\|", "\\\\\\|");
        }
        if (word.contains("+")) {
            word = word.replaceAll("\\+", "\\\\\\+");
        }
        if (word.contains("^")) {
            word = word.replaceAll("\\^", "\\\\\\^");
        }
        if (word.contains(".")) {
            word = word.replaceAll("\\.", "\\\\\\.");
        }
        return word;
    }

    /**
     * 将中文括号统一替换为英文的
     *
     * @param content
     * @return String
     * @author: yjleng
     * @createTime: 2014-8-21 上午11:12:52
     */
    public static String formatBracket(String content) {
        if (!StringUtils.isBlank(content)) {
            content = content.replaceAll("（", "(").replaceAll("）", ")").trim();
        }
        return content;
    }

    /**
     * 获取文件名
     *
     * @param filePath 文件的路径
     * @return String
     * @author: yhsu
     * @createTime: 2016年1月20日 下午4:53:03
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        } else {
            return filePath.substring(filePath.lastIndexOf("/") + 1);
        }
    }

    /**
     * 判断是否为合法手机号码
     *
     * @param phoneNum the phone num
     * @return the boolean
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-7 10:42:35
     * @version: yungo 2.0
     */
    public static boolean isLegalPhoneNum(String phoneNum) {
        if (StringUtils.isBlank(phoneNum)) {
            return false;
        }
        String regex = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)[0-9]{8}$";
        return checkRegex(regex, phoneNum);
    }


    /**
     * 将输入流转成字符串
     *
     * @param is the is
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-28 17:03:31
     * @version: yungo 2.0
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return sb.toString();
    }


}
