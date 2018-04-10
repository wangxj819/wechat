package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.im.SendImSms;
import com.kxjl.brain.police.common.logs.event.BrainApiEvent;
import com.kxjl.brain.police.common.logs.model.WxApiLog;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by xjwang5 on 2018/2/10.
 */
public class EndChatContext extends BaseContext<Map, String>
{
    private RedisService redisService;

    private HttpServletResponse response;

    private HttpServletRequest request;

    private WxUtilsService wxUtilsService;

      private static LogModule log = WxApiLog.log();

    private EndChatContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static EndChatContext build(String businessId,String body, String token, LogModule log){
        return new EndChatContext(businessId,body,token,log);
    }

    public EndChatContext setService(RedisService redisService){
        this.redisService = redisService;
        return this;
    }

    public EndChatContext setService(HttpServletResponse response){
        this.response = response;
        return this;
    }

    public EndChatContext setService(HttpServletRequest request){
        this.request = request;
        return this;
    }

    public EndChatContext setService(WxUtilsService wxUtilsService){
        this.wxUtilsService = wxUtilsService;
        return this;
    }



    @Override
    protected void checkBefore() {
        return;
    }

    @Override
    protected Map parse() {
        JSONObject jsonObject = parseObject(body);
        if(jsonObject == null){
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        }else{
            Map map = parseObject(jsonObject.toString(), Map.class);
            return map;
        }
    }

    @Override
    protected void checkAfter() {
        if (StringUtils.isEmpty(dto)) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_Null_OR_Empty_Exp));
            return;
        }
    }

    @Override
    protected BaseRespResult run() {
        String openid = dto.get("openid").toString();
        log.info(BrainApiEvent.INIT,"用户openid"+openid);
        if (redisService.keyExists(openid)){
            //通知微信端下线
            SendImSms.sendLogoutMsg(openid);
            redisService.delKey(openid);
        }
        //boolean push = wxUtilsService.wxSendMsg(openid, Constants.END_WECHAT);
        //log.info(BrainApiEvent.INIT,"推送结果:"+push);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}

