package com.kxjl.brain.police.common.im;

import org.jivesoftware.smack.packet.ExtensionElement;

/**
*
* IM消息
*
* @author weideng
* @date 2018/2/7 11:31
*/
public class MsgElementExt implements ExtensionElement {

    private String elementName;

    private String elementValue;

    public MsgElementExt(String elementName, String elementValue) {
        this.elementName = elementName;
        this.elementValue = elementValue;
    }

    public static MsgElementExt build(String elementName, String elementValue) {
        return new MsgElementExt(elementName, elementValue);
    }

    @Override
    public String getNamespace() {
        return "";
    }

    @Override
    public String getElementName() {
        return elementName;
    }


    @Override
    public CharSequence toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(elementName).append(">");
        sb.append(elementValue);
        sb.append("</").append(elementName).append(">");
        return sb;
    }
}
