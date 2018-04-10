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
package com.kxjl.brain.police.service.api.wx.impl;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.*;
import com.kxjl.brain.police.dto.wx.WeChatDTO;
import com.kxjl.brain.police.service.api.wx.WXService;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

import static com.kxjl.brain.police.common.utils.HttpWalker.visit;
import static com.kxjl.brain.police.common.utils.JsonUtil.json2Obj;

/**
 * 对接微信
 * @Author DengWei
 * @Date 2017/5/25 15:51
 */
@Service
public class WxUtilsServiceImpl implements WxUtilsService {

    @Autowired
    private WXService wxService;

    private static Logger LOG=  LoggerFactory.getLogger(WxUtilsServiceImpl.class);

    /**
     * 微信接入处理
     *
     * @param request
     * @param response
     */
    @Override public void wxHandle(HttpServletRequest request,
            HttpServletResponse response,String xmlStr) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // POST
        if ("POST".equals(request.getMethod())) {
            // 事件处理
            try {
                Map<String, String> requestMap = parseXml(xmlStr);
                if (requestMap != null && "event".equals(requestMap.get("MsgType"))) {// 用户关注时的事件推送
                    if (Constants.WX_CLICK_SUBSCRIBE_EVENT.equals(requestMap.get("Event"))) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("<xml>");
                        sb.append("<ToUserName><![CDATA[" + requestMap.get("FromUserName") + "]]></ToUserName>");
                        sb.append("<FromUserName><![CDATA[" + requestMap.get("ToUserName") + "]]></FromUserName>");
                        sb.append("<CreateTime>" + new Date().getTime() + "</CreateTime>");
                        sb.append("<MsgType><![CDATA[text]]></MsgType>");
                        sb.append("<Content><![CDATA[" + Constants.WX_SUBSCRIBE_INFO + "]]></Content>");
                        sb.append("</xml>");
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().print(sb);
                        response.getWriter().flush();
                        response.getWriter().close();
                    }
                }
                if (requestMap != null && "text".equals(requestMap.get("MsgType"))) {// 接受用户普通消息
                    String content = requestMap.get("Content");
                    LOG.info(requestMap.get("FromUserName")+"发送的消息:{}",content);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 验证接入
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");

            try {
                PrintWriter print =  response.getWriter();
                print.append(
                        (validateSignature(Constants.WX_APP_TOKEN, timestamp, nonce, signature) ? echostr : ""));
                print.flush();
                print.close();
            } catch (IOException e) {
                LOG.error("微信接入异常",e);
            }

        }

    }


