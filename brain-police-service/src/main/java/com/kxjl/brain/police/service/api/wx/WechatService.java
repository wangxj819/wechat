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
import com.kxjl.brain.police.dto.wx.LoginDto;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;

import java.util.Map;

public interface WechatService {

    BaseRespResult queryByList(WechatAlarmObject model);

    BaseRespResult queryCountByStatus();

    BaseRespResult queryById( Long id);

    BaseRespResult insertWechat(WechatAlarmObject model);

    BaseRespResult updateWechat(WechatAlarmObject model);

    BaseRespResult deleteWechat(Long id);

    BaseRespResult login(LoginDto param);

    BaseRespResult report(Map param);

    BaseRespResult queryDetail(Map data);
}
