package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.dto.wx.XmChatDTO;
import com.kxjl.brain.police.service.api.wx.XmChatService;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据总览
 * Created by DengWei on 2017-11-07.
 */
public class DataOverviewContext extends BaseContext<XmChatDTO, String>
{

    XmChatService xmChatService;

    public DataOverviewContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static DataOverviewContext build(String businessId, String body, String token, LogModule log) {
        return new DataOverviewContext(businessId, body, token, log);
    }

    public DataOverviewContext setService(XmChatService xmChatService) {
        this.xmChatService = xmChatService;
        return this;
    }

    @Override
    protected void checkAfter() {

    }

    @Override
    protected void checkBefore() {

    }

    @Override
    protected XmChatDTO parse()
    {
        return JsonUtil.json2Obj(body,XmChatDTO.class);
    }

    @Override
    protected BaseRespResult run(){
        JSONObject result = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
        String startDateTime = dto.getStartDateTime();
        String endDateTime = dto.getEndDateTime();

        if(StringUtils.isBlank(startDateTime)){
            startDateTime = sdfD.format(new Date())+" 00:00:00";
            dto.setStartDateTime(startDateTime);
        }

        if(StringUtils.isBlank(endDateTime)){
            endDateTime = sdf.format(new Date());
            dto.setEndDateTime(endDateTime);
        }
        System.out.println(startDateTime+"---"+endDateTime);
        JSONObject jsonObject = JSON.parseObject(xmChatService.queryDataOverview(dto)+"");
        int number = 0;
        if(jsonObject != null){
            JSONObject jsonObjectResult = (JSONObject) JSON.parse(jsonObject.get("result").toString());
            JSONArray jsonArray = JSON.parseArray(jsonObjectResult.get("rows").toString());
            number = jsonArray.size();
        }
        result.put("number",number);
        return BaseRespResult.build(BaseRespondCode.OK,result);
    }

}

