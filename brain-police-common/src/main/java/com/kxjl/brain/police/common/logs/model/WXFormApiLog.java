package com.kxjl.brain.police.common.logs.model;

import com.kxjl.brain.police.common.logs.BrainPoliceApi;

/**
 * Created by xjwang5 on 2017/5/27.
 */
public class WXFormApiLog extends BrainPoliceApi
{
    static private WXFormApiLog log = new WXFormApiLog();

    @Override
    public String getModelName() {
        return "WXFormApiLog";
    }

    public static WXFormApiLog log() {
        return log;
    }
}
