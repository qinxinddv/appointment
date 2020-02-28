package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.bank.domain.enumeration.BusiTypeEnum;

/**
 * 预约池
 */
@Entity
@Table(name = "appointment_pool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppointmentPool implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 日期
     */
    @Column(name = "date")
    private String date;

    /**
     * 时间段
     */
    @Column(name = "period")
    private String period;

    /**
     * 总数
     */
    @Column(name = "total_num")
    private Integer totalNum;

    /**
     * 剩余数量
     */
    @Column(name = "left_num")
    private Integer leftNum;

    /**
     * 类型（个人、企业、司法查询）
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "busi_type")
    private BusiTypeEnum busiType;

    public AppointmentPool(String date, String period, Integer totalNum, BusiTypeEnum type) {
        this.date = date;
        this.period = period;
        this.totalNum = totalNum;
        this.busiType = type;
        this.leftNum = totalNum;
    }

    public AppointmentPool(){}


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

    public BusiTypeEnum getBusiType() {
        return busiType;
    }

    public AppointmentPool busiType(BusiTypeEnum busiType) {
        this.busiType = busiType;
        return this;
    }

    public void setBusiType(BusiTypeEnum busiType) {
        this.busiType = busiType;
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
            ", busiType='" + getBusiType() + "'" +
            "}";
    }
}
