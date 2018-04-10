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
 * 1.0	     Administrator    2017/5/1120:57	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.alibaba.druid.support.json.JSONUtils;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.arsenal.restnew.event.ApiEvent;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.service.api.wx.WechatService;

import java.util.Map;

public class WechatDelContext extends BaseContext<Map, String> {


    WechatService wechatService;

    protected WechatDelContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static WechatDelContext build(String businessId, String body, String token, LogModule log) {
        return new WechatDelContext(businessId, body, token, log);
    }

    @Override protected Map parse() {
        return JsonUtil.json2Obj(body,Map.class);
    }

    public WechatDelContext setService(WechatService wechatService) {
        this.wechatService = wechatService;
        return this;
    }

    @Override protected void checkAfter() {

    }

    @Override protected void checkBefore() {
        super.checkBefore();
    }

    @Override protected BaseRespResult run() {
        log.info(ApiEvent.CHECK_BEFORE,"请求参数{}:", JSONUtils.toJSONString(dto));
        return  wechatService.deleteWechat(Long.parseLong(dto.get("id")+""));
    }
}
