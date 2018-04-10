package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.WxSignDTo;
import com.kxjl.brain.police.service.api.wx.WxSignService;

/**
 * Created by xjwang5 on 2017/5/27.
 */
public class AddSignContext extends BaseContext<WxSignDTo, String>
{

    WxSignService wxSignService;

    public AddSignContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static AddSignContext build(String businessId, String body, String token, LogModule log) {
        return new AddSignContext(businessId, body, token, log);
    }

    public AddSignContext setService(WxSignService wxSignService) {
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
    protected WxSignDTo parse()
    {
        JSONObject jsonObject = JSONObject.parseObject(body);
        if(jsonObject == null){
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        }else
        {
            WxSignDTo wxSignDTo = JSONObject.parseObject(jsonObject.toString(), WxSignDTo.class);
            return wxSignDTo;
        }
    }

    @Override
    protected BaseRespResult run(){
        wxSignService.insertSign(dto);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}

