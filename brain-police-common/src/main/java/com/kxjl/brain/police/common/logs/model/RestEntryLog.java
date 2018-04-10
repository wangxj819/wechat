package com.kxjl.brain.police.common.logs.model;

import com.kxjl.arsenal.log.LogModule;
import com.kxjl.brain.police.common.logs.BrainPoliceApi;

/**
 * Created by xiwangma on 16/8/26.
 */
public class RestEntryLog extends BrainPoliceApi {
    @Override
    public String getModelName() {
        return "RestEntry";
    }


    private static RestEntryLog log = new RestEntryLog();

    public static LogModule log() {
        return log;
    }
}
