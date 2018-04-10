package com.kxjl.brain.police;



import com.kxjl.brain.police.common.im.MessageFilter;
import com.kxjl.brain.police.common.im.MessageListener;
import com.kxjl.brain.police.common.im.MsgElementExt;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;


import java.io.IOException;

/**
 * Created by xiwangma on 2016/12/9.
 */
public class ImTest {
    private static AbstractXMPPConnection connection = null;
    static {
        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();

        configBuilder.setHost("172.19.11.203");
        configBuilder.setPort(5222);
        configBuilder.setResource("police-client");
        configBuilder.setServiceName("tigase.cc");
        configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        configBuilder.setUsernameAndPassword("webadmin", "");
        configBuilder.setCompressionEnabled(false);
        configBuilder.setDebuggerEnabled(false);
        configBuilder.setConnectTimeout(Integer.MAX_VALUE);
        SmackConfiguration.setDefaultPacketReplyTimeout(2000);

        connection = new XMPPTCPConnection(configBuilder.build());
        try {
            connection.connect();
            connection.login();
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, XMPPException, SmackException {
        for(int i=0;i<5;i++){
            sendMsg();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void sendMsg(){

        try {

            //初始化上线通知
            Presence presence = new Presence(Presence.Type.available);
            connection.sendStanza(presence);

            //连接断开通知
            connection.addConnectionListener(new AbstractConnectionClosedListener() {
                @Override
                public void connectionTerminated() {
                    System.out.print("断开连接");
                }
            });

            //消息处理
            connection.addAsyncStanzaListener(new MessageListener(), new MessageFilter());

            Message message = new Message();
            //message.setTo("18326605086@tigase.cc");
            message.setTo("tdd@tigase.cc");
            message.setFrom("webadmin@tigase.cc");
            message.addBody("cn", "用户即将发起IM聊天请注意！");
            message.addExtension(MsgElementExt.build("fromType", "admin"));
            message.addExtension(MsgElementExt.build("toType", "agent"));
            message.addExtension(MsgElementExt.build("channel", "wechat"));
            message.addExtension(MsgElementExt.build("openid", "12312312312323"));
            message.addExtension(MsgElementExt.build("msgType", "notify"));
            connection.sendStanza(message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
