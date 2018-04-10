package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.dto.wx.WxSignDTo;
import com.kxjl.brain.police.repository.bean.WxSignObject;
import com.kxjl.brain.police.service.api.wx.WxSignService;
/**
  *更新标签信息
  *Author:DengWei
  *Date:2017/7/3
  *Time:16:03
 **/
public class UpdateSignContext extends BaseContext<WxSignObject, String>
{

    WxSignService wxSignService;

    public UpdateSignContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static UpdateSignContext build(String businessId, String body, String token, LogModule log) {
        return new UpdateSignContext(businessId, body, token, log);
    }

    public UpdateSignContext setService(WxSignService wxSignService) {
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
    protected WxSignObject parse()
    {
        return JsonUtil.json2Obj(body,WxSignObject.class);
    }

    @Override
    protected BaseRespResult run(){
        wxSignService.updateSignInfo(dto);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}

