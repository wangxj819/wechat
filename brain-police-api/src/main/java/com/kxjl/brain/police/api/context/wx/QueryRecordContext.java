package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.service.api.wx.WechatService;

/**
 * Created by xjwang5 on 2017/5/16.
 */
public class QueryRecordContext extends BaseContext<JSONObject, String>{
        WechatService wechatService;

    public QueryRecordContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static QueryRecordContext build(String businessId, String body, String token, LogModule log) {
        return new QueryRecordContext(businessId, body, token, log);
    }

    public QueryRecordContext setService(WechatService wechatService) {
        this.wechatService = wechatService;
        return this;
    }

    @Override
    protected void checkAfter() {

     }

    @Override
    protected void checkBefore() {

    }

    @Override
    protected JSONObject parse() {
            JSONObject jsonObject = JSONObject.parseObject(body);
            return jsonObject;
     }

    @Override
    protected BaseRespResult run() {
        return wechatService.queryById(Long.parseLong(dto.get("id").toString()));
    }
}

