/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：brain-police
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     Administrator    2017/5/1118:47	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.dto.wx.RegistUserDTO;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.RegistUserService;
import com.kxjl.brain.police.service.api.wx.WechatService;

import java.util.Date;

/**
 * 保存提交数据
 * @Author DengWei
 * @Date 2017/5/11 18:50
 */
public class WechatDoSubmitContext extends BaseContext<WechatAlarmObject, String> {

    private  WechatService wechatService;

    private RedisService redisService;

    private RegistUserService registUserService;

    protected WechatDoSubmitContext(String businessId, String body,
            String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static WechatDoSubmitContext build(String businessId, String body, String token, LogModule log) {
        return new WechatDoSubmitContext(businessId, body, token, log);
    }

    public WechatDoSubmitContext setService(WechatService wechatService) {
        this.wechatService = wechatService;
        return this;
    }

    public WechatDoSubmitContext setService(RedisService redisService) {
        this.redisService = redisService;
        return this;
    }
    public WechatDoSubmitContext setService(RegistUserService registUserService) {
        this.registUserService = registUserService;
        return this;
    }

    @Override
    protected void checkAfter() {

    }

    @Override
    protected void checkBefore() {

        super.checkBefore();
    }


    @Override
    protected WechatAlarmObject parse() {
        JSONObject jsonObject = JSONObject.parseObject(body);
        if (jsonObject == null){
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        }
        else{
            WechatAlarmObject wechatAlarmObject = JSONObject.parseObject(jsonObject.toString(),WechatAlarmObject.class);
            wechatAlarmObject.setAlarmdate(new Date());
            wechatAlarmObject.setStatus("00");//00 保存本地系统   01、提交到DS
            wechatAlarmObject.setWechatnum("wx"+ System.currentTimeMillis());
            return wechatAlarmObject;
        }
    }

    @Override
    protected BaseRespResult run() {

        String telephone = Constants.SMSKEY+dto.getTelephone();
        String identifycode = dto.getIdentifycode();


        //if(identifycode.equals(redisService.get(telephone))){
        //    boolean keyExists = redisService.keyExists(Constants.SMSKEY+dto.getTelephone());
        //    if(keyExists){
        //        redisService.delKey(Constants.SMSKEY+dto.getTelephone());
        //    }
            RegistUserDTO registUserDTO = registUserService.getRegistUser(dto.getOpenid());
            if(registUserDTO == null){
                registUserDTO = new RegistUserDTO();
                registUserDTO.setTelphone(dto.getTelephone());
                registUserDTO.setOpenid(dto.getOpenid());
                registUserDTO.setIdcard(dto.getIdcode());
                registUserDTO.setName(dto.getName());
                registUserDTO.setSex(dto.getSex());
                registUserService.insertUser(registUserDTO);
            }
            return wechatService.insertWechat(dto);
        //}else{
        //    String error = "{\"message\":\"验证码错误,请重新输入!\"}";
        //    return BaseRespResult.build(BaseRespondCode.Param_Check_Exp, JSONUtils.parse(error));
        //}
    }
}
