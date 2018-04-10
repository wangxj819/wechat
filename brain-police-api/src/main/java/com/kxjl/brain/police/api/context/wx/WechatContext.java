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
 * 1.0	     Administrator    2017/5/1013:36	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.event.ApiEvent;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.HttpWalker;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯地图Map返回接口
 * @Author DengWei
 * @Date 2017/5/11 9:08
 */
public class WechatContext extends BaseContext<Map, String> {

    protected WechatContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static WechatContext build(String businessId, String body, String token, LogModule log) {
        return new WechatContext(businessId, body, token, log);
    }

    @Override protected void checkAfter() {

    }

    @Override protected Map parse() {
        //JSONObject jsonObject = JSONObject.parseObject(body);
//        Map paramsMap = getParamsMap(body,"UTF-8");
//       // Map map =JsonUtil.json2Obj(paramsMap.get("searchStr").toString(),Map.class);
//        String str = paramsMap.get("searchStr").toString();
//        log.info(ApiEvent.CHECK_AFTER,"resultStr:{}",str);
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }


    private static Map getParamsMap(String queryString, String enc) {
        Map paramsMap = new HashMap();
        if (queryString != null && queryString.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0;
            String subStr, param, value;
            String[] paramPair, values, newValues;
            do {
                ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = queryString.substring(lastAmpersandIndex);
                }
                paramPair = subStr.split("=");
                param = paramPair[0];
                value = paramPair.length == 1 ? "" : paramPair[1];
                try {
                    value = URLDecoder.decode(value, enc);
                } catch (UnsupportedEncodingException ignored) {
                }
                if (paramsMap.containsKey(param)) {
                    values = (String[])paramsMap.get(param);
                    int len = values.length;
                    newValues = new String[len + 1];
                    System.arraycopy(values, 0, newValues, 0, len);
                    newValues[len] = value;
                } else {
                    newValues = new String[] { value };
                }
                paramsMap.put(param, newValues);
            } while (ampersandIndex > 0);
        }
        return paramsMap;
    }

    @Override protected BaseRespResult run() {

        String cityName = dto.get("cityName").toString();
        String keyName = dto.get("keyName").toString();
        try {
            keyName = URLEncoder.encode(keyName,"UTF-8");
            cityName = URLEncoder.encode(cityName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://apis.map.qq.com/ws/place/v1/search?boundary=region("+cityName+",0)&page_size=10&page_index=1&keyword="+keyName+"&orderby=_distance&key="+ Constants.TENCENT_MAP_KEY;
        log.info(ApiEvent.CHECK_AFTER,"url:{}",url);
        String resultStr =  HttpWalker.visit(url).get().getResponseStr();
        log.info(ApiEvent.CHECK_AFTER,"resultStr:{}",resultStr);
        return BaseRespResult.build(BaseRespondCode.OK,JSONUtils.parse(resultStr));
    }
}
