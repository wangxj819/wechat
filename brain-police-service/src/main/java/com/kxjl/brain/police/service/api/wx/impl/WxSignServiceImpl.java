package com.kxjl.brain.police.service.api.wx.impl;

import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.WxSignDTo;
import com.kxjl.brain.police.repository.bean.WxSignObject;
import com.kxjl.brain.police.repository.mapper.WxSignMapper;
import com.kxjl.brain.police.service.api.wx.WxSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xjwang5 on 2017/5/27.
 */
@Service
public class WxSignServiceImpl implements WxSignService
{

    @Autowired
    private WxSignMapper wxSignMapper;

    @Override
    public BaseRespResult insertSign(WxSignDTo dto)
    {
        wxSignMapper.insertSign(dto);
        return BaseRespResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult deleteSign(String id) {
        wxSignMapper.deleteSign(id);
        return BaseRespResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult querySignInfo(String id) {
        return BaseRespResult.build(BaseRespondCode.OK,wxSignMapper.querySignInfo(id));
    }

    @Override
    public BaseRespResult updateSignInfo(WxSignObject wxSignObject) {
        wxSignMapper.updateSignInfo(wxSignObject);
        return BaseRespResult.build(BaseRespondCode.OK);
    }
}
