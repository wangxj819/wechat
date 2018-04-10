package com.kxjl.brain.police.common.im;

import com.kxjl.brain.police.common.Constants;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

/**
 *
 * Created by Dengwei on 2018/2/16.
 */
public class SendImSms {

    /**
     * 发送上线通知
     *
     * @param toUser
     * @param openid
     * @param nickName
     */
    public static void sendMsg(String toUser,String openid,String nickName){

        AbstractXMPPConnection connection = null;
        try{
            XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
            configBuilder.setHost(Constants.IM_HOST);
            configBuilder.setPort(Integer.parseInt(Constants.IM_PORT));
            configBuilder.setResource(Constants.IM_RESOURCE);
            configBuilder.setServiceName(Constants.IM_SERVICE_NAME);
            configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            configBuilder.setUsernameAndPassword(Constants.IM_USERNAME, Constants.IM_PASSWORD);
            configBuilder.setCompressionEnabled(false);
            configBuilder.setDebuggerEnabled(false);
            configBuilder.setConnectTimeout(Integer.MAX_VALUE);
            SmackConfiguration.setDefaultPacketReplyTimeout(2000);
            connection = new XMPPTCPConnection(configBuilder.build());
            connection.connect();
            connection.login();
            //初始化上线通知
            Presence presence = new Presence(Presence.Type.available);
            connection.sendStanza(presence);
            //连接断开通知
            connection.addConnectionListener(new AbstractConnectionClosedListener() {
            @Override
            public void connectionTerminated() {
                System.out.println("IM断开连接");
            }});

            //消息处理
            connection.addAsyncStanzaListener(new MessageListener(), new MessageFilter());

            //获取用户信息
            Message message = new Message();
            message.setTo(toUser+"@"+Constants.IM_SERVICE_NAME);
            message.setFrom(Constants.IM_USERNAME+"@"+Constants.IM_SERVICE_NAME);
            message.addBody("cn", "用户:"+nickName+"即将发起聊天请注意。");
            message.addExtension(MsgElementExt.build("fromType", "webadmin"));
            message.addExtension(MsgElementExt.build("toType", "agent"));
            message.addExtension(MsgElementExt.build("channel", "wechat"));
            message.addExtension(MsgElementExt.build("openid", openid));
            message.addExtension(MsgElementExt.build("msgType", "notify"));
            connection.sendStanza(message);
            connection.disconnect();
        }catch (Exception e){
          //e.printStackTrace();
        }

    }


    /**
     * 向微信端发送离线消息
     * @param toUser
     */
    public static void sendLogoutMsg(String toUser) {
        AbstractXMPPConnection connection = null;
        try{
            XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
            configBuilder.setHost(Constants.IM_HOST);
            configBuilder.setPort(Integer.parseInt(Constants.IM_PORT));
            configBuilder.setResource(Constants.IM_RESOURCE);
            configBuilder.setServiceName(Constants.IM_SERVICE_NAME);
            configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            configBuilder.setUsernameAndPassword(Constants.IM_USERNAME, Constants.IM_PASSWORD);
            configBuilder.setCompressionEnabled(false);
            configBuilder.setDebuggerEnabled(false);
            configBuilder.setConnectTimeout(Integer.MAX_VALUE);
            SmackConfiguration.setDefaultPacketReplyTimeout(2000);
            connection = new XMPPTCPConnection(configBuilder.build());
            connection.connect();
            connection.login();
            //初始化上线通知
            Presence presence = new Presence(Presence.Type.available);
            connection.sendStanza(presence);
            //连接断开通知
            connection.addConnectionListener(new AbstractConnectionClosedListener() {
                @Override
                public void connectionTerminated() {
                    System.out.println("IM断开连接");
                }});

            //消息处理
            connection.addAsyncStanzaListener(new MessageListener(), new MessageFilter());
            Message message = new Message();
            message.setTo(toUser+"@"+Constants.IM_SERVICE_NAME);
            message.setFrom(Constants.IM_USERNAME+"@"+Constants.IM_SERVICE_NAME);
            message.addBody("cn", "结束会话");
            message.addExtension(MsgElementExt.build("fromType", "webadmin"));
            message.addExtension(MsgElementExt.build("toType", "agent"));
            message.addExtension(MsgElementExt.build("channel", "wechat"));
            message.addExtension(MsgElementExt.build("openid", toUser));
            message.addExtension(MsgElementExt.build("msgType", "notify"));
            connection.sendStanza(message);
            connection.disconnect();
        } catch (SmackException.NotConnectedException e1) {
           // e1.printStackTrace();
        } catch (XMPPException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (SmackException e) {
            //e.printStackTrace();
        }
    }



}
