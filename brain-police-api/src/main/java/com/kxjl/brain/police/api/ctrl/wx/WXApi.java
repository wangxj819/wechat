package com.kxjl.brain.police.api.ctrl.wx;

import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.api.context.wx.*;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.imgcode.VerifyCodeUtils;
import com.kxjl.brain.police.common.logs.model.WxApiLog;
import com.kxjl.brain.police.service.api.redis.RedisService;
import com.kxjl.brain.police.service.api.wx.RegistUserService;
import com.kxjl.brain.police.service.api.wx.WXReportService;
import com.kxjl.brain.police.service.api.wx.WXService;
import com.kxjl.brain.police.service.api.wx.WechatService;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具
 * @Author DengWei
 * @Date 2017/5/11 9:07
 */
@Service
@Path("/api/v1/wx/{businessId}")
public class WXApi {
    @Autowired
    private WXService wxService;

    @Autowired
    private WechatService wechatService;

    @Autowired
    private WXReportService ws;

    @Autowired
    private RedisService redisService;
    @Autowired
    private RegistUserService registUserService;

    @Autowired
    private HttpServletResponse response;

    private static Logger log=  LoggerFactory.getLogger(WXApi.class);

    @Path("/queryWXCallPolice")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String queryWXCallPolice(@PathParam("businessId") String businessId,
                                    @QueryParam("token") String token, String json) {
        return QueryWXCallPoliceContext
                .build(businessId, json, token, WxApiLog.log())
                .setService(wxService)
                .process()
                .toString();
    }


    /**
     * 获取工单数量
     * @return
     */
    @Path("/getCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BaseRespResult getCount() {
        return wechatService.queryCountByStatus();
    }

