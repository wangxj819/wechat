package com.kxjl.brain.police.repository.bean;

import com.kxjl.brain.police.dto.comm.CommonDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**微信报表单bean
 * @Author liyu
 * @Date 2017/5/18 15:32
 */
public class WXReportObject extends CommonDTO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 来话类别
     */
    private String callCategory;
    /**
     * 移交县市
     */
    private String belongCity;
    /**
     * 机关分类
     */
    private String belongPolice;
    /**
     * 所属机关
     */
    private String belongPosition;
    /**
     * 辖区
     */
    private String belongArea;
    /**
     * 报警类别
     */
    private String alarmCategory;
    /**
     * 报警类型
     */
    private String alarmType;
    /**
     * 报警细类
     */
    private String alarmDetail;
    /**
     * 外键
     */
    private Long wechatId;
    /**
     * 报警人标签
     */
    private String alarmPeosign;
    /**
     * 主要情节
     */
    private String importPlot;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="YYYY-MM-DD HH24:MI:SS")
    private Date createTime;
    /**
     * 查询起始时间
     */
    //@DateTimeFormat(pattern="YYYY-MM-DD HH24:MI:SS")
    private String startDateTime;

    /**
     * 终止时间
     */
    //@DateTimeFormat(pattern="YYYY-MM-DD HH24:MI:SS")
    private String endDateTime;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallCategory() {
        return callCategory;
    }

    public void setCallCategory(String callCategory) {
        this.callCategory = callCategory;
    }

    public String getBelongCity() {
        return belongCity;
    }

    public void setBelongCity(String belongCity) {
        this.belongCity = belongCity;
    }

    public String getBelongPolice() {
        return belongPolice;
    }

    public void setBelongPolice(String belongPolice) {
        this.belongPolice = belongPolice;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public String getAlarmCategory() {
        return alarmCategory;
    }

    public void setAlarmCategory(String alarmCategory) {
        this.alarmCategory = alarmCategory;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmDetail() {
        return alarmDetail;
    }

    public void setAlarmDetail(String alarmDetail) {
        this.alarmDetail = alarmDetail;
    }

    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }

    public String getBelongPosition() {
        return belongPosition;
    }

    public void setBelongPosition(String belongPosition) {
        this.belongPosition = belongPosition;
    }

    public String getAlarmPeosign() {
        return alarmPeosign;
    }

    public void setAlarmPeosign(String alarmPeosign) {
        this.alarmPeosign = alarmPeosign;
    }

    public String getImportPlot() {
        return importPlot;
    }

    public void setImportPlot(String importPlot) {
        this.importPlot = importPlot;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getWechatnum() {
        return wechatnum;
    }

    public void setWechatnum(String wechatnum) {
        this.wechatnum = wechatnum;
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
}
