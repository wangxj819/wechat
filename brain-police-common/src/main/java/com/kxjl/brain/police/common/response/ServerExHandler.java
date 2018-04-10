package com.kxjl.brain.police.common.response;
import com.kxjl.brain.police.common.logs.event.BrainApiEvent;
import com.kxjl.brain.police.common.logs.model.RestEntryLog;
import com.kxjl.brain.police.common.response.code.BaseCode;
import com.kxjl.brain.police.common.response.result.BaseResult;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by xiwangma on 16/8/7.
 */
public class ServerExHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception ex) {

        RestEntryLog.log().error(ex, BrainApiEvent.RestHandle, BaseCode.Internal_Exp.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(
                BaseResult.build(BaseCode.Bad_Request_Exp).toString()
        ).build();
    }
}
