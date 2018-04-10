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
 * 1.0	     Administrator    2017/5/1121:23	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.alibaba.druid.support.json.JSONUtils;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.event.ApiEvent;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;
import com.kxjl.brain.police.service.api.wx.WechatService;
/**
 * 微信工单修改
 * @Author DengWei
 * @Date 2017/5/20 9:09
 */
public class WechatUpdateContext extends
        BaseContext<WechatAlarmObject, String> {


    WechatService wechatService;

    protected WechatUpdateContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static WechatUpdateContext build(String businessId, String body, String token, LogModule log) {
        return new WechatUpdateContext(businessId, body, token, log);
    }

    @Override protected WechatAlarmObject parse() {
        return JsonUtil.json2Obj(body,WechatAlarmObject.class);
    }

    public WechatUpdateContext setService(WechatService wechatService) {
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
        return  wechatService.updateWechat(dto);
    }

}
