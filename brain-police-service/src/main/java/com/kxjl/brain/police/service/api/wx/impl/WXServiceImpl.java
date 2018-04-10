package com.kxjl.brain.police.service.api.wx.impl;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.result.SearchResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.HttpWalker;
import com.kxjl.brain.police.dto.wx.QueryWXCallPoliceDTO;
import com.kxjl.brain.police.service.api.wx.WXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 微信获取AccessToken和Jsticket
 * Created by Dengwei on 2017/5/8.
 */
@Service
public class WXServiceImpl implements WXService {



    private static Logger logger = LoggerFactory.getLogger(WXServiceImpl.class);

    /** 全局access_token */
    private static String WX_ACCESS_TOKEN = "";
    /** 全局js ticket */
    private static String WX_JS_TICKET = "";

    @Override
    public SearchResult queryWXCallPolice(QueryWXCallPoliceDTO queryWXCallPoliceDTO) {
        return SearchResult.build(BaseRespondCode.OK, new ArrayList(), 0);
    }

    //静态代码块启动token刷新任务
    static{
        WX_ACCESS_TOKEN = requestAccessToken();
        WX_JS_TICKET = requestJSTicket();

        logger.info("微信服务启动，获取access_token：{}", WX_ACCESS_TOKEN);
        logger.info("微信服务启动，获取js_ticket：{}", WX_JS_TICKET);
        logger.info("token刷新任务开始，刷新间隔{}分钟", Constants.WX_TOKEN_TIME);

        //定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                WX_ACCESS_TOKEN = requestAccessToken();
                WX_JS_TICKET = requestJSTicket();

                logger.info("微信定时刷新token任务结束，access_token:{}，js_ticket:{}", WX_ACCESS_TOKEN, WX_JS_TICKET );
            }
        }, Integer.parseInt(Constants.WX_TOKEN_TIME) * 60 * 1000, Integer.parseInt(Constants.WX_TOKEN_TIME) * 60 * 1000);
    }



    @Override
    public String getAccessToken() {
        return WX_ACCESS_TOKEN;
    }

    @Override
    public String getJSTicket() {
        return WX_JS_TICKET;
    }
/*
    @Override public String getAccessToken() {
        Map map = new TreeMap();
        String timestamp = System.currentTimeMillis()+"";
        timestamp = timestamp.substring(0,10);
        map.put("app_id",Constants.POLICE_APPID);
        map.put("app_secrect",Constants.POLICE_SECRECT);
        map.put("timestamp",timestamp);
        String signStr = MD5Utils.getMD5Value(SignUtils.signStr(map)).toLowerCase();
        String url = Constants.POLICE_WX_URL+"/access_token?app_id="+Constants.POLICE_APPID+"&timestamp="+timestamp+"&sign="+signStr;
        System.out.println(url);
        String resultStr = HttpWalker.visit(url).get().getResponseStr();
        Map result = JsonUtil.json2Obj(resultStr,Map.class);
        Map resultMap = (Map) result.get("data");
        return resultMap.get("access_token").toString();
    }

    @Override public String getJSTicket() {
        Map map = new TreeMap();
        String timestamp = System.currentTimeMillis()+"";
        timestamp = timestamp.substring(0,10);
        map.put("app_id",Constants.POLICE_APPID);
        map.put("app_secrect",Constants.POLICE_SECRECT);
        map.put("timestamp",timestamp);
        String signStr = MD5Utils.getMD5Value(SignUtils.signStr(map)).toLowerCase();
        String url = Constants.POLICE_WX_URL+"/access_token?app_id="+Constants.POLICE_APPID+"&timestamp="+timestamp+"&sign="+signStr;
        System.out.println(url);
        String resultStr = HttpWalker.visit(url).get().getResponseStr();
        Map result = JsonUtil.json2Obj(resultStr,Map.class);
        Map resultMap = (Map) result.get("data");
        return resultMap.get("jsapi_ticket").toString();
    }*/




    /**
     * 请求access token<br/>
     */
    private static String requestAccessToken() {
        // 本地直接访问微信接口，上线修改为访问redis
        String tokenUrl = String.format(Constants.WX_ACCESS_TOKEN_URL, Constants.WX_APP_ID, Constants.WX_APP_SECRET);
//        System.out.println("------>"+tokenUrl);
        String jsonStr = HttpWalker.visit(tokenUrl).get().getResponseStr();
        String token = JSONObject.parseObject(jsonStr).getString("access_token");

        return token;
    }

    /**
     * 请求js ticket
     */
    private static String requestJSTicket() {
        // 本地直接访问微信接口，上线修改为访问redis
        String tokenUrl = String.format(Constants.WX_JSTICKET_URL, WX_ACCESS_TOKEN);
//        System.out.println("------>"+tokenUrl);
        String jsonStr = HttpWalker.visit(tokenUrl).get().getResponseStr();
        String ticket = JSONObject.parseObject(jsonStr).getString("ticket");

        return ticket;
    }


    @Override
    public void reloadWxMenu() {

        String menuJson;

        try {
            menuJson = org.apache.commons.io.FileUtils.readFileToString(
                    new File(WxUtilsServiceImpl.class.getClassLoader().getResource("wx-menu.json").getFile()),
                    "utf-8");
            logger.info("加载微信菜单：\n{}", menuJson);
            createWxMenu(menuJson);
        } catch (IOException e) {
            logger.error("读取微信菜单配置出错", e);
        }
    }


    /**
     * 创建微信菜单
     */
    private boolean createWxMenu(String json) {
        String createUrl = String.format(Constants.WX_MENU_CREATE_URL, WX_ACCESS_TOKEN);
        String jsonStr = HttpWalker.visit(createUrl).putBody(json).post().getResponseStr();
        Integer errcode = JSONObject.parseObject(jsonStr).getInteger("errcode");
        return errcode == 0;
    }


   /* public static void main(String[] args) {
        Map map = new TreeMap();
        String timestamp = System.currentTimeMillis()+"";
        timestamp = timestamp.substring(0,10);
        map.put("app_id","1102");
        map.put("app_secrect","bCDWjTMYRzEIxVYi");
        map.put("timestamp",timestamp);
        String signStr = MD5Utils.getMD5Value(SignUtils.signStr(map)).toLowerCase();
        String url = "http://police.eisong.cc/api/weixin/access_token?app_id=1102"+"&timestamp="+timestamp+"&sign="+signStr;
        System.out.println(url);
        String resultStr = HttpWalker.visit(url).get().getResponseStr();
        Map result = JsonUtil.json2Obj(resultStr,Map.class);

        Map resultMap = (Map) result.get("data");
        System.out.println( resultMap.get("jsapi_ticket").toString());
    }*/

}
