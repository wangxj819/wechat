package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.RegistUserDTO;
import com.kxjl.brain.police.service.api.wx.RegistUserService;

/**
 * 微信注册用户
 * dengwei
 */
public class RegistUserContext extends BaseContext<RegistUserDTO, String>
{
    private RegistUserService registUserService;


    public RegistUserContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static RegistUserContext build(String businessId, String body, String token, LogModule log) {
        return new RegistUserContext(businessId, body, token, log);
    }

    public RegistUserContext setService(RegistUserService registUserService) {
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
    protected RegistUserDTO parse(){
        RegistUserDTO registUserDTO = JSONObject.parseObject(body, RegistUserDTO.class);
        return registUserDTO;
    }

    @Override
    protected BaseRespResult run(){
        return registUserService.insertUser(dto);
    }
}

