package com.bank.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.bank.domain.enumeration.BusiTypeEnum;

/**
 * A DTO for the {@link com.bank.domain.AppointmentPool} entity.
 */
public class AppointmentPoolDTO implements Serializable {

    private Long id;

    private String date;

    private String period;

    private Integer totalNum;

    private Integer leftNum;

    private BusiTypeEnum busiType;


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
            "}";
    }
}
