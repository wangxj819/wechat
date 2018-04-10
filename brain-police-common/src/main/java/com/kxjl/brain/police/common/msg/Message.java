/**
 * Copyright 2014 IFlyTek. All rights reserved.<br>
 */
package com.kxjl.brain.police.common.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kxjl.brain.police.common.utils.StringUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * <p>
 * <code>Message</code>读取message配置信息.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public abstract class Message
{
    @JsonIgnore
    private String key;

    private String message;

    private static final String FILE_PATH = "/config/message.properties";

    /**
     * 默认构造
     */
    public Message() {

    }

    /**
     * @param key
     */
    public Message(String key) {
        super();
        this.key = key;
    }

    /**
     * @return the key
     */
    @JSONField(serialize = false, deserialize = false)
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    @JSONField(serialize = false, deserialize = false)
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        try {
            if (!StringUtils.isBlank(this.message)) {
                return this.message;
            }
            this.message = new PropertiesConfiguration(FILE_PATH)
                    .getString(key);
            return this.message;
        } catch (ConfigurationException e) {
            return "提示信息未定义";
        }
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
