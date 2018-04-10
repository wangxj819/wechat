package com.kxjl.brain.police.common.im;


import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
* Im消息
*
* @author weideng
* @date 2018/2/7 11:31
*/
public class MessageListener implements StanzaListener {

    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void processPacket(Stanza stanza) throws SmackException.NotConnectedException {
        logger.info("Receive packet msg: " + stanza.toXML());
    }

}
