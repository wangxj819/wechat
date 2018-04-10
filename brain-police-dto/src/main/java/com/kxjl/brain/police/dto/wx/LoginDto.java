package com.kxjl.brain.police.dto.wx;

/**
 * Created by xjwang5 on 2018/2/3.
 */
public class LoginDto
{
    /**主键*/
    private Long id;
    /**登录名称*/
    private String account;
    /**登录密码*/
    private String password;
    /**在线离线*/
    private String status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
