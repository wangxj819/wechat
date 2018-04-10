package com.kxjl.brain.police.common.config;

import com.kxjl.arsenal.config.ConfigBuilder;

/**
 * Created by zmpan on 2017/4/18.
 */
public class BrainConfig {
    public static ConfigBuilder reader = ConfigBuilder.build();

    public static String OSS_BASE_URL = "http://172.16.1.64:8185/yungo-oss";

    public static String OUTBOUND_BASE_URL = "http://localhost:8080/outbound";

    public static String ABP_BASE_URL = "http://172.16.1.22:8141/yungo-abp";

    public static String SMS_BUSINESS_ID = "74";

    static {
        loadAppConfig();
    }

    public static void loadAppConfig() {
        OSS_BASE_URL = reader.readString("public.kxjl_oss.baseurl", OSS_BASE_URL);
        OUTBOUND_BASE_URL = reader.readString("public.outbound.baseurl", OUTBOUND_BASE_URL);

        OUTBOUND_BASE_URL = urlHandler(OUTBOUND_BASE_URL);

        ABP_BASE_URL = reader.readString("yungo.abp.baseurl", ABP_BASE_URL);
        ABP_BASE_URL = urlHandler(ABP_BASE_URL);
        SMS_BUSINESS_ID = reader.readString("sms.businessId.id", SMS_BUSINESS_ID);
    }

    private static String urlHandler(String path) {
        if (path.endsWith("/")) {
            return path;
        } else {
            return path + "/";
        }
    }
}
