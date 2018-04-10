package com.kxjl.brain.police.dto.wx;

/**
 * Created by xjwang5 on 2017/5/27.
 */
public class WxSignDTo
{
    /**
     * 主键
     */
    private Long id;
    /**
     * 标号
     */
    private String code;
    /**
     * 标签名
     */
    private String name;

    /**
     * 状态
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
