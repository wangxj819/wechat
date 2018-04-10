package com.kxjl.brain.police.common;

/**
 * 系统常量定义
 * Created by zmpan on 2017/4/17.
 */
public class Constants {



    public static final int SUCCESS_CODE = 200;

    public static final int ERROR_CODE = 500;



    /** 文件存取物理根路径 **/
    public static String REAL_FILE_ROOT = "";




    /** 微信公号appid */
    public static String WX_APP_ID = "";
    /** 微信接入token */
    public static String WX_APP_TOKEN = "";
    /** 微信公号秘钥 */
    public static String WX_APP_SECRET = "";
    /** 微信token刷新时间 */
    public static String WX_TOKEN_TIME = "";

    /** 微信access token获取地址 */
    public static String WX_ACCESS_TOKEN_URL = "";
    /** 微信jsticket获取地址 */
    public static String WX_JSTICKET_URL = "";
    /** 微信菜单删除地址 */
    public static String WX_MENU_DELETE_URL = "";
    /** 微信菜单创建地址 */
    public static String WX_MENU_CREATE_URL = "";
    /** 微信网页授权地址 */
    public static String WX_OAUTH2_URL = "";

    /** 微信网页授权code换取token地址 */
    public static String WX_OAUTH2_TOKEN_URL = "";
    /** 微信用户信息获取地址 */
    public static String WX_USERINFO_URL = "";
    /** 关注时返回的信息 */
    public static String WX_SUBSCRIBE_INFO = "欢迎关注合肥警方";
    /** 关注事件类型 */
    public static String WX_CLICK_SUBSCRIBE_EVENT = "";
    /** 微信网页授权拉取用户信息地址 */
    public static String WX_OAUTH2_USERINFO_URL = "";

    /** 微信统一授权：鉴权参数名称 */
    public static String WX_AUTH_PARAM_NAME = "";

    /** 微信统一授权：注册级别 */
    public static String WX_AUTH_LEVEL_REG = "";

    /** 腾讯地图Key */
    public static String TENCENT_MAP_KEY = "";

    // --------------------非配置类----------------------
    /** session用户key */
    public static String KEY_SESSION_USER = "";

    public static String KEY_SESSION_REG = "";

    /** 站点域名端口 **/
    public static String APP_SITE_HOST = "";

    /**站点上下文*/
    public static  String CONTEXT_PATH_URL = "";

    /**
     * 微信通知URL
     */
    public static String WX_NOTIFY_URL = "";
    /**
     * 报警提交图片
     */
    public static String WX_ALARM_IMG="";
    /**
     * 报警提交视频
     */
    public static String WX_ALARM_VEDIO="";

    /**
     *
     * 短信签名
     */
    public static String SIGNNAME = "科讯嘉联";

    /**
     *
     * 短信模板编号
     */
    public static String TEMPLATECODE = "";

    /**
     * 短信验证域名+端口号
     */
    public static String SMSURL = "";

    /*OSS地址*/
    public static String OSS_BASE_URL = "";

    /***/
    public static String IMGPATH = "";

    /***/
    public static String VIDEOPATH = "";

    public static String WECHAT_MSG_SEND_URL = "";

    public static String OSS_PATH = "";
//尊敬的XXX先生：我们已经收到您于XXX的信息，报警内容为：（内容），请保持通讯畅通。
    //尊敬的：%s   我们已经收到您于：%s，报案内容为:%s。   警方会尽快与您联系，请保持电话畅通！
    public static String WECHAT_MSG = "尊敬的%s：我们已经收到您于%s的信息，报警内容为：%s，请保持通讯畅通。";

    /**
     * 短信过期时间
     */
    public static  int EXPIRE=0;

    /**
     * 短信验证的key
     */
    public static String SMSKEY = "";


    /**
     * 合肥警方appid
     */
    public static String POLICE_APPID = "";

    /**
     * 合肥警方密钥
     */
    public static String POLICE_SECRECT = "";

    /**
     * 公众号对接的
     */
    public static String POLICE_WX_URL = "";


    /**
     * 合肥警方公众号APPID
     */
    public static String POLICE_WX_APPID = "";

    /**
     * DS工单提交URL
     */
    public static String  DS_SUB_URL="";

    /**
     * 微信下载文件的地址
     */
    public static String WX_FILE_DOWNLOAD_URL ="";

    /**
     * 微信服务器原始录音文件
     */
    public static String WX_VOICE_AMR_PATH ="";


    /**
     * 原始文件转换后成MP3
     */
    public static String WX_VOICE_MP3_PATH ="";


    /**
     * 微信服务器原始录音文件OSS
     */
    public static String WX_VOICE_AMR_OSS_PATH ="";


    /**
     * 原始文件转换后成MP3OSS
     */
    public static String WX_VOICE_MP3_OSS_PATH ="";

    /**
     * IM 转微信 url配置
     */
    public static String TRUN_URL = "";


    /**
     *  主机
     */
    public static String  IM_HOST="";


    /**
     *  端口
     */
    public static String IM_PORT="";

    /**
     *  资源
     */
    public static String IM_RESOURCE ="";



    /**
     *  服务名
     */
    public static String  IM_SERVICE_NAME="";


    /**
     *  用户名
     */
    public static String  IM_USERNAME="";


    /**
     *  用户密码
     */
    public static String  IM_PASSWORD="";


    /**
     *  接收方账号
     */
    public static String  IM_TO_USER="";

    /**
     * 跳转微信聊天
     */
    public static String IM_CHAT = "";

    /**
     * 点击连接验证码错误跳转错误页面
     */
    public static String ERROR_HTML = "";

    /**
     * 通知用户可以聊天
     */
    public static String USER_CHAT = "";

    /**
     * 微信会话结束页面
     */
    public static String END_WECHAT = "";

    /**
     * 长连接换取短连接
     */
    public static String LONG_SHORT = "";

    /**
     * 提示信息
     */
    public static String PROMPT = "";
}
