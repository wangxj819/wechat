package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.service.api.wx.XmChatService;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by xjwang5 on 2018/3/11.
 */
public class ImDetailContext extends BaseContext<Map, String>
{
    XmChatService xmChatService;

    protected ImDetailContext(String businessId, String body, String token, LogModule log){
        super(businessId, body, token, log);
    }

    public static ImDetailContext build(String businessId, String json, String token, LogModule log){
        return new ImDetailContext(businessId, json, token, log);
    }

    public ImDetailContext setService(XmChatService xmChatService){
        this.xmChatService = xmChatService;
        return this;
    }

    @Override
    protected void checkBefore(){
        return;
    }

    @Override
    protected void checkAfter(){
        if (StringUtils.isEmpty(dto)) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_Null_OR_Empty_Exp));
            return;
        }
    }

    @Override
    protected Map parse(){

        JSONObject jsonObject = JSONObject.parseObject(body);

        if (jsonObject == null){
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        }
        else{
            Map map = JSONObject.parseObject(jsonObject.toString(), Map.class);
            return map;
        }
    }

    @Override
    protected BaseRespResult run()
    {
        return xmChatService.queryDetail(dto);
    }
}

