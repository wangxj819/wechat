package com.kxjl.brain.police.service.api.wx;

import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.WxSignDTo;
import com.kxjl.brain.police.repository.bean.WxSignObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xjwang5 on 2017/5/27.
 */
public interface WxSignService
{

    BaseRespResult insertSign(WxSignDTo dto);

    BaseRespResult deleteSign( String id);

    BaseRespResult querySignInfo(String id);

    BaseRespResult updateSignInfo(WxSignObject wxSignObject);

}
