package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.logs.event.BrainApiEvent;
import com.kxjl.brain.police.common.logs.model.WxApiLog;
import com.kxjl.brain.police.common.utils.HttpWalker;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.WXService;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by xjwang5 on 2018/2/7.
 */
public class PushWechatContext extends BaseContext<Map, String>
{
    private WxUtilsService wxUtilsService;

    private WXService wxService;

    private RedisService redisService;

    private static LogModule log = WxApiLog.log();

    private PushWechatContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static PushWechatContext build(String businessId,String body, String token, LogModule log){
        return new PushWechatContext(businessId,body,token,log);
    }

    public PushWechatContext setService(WxUtilsService wxUtilsService){
        this.wxUtilsService = wxUtilsService;
        return this;
    }

    public PushWechatContext setService(WXService wxService){
        this.wxService = wxService;
        return this;
    }

    public PushWechatContext setService(RedisService redisService){
        this.redisService = redisService;
        return this;
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
        //Map<String,Object> map = CodeUtil.generateCodeAndPic();
        //String code = map.get("code").toString();
        String openid = dto.get("openid").toString();
        String casenum = dto.get("casenum").toString();
        String account = dto.get("account").toString();
        String longlink =Constants.APP_SITE_HOST+Constants.CONTEXT_PATH_URL+Constants.APP_SITE_HOST+Constants.IM_CHAT;
        log.info(BrainApiEvent.INIT,"发起的长连接"+longlink);
        String accesstoken = wxService.getAccessToken();
        String posturl = Constants.LONG_SHORT + accesstoken;
        Map link = new HashMap();
        //link.put("access_token",accesstoken);
        link.put("action","long2short");
        link.put("long_url",longlink);
        //String long2short = HttpWalker.visit(posturl).addParams(link).post().getResponseStr();
        String long2short = HttpWalker.visit(posturl).putBody(JSON.toJSONString(link)).post().getResponseStr();
        log.info(BrainApiEvent.INIT,"长连接转短连接"+long2short);
        /**
         * 微信官方返回数据格式
         * {"errcode":0,"errmsg":"ok","short_url":"http:\/\/w.url.cn\/s\/AvCo6Ih"}
         */
        Map shortlink = JSONObject.parseObject(long2short,Map.class);
        String msg = "报案信息不够详细,马上将由接警员发起主动聊天,请点击收到的链接,进入聊天界面:"+shortlink.get("short_url").toString();
        log.info(BrainApiEvent.INIT,"推送的短连接"+msg);
        Map cache = new HashMap();
        //cache.put("code",code);
        cache.put("account",account);
        cache.put("casenum",casenum);
        redisService.set(openid, JSON.toJSONString(cache));
        boolean push = wxUtilsService.wxSendMsg(openid,msg);
        log.info(BrainApiEvent.INIT,"推送结果:"+push);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}


