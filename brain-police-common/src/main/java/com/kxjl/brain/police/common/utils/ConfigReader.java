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
 * 1.0	     admin    2016/6/2510:52	  Create	
 */
package com.kxjl.brain.police.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * The type Config reader.
 *
 * @desc: config.properties 配置文件读取类
 * @author: yhsu
 * @createTime: 2016 -6-25 10:52:41
 * @version: yungo 2.0
 */
public class ConfigReader
{

    /**
     * config.properties 文件位置
     */
    private static final String CONFIG_PROPERTIES_PATH = "/config/config.properties";

    /**
     * 文件读取类
     */
    private static Properties properties = PropertiesUtils.getProperties(CONFIG_PROPERTIES_PATH);

    /**
     * 读取相关值
     *
     * @param key the key
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 11:02:44
     * @version: yungo 2.0
     */
    public static String readValue(String key) {
        return properties.getProperty(key);
    }


    /**
     * 读取SMS的Host配置
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 10:55:14
     * @version: yungo 2.0
     */
    public static String readSMSHost() {
        return properties.getProperty(SMS_HOST_KEY);
    }

    /**
     * Read sms app id string.
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 10:59:21
     * @version: yungo 2.0
     */
    public static String readSMSAppId() {
        return properties.getProperty(SMS_APPID_KEY);

    }

    /**
     * Read sms app secrect string.
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 10:59:23
     * @version: yungo 2.0
     */
    public static String readSMSAppSecrect() {
        return properties.getProperty(SMS_APP_SECRECT_KEY);

    }


    /**
     * Read smsurl string.
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 10:59:25
     * @version: yungo 2.0
     */
    public static String readSMSURL() {
        return properties.getProperty(SMS_URL_KEY);

    }

    /**
     * Read sms header nonce string.
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 10:59:27
     * @version: yungo 2.0
     */
    public static String readSMSHeaderNonce() {
        return properties.getProperty(SMS_HEADER_NONCE_KEY);

    }

    /**
     * Read sms short code string.
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 10:59:29
     * @version: yungo 2.0
     */
    public static String readSMSShortCode() {
        return properties.getProperty(SMS_SHORT_CODE_KEY);

    }

    /**
     * 获取短信签名
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 13:04:18
     * @version: yungo 2.0
     */
    public static String readMsgPreName() {
        return properties.getProperty(SMS_AUTH_PRE_NAME);
    }

    /**
     * 获取企业默认ID
     *
     * @return the integer
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-25 12:53:18
     * @version: yungo 2.0
     */
    public static Long readRobotDefaultBusId() {
        return Long.valueOf(properties.getProperty(DEFAULT_BUS_ID));
    }

    /**
     * 获取机器人SIP服务地址
     *
     * @return the string
     * @author:xfxing
     * @createTime:2016-6-28 15 :13:25
     * @version: yungo2.0
     */
    public static String readRobotSIPServer() {
        return properties.getProperty(ROBOT_SIP_SERVER);
    }


    /**
     * The constant SMS_HOST_KEY.
     */
    /**
     * 获取生物认证平台 声纹相关接口地址
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-28 16:14:19
     * @version: yungo 2.0
     */
    public static String readBATAuthServiceURL() {
        return properties.getProperty(BAT_SERVICE_URL);
    }

    /**
     * 读取BAT的appid
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-28 16:19:56
     * @version: yungo 2.0
     */
    public static String readBATAppid() {
        return properties.getProperty(BAT_APPID);
    }


    /**
     * 读取短信媒体网关地址
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-30 18:44:39
     * @version: yungo 2.0
     */
    public static String readSMSMGWUrl() {
        return properties.getProperty(SMSCHAT_MESSAGE_SERVER_HOST);
    }

    /**
     * 读取web聊天媒体网关地址
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-30 18:44:48
     * @version: yungo 2.0
     */
    public static String readWebChatMGWUrl() {
        return properties.getProperty(WEBCHAT_MESSAGE_SERVER_HOST);
    }

    /**
     * 读取微信聊天媒体网关地址
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-30 18:44:59
     * @version: yungo 2.0
     */
    public static String readWeChatMGWUrl() {
        return properties.getProperty(WECHAT_MESSAGE_SERVER_HOST);
    }

