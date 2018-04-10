package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.im.SendImSms;
import com.kxjl.brain.police.common.logs.event.BrainApiEvent;
import com.kxjl.brain.police.common.logs.model.WxApiLog;
import com.kxjl.brain.police.dto.wx.WeChatDTO;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjwang5 on 2018/2/7.
 */
public class ImChatContext extends BaseContext<Map, String>
{
    private HttpServletRequest request;

    private HttpServletResponse response;

    private RedisService redisService;

    private WxUtilsService wxUtilsService;

    private static LogModule log = WxApiLog.log();

    private ImChatContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static ImChatContext build(String businessId,String body, String token, LogModule log){
        return new ImChatContext(businessId,body,token,log);
    }

    public ImChatContext setService(RedisService redisService){
        this.redisService = redisService;
        return this;
    }

    public ImChatContext setService(HttpServletRequest request){
        this.request = request;
        return this;
    }

    public ImChatContext setService(HttpServletResponse response){
        this.response = response;
        return this;
    }

    public ImChatContext setService(WxUtilsService wxUtilsService){
        this.wxUtilsService = wxUtilsService;
        return this;
    }



    @Override
    protected void checkBefore() {
        return;
    }

    @Override
    protected Map parse() {
        Map map = new HashMap();
        //map.put("code",request.getParameter("code"));
        map.put("openid",request.getParameter("openid"));
        return map;
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
        //String code = dto.get("code").toString();
        String openid = dto.get("openid").toString();
        if (redisService.keyExists(openid)){
            String msg = redisService.get(openid);
            Map check = JSONObject.parseObject(msg,Map.class);
            String casenum = check.get("casenum").toString();
            String account = check.get("account").toString();
            WeChatDTO  weChatDTO = wxUtilsService.getUserInfoByOpenId(openid);
            String nickName = "";
            if(weChatDTO != null){
                nickName = weChatDTO.getNickname();
            }
            //通知座席端
            SendImSms.sendMsg(account,openid,nickName);
           //通知客户
            try
            {
                log.info(BrainApiEvent.INIT,"转发进入了");
                response.sendRedirect(Constants.USER_CHAT+"?openid="+openid+"&account="+account);
                log.info(BrainApiEvent.INIT,"转发执行了");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }else {
            try
            {
                response.sendRedirect(Constants.END_WECHAT);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}

