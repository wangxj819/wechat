package com.kxjl.brain.police.api.context.wx;

import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xjwang5 on 2017/5/16.
 */
public class WechatFormContext extends BaseContext<Map, String> {
    protected WechatFormContext(String businessId,String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }
    public static WechatFormContext build(String businessId, String body, String token, LogModule log){
        return new WechatFormContext(businessId,body, token, log);
    }
    @Override
    protected void checkAfter() {

    }

    @Override
    protected BaseRespResult run() {
        List<JSONObject> jsons=new ArrayList<>();
        String json="{\"telType\":[\"真报\",\"谎报\"],\"city\":[{\"合肥市\":\"hf\"},{\"六安市\":\"la\"},{\"亳州市\":\"bz\"}],\"parstation\":[{\"hf\":[\"分局\",\"派出所\",\"支队\",\"拘留所\"]},{\"la\":[\"派出所\",\"支队\",\"拘留所\"]},{\"bz\":[\"派出所\",\"支队\"]}],\"midstation\":[{\"分局\":\"fj\"},{\"派出所\":\"pcs\"},{\"支队\":\"zd\"},{\"拘留所\":\"jls\"}],\"substation\":[{\"fj\":[\"瑶海分局\",\"蜀山分局\"]},{\"pcs\":[\"望湖派出所\",\"三里庵派出所\"]},{\"zd\":[\"刑警支队\",\"交警支队\"]},{\"jls\":[\"女子拘留所\",\"合肥市看守所\"]}],\"area\":[\"三里庵\",\"望湖城\",\"芜湖路\",\"骆岗\",\"中山路\",\"望江西路\"],\"category\":[\"微信报警\",\"电话报警\"],\"type\":[\"偷窃\",\"抢劫\",\"杀人\",\"斗殴\"],\"detail\":[\"细类一\",\"细类二\",\"细类三\",\"细类四\",\"细类五\"]}";
        jsons.add(JSONObject.fromObject(json));
        return BaseRespResult.build(BaseRespondCode.OK, jsons);
    }
}