    /**
     * 获取机器人的分机号
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-30 20:36:50
     * @version: yungo 2.0
     */
    public static String readRobotDN() {
        return properties.getProperty(ROBOT_DN, StringUtils.EMPTY);
    }

    /**
     * 读取机器人开关
     *
     * @return the boolean
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -6-30 20:38:51
     * @version: yungo 2.0
     */
    public static boolean readRobotON() {
        return Boolean.valueOf(properties.getProperty(ROBOT_DN, StringUtils.EMPTY));
    }

    /**
     * 机器人拒识回复语
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -7-1 10:14:48
     * @version: yungo 2.0
     */
    public static String readRobotRefuseMsg() {
        return properties.getProperty(ROBOT_REFUSE_MSG);
    }

    /**
     * 机器人欢迎回复语
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -7-1 10:14:48
     * @version: yungo 2.0
     */
    public static String readRobotWelcomeMsg() {
        return properties.getProperty(ROBOT_WELCOME_MSG);
    }

    public static String readRobotTransManFlag() {
        return properties.getProperty(ROBOT_TRANS_MAN_FLAG);
    }

    public static String readRobotHelloFlag() {
        return properties.getProperty(ROBOT_HELLO_FLAG);
    }


    /**
     * 获取瓦力引擎企业代码.配置
     *
     * @return the string
     * @desc:
     * @author: ryhan
     * @createTime: 2016 -8-27 14:39:01
     * @version: yungo 2.0
     */
    public static String readWalleOrgCode() {
        return properties.getProperty(WALLE_ORG_CODE);
    }

    /**
     * 获取瓦力引擎nbest配置
     *
     * @return the string
     * @desc:
     * @author: ryhan
     * @createTime: 2016 -8-27 14:39:02
     * @version: yungo 2.0
     */
    public static String readWalleOrgNBest() {
        return properties.getProperty(WALLE_NBEST);
    }


    public static String readAmapSearchCity() {
        return properties.getProperty(AMAP_CONFIG_CITY);
    }

    public static String readAmapSearchCityLimit() {
        return properties.getProperty(AMAP_CONFIG_CITYLIMIT);
    }

    public static String readAmapSearchTypes() {
        return properties.getProperty(AMAP_CONFIG_TYPES);
    }

    public static String readAmapSearchOffset() {
        return properties.getProperty(AMAP_CONFIG_OFFSET);
    }


    public static String readAsrLogFilesRemotePath() {
        return properties.getProperty(ASR_LOGFILES_REMOTE_PATH);
    }

    public static String readAsrLogFilesLocalPath() {
        return properties.getProperty(ASR_LOGFILES_LOCAL_PATH);
    }

    public static String readIirAudioParams() {
        return properties.getProperty(IIR_Audio_Params);
    }

    public static String readIirCLibFullPath() {
        return properties.getProperty(IIR_C_Lib_FullPath);
    }

    public static String readIirJniLibPath() {
        return properties.getProperty(IIR_JNI_Lib_Path);
    }


    public static String readPoiCLibCfgPath() {
        return properties.getProperty(POI_C_Lib_cfg_Path);
    }

    public static String readPoiCLibFullath() {
        return properties.getProperty(POI_C_Lib_FullPath);
    }

    public static String readPoiJniLibPath() {
        return properties.getProperty(POI_JNI_Lib_Path);
    }

    public static String readEsBaseUrl() {
        return properties.getProperty(ES_BASE_URL);
    }

    public static String readEsPoiSql() {
        return properties.getProperty(ES_POI_DEFAULT_SQL);
    }

    public static String readEsPlanSql() {
        return properties.getProperty(ES_PLAN_DEFAULT_SQL);
    }

    /**
     * 转人工关键词
     *
     * @return the string
     * @desc:
     * @author: yhsu
     * @createTime: 2016 -7-9 14:50:32
     * @version: yungo 2.0
     */
    public static List<String> readTransManKeyWords() {
        List<String> keyWordList = new ArrayList<String>();
        String keyWords = properties.getProperty(ROBOT_TRANS_MAN_KEYWORDS);
        if (!StringUtils.isBlank(keyWords)) {
            keyWordList = Arrays.asList(StringUtils.split(keyWords, ","));
        }
        return keyWordList;
    }

