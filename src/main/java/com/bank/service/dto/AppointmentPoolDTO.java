package com.bank.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bank.domain.AppointmentPool} entity.
 */
public class AppointmentPoolDTO implements Serializable {

    private Long id;

    private String date;

    private String period;

    private Integer totalNum;

    private Integer leftNum;

    private String type;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
            ", type='" + getType() + "'" +
            "}";
    }
}
