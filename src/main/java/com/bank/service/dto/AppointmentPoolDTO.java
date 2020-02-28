package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import com.bank.domain.enumeration.BusiTypeEnum;

/**
 * A DTO for the {@link com.bank.domain.AppointmentPool} entity.
 */
@ApiModel(description = "预约池")
public class AppointmentPoolDTO implements Serializable {

    private Long id;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String date;

    /**
     * 时间段
     */
    @ApiModelProperty(value = "时间段")
    private String period;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private Integer totalNum;

    /**
     * 剩余数量
     */
    @ApiModelProperty(value = "剩余数量")
    private Integer leftNum;

    /**
     * 类型（个人、企业、司法查询）
     */
    @ApiModelProperty(value = "类型（个人、企业、司法查询）")
    private BusiTypeEnum busiType;


    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentPoolDTO appointmentPoolDTO = (AppointmentPoolDTO) o;
        if (appointmentPoolDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentPoolDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentPoolDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", period='" + getPeriod() + "'" +
            ", totalNum=" + getTotalNum() +
            ", leftNum=" + getLeftNum() +
            ", busiType='" + getBusiType() + "'" +
            ", orgId=" + getOrgId() +
            "}";
    }
}
