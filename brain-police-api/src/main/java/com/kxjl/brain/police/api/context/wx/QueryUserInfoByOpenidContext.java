package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;

import java.util.Map;

/**
 * 根据openid查询微信用户信息
 * dengwei
 */
public class QueryUserInfoByOpenidContext extends BaseContext<Map, String>
{
    private WxUtilsService wxUtilsService;


    public QueryUserInfoByOpenidContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static QueryUserInfoByOpenidContext build(String businessId, String body, String token, LogModule log) {
        return new QueryUserInfoByOpenidContext(businessId, body, token, log);
    }

    public QueryUserInfoByOpenidContext setService(WxUtilsService wxUtilsService) {
        this.wxUtilsService = wxUtilsService;
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
        return BaseRespResult.build(BaseRespondCode.OK,wxUtilsService.getUserInfoByOpenId(dto.get("openid").toString()));
    }
}

