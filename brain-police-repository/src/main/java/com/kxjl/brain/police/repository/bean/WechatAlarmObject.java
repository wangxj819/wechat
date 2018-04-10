/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：brain-police
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     Administrator    2017/5/1013:38	  Create	
 */
package com.kxjl.brain.police.repository.bean;

import com.kxjl.brain.police.dto.comm.CommonDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 微信工单Bean
 * @Author DengWei
 * @Date 2017/5/11 17:21
 */
public class WechatAlarmObject extends CommonDTO {
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
    private String attachment;
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
    @DateTimeFormat(pattern="YYYY-MM-DD HH24:MI:SS")
    private Date alarmdate;

    /**
     * 查询起始时间
     */
    private String startDateTime;

    /**
     * 查询结束时间
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getWechatnum() {
        return wechatnum;
    }

    public void setWechatnum(String wechatnum) {
        this.wechatnum = wechatnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdentifycode() {
        return identifycode;
    }

    public void setIdentifycode(String identifycode) {
        this.identifycode = identifycode;
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public String getIncidentaddress() {
        return incidentaddress;
    }

    public void setIncidentaddress(String incidentaddress) {
        this.incidentaddress = incidentaddress;
    }

    public String getAlarmaddress() {
        return alarmaddress;
    }

    public void setAlarmaddress(String alarmaddress) {
        this.alarmaddress = alarmaddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getIncidentlongitude() {
        return incidentlongitude;
    }

    public void setIncidentlongitude(String incidentlongitude) {
        this.incidentlongitude = incidentlongitude;
    }

    public String getIncidentlatitude() {
        return incidentlatitude;
    }

    public void setIncidentlatitude(String incidentlatitude) {
        this.incidentlatitude = incidentlatitude;
    }

    public String getAlarmlongitude() {
        return alarmlongitude;
    }

    public void setAlarmlongitude(String alarmlongitude) {
        this.alarmlongitude = alarmlongitude;
    }

    public String getAlarmlatitude() {
        return alarmlatitude;
    }

    public void setAlarmlatitude(String alarmlatitude) {
        this.alarmlatitude = alarmlatitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAlarmdate() {
        return alarmdate;
    }

    public void setAlarmdate(Date alarmdate) {
        this.alarmdate = alarmdate;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
