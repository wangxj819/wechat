package com.kxjl.brain.police.api.ctrl.wx;


import com.kxjl.brain.police.api.context.wx.*;
import com.kxjl.brain.police.common.im.SendImSms;
import com.kxjl.brain.police.common.logs.model.WxApiLog;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.WXService;
import com.kxjl.brain.police.service.api.wx.WechatService;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import com.kxjl.brain.police.service.api.wx.XmChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;


/**
 * 小曼客服对接
 * @Author DengWei
 * @Date 2017/5/11 9:07
 */
@Service
@Path("/api/v1/chat/{businessId}")
public class XmChatApi {

    @Autowired
    private XmChatService xmChatService;

    @Autowired
    private WechatService wechatService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private WxUtilsService wxUtilsService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private WXService wxService;

    /**
     * 创建工单
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/addChatOrder")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addChatOrder(@PathParam("businessId") String businessId,
                                    @QueryParam("token") String token, String json) {
        return AddXmChatContext.build(businessId, json, token, WxApiLog.log())
                .setService(xmChatService)
                .process()
                .toString();
    }



    /**
     * 更新工单状态
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/updateChatOrderById")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateChatOrderById(@PathParam("businessId") String businessId,
                                 @QueryParam("token") String token, String json) {
        return UpdateXmChatByIdContext.build(businessId, json, token, WxApiLog.log())
                .setService(xmChatService)
                .process()
                .toString();
    }


    /**
     * 查询工单
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/queryChatOrder")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String queryChatOrder(@PathParam("businessId") String businessId,
                                 @QueryParam("token") String token, String json) {
        return QueryByPageXmChatContext.build(businessId, json, token, WxApiLog.log())
                .setService(xmChatService)
                .process()
                .toString();
    }


    /**
     * 获取总览数据
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/getDataOverview")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getDataOverview(@PathParam("businessId") String businessId,
                             @QueryParam("token") String token, String json) {
        return DataOverviewContext.build(businessId, json, token, WxApiLog.log())
                .setService(xmChatService)
                .process()
                .toString();
    }


    /**
     * 列表查询报表单
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/queryReportList")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String queryReportList(@PathParam("businessId") String businessId,
                                  @QueryParam("token") String token, String json){
        return WechatQueryListContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }


    /***
     * 存储openid,向公众号发起请求
     */
    @Path("/pushWechat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String pushWechat(@PathParam("businessId") String businessId,
                                  @QueryParam("token") String token, String json){
        return PushWechatContext
                .build(businessId, json, token, WxApiLog.log())
                .setService(wxUtilsService)
                .setService(redisService)
                .setService(wxService)
                .process()
                .toString();
    }

    /**
     * 连接验证
     */
    @Path("/imChat")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String imChat(@PathParam("businessId") String businessId,
                             @QueryParam("token") String token, String json){
        return ImChatContext
                .build(businessId, json, token, WxApiLog.log())
                .setService(request)
                .setService(response)
                .setService(redisService)
                .setService(wxUtilsService)
                .process()
                .toString();
    }

    /**
     * 结束会话,删除用户信息
     */
    @Path("/endChat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String endChat(@PathParam("businessId") String businessId,
                         @QueryParam("token") String token, String json){
        return EndChatContext
                .build(businessId, json, token, WxApiLog.log())
                .setService(redisService)
                .setService(request)
                .setService(response)
                .setService(wxUtilsService)
                .process()
                .toString();
    }

    /**
     * SendSms
     */
    @Path("/sendSms")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String sendSms(@PathParam("businessId") String businessId,
                         @QueryParam("token") String token, String json){
        Map map =  JsonUtil.json2Obj(json, Map.class);
        SendImSms.sendMsg(map.get("toUser").toString(),map.get("openid").toString(),map.get("nickName").toString());
        return "200";
    }

    /**
     * 根据id查询工单(im)详情
     */
    @Path("/imDetail")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String imDetail(@PathParam("businessId") String businessId,
                         @QueryParam("token") String token, String json){
        return ImDetailContext
                .build(businessId, json, token, WxApiLog.log())
                .setService(xmChatService)
                .process()
                .toString();
    }
}
