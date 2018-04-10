/**
 * Copyright 2014 IFlyTek. All rights reserved.<br>
 */
package com.kxjl.brain.police.common.msg;

/**
 * <p>
 * <code>MsgKey</code>获取配置文件中提示信息的key.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public final class MsgKey
{

    /**
     * 成功前缀
     */
    public static final String SUCC_PRFIX = "success.";

    /**
     * 失败前缀
     */
    public static final String ERROR_PRFIX = "error.";

    /**
     * 不合法参数
     */
    public static final String ERROR_PARAM_ILLEGAL = "error.param.illegal";

    /**
     * 操作成功
     */
    public static final String SUCCESS_OPERATION = "success.operation";

    /**
     * 操作失败
     */
    public static final String ERROR_OPERATION = "error.operation";


    /**
     * 没有数据
     */
    public static final String ERROR_NO_DATA = "error.nodata";

    /**
     * 数据已经删除
     */
    public static final String ERROR_RESULT_IS_DELETE = "error.result.is.delete";

    /**
     * 引擎编译失败
     */
    public static final String ERROR_SRCS_COMPILE_FAIL = "error.srcs.errorcode.";

    /**
     * 编译资源为空
     */
    public static final String ERROR_COMPILE_RESOURCE_NULL = "error.compile.resource.null";

    /**
     * 资源发布失败
     */
    public static final String ERROR_PUBLISH_RESOURCE_FAIL = "error.publish.resource.fail";

    /**
     * ISS 语义解析失败
     */
    public static final String ERROR_ISS_PARSE_FAIL = "error.iss.parse.fail.";

    /**
     * RestFull接口调用失败
     */
    public static final String ERROR_REST_FULL = "error.rest.full";


    /**
     * 短信发送失败
     */
    public static final String ERROR_SEND_SMSMSG_FAIL = "error.send.smsmsg.fail";

    /**
     * 查询答案失败
     */
    public static final String ERROR_FAILED_QUERY_ANSWER = "error.failed.query.answer";

}
