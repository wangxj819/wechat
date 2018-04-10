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
import com.kxjl.brain.police.dto.wx.RegistUserDTO;

public interface RegistUserService {

    BaseRespResult insertUser(RegistUserDTO registUserDTO);


    BaseRespResult queryRegistUser(String openid);

    RegistUserDTO getRegistUser(String openid);

}
