package com.kxjl.brain.police.repository.bean.report;

/**
 * Created by xjwang5 on 2018/2/5.
 */
public class WechatCountDim implements Comparable<WechatCountDim>
{
    private String time;
    private int connectNum;

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getConnectNum()
    {
        return connectNum;
    }

    public void setConnectNum(int connectNum)
    {
        this.connectNum = connectNum;
    }

    @Override
    public int compareTo(WechatCountDim dim)
    {
        return this.getTime().compareTo(dim.getTime());
    }
}

