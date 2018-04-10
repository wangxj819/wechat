/**
 * Copyright 2015 Iflytek, Inc. All rights reserved.
 */
package com.kxjl.brain.police.common.resteasy;

import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * <p>
 * <code>ResteasyClientResult</code>封装了Resteasy服务端返回的数据.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public class ResteasyClientResult extends BaseResteasyClient {
    /**
     * <code>readEntity</code>方法根据Clazz类型返回实体
     *
     * @param clazz    返回的数据类型
     * @param address  Resteasy协议服务端地址
     * @param paramMap 协议参数,key-value
     * @return Clazz类型返回值
     */
    public <T> T readEntity(Class<T> clazz, String address,
                            Map<String, String> paramMap) {
        Response response = super.post(address, paramMap);
        T t = response.readEntity(clazz);
        response.close();
        return t;
    }


    /**
     * 获取远程数据
     *
     * @param clazz
     * @param address
     * @param body
     * @param <T>
     * @return
     * @author huanliu4
     */
    public <T> T readEntity(Class<T> clazz, String address, String body) {
        Response response = super.post(address, body);
        T t = response.readEntity(clazz);
        response.close();
        return t;
    }


    /**
     * get请求
     *
     * @param clazz
     * @param address
     * @param <T>
     * @return
     * @author huanliu4
     */
    public <T> T readGetEntity(Class<T> clazz, String address) {
        Response response = super.get(address);
        T t = response.readEntity(clazz);
        response.close();
        return t;
    }


}
