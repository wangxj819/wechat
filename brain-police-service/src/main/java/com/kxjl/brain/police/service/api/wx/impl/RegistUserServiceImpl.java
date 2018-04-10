package com.kxjl.brain.police.service.api.wx.impl;

import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.arsenal.rest.result.SearchResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.RandomNum;
import com.kxjl.brain.police.dto.wx.RegistUserDTO;
import com.kxjl.brain.police.repository.mapper.RegistUserMapper;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.RegistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/29.
 */
@Service
public class RegistUserServiceImpl  implements RegistUserService{

    @Autowired
    private RegistUserMapper registUserMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public BaseRespResult insertUser(RegistUserDTO registUserDTO) {

//        System.out.println(JsonUtil.obj2json(registUserDTO));

      /*  if(registUserDTO.getCode().equals(redisService.get(registUserDTO.getTelphone()))){
            boolean keyExists = redisService.keyExists(Constants.SMSKEY+registUserDTO.getTelphone());
            if(keyExists){
                redisService.delKey(Constants.SMSKEY+registUserDTO.getTelphone());
            }
            registUserMapper.insertUser(registUserDTO);
            return SearchResult.build(BaseRespondCode.OK);
        }else{
            String error = "{\"message\":\"验证码错误,请重新输入!\"}";
            return BaseRespResult.build(BaseRespondCode.Param_Check_Exp, JSONUtils.parse(error));
        }*/

        registUserMapper.insertUser(registUserDTO);
        return SearchResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult queryRegistUser(String openid) {
        RegistUserDTO registUserDTO = registUserMapper.queryRegistUser(openid);
        if(registUserDTO  == null){
            //30001 openid 不存在
            return SearchResult.build(BaseRespondCode.OAuth_Account_OR_Password_Error);
        }else{
            String randomnum = RandomNum.getRandNum();
            boolean keyExists = redisService.keyExists(Constants.SMSKEY+registUserDTO.getTelphone());
            if(keyExists){
                redisService.delKey(Constants.SMSKEY+registUserDTO.getTelphone());
            }
            /**
             * redis三个参数含义:
             * 第一个:key
             * 第二个:value
             * 第三个:过期时间
             */
            redisService.set(Constants.SMSKEY+registUserDTO.getTelphone(),randomnum,600*3);
            registUserDTO.setCode(randomnum);
            return SearchResult.build(BaseRespondCode.OK,registUserDTO);
        }

    }

    @Override
    public RegistUserDTO getRegistUser(String openid) {
        return registUserMapper.queryRegistUser(openid);
    }
}
