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
 * 1.0	     Administrator    2017/5/2515:11	  Create	
 */
package com.kxjl.brain.police.service.api.wx;

import com.kxjl.brain.police.dto.wx.WeChatDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* 微信相关工具类
*
* @author weideng
* @date 2018/2/7 11:57
*/
public interface WxUtilsService {

    /**
     * 微信消息分发
     * @param request
     * @param response
     * @param xmlStr
     */
    void wxHandle(HttpServletRequest request, HttpServletResponse response,String xmlStr);

    /**
     *微信授权
     * @param request
     * @param response
     */
    void wxAuthor(HttpServletRequest request, HttpServletResponse response);

    /**
     *根据Openid发送消息
     * @param openid
     * @param msg
     * @return
     */
    boolean wxSendMsg(String openid,String msg);

    /**
     * 根据Openid获取用户信息
     * @param openid
     * @return
     */
    WeChatDTO getUserInfoByOpenId(String openid);

}
