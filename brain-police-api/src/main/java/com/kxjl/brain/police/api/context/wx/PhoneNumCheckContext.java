package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.event.ApiEvent;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.HttpWalker;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.common.utils.RandomNum;
import com.kxjl.brain.police.service.api.redis.RedisService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjwang5 on 2017/5/15.
 */
public class PhoneNumCheckContext extends BaseContext<Map, String>
{

        RedisService redisService;

public PhoneNumCheckContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
        }

public static PhoneNumCheckContext build(String businessId, String body, String token, LogModule log) {
        return new PhoneNumCheckContext(businessId, body, token, log);
        }

public PhoneNumCheckContext setService(RedisService redisService) {
        this.redisService = redisService;
        return this;
}

        @Override
        protected void checkAfter() {

        }

        @Override
        protected void checkBefore() {

        }

        @Override
        protected Map parse() {
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
        }

        @Override
        protected BaseRespResult run() {
        /**
        * 请求参数:
        * telNums:接收短信的手机号码(接收参数)
        * content:发送内容(验证码)
        * signName:短信签名(微信报警)
        * templateCode:短信模板编号
        * params:模板动态参数
        */
        /**
        * 动态生成六位数验证码
        */
        String randomnum = RandomNum.getRandNum();

        List list = JsonUtil.json2Obj(dto.get("telNums").toString(), List.class);

        String content = "验证码:"+ randomnum +"，如非本人操作，请忽略此短信。";
        String signName = Constants.SIGNNAME;
        String templateCode = Constants.TEMPLATECODE;
        String url = Constants.SMSURL;

        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,String> param = new HashMap<String,String> ();
        param.put("randomNum",randomnum);
        map.put("telNums",list.toArray());
        map.put("content",content);
        map.put("signName",signName);
        map.put("templateCode",templateCode);
        map.put("params",param);

        log.info(ApiEvent.CHECK_AFTER,"url:{}",url);
        String resultStr =  HttpWalker.visit(url).putBody(JsonUtil.obj2json(map)).post().getResponseStr();
        log.info(ApiEvent.CHECK_AFTER,"resultStr:{}",resultStr);
        /**
         * 判断redis库中键是否存在,如果存在,删除,重新添加
         */
        boolean keyExists = redisService.keyExists(Constants.SMSKEY+list.get(0));
        if(keyExists){
            redisService.delKey(Constants.SMSKEY+list.get(0));
        }
        /**
         * redis三个参数含义:
         * 第一个:key
         * 第二个:value
         * 第三个:过期时间
         */
        redisService.set(Constants.SMSKEY+list.get(0),randomnum,Constants.EXPIRE);

        return BaseRespResult.build(BaseRespondCode.OK);
        }
}

