package com.kxjl.brain.police.common.logs.model;


import com.kxjl.brain.police.common.logs.BrainPoliceApi;

/**
 * Created by jwyuan on 2017/4/18.
 */
public class WxApiLog extends BrainPoliceApi {
    static private WxApiLog log = new WxApiLog();

    @Override
    public String getModelName() {
        return "WxApiLog";
    }

    public static WxApiLog log() {
        return log;
    }
}
