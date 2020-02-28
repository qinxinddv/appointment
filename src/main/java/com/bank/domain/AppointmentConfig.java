package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.bank.domain.enumeration.BusiTypeEnum;

/**
 * 预约配置表
 */
@Entity
@Table(name = "appointment_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppointmentConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 时间段
     */
    @Column(name = "period")
    private String period;

    /**
     * 数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 类型（个人、企业、司法查询）
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "busi_type")
    private BusiTypeEnum busiType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public AppointmentConfig period(String period) {
        this.period = period;
        return this;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getNum() {
        return num;
    }

    public AppointmentConfig num(Integer num) {
        this.num = num;
        return this;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BusiTypeEnum getBusiType() {
        return busiType;
    }

    public AppointmentConfig busiType(BusiTypeEnum busiType) {
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
        if (!(o instanceof AppointmentConfig)) {
            return false;
        }
        return id != null && id.equals(((AppointmentConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AppointmentConfig{" +
            "id=" + getId() +
            ", period='" + getPeriod() + "'" +
            ", num=" + getNum() +
            ", busiType='" + getBusiType() + "'" +
            "}";
    }
}
