package com.kxjl.brain.police.api.listener;


import com.kxjl.arsenal.log.LogModule;
import com.kxjl.brain.police.common.logs.BrainPoliceApi;
import com.kxjl.brain.police.common.logs.event.BrainApiEvent;
import com.kxjl.brain.police.common.logs.model.WxApiLog;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by huanliu4 on 2016/10/27.
 */
public class ApiSpringContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LogModule logModule = WxApiLog.log();
        logModule.info(BrainApiEvent.INIT, "init context!");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
