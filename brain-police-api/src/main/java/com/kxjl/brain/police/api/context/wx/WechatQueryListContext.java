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
 * 1.0	     Administrator    2017/5/119:15	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;
import com.kxjl.brain.police.service.api.wx.WechatService;

import static com.kxjl.brain.police.common.utils.JsonUtil.json2Obj;

/**
 * 微信上表单上传
 * @Author DengWei
 * @Date 2017/5/11 9:15
 */
public class WechatQueryListContext extends BaseContext<WechatAlarmObject, String> {

    WechatService wechatService;

    protected WechatQueryListContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static WechatQueryListContext build(String businessId, String body, String token, LogModule log) {
        return new WechatQueryListContext(businessId, body, token, log);
    }

    @Override protected WechatAlarmObject parse() {
        WechatAlarmObject wechatAlarmObject  = json2Obj(body,WechatAlarmObject.class);
        return wechatAlarmObject;
    }

    public WechatQueryListContext setService(WechatService wechatService) {
        this.wechatService = wechatService;
        return this;
    }

    @Override protected void checkAfter() {

    }

    @Override protected void checkBefore() {
        super.checkBefore();
    }

    @Override protected BaseRespResult run() {
        return  wechatService.queryByList(dto);
    }

}
