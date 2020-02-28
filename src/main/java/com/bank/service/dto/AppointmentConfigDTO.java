package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import com.bank.domain.enumeration.BusiTypeEnum;

/**
 * A DTO for the {@link com.bank.domain.AppointmentConfig} entity.
 */
@ApiModel(description = "预约配置表")
public class AppointmentConfigDTO implements Serializable {

    private Long id;

    /**
     * 时间段
     */
    @ApiModelProperty(value = "时间段")
    private String period;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer num;

    /**
     * 类型（个人、企业、司法查询）
     */
    @ApiModelProperty(value = "类型（个人、企业、司法查询）")
    private BusiTypeEnum busiType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BusiTypeEnum getBusiType() {
        return busiType;
    }

    public void setBusiType(BusiTypeEnum busiType) {
        this.busiType = busiType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentConfigDTO appointmentConfigDTO = (AppointmentConfigDTO) o;
        if (appointmentConfigDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appointmentConfigDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppointmentConfigDTO{" +
            "id=" + getId() +
            ", period='" + getPeriod() + "'" +
            ", num=" + getNum() +
            ", busiType='" + getBusiType() + "'" +
            "}";
    }
}
