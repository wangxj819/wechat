package com.kxjl.brain.police.common.logs;

import com.kxjl.brain.police.common.logs.model.WxApiLog;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContextEvent;

/**
 * Created by xiwangma on 16/8/6.
 */
public class BrainPoliceApiLog4jListener extends Log4jConfigListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WxApiLog.log();

        super.contextInitialized(event);
    }

}
