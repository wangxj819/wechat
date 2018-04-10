package com.kxjl.brain.police.api.context.wx;

import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.repository.bean.WXReportObject;
import com.kxjl.brain.police.service.api.wx.WXReportService;

import java.util.Date;

import static com.kxjl.brain.police.common.utils.JsonUtil.json2Obj;

/**添加报表单
 * @Author liyu
 * @Date 2017/5/17 15:37
 */
public class WXReportInsertContext extends BaseContext<WXReportObject,String> {
    WXReportService ws;
    protected WXReportInsertContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }
    public static WXReportInsertContext build(String businessId, String body, String token, LogModule log){
        return new WXReportInsertContext(businessId, body, token, log);
    }
    @Override protected WXReportObject parse(){
        WXReportObject wo=json2Obj(body,WXReportObject.class);
        wo.setCreateTime(new Date());
        return wo;
    }
    public WXReportInsertContext setService(WXReportService ws){
        this.ws=ws;
        return this;
    }
    @Override
    protected void checkAfter() {

    }
    @Override protected void checkBefore() {
        super.checkBefore();
    }
    @Override
    protected BaseRespResult run() {
        return ws.insertReport(dto);
    }
}
