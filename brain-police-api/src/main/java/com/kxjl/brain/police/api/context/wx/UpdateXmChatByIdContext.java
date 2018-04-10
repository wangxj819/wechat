package com.kxjl.brain.police.api.context.wx;

import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.dto.wx.XmChatDTO;
import com.kxjl.brain.police.service.api.wx.XmChatService;

/**
 * 更新工单状态
 * Created by DengWei on 2017-11-07.
 */
public class UpdateXmChatByIdContext extends BaseContext<XmChatDTO, String>
{

    XmChatService xmChatService;

    public UpdateXmChatByIdContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }

    public static UpdateXmChatByIdContext build(String businessId, String body, String token, LogModule log) {
        return new UpdateXmChatByIdContext(businessId, body, token, log);
    }

    public UpdateXmChatByIdContext setService(XmChatService xmChatService) {
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
        return BaseRespResult.build(BaseRespondCode.OK,xmChatService.updateXmChatById(dto));
    }
}

