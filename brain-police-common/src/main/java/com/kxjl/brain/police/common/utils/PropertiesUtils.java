/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：yungo-service
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     admin    2016/6/2510:43	  Create	
 */
package com.kxjl.brain.police.common.utils;

import jxl.common.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The type Properties utils.
 *
 * @desc: 配置文件读取工具类
 * @author: yhsu
 * @createTime: 2016 -6-25 10:44:35
 * @version: yungo 2.0
 */
public class PropertiesUtils
{


    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(PropertiesUtils.class);

    /**
     * 配置文件读取的相关
     */
    private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

    /**
     * Get properties properties.
     *
     * @param path the path
     * @return the properties
     * @desc: 获取配置文件读取的相关对象
     * @author: yhsu
     * @createTime: 2016 -6-25 10:47:59
     * @version: yungo 2.0
     */
    public static Properties getProperties(String path) {
        try {
            Properties properties = propertiesMap.get(path);
            if (null != properties) {
                return properties;
            }
            properties = new Properties();
            properties.load(PropertiesUtils.class.getResourceAsStream(path));
            propertiesMap.put(path, properties);
            return properties;
        } catch (IOException e) {
            logger.error("getProperties：获取配置文件读取文件失败", e);
            return null;
        }
    }
}
