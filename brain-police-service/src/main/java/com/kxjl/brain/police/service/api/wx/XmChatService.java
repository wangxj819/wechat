/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：brain-police
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     Administrator    2017/5/1111:18	  Create	
 */
package com.kxjl.brain.police.service.api.wx;

import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.dto.wx.XmChatDTO;

import java.util.Map;

/**
 * 小曼客服服务端
 */
public interface XmChatService {

    BaseRespResult addXmChatOrder(XmChatDTO xmChatDTO);

    BaseRespResult queryPageXmChat(XmChatDTO xmChatDTO);

    BaseRespResult updateXmChatById(XmChatDTO xmChatDTO);

    BaseRespResult queryDataOverview(XmChatDTO xmChatDTO);

    BaseRespResult queryAlarmingTrend(XmChatDTO xmChatDTO);

    BaseRespResult queryDetail(Map data);
}
