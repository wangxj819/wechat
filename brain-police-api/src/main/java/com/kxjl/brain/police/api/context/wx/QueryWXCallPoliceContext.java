package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.QueryWXCallPoliceDTO;
import com.kxjl.brain.police.repository.bean.WXCallPoliceInfo;
import com.kxjl.brain.police.service.api.wx.WXService;

/**
 * Created by xxqin on 2017/5/8.
 */
public class QueryWXCallPoliceContext extends BaseContext<QueryWXCallPoliceDTO, String> {
    WXService wxService;

    public QueryWXCallPoliceContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static QueryWXCallPoliceContext build(String businessId, String body, String token, LogModule log) {
        return new QueryWXCallPoliceContext(businessId, body, token, log);
    }

    public QueryWXCallPoliceContext setService(WXService wxService) {
        this.wxService = wxService;
        return this;
    }

    @Override
    protected void checkAfter() {

    }

    @Override
    protected void checkBefore() {

    }

    @Override
    protected QueryWXCallPoliceDTO parse() {
        JSONObject jsonObject = JSONObject.parseObject(body);
        if (jsonObject == null) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_Check_Exp));
            return null;
        } else {
            QueryWXCallPoliceDTO queryWXCallPoliceDTO = JSONObject.parseObject(jsonObject.toString(), QueryWXCallPoliceDTO.class);
            queryWXCallPoliceDTO.setBusinessId(Long.parseLong(businessId));
            return queryWXCallPoliceDTO;
        }
    }

    @Override
    protected BaseRespResult run() {
        return wxService.queryWXCallPolice(dto);
    }
}
