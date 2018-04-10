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
  *删除标签
  *Author:DengWei
  *Date:2017/7/3
  *Time:16:01
 **/
public class DeleteSignContext extends BaseContext<String, String>
{

    WxSignService wxSignService;

    public DeleteSignContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static DeleteSignContext build(String businessId, String body, String token, LogModule log) {
        return new DeleteSignContext(businessId, body, token, log);
    }

    public DeleteSignContext setService(WxSignService wxSignService) {
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
        Map map = JsonUtil.json2Obj(body, Map.class);
        return map.get("id").toString();
    }

    @Override
    protected BaseRespResult run(){
        wxSignService.deleteSign(dto);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}

