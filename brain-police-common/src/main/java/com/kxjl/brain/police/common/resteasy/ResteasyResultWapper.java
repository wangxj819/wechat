/**
 * Copyright 2015 Iflytek, Inc. All rights reserved.
 */
package com.kxjl.brain.police.common.resteasy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <code>ResteasyResultWapper</code>对Resteasy服务返回的结果进行了不同适配.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public class ResteasyResultWapper extends ResteasyClientResult {
    /**
     * <code>读取Resteasy服务端返回的字符串类型数据</code>
     *
     * @param address  Resteasy协议服务端地址
     * @param paramMap 协议参数,key-value
     * @return 返回的字符串数据
     */
    public String readString(String address, Map<String, String> paramMap) {
        return super.readEntity(String.class, address, paramMap);
    }

    /**
     * <code>读取Resteasy服务端返回的字符串类型数据</code>
     *
     * @param address    Resteasy协议服务端地址
     * @param paramName  参数名
     * @param paramValue 参数值
     * @return 返回的字符串数据
     */
    public String readString(String address, String paramName, String paramValue) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        return super.readEntity(String.class, address, paramMap);
    }


    /**
     * 读取数据
     *
     * @param address
     * @param body
     * @return
     * @author huanliu4
     */
    public String readString(String address, String body) {
        return super.readEntity(String.class, address, body);
    }

    /**
     * get请求
     *
     * @param address
     * @return
     * @author huanliu4
     */
    public String readGetString(String address) {
        return super.readGetEntity(String.class, address);
    }
}
