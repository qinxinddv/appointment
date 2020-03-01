package com.bank.service.dto.custom;

import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.domain.enumeration.SymptomEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "预约申请实体")
public class AppointmentApplyDto {

    @NotBlank
    @ApiModelProperty(value = "微信账户唯一编号")
    private String openId;

    @NotBlank
    @ApiModelProperty(value = "预约日期")
    private String date;
    /**
     * 身份证号
     */
    @Pattern(regexp="(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)",
        message = "请输入正确身份证号")
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 姓名
     */
    @Length(min=2, max=20,message = "非法长度")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 手机号
     */
    @Length(min = 11,max = 11,message = "非法手机号")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 家庭地址
     */
    @NotBlank
    @Length(min=2, max=20,message = "非法长度")
    @ApiModelProperty(value = "家庭地址")
    private String addr;

    /**
     * 时间段
     */
    @NotBlank
    @ApiModelProperty(value = "时间段")
    private String timePeriodCode;

    /**
     * 时间段值
     */
    @NotBlank
    @ApiModelProperty(value = "时间段值")
    private String timePeriodValue;

    @NotNull
    @ApiModelProperty(value = "营业网点ID")
    private Long orgId;

    /**
     * 体温
     */
    @ApiModelProperty(value = "体温")
    private String temperature;

    @ApiModelProperty(value = "症状")
    private String symptom;

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

    /**
     * 类型（个人、企业、司法查询）
     */
    @ApiModelProperty(value = "类型（个人、企业、司法查询）")
    private BusiTypeEnum busiType;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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

    public String getOpenId() {
        return openId;
    }

    @Override
    public String toString() {
        return "AppointmentApplyDto{" +
            "openId='" + openId + '\'' +
            ", date='" + date + '\'' +
            ", idCard='" + idCard + '\'' +
            ", name='" + name + '\'' +
            ", mobile='" + mobile + '\'' +
            ", addr='" + addr + '\'' +
            ", timePeriodCode='" + timePeriodCode + '\'' +
            ", timePeriodValue='" + timePeriodValue + '\'' +
            ", orgId=" + orgId +
            ", temperature='" + temperature + '\'' +
            ", symptom='" + symptom + '\'' +
            ", latitude='" + latitude + '\'' +
            ", longitude='" + longitude + '\'' +
            ", busiType=" + busiType +
            '}';
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
