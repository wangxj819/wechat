package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.LoginDto;
import com.kxjl.brain.police.service.api.wx.WechatService;
import org.springframework.util.StringUtils;

/**
 * Created by xjwang5 on 2018/2/3.
 */
public class WxLoginContext extends BaseContext<LoginDto, String>
{

    WechatService wechatService;

    public WxLoginContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static WxLoginContext build(String businessId, String body, String token, LogModule log) {
        return new WxLoginContext(businessId, body, token, log);
    }

    public WxLoginContext setService(WechatService wechatService) {
        this.wechatService = wechatService;
        return this;
    }

    @Override
    protected void checkAfter() {
        if (StringUtils.isEmpty(dto)) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_Null_OR_Empty_Exp));
            return;
        }
    }

    @Override
    protected void checkBefore() {
        return;
    }

    @Override
    protected LoginDto parse()
    {
        JSONObject jsonObject = JSONObject.parseObject(body);
        if(jsonObject == null){
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        }else
        {
            LoginDto loginDto = JSONObject.parseObject(jsonObject.toString(), LoginDto.class);
            return loginDto;
        }
    }

    @Override
    protected BaseRespResult run(){
        return wechatService.login(dto);
    }
}

