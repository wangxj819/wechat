package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.service.api.wx.RegistUserService;

import java.util.Map;

/**
 * 微信注册用户
 * dengwei
 */
public class RegistUserVerifyOpenidContext extends BaseContext<Map, String>
{
    private RegistUserService registUserService;


    public RegistUserVerifyOpenidContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static RegistUserVerifyOpenidContext build(String businessId, String body, String token, LogModule log) {
        return new RegistUserVerifyOpenidContext(businessId, body, token, log);
    }

    public RegistUserVerifyOpenidContext setService(RegistUserService registUserService) {
        this.registUserService = registUserService;
        return this;
    }

    @Override
    protected void checkAfter() {

    }

    @Override
    protected void checkBefore() {

    }

    @Override
    protected Map parse(){
        Map map = JSONObject.parseObject(body, Map.class);
        return map;
    }

    @Override
    protected BaseRespResult run(){
        return registUserService.queryRegistUser(dto.get("openid").toString());
    }
}

