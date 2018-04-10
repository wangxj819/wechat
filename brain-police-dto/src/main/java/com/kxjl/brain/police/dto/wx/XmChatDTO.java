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
 * 1.0	     Administrator    2017/5/249:25	  Create	
 */
package com.kxjl.brain.police.dto.wx;

import com.kxjl.brain.police.dto.comm.CommonDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 对接服务端
 * @Author DengWei
 * @Date 2017/5/24 13:47
 */
public class XmChatDTO extends CommonDTO {

    private Long id;//主键ID

    private String name;//报警人姓名

    private String sex;//报警人性别

    private String  address;//事发地址

    private String attachment;//附件

    @DateTimeFormat(pattern="YYYY-MM-DD HH24:MI:SS")
    private Date alarmdate;//报警时间

    private String openid;//报警人用户ID

    private String status;//报警单状态

    private String casetype;//案件类型:01、刑事案件 02、民事案件 03、经济案件 04、行政案件 05、其他案件

    private String content;//报案主要内容

    private String startDateTime;

    private String endDateTime;

    private String imno;//报警编号

    private String sortName;

    private String sortOrder;

    @Override
    public String getSortName() {
        return sortName;
    }

    @Override
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String getSortOrder() {
        return sortOrder;
    }

    @Override
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getImno() {
        return imno;
    }

    public void setImno(String imno) {
        this.imno = imno;
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

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getAlarmdate() {
        return alarmdate;
    }

    public void setAlarmdate(Date alarmdate) {
        this.alarmdate = alarmdate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
