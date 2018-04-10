package com.kxjl.brain.police.api.ctrl.wx;

import com.kxjl.brain.police.api.context.wx.*;
import com.kxjl.brain.police.common.logs.model.WXFormApiLog;
import com.kxjl.brain.police.service.api.wx.WxSignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
  *标签管理
  *Author:DengWei
  *Date:2017/7/3
  *Time:16:49
 **/
@Service
@Path("/api/v1/wxForm/{businessId}")
public class WXFormApi {

    @Autowired
    WxSignService wxSignService;

    private static Logger log=  LoggerFactory.getLogger(WXFormApi.class);
    @Path("/getFormJson")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getFormJson(@PathParam("businessId") String businessId,@QueryParam("token") String token, String json){
        return WechatFormContext
                .build(businessId, json, token, WXFormApiLog.log())
                .process()
                .toString();
    }

    /**
     * 添加标签
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/addSign")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addSign(@PathParam("businessId") String businessId,
                            @QueryParam("token") String token, String json) {
        return  AddSignContext
                .build(businessId, json, token, WXFormApiLog.log())
                .setService(wxSignService)
                .process()
                .toString();
    }
    /**
     * 查询标签
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/querySignInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String querySignInfo(@PathParam("businessId") String businessId,
                          @QueryParam("token") String token, String json) {
        return  QuerySignInfoContext
                .build(businessId, json, token, WXFormApiLog.log())
                .setService(wxSignService)
                .process()
                .toString();
    }
    /**
     * 删除标签
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/deleteSign")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSign(@PathParam("businessId") String businessId,
                          @QueryParam("token") String token, String json) {
        return  DeleteSignContext
                .build(businessId, json, token, WXFormApiLog.log())
                .setService(wxSignService)
                .process()
                .toString();
    }
    /**
     * 更新标签
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/updateSignInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSignInfo(@PathParam("businessId") String businessId,
                          @QueryParam("token") String token, String json) {
        return  UpdateSignContext
                .build(businessId, json, token, WXFormApiLog.log())
                .setService(wxSignService)
                .process()
                .toString();
    }

}
