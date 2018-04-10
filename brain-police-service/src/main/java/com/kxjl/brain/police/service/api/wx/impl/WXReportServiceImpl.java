package com.kxjl.brain.police.service.api.wx.impl;

import com.github.pagehelper.PageHelper;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.arsenal.rest.result.SearchResult;
import com.kxjl.brain.police.common.utils.GridPageUtil;
import com.kxjl.brain.police.repository.bean.WXReportObject;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;
import com.kxjl.brain.police.repository.mapper.WXReportMapper;
import com.kxjl.brain.police.repository.mapper.WechatMapper;
import com.kxjl.brain.police.service.api.wx.WXReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**微信报表单service
 * @Author liyu
 * @Date 2017/5/17 15:34
 */
@Service
public class WXReportServiceImpl implements WXReportService {
    @Autowired
    WXReportMapper wm;
    @Autowired
    WechatMapper wem;
    @Override
    public BaseRespResult queryByList(WXReportObject wo) {
        PageHelper.startPage(wo.getPageIndex(),wo.getPageSize());
        return BaseRespResult.build(BaseRespondCode.OK, GridPageUtil.createPageInfo(wm.queryInfo(wo)));
    }

    @Override
    public BaseRespResult insertReport(WXReportObject wo) {
        if(wm.insertInfo(wo)>0){
            WechatAlarmObject wao=new WechatAlarmObject();
            wao.setId(wo.getWechatId());
            String status="";
            if(wo.getCallCategory().equals("真报")){
               status="01";
            }else if(wo.getCallCategory().equals("假报")){
                status="02";
            }
            wao.setStatus(status);
            wem.updateWechat(wao);
        }
        return SearchResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult updateReport(WXReportObject wo) {
        wm.updateInfo(wo);
        return SearchResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult deleteReport(Long id,Long wechatId) {
        wem.deleteWechat(wechatId);
        wm.deleteInfo(id);
        return SearchResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult queryReportBYId(Long id) {
        return BaseRespResult.build(BaseRespondCode.OK,wm.queryById(id));
    }
}
