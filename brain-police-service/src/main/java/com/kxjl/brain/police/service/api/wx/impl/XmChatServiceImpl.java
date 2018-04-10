package com.kxjl.brain.police.service.api.wx.impl;

import com.github.pagehelper.PageHelper;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.utils.GridPageUtil;
import com.kxjl.brain.police.dto.wx.XmChatDTO;
import com.kxjl.brain.police.repository.bean.XmChatObject;
import com.kxjl.brain.police.repository.mapper.XmChatMapper;
import com.kxjl.brain.police.service.api.wx.XmChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 小曼客服服务端
 * Created by DengWei on 2017/8/29.
 */
@Service
public class XmChatServiceImpl implements XmChatService{

    @Autowired
    private XmChatMapper xmChatMapper;

    @Override
    public BaseRespResult addXmChatOrder(XmChatDTO xmChatDTO) {
        return BaseRespResult.build(BaseRespondCode.OK,xmChatMapper.addXmChatOrder(xmChatDTO));
    }

    @Override
    public BaseRespResult queryPageXmChat(XmChatDTO xmChatDTO) {
        PageHelper.startPage(xmChatDTO.getPageIndex(), xmChatDTO.getPageSize());
        List<XmChatObject> list = xmChatMapper.queryPageXmChat(xmChatDTO);
        return BaseRespResult.build(BaseRespondCode.OK, GridPageUtil.createPageInfo(list));
    }

    @Override
    public BaseRespResult updateXmChatById(XmChatDTO xmChatDTO) {
        return BaseRespResult.build(BaseRespondCode.OK,xmChatMapper.updateXmChatById(xmChatDTO));
    }

    @Override
    public BaseRespResult queryDataOverview(XmChatDTO xmChatDTO) {
        return BaseRespResult.build(BaseRespondCode.OK,xmChatMapper.queryDataOverview(xmChatDTO));
    }

    @Override
    public BaseRespResult queryAlarmingTrend(XmChatDTO xmChatDTO) {
        return BaseRespResult.build(BaseRespondCode.OK,xmChatMapper.queryAlarmingTrend(xmChatDTO));
    }

    @Override
    public BaseRespResult queryDetail(Map data)
    {
        Long id = Long.parseLong(data.get("id").toString());
        List<XmChatObject> detailList = xmChatMapper.queryDetail(id);
        return BaseRespResult.build(BaseRespondCode.OK,detailList);
    }

}
