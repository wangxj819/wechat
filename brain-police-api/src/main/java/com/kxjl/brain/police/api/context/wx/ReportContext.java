package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.service.api.wx.WechatService;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by xjwang5 on 2018/2/5.
 */
public class ReportContext extends BaseContext<Map, String>
{
    private WechatService wechatService;

    private ReportContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static ReportContext build(String businessId,String body, String token, LogModule log){
        return new ReportContext(businessId,body,token,log);
    }

    public ReportContext setService(WechatService wechatService){
        this.wechatService = wechatService;
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

        return wechatService.report(dto);
    }
}

