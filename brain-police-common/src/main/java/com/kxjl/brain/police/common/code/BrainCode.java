package com.kxjl.brain.police.common.code;

import com.kxjl.arsenal.rest.code.RespondCode;

/**
 * Created by jwyuan on 2017/4/19.
 */
public enum  BrainCode implements RespondCode {
    Execute_Flow_Error(213001, "application error");

    BrainCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
