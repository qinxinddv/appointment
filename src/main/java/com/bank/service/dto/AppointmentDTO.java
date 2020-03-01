package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.domain.enumeration.AppointStateEnum;

/**
 * A DTO for the {@link com.bank.domain.Appointment} entity.
 */
@ApiModel(description = "预约申请表")
public class AppointmentDTO implements Serializable {

    private Long id;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 家庭地址
     */
    @ApiModelProperty(value = "家庭地址")
    private String addr;

    /**
     * 体温
     */
    @ApiModelProperty(value = "体温")
    private String temperature;

    /**
     * 症状
     */
    @ApiModelProperty(value = "症状")
    private String symptom;

    /**
     * 时间段
     */
    @ApiModelProperty(value = "时间段")
    private String timePeriodCode;

    /**
     * 时间段值
     */
    @ApiModelProperty(value = "时间段值")
    private String timePeriodValue;

    /**
     * 类型（个人、企业、司法查询）
     */
    @ApiModelProperty(value = "类型（个人、企业、司法查询）")
    private BusiTypeEnum busiType;

    /**
     * 处理状态
     */
    @ApiModelProperty(value = "处理状态")
    private AppointStateEnum state;

    /**
     * 处理意见
     */
    @ApiModelProperty(value = "处理意见")
    private String opnion;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    /**
     * 处理时间
     */
    @ApiModelProperty(value = "处理时间")
    private Date opnionTime;

    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    private String date;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String latitude;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;


    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getTimePeriodCode() {
        return timePeriodCode;
    }

    public void setTimePeriodCode(String timePeriodCode) {
        this.timePeriodCode = timePeriodCode;
    }

    public String getTimePeriodValue() {
        return timePeriodValue;
    }

    public void setTimePeriodValue(String timePeriodValue) {
        this.timePeriodValue = timePeriodValue;
    }

    public BusiTypeEnum getBusiType() {
        return busiType;
    }

    public void setBusiType(BusiTypeEnum busiType) {
        this.busiType = busiType;
    }

    public AppointStateEnum getState() {
        return state;
    }

    public void setState(AppointStateEnum state) {
        this.state = state;
    }

    public String getOpnion() {
        return opnion;
    }

    public void setOpnion(String opnion) {
        this.opnion = opnion;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getOpnionTime() {
        return opnionTime;
    }

    public void setOpnionTime(Date opnionTime) {
        this.opnionTime = opnionTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentDTO appointmentDTO = (AppointmentDTO) o;
        if (appointmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
            "id=" + getId() +
            ", idCard='" + getIdCard() + "'" +
            ", name='" + getName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", addr='" + getAddr() + "'" +
            ", temperature='" + getTemperature() + "'" +
            ", symptom='" + getSymptom() + "'" +
            ", timePeriodCode='" + getTimePeriodCode() + "'" +
            ", timePeriodValue='" + getTimePeriodValue() + "'" +
            ", busiType='" + getBusiType() + "'" +
            ", state='" + getState() + "'" +
            ", opnion='" + getOpnion() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", opnionTime='" + getOpnionTime() + "'" +
            ", date='" + getDate() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", orgId=" + getOrgId() +
            "}";
    }
}
