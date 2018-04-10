package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.logs.event.BrainApiEvent;
import com.kxjl.brain.police.dto.wx.XmChatDTO;
import com.kxjl.brain.police.service.api.wx.XmChatService;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 小曼客服添加
 * Created by DengWei on 2017-11-07.
 */
public class AddXmChatContext extends BaseContext<XmChatDTO, String>
{

    XmChatService xmChatService;

    public AddXmChatContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static AddXmChatContext build(String businessId, String body, String token, LogModule log) {
        return new AddXmChatContext(businessId, body, token, log);
    }

    public AddXmChatContext setService(XmChatService xmChatService) {
        this.xmChatService = xmChatService;
        return this;
    }

    @Override
    protected void checkBefore() {
        return;
    }

    @Override
    protected void checkAfter()
    {
        if (StringUtils.isEmpty(dto)) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_Null_OR_Empty_Exp));
            return;
        }
    }

    @Override
    protected XmChatDTO parse(){
        JSONObject jo = JSONObject.parseObject(body);
        if (jo == null){
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        }
        else{
            XmChatDTO xmChatDTO = JSONObject.parseObject(jo.toString(), XmChatDTO.class);
            //System.out.println("请求数据:"+cameraList);
            return xmChatDTO;
        }
    }

    /*@Override
    protected XmChatDTO parse()
    {
        XmChatDTO xmChatDTO = JsonUtil.json2Obj(body,XmChatDTO.class);
        xmChatDTO.setStatus("01");
        xmChatDTO.setImno("wxim"+System.currentTimeMillis());
        xmChatDTO.setAlarmdate(new Date());
        return xmChatDTO;
    }*/

    @Override
    protected BaseRespResult run(){
        log.info(BrainApiEvent.CREATE,"保存数据:"+ JSON.toJSONString(dto));
        dto.setStatus("01");
        dto.setImno("wxim"+System.currentTimeMillis());
        dto.setAlarmdate(new Date());
        xmChatService.addXmChatOrder(dto);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}

