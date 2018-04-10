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
 * 1.0	     Administrator    2017/5/1111:12	  Create	
 */
package com.kxjl.brain.police.repository.mapper;

import com.kxjl.brain.police.dto.wx.LoginDto;
import com.kxjl.brain.police.dto.wx.WechatAlarmDTO;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;
import com.kxjl.brain.police.repository.bean.report.WechatCountDim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信Dao
 * @Author DengWei
 * @Date 2017/5/11 11:14
 */
public interface WechatMapper {

    List<WechatAlarmDTO> queryByList(WechatAlarmObject model);

    Long queryCountByStatus(@Param("status") String status);

    WechatAlarmDTO queryById(@Param("id") Long id);

    void insertWechat(WechatAlarmObject model);

    void updateWechat(WechatAlarmObject model);

    void deleteWechat(@Param("id") Long id);

    List<LoginDto> login(LoginDto dto);

    List<WechatCountDim> report(@Param("startTime") String startTime,
                                @Param("endTime") String endTime,
                                @Param("type") String type);

    List<WechatAlarmObject> queryDetail(@Param("id") Long id);
}
