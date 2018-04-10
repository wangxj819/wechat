package com.kxjl.brain.police.repository.mapper;

import com.kxjl.brain.police.dto.wx.XmChatDTO;
import com.kxjl.brain.police.repository.bean.XmChatObject;
import com.kxjl.brain.police.repository.bean.report.WechatCountDim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小曼服务端对接
 * Created by DengWei on  2017-110-07
 */

public interface XmChatMapper {

    Integer addXmChatOrder(XmChatDTO xmChatDTO);

    List<XmChatObject> queryPageXmChat(XmChatDTO xmChatDTO);

    Integer updateXmChatById(XmChatDTO xmChatDTO);

    //数据总览
    List<XmChatDTO> queryDataOverview(XmChatDTO xmChatDTO);

    //报警趋势
    List<XmChatDTO> queryAlarmingTrend(XmChatDTO xmChatDTO);

    List<WechatCountDim> report(@Param("startTime") String startTime,
                                @Param("endTime") String endTime,
                                @Param("type") String type);

    List<XmChatObject> queryDetail(@Param("id") Long id);
}