    /**
     * 获取商品介绍内容
     *
     * @param productName the product name
     * @return the string
     * @author:yhsu
     * @createTime:2016-7-11 14 :29:45
     * @version: yungo2.0
     */
    public static String readProductIntroduce(String productName) {
        return properties.getProperty(ROBOT_PRODUCT_INTRODUCE + productName);
    }

    /**
     * 获取商品HTML
     *
     * @param productName the product name
     * @return the string
     * @author:yhsu
     * @createTime:2016-7-11 14 :29:45
     * @version: yungo2.0
     */
    public static String readProductHTML(String productName) {
        return properties.getProperty(ROBOT_PRODUCT_HTML + productName);
    }


    private static final String SMS_HOST_KEY = "sms.host";
    /**
     * The constant SMS_APPID_KEY.
     */
    private static final String SMS_APPID_KEY = "sms.appid";
    /**
     * The constant SMS_APP_SECRECT_KEY.
     */
    private static final String SMS_APP_SECRECT_KEY = "sms.appsecrect";
    /**
     * The constant SMS_URL_KEY.
     */
    private static final String SMS_URL_KEY = "sms.url";
    /**
     * The constant SMS_HEADER_NONCE_KEY.
     */
    private static final String SMS_HEADER_NONCE_KEY = "sms.header.nonce";
    /**
     * The constant SMS_SHORT_CODE_KEY.
     */
    private static final String SMS_SHORT_CODE_KEY = "sms.short.code";
    /**
     * The constant SMS_AUTH_PRE_NAME.
     */
    private static final String SMS_AUTH_PRE_NAME = "sms.auth.pre.name";

    /**
     * The constant DEFAULT_BUS_ID.
     */
    private static final String DEFAULT_BUS_ID = "robot.default.bus.id";

    /**
     * The constant ROBOT_SIP_SERVER.
     */
    private static final String ROBOT_SIP_SERVER = "robot.sip.server";

    private static final String BAT_SERVICE_URL = "bat.auth.service.url";
    private static final String BAT_APPID = "bat.appid";

    private static final String ROBOT_DN = "robot.dn";
    private static final String ROBOT_ON = "robot.on";
    private static final String ROBOT_REFUSE_MSG = "robot.refuse.msg";
    private static final String ROBOT_WELCOME_MSG = "robot.welcome.msg";
    private static final String ROBOT_TRANS_MAN_FLAG = "robot.trans.man.flag";
    private static final String ROBOT_HELLO_FLAG = "robot.hello.flag";
    private static final String ROBOT_TRANS_MAN_KEYWORDS = "robot.trans.man.keywords";
    private static final String ROBOT_PRODUCT_INTRODUCE = "robot.product.introduce.";
    private static final String ROBOT_PRODUCT_HTML = "robot.product.html.";

    private static final String WEBCHAT_MESSAGE_SERVER_HOST = "webchat.message.server.host";
    private static final String WECHAT_MESSAGE_SERVER_HOST = "wechat.message.server.host";
    private static final String SMSCHAT_MESSAGE_SERVER_HOST = "smschat.message.server.host";


    private static final String WALLE_ORG_CODE = "walle.org.code";
    private static final String WALLE_NBEST = "walle.nbest";

    private static final String AMAP_CONFIG_CITY = "amap.config.city";
    private static final String AMAP_CONFIG_CITYLIMIT = "amap.config.citylimit";
    private static final String AMAP_CONFIG_TYPES = "amap.config.types";
    private static final String AMAP_CONFIG_OFFSET = "amap.config.offset";

    private static final String ASR_LOGFILES_REMOTE_PATH = "asr.logfiles.remote.path";
    private static final String ASR_LOGFILES_LOCAL_PATH = "asr.logfiles.local.path";


    private static final String IIR_Audio_Params = "iir.audio.params";
    private static final String IIR_C_Lib_FullPath = "iir.c.lib.fullpath";
    private static final String IIR_JNI_Lib_Path = "iir.jni.lib.path";


    private static final String POI_JNI_Lib_Path = "poi.jni.lib.path";
    private static final String POI_C_Lib_cfg_Path = "poi.c.lib.cfg.path";
    private static final String POI_C_Lib_FullPath = "poi.c.lib.fullpath";

    private static final String ES_BASE_URL = "es.url";
    private static final String ES_POI_DEFAULT_SQL = "es.poi.default.sql";
    private static final String ES_PLAN_DEFAULT_SQL = "es.plan.default.sql";

}
