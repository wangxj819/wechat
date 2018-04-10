package com.kxjl.brain.police.dto.wx;

import com.kxjl.brain.police.dto.comm.CommonDTO;

import java.util.List;

/**
 * Created by xjwang5 on 2018/3/1.
 */
public class WechatReflactDTO extends CommonDTO
{
    /**
     * 主键
     */
    private Long id;
    /**
     * 微信工单号
     */
    private String wechatnum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机
     */
    private String telephone;
    /**
     * 验证码
     */
    private String identifycode;
    /**
     * 身份证
     */
    private String idcode;
    /**
     * 事发地址
     */
    private String incidentaddress;
    /**
     * 报警地址
     */
    private String alarmaddress;
    /**
     * 举报线索内容
     */
    private String description;
    /**
     * 附件
     */
    private List attachment;
    /**
     * 事发地址经度
     */
    private String incidentlongitude;
    /**
     * 事发地址纬度
     */
    private String incidentlatitude;
    /**
     * 报警地址经度
     */
    private String alarmlongitude;
    /**
     * 报警地址纬度
     */
    private String alarmlatitude;
    /**
     * 工单状态
     */
    private String status;
    /**
     * 报警时间
     */
    private String alarmdate;
    /**
     * 查询起始时间
     */
    private String startDateTime;
    /**
     * 报警时间
     */
    private String endDateTime;
    /**
     * 关键词
     */
    private String keyWord;
    /**
     * openid
     */
    private String openid;
    /**
     * 昵称
     */
    private String nickname;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getWechatnum()
    {
        return wechatnum;
    }

    public void setWechatnum(String wechatnum)
    {
        this.wechatnum = wechatnum;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getIdentifycode()
    {
        return identifycode;
    }

    public void setIdentifycode(String identifycode)
    {
        this.identifycode = identifycode;
    }

    public String getIdcode()
    {
        return idcode;
    }

    public void setIdcode(String idcode)
    {
        this.idcode = idcode;
    }

    public String getIncidentaddress()
    {
        return incidentaddress;
    }

    public void setIncidentaddress(String incidentaddress)
    {
        this.incidentaddress = incidentaddress;
    }

    public String getAlarmaddress()
    {
        return alarmaddress;
    }

    public void setAlarmaddress(String alarmaddress)
    {
        this.alarmaddress = alarmaddress;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List getAttachment()
    {
        return attachment;
    }

    public void setAttachment(List attachment)
    {
        this.attachment = attachment;
    }

    public String getIncidentlongitude()
    {
        return incidentlongitude;
    }

    public void setIncidentlongitude(String incidentlongitude)
    {
        this.incidentlongitude = incidentlongitude;
    }

    public String getIncidentlatitude()
    {
        return incidentlatitude;
    }

    public void setIncidentlatitude(String incidentlatitude)
    {
        this.incidentlatitude = incidentlatitude;
    }

    public String getAlarmlongitude()
    {
        return alarmlongitude;
    }

    public void setAlarmlongitude(String alarmlongitude)
    {
        this.alarmlongitude = alarmlongitude;
    }

    public String getAlarmlatitude()
    {
        return alarmlatitude;
    }

    public void setAlarmlatitude(String alarmlatitude)
    {
        this.alarmlatitude = alarmlatitude;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAlarmdate()
    {
        return alarmdate;
    }

    public void setAlarmdate(String alarmdate)
    {
        this.alarmdate = alarmdate;
    }

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getStartDateTime()
    {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime()
    {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime)
    {
        this.endDateTime = endDateTime;
    }

    public String getKeyWord()
    {
        return keyWord;
    }

    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }
}
