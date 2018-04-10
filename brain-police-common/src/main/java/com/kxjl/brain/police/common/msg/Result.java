/**
 * Copyright 2014 IFlyTek. All rights reserved.<br>
 */
package com.kxjl.brain.police.common.msg;

import com.alibaba.fastjson.JSON;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.StringUtils;

/**
 * <p>
 * <code>Result</code>封装返回的结果集.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public class Result<T> extends Message {

    // 返回结果的状态
    private int code;

    // 返回对象
    private T data;

    public static final String CODE_KEY = "code";

    public static final String MESSAGE_KEY = "message";

    public static final String DATA_KEY = "data";

    /**
     * 默认构造
     */
    public Result() {
        super();
    }

    /**
     * @param code
     * @param key
     * @param data
     */
    public Result(int code, String key, T data) {
        super(key);
        this.code = code;
        this.data = data;
    }

    /**
     * @param code
     * @param key
     */
    public Result(int code, String key) {
        super(key);
        this.code = code;
    }

    /**
     * @param key
     */
    public Result(String key) {
        super(key);
        if (!StringUtils.isEmpty(key)) {
            if (key.startsWith(MsgKey.SUCC_PRFIX)) {
                this.code = Constants.SUCCESS_CODE;
            } else if (key.startsWith(MsgKey.ERROR_PRFIX)) {
                this.code = Constants.ERROR_CODE;
            }
        }
    }

    /**
     * @param key
     * @param data
     */
    public Result(String key, T data) {
        super(key);
        this.data = data;
        if (!StringUtils.isEmpty(key)) {
            if (key.startsWith(MsgKey.SUCC_PRFIX)) {
                this.code = Constants.SUCCESS_CODE;
            } else if (key.startsWith(MsgKey.ERROR_PRFIX)) {
                this.code = Constants.ERROR_CODE;
            }
        }
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * @param result 只保留相关的Code和Message作为Result<String>对象
     * @return
     * @description 将Result对象只保留相关的Code和Message作为Result<String>返回
     * @author yhsu
     * @create 2015年12月17日下午7:31:41
     * @version 1.0
     */
    public static Result<String> trans2StringResult(Result<?> result) {
        if (null == result) {
            return null;
        }
        Result<String> strResult = new Result<String>();
        strResult.setCode(result.getCode());
        strResult.setMessage(result.getMessage());
        return strResult;
    }

}
