package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.dto.wx.WxSignDTo;
import com.kxjl.brain.police.service.api.wx.WxSignService;

import java.util.Map;

/**
   *查询标签信息
   *Author:DengWei
   *Date:2017/7/3
   *Time:16:02
  **/
public class QuerySignInfoContext extends BaseContext<String, String>
{

    WxSignService wxSignService;

    public QuerySignInfoContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static QuerySignInfoContext build(String businessId, String body, String token, LogModule log) {
        return new QuerySignInfoContext(businessId, body, token, log);
    }

    public QuerySignInfoContext setService(WxSignService wxSignService) {
        this.wxSignService = wxSignService;
        return this;
    }

    @Override
    protected void checkAfter() {

    }

    @Override
    protected void checkBefore() {

    }

    @Override
    protected String parse(){
        Map map= JsonUtil.json2Obj(body,Map.class);
        if(map == null){
            return null;
        }else{
            return map.get("id").toString();
        }
    }

    @Override
    protected BaseRespResult run(){
        return wxSignService.querySignInfo(dto);
    }
}

