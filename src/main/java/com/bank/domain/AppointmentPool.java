package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AppointmentPool.
 */
@Entity
@Table(name = "appointment_pool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppointmentPool implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "period")
    private String period;

    @Column(name = "total_num")
    private Integer totalNum;

    @Column(name = "left_num")
    private Integer leftNum;

    @Column(name = "type")
    private String type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public AppointmentPool date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public AppointmentPool period(String period) {
        this.period = period;
        return this;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public AppointmentPool totalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public AppointmentPool leftNum(Integer leftNum) {
        this.leftNum = leftNum;
        return this;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public String getType() {
        return type;
    }

    public AppointmentPool type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppointmentPool)) {
            return false;
        }
        return id != null && id.equals(((AppointmentPool) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AppointmentPool{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", period='" + getPeriod() + "'" +
            ", totalNum=" + getTotalNum() +
            ", leftNum=" + getLeftNum() +
            ", type='" + getType() + "'" +
            "}";
    }
}
