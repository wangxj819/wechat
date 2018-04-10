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
 * 1.0	     Administrator    2017/5/919:04	  Create	
 */
package com.kxjl.brain.police.api;

import com.kxjl.brain.police.service.api.wx.WXService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 初始化方法
 * @Author DengWei
 * @Date 2017/5/11 9:07
 */
public class AppInitMethod {

    @Autowired
    private WXService wxService;

    public void init() {
        /*wxService.startAccessToken();*/
        wxService.reloadWxMenu();
        System.out.println("系统初始化执行.........");
    }


}
