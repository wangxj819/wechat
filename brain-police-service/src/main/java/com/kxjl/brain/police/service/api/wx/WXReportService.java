package com.kxjl.brain.police.service.api.wx;

import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.repository.bean.WXReportObject;

/**
 * Created by liyu on 2017/5/17.
 */
public interface WXReportService {
    BaseRespResult queryByList(WXReportObject wo);
    BaseRespResult insertReport(WXReportObject wo);
    BaseRespResult updateReport(WXReportObject wo);
    BaseRespResult deleteReport(Long id,Long wechatId);
    BaseRespResult queryReportBYId(Long id);
}
