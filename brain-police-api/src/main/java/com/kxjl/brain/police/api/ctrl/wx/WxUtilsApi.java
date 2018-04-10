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
 * 1.0	     Administrator    2017/5/2515:00	  Create	
 */
package com.kxjl.brain.police.api.ctrl.wx;

import com.kxjl.brain.police.api.context.wx.QueryUserInfoByOpenidContext;
import com.kxjl.brain.police.common.logs.model.WxApiLog;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import com.kxjl.brain.police.service.api.wx.impl.WxUtilsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 微信消息工具
 * @Author DengWei
 * @Date 2017/5/25 15:46
 */
@Service
@Path("/api/v1/wx/{businessId}")
public class WxUtilsApi {

    private static Logger LOG=  LoggerFactory.getLogger(WxUtilsServiceImpl.class);

    @Autowired
    private HttpServletRequest servletRequest;
    @Autowired
    private HttpServletResponse servletResponse;
    @Autowired
    private WxUtilsService wxUtilsService;

    /**
     * 消息接口
     * @param businessId
     * @param token
     * @param json
     */
    @Path("/initWechat")
    @POST
    public void initWechat(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json) {
        LOG.info("用户发来的消息:{}",json);
        //对接晓曼客服
        wxUtilsService.wxHandle(servletRequest,servletResponse,json);
    }


/*
    //消息接口验证
    @Path("/initWechat")
    @GET
    public void initWechat(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json) {
        wxUtilsService.wxHandle(servletRequest,servletResponse,"");
    }
*/

    /**
     * 微信授权连接
     */
    @Path("/wxAuthor")
    @GET
    public void wxAuthor(){
        wxUtilsService.wxAuthor(servletRequest,servletResponse);
    }



    /**
     * 根据openid查询用户信息
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/getUserInfoByOpenid")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserInfoByOpenid(@PathParam("businessId") String businessId,
                                 @QueryParam("token") String token, String json){
        return QueryUserInfoByOpenidContext.build(businessId, json, token, WxApiLog.log())
                .setService(wxUtilsService)
                .process()
                .toString();
    }

}
