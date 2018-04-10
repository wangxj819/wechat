package com.kxjl.brain.police.common.logs.event;

import com.kxjl.arsenal.log.LogEvent;

/**
 * Created by jwyuan on 2017/4/15.
 */
public enum RestEntryEvent implements LogEvent {
    RestServerStart,
    RestServerStartError,
    SessionInit,
    SessionInitError,
    NotifyEvent2OB,
    NotifyEvent2OBNoSignal,
    NotifyEvent2OBNoIvrEvent,
    NotifyEvent2OBError,
    NotifyEvent2OBResult,
    Execute,
    ExecuteError
}
