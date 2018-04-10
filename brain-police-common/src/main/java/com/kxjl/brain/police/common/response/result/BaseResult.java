package com.kxjl.brain.police.common.response.result;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Strings;
import com.kxjl.brain.police.common.response.code.BaseCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwangma on 16/8/26.
 */
public class BaseResult<T> {

    protected BaseCode code = BaseCode.OK;

    protected List<T> rows;

    protected String msgExt = "";

    BaseResult(BaseCode code, List<T> rows) {
        this.code = code;
        this.rows = rows;
    }

    BaseResult(BaseCode code, List<T> rows, String msgExt) {
        this.code = code;
        this.rows = rows;
        this.msgExt = msgExt;
    }

    public static BaseResult build(BaseCode code) {
        return new BaseResult(code, null);
    }

    public static BaseResult build(BaseCode code, String msg) {
        return new BaseResult(code, null, msg);
    }

    public static BaseResult build(BaseCode code, List rows) {
        return new BaseResult(code, rows);
    }

    public static BaseResult build(BaseCode code, List rows, String msgExt) {
        return new BaseResult(code, rows, msgExt);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("{")
                .append("\"code\":")
                .append(code.getCode()).append(",")
                .append("\"message\":")
                .append("\"").append(code.getMessage());

        if (!Strings.isNullOrEmpty(msgExt)) {
            sb.append(" : ").append(msgExt);
        }

        sb.append("\",");

        if (rows == null) {
            rows = new ArrayList();
        }

        sb.append("\"result\":{").append("\"rows\":")
                .append(JSONObject.toJSONString(rows, SerializerFeature.WriteMapNullValue)).append("}");

        sb.append("}");

        return sb.toString();
    }

    public BaseCode getCode() {
        return code;
    }

    public void setCode(BaseCode code) {
        this.code = code;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public String getMsgExt() {
        return msgExt;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }

    public static void main(String[] args) {

        ArrayList rows = new ArrayList();
        BaseResult baseResult = BaseResult.build(BaseCode.OK, rows);
        System.out.println(baseResult.toString());

        BaseResult baseMoreResult = BaseResult.build(BaseCode.Param_Exp, rows, "id is null");
        System.out.println(baseMoreResult.toString());

    }

}
