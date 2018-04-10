package com.kxjl.brain.police.common.im;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

/**
*  
* Im消息
* 
* @author weideng 
* @date 2018/2/7 11:31
*/
public class MessageFilter implements StanzaFilter {
    @Override
    public boolean accept(Stanza stanza) {
        if (stanza instanceof Message) {
            return true;
        }
        return false;
    }
}
