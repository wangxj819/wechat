package com.kxjl.brain.police.common.response.code;

/**
 * Created by xiwangma on 16/8/26.
 */
public enum BaseCode {

    OK(0, "操作成功"),

    //参数异常
    Param_Exp(10000, "参数校验失败"),


    //内部异常
    Internal_Exp(20000, "系统内部错误"),
    Bad_Request_Exp(10001, "bad request exception"),

    //网络异常
    Network_Exp(20000, "网络异常"),

    //IO异常
    IO_Exp(30000, "IO 异常");


    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    BaseCode(int code, String detail) {
        this.code = code;
        this.message = detail;
    }
}
