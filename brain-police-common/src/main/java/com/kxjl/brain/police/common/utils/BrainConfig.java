package com.kxjl.brain.police.common.utils;

import com.kxjl.arsenal.config.ConfigBuilder;

/**
 * Created by jwyuan on 2017/4/15.
 */
public class BrainConfig {
    static ConfigBuilder reader = ConfigBuilder.build();

    public static String HOST = "127.0.0.1";

    public static Integer PORT = 9104;

    public static String OB_HOST = "http://127.0.0.1:8080";

    public static int NODE_FAIL_COUNT = 3;

    public static long APP_START_TIME_OUT = 10000;

    static {
        loadConfig();
    }

    public static void loadConfig() {
        HOST = reader.readString("servers.brain.rest.host", HOST);
        PORT = reader.readInteger("servers.brain.rest.port", PORT);
        OB_HOST = reader.readString("public.outbound.baseurl", OB_HOST);
        NODE_FAIL_COUNT = reader.readInteger("public.brain.node.failCount", NODE_FAIL_COUNT);
        APP_START_TIME_OUT = reader.readLong("servers.brain.rest.application.startTimeOut",
                APP_START_TIME_OUT);
    }

}
