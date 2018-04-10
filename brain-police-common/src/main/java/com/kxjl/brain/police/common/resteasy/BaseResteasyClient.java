package com.kxjl.brain.police.common.resteasy;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * <code>BaseResteasyClient</code>封装Resteasy客户端的调用方法.
 */
public abstract class BaseResteasyClient {


    /**
     * <code>target</code>方法主要是和Resteasy服务端建立目标连接
     *
     * @param address Resteasy协议服务端地址
     * @return ResteasyWebTarget 建立服务的目标对象
     */
    protected ResteasyWebTarget target(String address) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(address);
        return target;
    }

    /**
     * <code>post</code>方法通过Post方式从Resteasy服务端获取返回信息
     *
     * @param address  Resteasy协议服务端地址
     * @param paramMap 协议参数,key-value
     * @return Response Response对象
     */
    protected Response post(String address, Map<String, String> paramMap) {
        Form form = new Form();
        if (null != paramMap) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                form.param(entry.getKey(), entry.getValue());
            }
        }
        ResteasyWebTarget target = this.target(address);
        return target.request().post(Entity.form(form));
    }

    /**
     * post json
     *
     * @param address
     * @param bodyParameter
     * @return
     * @author huanliu4
     */
    protected Response post(String address, String bodyParameter) {
        ResteasyWebTarget target = this.target(address);
        return target.request().post(Entity.json(bodyParameter));
    }

    /**
     * @param address
     * @return
     * @author huanliu4
     */
    protected Response get(String address) {
        ResteasyWebTarget target = this.target(address);
        return target.request().get();
    }
}
