package com.kxjl.brain.police.common.logs.event;


import com.kxjl.arsenal.log.LogEvent;

/**
 * Created by xxqin on 2017/4/12.
 */
public enum BrainApiEvent implements LogEvent {
    INIT,
    CREATE,
    DELETE,
    UPDATE,
    QUERY,
    RestHandle
}
