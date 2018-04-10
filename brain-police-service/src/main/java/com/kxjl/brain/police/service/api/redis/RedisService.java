/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：brain-police
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     Administrator    2017/5/179:32	  Create	
 */
package com.kxjl.brain.police.service.api.redis;

import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     *
     * @Description: 获取hash集合中的所有key
     * @param @param key
     * @param @return    设定文件
     * @return Set<String>    返回类型
     * @throws
     */
    Set<String> hkeys(final String key);

    void hMset(final String key,final Map<String, String> map,final long expireTime);

    /**
     *
     * @Description: 判断set集合中是否存在mem内容
     * @param @param key
     * @param @param mem
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    boolean sismember(final String key,final String mem);

    void srem(final String key,final String mem);
    /**
     * @Description: 获取Set集合中的总数
     * @param @param key
     * @param @return    设定文件
     * @return long    返回类型
     * @throws
     */
    long scard(final String key);

    /**
     *
     * @Description: 模糊查询所有匹配patten的key
     * @param @param patten
     * @param @return    设定文件
     * @return Set<String>    返回类型
     * @throws
     */
    Set<String> keys(final String patten);

    /**
     * 向redis添加hash类型数据
     * @param key
     * @param map
     */
    void hMset(final String key,final Map<String, String> map);

    /**
     * 从redis获取hash数据
     * @param key
     * @return
     */
    Map<String,String> hGetAll(final String key);

    /**
     * @Description: key中的hkey的value
     * @param @param key
     * @param @param hkey
     * @param @param hval
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    Boolean hset(final String key,final String hkey,final String hval);

    /**
     * @Description: 获取指定key中的hkey的value
     * @param @param key
     * @param @param hkey
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    String hget(final String key,final String hkey);

    /**
     * @Description: 从redis获取hash的key是否存在
     * @param @param key
     * @param @param hkey
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    boolean hkeyExist(final String key,final String hkey);

    /**
     * 向redis添加set类型数据
     * @param key
     * @param value
     */
    void sAdd(final String key,final String value);

    void set(final String key,final String value,final long expireTime);

    String get(final String key);
    /**
     * 从redis获取set数据
     * @param key
     * @return
     */
    Set<String> sMembers(final String key);

    /**
     * 判断redis是否存在key
     * @param key
     * @return
     */
    boolean keyExists(final String key);

    /**
     * 删除key
     * @param key
     */
    void delKey(final String key);

    /**
     * 选择redis数据库
     * @param dbId
     */
    void selectDb(final int dbId);


    /**
     *得到key的个数
     * @param key
     * @return
     */
    long hLen(final String key);

    /**
     * 保存String
     * @param key
     * @param value
     */
    void set(final String key, final String value);


    /**
     * 设置key的过期时间
     * @param key
     * @param seconds
     */
    void expire(final String key, final long seconds);
}
