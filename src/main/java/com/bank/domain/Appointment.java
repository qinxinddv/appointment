package com.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.bank.domain.enumeration.BusiTypeEnum;

import com.bank.domain.enumeration.YesNoEnum;

/**
 * 预约申请表
 */
@Entity
@Table(name = "appointment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 家庭地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 时间段
     */
    @Column(name = "time_period_code")
    private String timePeriodCode;

    /**
     * 时间段值
     */
    @Column(name = "time_period_value")
    private String timePeriodValue;

    /**
     * 类型（个人、企业、司法查询）
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "busi_type")
    private BusiTypeEnum busiType;

    /**
     * 处理状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private YesNoEnum state;

    /**
     * 处理意见
     */
    @Column(name = "opnion")
    private String opnion;

    /**
     * 申请时间
     */
    @Column(name = "apply_time")
    private ZonedDateTime applyTime;

    /**
     * 处理时间
     */
    @Column(name = "opnion_time")
    private ZonedDateTime opnionTime;

    /**
     * 预约日期
     */
    @Column(name = "date")
    private String date;

    @ManyToOne
    @JsonIgnoreProperties("appointments")
    private Org org;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public Appointment idCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public Appointment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public Appointment mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddr() {
        return addr;
    }

    public Appointment addr(String addr) {
        this.addr = addr;
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTimePeriodCode() {
        return timePeriodCode;
    }

    public Appointment timePeriodCode(String timePeriodCode) {
        this.timePeriodCode = timePeriodCode;
        return this;
    }

    public void setTimePeriodCode(String timePeriodCode) {
        this.timePeriodCode = timePeriodCode;
    }

    public String getTimePeriodValue() {
        return timePeriodValue;
    }

    public Appointment timePeriodValue(String timePeriodValue) {
        this.timePeriodValue = timePeriodValue;
        return this;
    }

    public void setTimePeriodValue(String timePeriodValue) {
        this.timePeriodValue = timePeriodValue;
    }

    public BusiTypeEnum getBusiType() {
        return busiType;
    }

    public Appointment busiType(BusiTypeEnum busiType) {
        this.busiType = busiType;
        return this;
    }

    public void setBusiType(BusiTypeEnum busiType) {
        this.busiType = busiType;
    }

    public YesNoEnum getState() {
        return state;
    }

    public Appointment state(YesNoEnum state) {
        this.state = state;
        return this;
    }

    public void setState(YesNoEnum state) {
        this.state = state;
    }

    public String getOpnion() {
        return opnion;
    }

    public Appointment opnion(String opnion) {
        this.opnion = opnion;
        return this;
    }

    public void setOpnion(String opnion) {
        this.opnion = opnion;
    }

    public ZonedDateTime getApplyTime() {
        return applyTime;
    }

    public Appointment applyTime(ZonedDateTime applyTime) {
        this.applyTime = applyTime;
        return this;
    }

    public void setApplyTime(ZonedDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public ZonedDateTime getOpnionTime() {
        return opnionTime;
    }

    public Appointment opnionTime(ZonedDateTime opnionTime) {
        this.opnionTime = opnionTime;
        return this;
    }

    public void setOpnionTime(ZonedDateTime opnionTime) {
        this.opnionTime = opnionTime;
    }

    public String getDate() {
        return date;
    }

    public Appointment date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Org getOrg() {
        return org;
    }

    public Appointment org(Org org) {
        this.org = org;
        return this;
    }

    public void setOrg(Org org) {
        this.org = org;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Appointment)) {
            return false;
        }
        return id != null && id.equals(((Appointment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Appointment{" +
            "id=" + getId() +
            ", idCard='" + getIdCard() + "'" +
            ", name='" + getName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", addr='" + getAddr() + "'" +
            ", timePeriodCode='" + getTimePeriodCode() + "'" +
            ", timePeriodValue='" + getTimePeriodValue() + "'" +
            ", busiType='" + getBusiType() + "'" +
            ", state='" + getState() + "'" +
            ", opnion='" + getOpnion() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", opnionTime='" + getOpnionTime() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