    /**
     * 获取签名数据
     * @return
     */
    @Path("/getJsApiTicket")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map getJsApi() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("ticket", wxService.getJSTicket() );
        //data.put("token", LocalWechatServiceImpl.WX_ACCESS_TOKEN);
        data.put("appId", Constants.POLICE_WX_APPID);
        return data;
    }



    /**
     * 获取签名数据
     * @return
     */
    @Path("/getToken")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map getToken() {
        Map<String, String> data = new HashMap<String, String>();
        //data.put("ticket", wxService.getJSTicket() );
        data.put("token", wxService.getAccessToken());
        //data.put("appId", Constants.WX_APP_ID);
        return data;
    }

    /**
     * 校验网络
     * @return
     */
    @Path("/verifyNet")
    @GET
    public String verifyNet() {
        return "ok";
    }

    /**
     * 搜索地名
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/getListName")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getListName(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json){

        return WechatContext.build(businessId, json, token, WxApiLog.log())
                .process()
                .toString();
    }

    /**
     * 录音上传
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/uploadVoiceMediaId")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadVoiceMediaId(@PathParam("businessId") String businessId,
                              @QueryParam("token") String token, String json){

        return UploadVoiceMediaIdContext.build(businessId, json, token, WxApiLog.log())
                .setService(wxService)
                .process()
                .toString();
    }

    /**
     * 上传图片
     * @param businessId
     * @param token
     * @param input
     * @return
     */
    @Path("/doUpload")
    @POST
    @Consumes({"multipart/mixed", "multipart/form-data"})
    @Produces(MediaType.TEXT_PLAIN)
    public String doUpload(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, MultipartFormDataInput input){
        return WechatUploadFileContext.build(businessId, input, token, WxApiLog.log())
                .process()
                .toString();
    }

    /**
     * 微信工单查询
     *
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/queryList")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String queryList(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json){
        //new SimpleDateFormat("yyyy-MM-dd").parse(filldateStr)
        return WechatQueryListContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }

    /**
     * 微信工单提交
     *
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/doSubmit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String doSubmit(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json){
        return WechatDoSubmitContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .setService(redisService)
                .setService(registUserService)
                .process()
                .toString();
    }


    /**
     * 删除微信工单
     *
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/delWechat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String delWechat(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json){
        return WechatDelContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }


    /**
     * 修改微信工单
     *
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/updateWechat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateWechat(@PathParam("businessId") String businessId,
            @QueryParam("token") String token, String json){
        return WechatUpdateContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }

    /**
     * 微信工单详情
     *
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/orderDetails")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String orderDetails(@PathParam("businessId") String businessId,
                            @QueryParam("token") String token, String json){
        return WechatOrderDetailsContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }


    /**
     * 电话号码验证
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/phoneNumCheck")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String phoneNumCheck(@PathParam("businessId") String businessId,
                               @QueryParam("token") String token, String json){
        return PhoneNumCheckContext.build(businessId, json, token, WxApiLog.log())
                .setService(redisService)
                .process()
                .toString();
    }

    /**
     * 查询一条记录
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/queryRecord")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String queryRecord(@PathParam("businessId") String businessId,
                                @QueryParam("token") String token, String json){
        return QueryRecordContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }

    /**
     * 添加报表单
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/insertReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String insertReport(@PathParam("businessId") String businessId,
                               @QueryParam("token") String token, String json){
        return WXReportInsertContext.build(businessId, json, token, WxApiLog.log())
                .setService(ws)
                .process()
                .toString();
    }

    /**
     * 修改报表单
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/updateReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String updateReport(@PathParam("businessId") String businessId,
                               @QueryParam("token") String token, String json){
        return WXReportUpdateContext.build(businessId, json, token, WxApiLog.log())
                .setService(ws)
                .process()
                .toString();
    }

    /**
     * 删除报表单
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/deleteReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteReport(@PathParam("businessId") String businessId,
                               @QueryParam("token") String token, String json){
        return WXReportDeleteContext.build(businessId, json, token, WxApiLog.log())
                .setService(ws)
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
        return WXReportQueryContext.build(businessId, json, token, WxApiLog.log())
                .setService(ws)
                .process()
                .toString();
    }

    /**
     * 查询单条报表单数据
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/queryOneReport")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String queryOneReport(@PathParam("businessId") String businessId,
                                  @QueryParam("token") String token, String json){
        return WXRepQueryOneContext.build(businessId, json, token, WxApiLog.log())
                .setService(ws)
                .process()
                .toString();
    }



    /**
     * 微信注册
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/registUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String registUser(@PathParam("businessId") String businessId,
                                 @QueryParam("token") String token, String json){
        return RegistUserContext.build(businessId, json, token, WxApiLog.log())
                .setService(registUserService)
                .process()
                .toString();
    }


    /**
     * 根据openid查询用户是否存在
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/queryByOpenid")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String isVerifyOpenid(@PathParam("businessId") String businessId,
                                 @QueryParam("token") String token, String json){
        return RegistUserVerifyOpenidContext.build(businessId, json, token, WxApiLog.log())
                .setService(registUserService)
                .process()
                .toString();
    }

    /**
     * 用户登录
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@PathParam("businessId") String businessId,
                        @QueryParam("token") String token, String json){
        return WxLoginContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }

    /**
     * 微信统计报表
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/report")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String report(@PathParam("businessId") String businessId,
                         @QueryParam("token") String token, String json) {
        return ReportContext.build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }

    /**
     * 登录生成图片验证码
     * @param
     * @return
     */
    @Path("/imgCode")
    @GET
    public void imgCode() {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 生成图片
        int w = 100, h = 50;
        try
        {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 微信统计报表
     * @param businessId
     * @param token
     * @param json
     * @return
     */
    @Path("/wechatDetail")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String wechatDetail(@PathParam("businessId") String businessId,
                         @QueryParam("token") String token, String json) {
        return WechatDetailContext
                .build(businessId, json, token, WxApiLog.log())
                .setService(wechatService)
                .process()
                .toString();
    }
}