    /**
     * 微信授权业务统一回调
     *
     * 回调原则：拦截所有授权请求，检查session是否存在wxuser，没有，统一重定向至微信网页授权地址，有直接重定向至业务页面地址
     * 使用方法：需要授权的业务菜单配置
     * http://domain/app/wxAuthor.do?url=http://domain/app/xxx.do，
     * 最终会在session和参数中增加user对象
     */
    @Override
    public void wxAuthor(HttpServletRequest request, HttpServletResponse response) {

        String url = request.getParameter("url");
        String code = request.getParameter("code");
        LOG.info("code:{}", code);
        if (StringUtil.isNullOrBlank(code)) {
            LOG.info("开始微信授权业务统一回调：{}", url);
            String oauthUrl = createOauthUrl(url, request.getParameter(Constants.WX_AUTH_PARAM_NAME));
            LOG.info("权回调，重定向至微信网页授权页面：{}", oauthUrl);
            try {
                response.sendRedirect(oauthUrl);
            } catch (IOException e) {
                LOG.error("重定向至微信授权地址出错", e);
            }

        } else {
            String openid = getOpenIdByOauthCode(code);
            WeChatDTO weChatDTO = getUserInfoByOpenId(openid);
            try {
                url += "?openid=" + openid+"&nickname="+ URLEncoder.encode(weChatDTO.getNickname(),"utf-8");
                LOG.info("重定向URL:{}",url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            LOG.info("openid:{}", openid);
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                LOG.error("重定向地址出错", e);
            }
        }

       /* String url = request.getParameter("url");
        Map map = new TreeMap();
        String timestamp = System.currentTimeMillis()+"";
        timestamp = timestamp.substring(0,10);
        map.put("app_id", Constants.POLICE_APPID);
        map.put("app_secrect", Constants.POLICE_SECRECT);
        map.put("timestamp",timestamp);
        map.put("url",url);
        String signStr = MD5Utils.getMD5Value(SignUtils.signStr(map)).toLowerCase();
        url = Constants.POLICE_WX_URL+"?app_id="+ Constants.POLICE_APPID+"&timestamp="+timestamp+"&sign="+signStr+"&url="+ URLEncoder.encode(url);
        System.out.println(url);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
     /*   String resultStr = HttpWalker.visit(url).get().getResponseStr();
        Map result = JsonUtil.json2Obj(resultStr,Map.class);
        System.out.println(result.get("msg"));*/
    }

    /**
     * 向指定用户推送文本消息
     * @param openid
     * @param msg
     * @return
     */
    @Override
    public boolean wxSendMsg(String openid, String msg) {
         Map map = new HashMap();
         Map content = new HashedMap();
         content.put("content",msg);
         map.put("touser",openid);
         map.put("msgtype","text");
         map.put("text",content);
         LOG.info("微信端发送消息到用户:{}",map);
         String resultStr = HttpWalker.visit(String.format(Constants.WECHAT_MSG_SEND_URL,wxService.getAccessToken()))
                .putBody(JsonUtil.obj2json(map)).post().getResponseStr();
         Map resultMap = JsonUtil.json2Obj(resultStr,Map.class);
         LOG.info("微信端发送消息到用户微信返回:{}",resultMap);
         return "0".equals(resultMap.get("errcode"));
    }


    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     */
    public WeChatDTO getUserInfoByOpenId(String openid) {
        String url = String.format(Constants.WX_USERINFO_URL,wxService.getAccessToken() , openid);
        System.out.println("getAccessToken  ---- "+wxService.getAccessToken());
        System.out.println(url);
        String jsonStr = HttpWalker.visit(url).get().getResponseStr();
        System.out.println(jsonStr);
        WeChatDTO chatModel = json2Obj(jsonStr, WeChatDTO.class);
        System.out.println("---chatModel---"+chatModel.getNickname());
        chatModel.setNickname(EmojiFilterUtils.filterEmoji(chatModel.getNickname()).trim());
        chatModel.setPhone(null);
        chatModel.setSubscribe_time(DateUtil.getNowDate());
        chatModel.setAddres(chatModel.getCountry() + chatModel.getProvince() + chatModel.getCity());
        return chatModel;
    }


    /**
     * 根据Code获取openid
     * @param code
     * @return
     */
    public String getOpenIdByOauthCode(String code) {
        String url = String.format(Constants.WX_OAUTH2_TOKEN_URL, Constants.WX_APP_ID, Constants.WX_APP_SECRET, code);
        String jsonStr = visit(url).get().getResponseStr();
        return JSONObject.parseObject(jsonStr).getString("openid");
    }






    /**
     * 创建微信网页授权oauth目标访问地址
     *
     * @param target
     *            目标回调页面
     * @param authz
     *            鉴权级别
     * @return
     */
    private String createOauthUrl(String target, String authz) {
        try {
            String callBackUrl = URLEncoder
                    .encode(Constants.APP_SITE_HOST + Constants.CONTEXT_PATH_URL+ target
                    + (StringUtil.isNullOrBlank(authz) ? "" : ("&authz=" + authz)), "utf-8");
            LOG.info("授权URL：{}", callBackUrl);
            return String.format(Constants.WX_OAUTH2_URL, Constants.WX_APP_ID, callBackUrl, "");
        } catch (UnsupportedEncodingException e) {
            LOG.error("创建oauth目标访问地址出错", e);
        }
        return null;
    }


    /**
     * 签名验证
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    private boolean validateSignature(String token, String timestamp, String nonce, String signature) {
        // 排序并组合
        String[] array = new String[] { token, timestamp, nonce };
        Arrays.sort(array);
        String content = array[0].concat(array[1]).concat(array[2]);
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] digest = sha1.digest(content.getBytes());
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                result.append(byteToHexStr(digest[i]));
            }
            return signature.equalsIgnoreCase(result.toString());
        } catch (Exception e) {
            LOG.error("微信签名验证异常", e);
        }

        return false;
    }

    /**
     * 字节转十六进制字符
     *
     * @param mByte
     * @return
     */
    private String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;

    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        getStrFromInputSteam(inputStream);
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 解析微信发来的请求（XML）
     *
     * @param str
     * @return
     * @throws Exception
     */
    public Map<String, String> parseXml(String str) throws Exception {
        if(StringUtil.isNullOrBlank(str)){
            return null;
        }
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }

    /**
     * 输入流转成字符串
     * @param in
     * @return
     */
    public String getStrFromInputSteam(InputStream in){
        BufferedReader bf= null;
        try {
            bf = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //最好在将字节流转换为字符流的时候 进行转码
        StringBuffer buffer=new StringBuffer();
        String line="";
        try {
            while((line=bf.readLine())!=null){
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("请求值.....",buffer.toString());
        return buffer.toString();
    }


  /*  public static void main(String[] args) {
        Map map = new HashMap();
        Map content = new HashedMap();
        content.put("content","11111111");
        map.put("touser","orBHZw1faI6FWLsWo2Dh96GjbRIU");
        map.put("msgtype","text");
        map.put("text",content);
        LOG.info("微信端发送消息到用户:{}",map);
        String resultStr = HttpWalker.visit(String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s","brtFuOzDd6Cv0OecjlePcil8jyWBRMhhWXbJBdVmY7Lyd2OImYtIcg5DbJmnwUqVA1AWkxBIDs_bDCyy5tsZjkd8Dynsv4YtUq5navYn7WAb2IjKpwfGT82eRHRAJrtMOMTcAGARPW")).putBody(JsonUtil.obj2json(map)).post().getResponseStr();
        Map resultMap = JsonUtil.json2Obj(resultStr,Map.class);
        LOG.info("微信端发送消息到用户微信返回:{}",resultMap);

    }*/



    public static void main(String[] args) {
        Map map = new TreeMap();
        String timestamp = System.currentTimeMillis()+"";
        timestamp = timestamp.substring(0,10);
        map.put("app_id","1102");
        map.put("app_secrect","bCDWjTMYRzEIxVYi");
        map.put("timestamp",timestamp);
        //map.put("url","http://static.police.xiaomankf.com/police/wechat/index.html");
        String signStr = MD5Utils.getMD5Value(SignUtils.signStr(map)+"").toLowerCase();
        String url = "http://police.eisong.cc/api/weixin/access_token?app_id=1102"+"&timestamp="+timestamp+"&sign="+signStr;
        System.out.println(url);
        String resultStr = HttpWalker.visit(url).get().getResponseStr();
        Map result = JsonUtil.json2Obj(resultStr,Map.class);
        System.out.println(result.get("data"));
    }
}
