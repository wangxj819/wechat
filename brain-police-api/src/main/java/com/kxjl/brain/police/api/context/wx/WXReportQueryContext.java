package com.kxjl.brain.police.api.context.wx;

import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.repository.bean.WXReportObject;
import com.kxjl.brain.police.service.api.wx.WXReportService;

import static com.kxjl.brain.police.common.utils.JsonUtil.json2Obj;

/**查询报表单
 * @Author liyu
 * @Date 2017/5/17 15:38
 */
public class WXReportQueryContext extends BaseContext<WXReportObject,String> {
    WXReportService ws;
    protected WXReportQueryContext(String businessId, String body, String token, LogModule log) {
        super(businessId, body, token, log);
    }
    public static WXReportQueryContext build(String businessId, String body, String token, LogModule log){
        return new WXReportQueryContext(businessId, body, token, log);
    }
    @Override protected WXReportObject parse(){
        WXReportObject wo=json2Obj(body,WXReportObject.class);
        return wo;
    }
    public WXReportQueryContext setService(WXReportService ws){
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
        return ws.queryByList(dto);
    }
}
